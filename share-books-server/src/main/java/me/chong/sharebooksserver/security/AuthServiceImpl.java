package me.chong.sharebooksserver.security;

import me.chong.sharebooksserver.domain.UserRepository;
import me.chong.sharebooksserver.domain.UserRoleRepository;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.entity.UserRole;
import me.chong.sharebooksserver.enums.RoleEnum;
import me.chong.sharebooksserver.exception.ParamException;
import me.chong.sharebooksserver.exception.PersistenceException;
import me.chong.sharebooksserver.utils.JwtTokenUtil;
import me.chong.sharebooksserver.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getName();
        if(userRepository.findByName(username)!=null) {
            throw new PersistenceException.RepetitiveParamException("用户名不能重复");
        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
//        userToAdd.setPassword(encoder.encode(rawPassword));
        try {
            String digest = SecurityUtils.getDigestByMD5(rawPassword);
            userToAdd.setPassword(SecurityUtils.encodeByBase64(digest));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        userToAdd.setEnable(true);
        userToAdd.setCreateTime(new Date());
        User user = userRepository.save(userToAdd);
        if (user != null && user.getId() != null) {
            userRoleRepository.save(new UserRole(user.getId(), RoleEnum.USER.getIndex(), new Date()));
        }
        return user;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        if (oldToken == null || "".equals(oldToken)) {
            throw new ParamException.NullParamException("token不能为空");
        }
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
//        MyUserDetails user = (MyUserDetails) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
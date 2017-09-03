package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.security.AuthService;
import me.chong.sharebooksserver.security.JwtAuthenticationRequest;
import me.chong.sharebooksserver.security.JwtAuthenticationResponse;
import me.chong.sharebooksserver.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Token授权信息", protocols = "JSON")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping
    @ApiOperation(value = "登录并创建Token", httpMethod = "POST", response = Result.class, notes = "登录并创建Token")
    public Result createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return ResultUtils.success(new JwtAuthenticationResponse(token));
    }

//    ${jwt.route.authentication.refresh}
    @GetMapping("/refresh")
    @ApiParam(required = true, name = "token", value = "用户token")
    @ApiOperation(value = "更新Token信息", httpMethod = "GET", response = Result.class, notes = "更新Token信息")
    public Result refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResultUtils.error(ResultCodeEnum.FAILED, "Token尚未过期");
        } else {
            return ResultUtils.success(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @PostMapping("/register")
    @ApiParam(required = true, name = "addedUser", value = "用户注册信息")
    @ApiOperation(value = "用户注册", httpMethod = "POST", response = Result.class, notes = "用户注册")
    public Result register(@RequestBody User addedUser) {
        User user = authService.register(addedUser);
        return ResultUtils.success(user.getName());
    }
}

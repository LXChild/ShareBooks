package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.UserRepository;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理相关服务
 * Created by LXChild on 19/03/2017.
 */
@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByName(String name) {
        BaseValidator.notEmpty(name, "用户名不能为空");
        return repository.findByName(name);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findOne(Long userId) {
        return repository.findOne(userId);
    }
}

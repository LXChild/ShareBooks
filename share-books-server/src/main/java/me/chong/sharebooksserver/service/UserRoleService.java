package me.chong.sharebooksserver.service;

import me.chong.sharebooksserver.domain.UserRoleRepository;
import me.chong.sharebooksserver.entity.UserRole;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 进行用户角色管理
 * Created by LXChild on 03/04/2017.
 */
@Service
public class UserRoleService {

    private final UserRoleRepository repository;

    @Autowired
    public UserRoleService(UserRoleRepository repository) {
        this.repository = repository;
    }

    public UserRole save(UserRole userRole) {
        BaseValidator.notEmpty(userRole.getUserId());
        BaseValidator.notEmpty(userRole.getRoleId());
        BaseValidator.notEmpty(userRole.getCreateTime());
        return repository.save(userRole);
    }

    public UserRole findByUserId(Long userId) {
        BaseValidator.notEmpty(userId);
        return repository.findByUserId(userId);
    }

    public void update(Long userId, Integer roleId) {
        BaseValidator.notEmpty(userId);
        BaseValidator.notEmpty(roleId);
        repository.update(userId, roleId, new Date());
    }
}

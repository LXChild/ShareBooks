package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "用户管理接口", protocols = "JSON")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SYS_ADMIN, ADMIN')")
    @ApiOperation(value = "查询所有用户", httpMethod = "GET", response = Result.class, notes = "查询所有用户")
    public Result findAll() {
        return ResultUtils.success(userService.findAll());
    }

    @GetMapping("/id/{id}")
    @ApiParam(required = true, name = "id", value = "用户ID")
    @ApiOperation(value = "根据用户ID查询用户", httpMethod = "GET", response = Result.class, notes = "根据用户ID查询用户")
    public Result findOne(@PathVariable("id") Long id) {
        return ResultUtils.success(userService.findOne(id));
    }

    @GetMapping("/mine")
    @ApiOperation(value = "查询登录用户", httpMethod = "GET", response = Result.class, notes = "查询登录用户")
    public Result findOne(HttpServletRequest request) {
        return ResultUtils.success(userService.findByName(request.getRemoteUser()));
    }

}

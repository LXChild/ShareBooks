package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.Post;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.service.PostService;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ResultUtils;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/posts")
@Api(value = "书圈管理接口", protocols = "JSON")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiParam(required = true, name = "post", value = "帖子")
    @ApiOperation(value = "发表书圈", httpMethod = "POST", response = Result.class, notes = "发表书圈")
    public Result save(HttpServletRequest request, @RequestBody @Valid Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCodeEnum.FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        post.setCreatorId(user.getId());
        post.setCreatorName(user.getName());
        post.setCreateTime(new Date());
        return ResultUtils.success(postService.save(post));
    }

    @GetMapping
    @ApiOperation(value = "获取所有书圈", httpMethod = "GET", response = Result.class, notes = "获取所有书圈")
    public Result findAll() {
        return ResultUtils.success(postService.findAll());
    }
}


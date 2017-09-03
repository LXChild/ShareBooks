package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.service.BookClassService;
import me.chong.sharebooksserver.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookClass")
@Api(value = "书籍类别管理接口", protocols = "JSON")
public class BookClassController {

    @Autowired
    private BookClassService bookClassService;

    @GetMapping
    @ApiOperation(value = "查看所有书籍类别", httpMethod = "GET", response = Result.class, notes = "查看所有书籍类别")
    public Result findAll() {
        return ResultUtils.success(bookClassService.findAll());
    }

    @GetMapping("/id/{id}")
    @ApiParam(required = true, name = "id", value = "书籍类别Id")
    @ApiOperation(value = "查看所有书籍类别", httpMethod = "GET", response = Result.class, notes = "查看所有书籍类别")
    public Result findOne(@PathVariable("id") Long id) {
        return ResultUtils.success(bookClassService.findOne(id));
    }
}

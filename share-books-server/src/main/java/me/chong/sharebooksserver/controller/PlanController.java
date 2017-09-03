package me.chong.sharebooksserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.entity.Plan;
import me.chong.sharebooksserver.entity.User;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.exception.ParamException;
import me.chong.sharebooksserver.exception.PersistenceException;
import me.chong.sharebooksserver.service.PlanService;
import me.chong.sharebooksserver.service.UserService;
import me.chong.sharebooksserver.utils.ResultUtils;
import me.chong.sharebooksserver.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/plans")
@Api(value = "计划管理接口", protocols = "JSON")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiParam(required = true, name = "plan", value = "计划")
    @ApiOperation(value = "添加计划", httpMethod = "POST", response = Result.class, notes = "添加计划")
    public Result save(HttpServletRequest request, @RequestBody @Valid Plan plan, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultCodeEnum.FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        List<Plan> plans = planService.findByUserId(user.getId());

        if (!plans.isEmpty()) {
            Stream<Plan> planStream = plans.stream().filter(e -> !e.getComplete());
            if (planStream.count() > 0) {
                throw new PersistenceException.RepetitiveParamException("已有未完成计划");
            }
        }

        plan.setUserId(user.getId());
        plan.setUserName(username);
        plan.setCreateTime(new Date());

        return ResultUtils.success(planService.save(plan));
    }

    @PutMapping
    @ApiOperation(value = "每日打卡", httpMethod = "PUT", response = Result.class, notes = "每日打卡")
    public Result update(HttpServletRequest request) {
        String username = request.getRemoteUser();
        BaseValidator.notEmptyString(username);

        User user = userService.findByName(username);
        BaseValidator.notEmpty(user);
        BaseValidator.notEmpty(user.getId());

        List<Plan> plans = planService.findByUserId(user.getId());
        Stream<Plan> stream = plans.stream().filter(e -> !e.getComplete());
        List<Plan> results = stream.collect(Collectors.toList());
        if (results.isEmpty()) {
            throw new ParamException.NotInDBException("任务已完成，请重新制定计划");
        } else if (!getDatePoor(new Date(), results.get(0).getUpdateTime())) {
            throw new PersistenceException.RepetitiveParamException("今日已打卡");
        }

        Plan plan = results.get(0);
        Integer readCount = plan.getReadCount() + 1;
        plan.setReadCount(readCount);
        if (readCount >= 21) {
            plan.setComplete(true);
        }
        planService.update(plan);

        return ResultUtils.success(plan);
    }

    @GetMapping
    @ApiOperation(value = "获取所有计划", httpMethod = "GET", response = Result.class, notes = "获取所有计划")
    public Result findAll(HttpServletRequest request) {
        return ResultUtils.success(planService.findByUserId(userService.findByName(request.getRemoteUser()).getId()));
    }

    private static Boolean getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        Integer ff = endDate.getDay() - nowDate.getDay();
        return day > 0 && ff > 0;
    }
}

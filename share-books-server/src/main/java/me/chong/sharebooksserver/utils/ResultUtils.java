package me.chong.sharebooksserver.utils;


import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.enums.ResultCodeEnum;

public class ResultUtils {

    private ResultUtils() {}

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.OK);
        result.setData(object);
        return result;
    }

    public static Result error(ResultCodeEnum code) {
        Result result = new Result();
        result.setCode(code);
        return result;
    }

    public static Result error(ResultCodeEnum code,  String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}

package me.chong.sharebookscommon.utils;


import me.chong.sharebookscommon.dataobject.Result;
import me.chong.sharebookscommon.enums.ResultCodeEnum;

public class ResultUtils {

    private ResultUtils() {}

    public static Result success(String url, Object object) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.OK);
        result.setUrl(url);
        result.setData(object);
        return result;
    }

    public static Result error(ResultCodeEnum code, String url) {
        Result result = new Result();
        result.setCode(code);
        result.setUrl(url);
        return result;
    }

    public static Result error(ResultCodeEnum code, String url,  String message) {
        Result result = new Result();
        result.setCode(code);
        result.setUrl(url);
        result.setMsg(message);
        return result;
    }
}

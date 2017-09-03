package me.chong.sharebooksserver;

import me.chong.sharebooksserver.dataobject.Result;
import me.chong.sharebooksserver.enums.ResultCodeEnum;
import me.chong.sharebooksserver.exception.ParamException;
import me.chong.sharebooksserver.exception.PersistenceException;
import me.chong.sharebooksserver.exception.StorageException;
import me.chong.sharebooksserver.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result jsonErrorHandler(Exception e) {

        if (e instanceof ParamException) {
            return ResultUtils.error(((ParamException) e).getCode(), e.getMessage());
        } else if (e instanceof PersistenceException) {
            return ResultUtils.error(((PersistenceException) e).getCode(), e.getMessage());
        } else if (e instanceof StorageException) {
            return ResultUtils.error(((StorageException) e).getCode(), e.getMessage());
        } else {
            LOGGER.error("【系统异常】", e);
            return ResultUtils.error(ResultCodeEnum.UNKNOWN, e.getMessage());
        }
    }

}

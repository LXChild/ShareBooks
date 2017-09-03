package me.chong.sharebooksserver.exception;

import lombok.Getter;
import me.chong.sharebooksserver.enums.ResultCodeEnum;

/**
 * 持久层异常类
 * Created by LXChild on 05/04/2017.
 */
public class PersistenceException extends RuntimeException {

    @Getter
    private final ResultCodeEnum code;

    private PersistenceException(ResultCodeEnum code) {
        super(code.getComment());
        this.code = code;
    }

    private PersistenceException(ResultCodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public static class RepetitiveParamException extends PersistenceException {
        public RepetitiveParamException() {
            super(ResultCodeEnum.REPETITIVE);
        }
        public RepetitiveParamException(String message) {
            super(ResultCodeEnum.REPETITIVE, message);
        }
    }
}

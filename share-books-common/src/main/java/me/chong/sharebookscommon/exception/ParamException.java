package me.chong.sharebookscommon.exception;

import lombok.Getter;
import me.chong.sharebookscommon.enums.ResultCodeEnum;

/**
 * 参数异常 包括参数为null异常，重复的参数，数据库查询不出结果等
 */
public class ParamException extends RuntimeException {

    @Getter
    private final ResultCodeEnum code;

    private ParamException(ResultCodeEnum code) {
        super(code.getComment());
        this.code = code;
    }

    private ParamException(ResultCodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public static class NullParamException extends ParamException {
        public NullParamException() {
            super(ResultCodeEnum.NULL);
        }
        public NullParamException(String message) {
            super(ResultCodeEnum.NULL, message);
        }
    }

    public static class NotInDBException extends ParamException {
        public NotInDBException() {
            super(ResultCodeEnum.NOT_IN_DB);
        }

        public NotInDBException(Long id) {
            super(ResultCodeEnum.NOT_IN_DB, String.format("id{%d}在数据库中查询不到结果", id));
        }
        public NotInDBException(String message) {
            super(ResultCodeEnum.NOT_IN_DB, message);
        }
    }

    public static class MalformedParamException extends ParamException {
        public MalformedParamException() {
            super(ResultCodeEnum.NOT_MATCH);
        }

        public MalformedParamException(String message) {
            super(ResultCodeEnum.NOT_MATCH, message);
        }
    }
}
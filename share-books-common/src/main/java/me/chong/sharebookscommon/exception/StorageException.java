package me.chong.sharebookscommon.exception;

import lombok.Getter;
import me.chong.sharebookscommon.enums.ResultCodeEnum;

import java.io.IOException;

/**
 * 存储异常
 * Created by LXChild on 07/04/2017.
 */
public class StorageException extends IOException {

    @Getter
    private final ResultCodeEnum code;

    private StorageException(ResultCodeEnum code) {
        super(code.getComment());
        this.code = code;
    }

    private StorageException(ResultCodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public static class StorageFileException extends StorageException {
        public StorageFileException() {
            super(ResultCodeEnum.STORAGE_FAILED);
        }

        public StorageFileException(String message) {
            super(ResultCodeEnum.STORAGE_FAILED, message);
        }
    }

    public static class FileNotFoundException extends StorageException {
        public FileNotFoundException() {
            super(ResultCodeEnum.NOT_FOUND);
        }

        public FileNotFoundException(String message) {
            super(ResultCodeEnum.NOT_FOUND, message);
        }
    }
}

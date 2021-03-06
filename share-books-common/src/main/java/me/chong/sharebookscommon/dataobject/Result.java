package me.chong.sharebookscommon.dataobject;

import lombok.Getter;
import lombok.Setter;
import me.chong.sharebookscommon.enums.ResultCodeEnum;

/**
 * http 请求返回结果对象
 * Created by LXChild on 04/04/2017.
 */
public class Result<T> {

    /**
     * 错误码
     * */
    @Getter
    private Integer code;

    @Getter
    @Setter
    private String url;

    /**
     * 提示信息
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 具体内容
     */
    @Getter
    @Setter
    private T data;

    public void setCode(ResultCodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getComment();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", url='" + url + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

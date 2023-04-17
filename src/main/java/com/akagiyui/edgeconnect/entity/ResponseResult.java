package com.akagiyui.edgeconnect.entity;

import lombok.Data;

/**
 * @author AkagiYui
 */

@Data
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(200);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

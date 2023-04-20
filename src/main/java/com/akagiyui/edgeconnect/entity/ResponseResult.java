package com.akagiyui.edgeconnect.entity;

import com.akagiyui.edgeconnect.utils.ResponseEnum;
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

    public static ResponseResult<Object> success() {
        return success(null);
    }

    public static ResponseResult<Object> error(Integer code, String msg) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResponseResult<Object> internalError() {
        return responseEnum(ResponseEnum.INTERNAL_ERROR);
    }

    public static ResponseResult<Object> responseEnum(ResponseEnum status) {
        return error(status.getCode(), status.getMsg());
    }
}

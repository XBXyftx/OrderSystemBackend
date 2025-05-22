package org.xbxyftx.ordersystembackend.common;

import lombok.Data;

@Data
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回信息
    private String message;
    // 返回数据
    private T data;

    // 成功返回
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "成功";
        result.data = data;
        return result;
    }

    // 错误返回
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 500;
        result.message = message;
        return result;
    }
} 
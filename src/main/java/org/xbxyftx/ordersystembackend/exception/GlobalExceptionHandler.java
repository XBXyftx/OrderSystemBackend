package org.xbxyftx.ordersystembackend.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xbxyftx.ordersystembackend.common.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 处理BusinessException异常
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        // 返回错误信息
        return Result.error(e.getMessage());
    }

    // 处理Exception异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 返回错误信息
        return Result.error("系统错误");
    }
} 
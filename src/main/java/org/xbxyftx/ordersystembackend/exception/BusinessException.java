package org.xbxyftx.ordersystembackend.exception;

// 定义一个自定义异常类，继承自RuntimeException
public class BusinessException extends RuntimeException {
    // 构造函数，接收一个字符串参数，用于传递异常信息
    public BusinessException(String message) {
        // 调用父类的构造函数，将异常信息传递给父类
        super(message);
    }
} 
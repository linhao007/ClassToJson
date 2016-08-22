package com.linhao007.www.exception;

/**
 * Created by daojialinhu01 on 2016-8-5.
 * 对象序列化成json异常
 */
public class JsonException extends RuntimeException {
    public JsonException(String message) {
        super(message);
    }
}

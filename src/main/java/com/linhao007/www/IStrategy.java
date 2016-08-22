package com.linhao007.www;


import com.linhao007.www.exception.JsonException;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * 策略模式  抽象策略对象   不管哪种类型  都要实现该接口 对其序列化成json
 */
public interface IStrategy {
     String toObjectJson(Object object) throws JsonException, IllegalAccessException;

     StringBuffer toObjectJson(Object object, StringBuffer buffer) throws JsonException, IllegalAccessException;
}

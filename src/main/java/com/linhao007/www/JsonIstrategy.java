package com.linhao007.www;


import com.linhao007.www.exception.JsonException;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * 策略模式  环境对象  控制哪种策略转换json
 */
public class JsonIstrategy {
    private IStrategy iStrategy;

    //构造函数，要你使用哪个策略
    public JsonIstrategy(IStrategy strategy){
        this.iStrategy = strategy;
    }

    public String operate(Object object) throws JsonException, IllegalAccessException {
        return this.iStrategy.toObjectJson(object);
    }

    public StringBuffer operate(Object object, StringBuffer buffer) throws JsonException, IllegalAccessException {
        return this.iStrategy.toObjectJson(object,buffer);
    }
}

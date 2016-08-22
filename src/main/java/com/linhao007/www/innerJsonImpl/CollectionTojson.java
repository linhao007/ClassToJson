package com.linhao007.www.innerJsonImpl;



import com.linhao007.www.IStrategy;
import com.linhao007.www.JsonIstrategy;
import com.linhao007.www.enums.CollectionEnum;
import com.linhao007.www.exception.JsonException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * 将集合类型装换成相应的json格式
 */
public class CollectionTojson implements IStrategy {

    public String toObjectJson(Object obj) throws JsonException, IllegalAccessException {
        JsonIstrategy jsonIstrategy = null;
        //定义一个StringBuffer类型的字符串
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Class<? extends Object> clazz = obj.getClass();
        //获取类中所有的字段
        Field[] declaredFields = clazz.getDeclaredFields();

        //设置可以获得私有字段的value
        Field.setAccessible(declaredFields, true);

        //定义全局变量
        boolean listf = false;
        boolean setf = false;
        Set<Object> set = null;
        List<Object> list = null;

        //遍历获取到的所有字段
        for (Field field : declaredFields) {
            //getDeclaringClass()同getClasses()，但不局限于public修饰，只要是目标类中声明的内部类和接口均可
            String simpleName = clazz.getSimpleName();
            //判断获取到的类型
            if (simpleName.equals(CollectionEnum.DAOJIA_ARRAYLIST.getType()) ||
                    simpleName.equals(CollectionEnum.DAOJIA_LINKEDLIST.getType())) {
                list = (List<Object>) obj;
                listf = true;
            }
            if (simpleName.equals(CollectionEnum.DAOJIA_HASHSET.getType()) ||
                    simpleName.equals(CollectionEnum.DAOJIA_TREESET.getType())) {
                set = (Set<Object>) obj;
                setf = true;
            }
        }
        //如果获取的对象类型为一个List集合  将List转换成相应的json格式
        if (listf == true) {
            jsonIstrategy = new JsonIstrategy(new ListToJson());
            return jsonIstrategy.operate(list, buffer).toString();
        }
        //如果获取的对象类型为一个Set集合
        if (setf == true) {
            jsonIstrategy = new JsonIstrategy(new SetToJson());
            buffer= jsonIstrategy.operate(set, buffer);
        }
        buffer.append("]");
        return buffer.toString();
    }

    public StringBuffer toObjectJson(Object object, StringBuffer buffer) throws JsonException {
        return null;
    }
}

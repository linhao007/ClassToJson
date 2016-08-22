package com.linhao007.www.innerJsonImpl;

import com.linhao007.www.IStrategy;
import com.linhao007.www.JsonIstrategy;
import com.linhao007.www.enums.CollectionEnum;
import com.linhao007.www.enums.ConstantEnum;
import com.linhao007.www.exception.JsonException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * 复杂类型序列化成相应的json格式
 */
public class ClassToJson implements IStrategy {


    public String toObjectJson(Object obj) throws JsonException {
        JsonIstrategy jsonIstrategy = null;
        //通过反射获取到类
        Class<? extends Object> clazz = obj.getClass();
        //获取类中所有的字段
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        //设置setAccessible方法能获取到类中的私有属性和方法
        Field.setAccessible(fields, true);
        //遍历所有的方法和属性
        for (Field field : fields) {
            try {
                Object object = field.get(obj);
                    //获取到该属性对应类型名称
                    String fieldName = field.getType().getSimpleName();
                    //如果该属性的值为空
                    if (object == null) {
                        //根据类型判断追加的值
                        if (fieldName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                            buffer.append("\"" + field.getName() + "\":\"\",");
                        } else if (ConstantEnum.getConstantTypeByName(fieldName)) {
                            buffer.append("\"" + field.getName() + "\":0,");
                        } else {
                            buffer.append("\"" + field.getName() + "\":null,");
                        }
                } else {
                    //获取到该属性的值对应的类
                    Class<? extends Object> fieldclass = object.getClass();
                    String simpleName = fieldclass.getSimpleName();
                    if (simpleName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                        buffer.append("\"" + field.getName() + "\":\"" + field.get(obj) + "\",");
                    } else if (ConstantEnum.getConstantTypeByName(simpleName)) {
                        buffer.append("\"" + field.getName() + "\":" + field.get(obj) + ",");
                    } else if (simpleName.equals(ConstantEnum.DAOJIA_DATE.getType())) {
                        Date date = (Date) object;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String simdate = simpleDateFormat.format(date);
                        buffer.append("\"" + field.getName() + "\":\"" + simdate + "\",");
                    } else if (simpleName.equals(CollectionEnum.DAOJIA_ARRAYLIST.getType())
                            || simpleName.equals(CollectionEnum.DAOJIA_LINKEDLIST.getType())) {
                        //将获取到的值强转为list集合
                        List<Object> list = (List<Object>) object;
                        buffer.append("\"" + field.getName() + "\":[");
                        //执行listTojson方法将获取到的list转为json格式
                        jsonIstrategy = new JsonIstrategy(new ListToJson());
                        buffer = jsonIstrategy.operate(list, buffer).append(",");
                    } else if (simpleName.equals(CollectionEnum.DAOJIA_TREESET.getType()) ||
                            simpleName.equals(CollectionEnum.DAOJIA_HASHSET.getType())) {
                        //将获取到的值强转为set集合
                        Set<Object> set = (Set<Object>) object;
                        buffer.append("\"" + field.getName() + "\":[");
                        //执行setTojson方法将获取到的set转为json格式
                        jsonIstrategy = new JsonIstrategy(new SetToJson());
                        buffer = jsonIstrategy.operate(set, buffer).append("]");
                    } else {
                        jsonIstrategy = new JsonIstrategy(new BeanToJson());
                        buffer = jsonIstrategy.operate(object, buffer).append(",");
                    }
                }
            } catch (Exception e) {
                throw new JsonException("复杂类型转换json出错，错误信息："+e);
            }

        }
        buffer = new StringBuffer(buffer.substring(0, buffer.length() - 1));
        buffer.append("}");
        return buffer.toString();
    }

    public StringBuffer toObjectJson(Object object, StringBuffer buffer) throws JsonException, IllegalAccessException {
        return null;
    }
}
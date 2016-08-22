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
 * 简单类型序列化成json格式 为内部转换 采用缺省标识
 */
class BeanToJson implements IStrategy {
    public String toObjectJson(Object object) throws JsonException {
        return null;
    }

    public StringBuffer toObjectJson(Object obj, StringBuffer buffer) throws JsonException, IllegalAccessException {
        JsonIstrategy jsonIstrategy = null;
        Class<? extends Object> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field.setAccessible(declaredFields, true);
        buffer.append("\"" + clazz.getSimpleName() + "\":{");
        for (Field field : declaredFields) {
            Object object = field.get(obj);
            String fieldName = field.getType().getSimpleName();
            if (object == null) {
                if (fieldName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                    buffer.append("\"" + field.getName() + "\":\"\",");
                } else {
                    buffer.append("\"" + field.getName() + "\":null,");
                }
            } else {
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
                } else if (CollectionEnum.DAOJIA_ARRAYLIST.getType().equals(simpleName) ||
                        CollectionEnum.DAOJIA_LINKEDLIST.getType().equals(simpleName)) {
                    List<Object> list = (List<Object>) object;
                    jsonIstrategy = new JsonIstrategy(new ListToJson());
                    buffer = jsonIstrategy.operate(list, buffer);
                } else if (CollectionEnum.DAOJIA_HASHSET.getType().equals(simpleName) ||
                        CollectionEnum.DAOJIA_TREESET.getType().equals(simpleName)) {
                    Set<Object> set = (Set<Object>) object;
                    jsonIstrategy = new JsonIstrategy(new ListToJson());
                    buffer = jsonIstrategy.operate(set, buffer);
                } else if (CollectionEnum.DAOJIA_HASHMAP.getType().equals(simpleName) ||
                        CollectionEnum.DAOJIA_HASHTABLE.getType().equals(simpleName)) {
                    buffer.append("\"" + field.getName() + "\":");
                    jsonIstrategy = new JsonIstrategy(new MapToJson());
                    StringBuffer mapbuffer = new StringBuffer(jsonIstrategy.operate(object));
                    mapbuffer.deleteCharAt(0);
                    buffer.append(mapbuffer);
                } else {
                    buffer = this.toObjectJson(object, buffer).append("}");
                }
            }

        }
        buffer = new StringBuffer("" + buffer.substring(0, buffer.length() - 1) + "");
        buffer.append("}");
        return buffer;
    }
}

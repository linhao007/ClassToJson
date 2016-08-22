package com.linhao007.www.innerJsonImpl;



import com.linhao007.www.IStrategy;
import com.linhao007.www.JsonIstrategy;
import com.linhao007.www.enums.CollectionEnum;
import com.linhao007.www.enums.ConstantEnum;
import com.linhao007.www.exception.JsonException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * map类型序列化成json格式
 */
public class MapToJson implements IStrategy {

    public String toObjectJson(Object obj) throws JsonException, IllegalAccessException {
        JsonIstrategy jsonIstrategy = null;
        StringBuffer buffer = new StringBuffer();
        Class<? extends Object> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Field.setAccessible(declaredFields, true);
        buffer.append("");
        Map<Object, Object> map = (Map<Object, Object>) obj;
        //通过Map.entrySet使用iterator(迭代器)遍历key和value
        Set<Map.Entry<Object, Object>> set = map.entrySet();
        Iterator iterator = set.iterator();
        buffer.append("{");
        while (iterator.hasNext()) {
            //使用Map.Entry接到通过迭代器循环出的set的值
            Map.Entry mapentry = (Map.Entry) iterator.next();
            Object value = mapentry.getValue();
            //使用getKey()获取map的键，getValue()获取键对应的值
            String valuename = value.getClass().getSimpleName();
            if (valuename.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                buffer.append("\"" + mapentry.getKey() + "\":\"" + mapentry.getValue() + "\",");
            } else if (ConstantEnum.getConstantTypeByName(valuename)) {
                buffer.append("\"" + mapentry.getKey() + "\":" + mapentry.getValue() + ",");
            } else if (valuename.equals(ConstantEnum.DAOJIA_DATE.getType())) {
                Date date = (Date) value;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String simdate = simpleDateFormat.format(date);
                buffer.append("\"" + mapentry.getKey() + "\":\"" + simdate + "\",");
            } else if (valuename.equals(CollectionEnum.DAOJIA_ARRAYLIST.getType()) ||
                    valuename.equals(CollectionEnum.DAOJIA_LINKEDLIST.getType())) {
                List<Object> list = (List<Object>) value;
                buffer.append("\"" + mapentry.getKey() + "\":[");
                jsonIstrategy = new JsonIstrategy(new ListToJson());
                buffer = jsonIstrategy.operate(list, buffer).append("]");
            } else if (valuename.equals(CollectionEnum.DAOJIA_HASHSET.getType()) ||
                    valuename.equals(CollectionEnum.DAOJIA_TREESET.getType())) {
                buffer.append("\"" + mapentry.getKey() + "\":[");
                Set<Object> sets = (Set<Object>) value;
                jsonIstrategy = new JsonIstrategy(new SetToJson());
                buffer = jsonIstrategy.operate(sets, buffer).append("]");
            } else if (valuename.equals(CollectionEnum.DAOJIA_HASHMAP.getType()) ||
                    valuename.equals(CollectionEnum.DAOJIA_HASHTABLE.getType())) {
                buffer.append("\"" + mapentry.getKey() + "\":");
                jsonIstrategy = new JsonIstrategy(new MapToJson());
                StringBuffer mapbuffer = new StringBuffer(jsonIstrategy.operate(value));
                mapbuffer.deleteCharAt(0);
                buffer.append(mapbuffer);
            } else {
                buffer.append("\"" + mapentry.getKey() + "\":");
                buffer.append("{");
                Class<? extends Object> class1 = value.getClass();
                Field[] fields = class1.getDeclaredFields();
                Field.setAccessible(fields, true);
                for (Field field : fields) {
                    Object object = field.get(value);
                    String fieldName = field.getType().getSimpleName();
                    if (object == null) {
                        if (fieldName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                            buffer.append("\"" + field.getName() + "\":\"\",");
                        } else {
                            buffer.append("\"" + field.getName() + "\":null,");
                        }
                    } else {
                        Class<? extends Object> fieldclass = field.get(value).getClass();
                        String simpleName = fieldclass.getSimpleName();
                        if (simpleName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                            buffer.append("\"" + field.getName() + "\":\"" + field.get(value) + "\",");
                        } else if (ConstantEnum.getConstantTypeByName(simpleName)) {
                            buffer.append("\"" + field.getName() + "\":" + field.get(value) + ",");
                        } else if (simpleName.equals(ConstantEnum.DAOJIA_DATE.getType())) {
                            Date date = (Date) object;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String simdate = simpleDateFormat.format(date);
                            buffer.append("\"" + field.getName() + "\":\"" + simdate + "\",");
                        } else if (simpleName.equals(CollectionEnum.DAOJIA_ARRAYLIST.getType()) ||
                                simpleName.equals(CollectionEnum.DAOJIA_LINKEDLIST.getType())) {
                            List<Object> list = (List<Object>) object;
                            buffer.append("\"" + field.getName() + "\":[");
                            jsonIstrategy = new JsonIstrategy(new ListToJson());
                            StringBuffer append = jsonIstrategy.operate(list, buffer).append("]");
                            buffer.append(append);
                        } else if (simpleName.equals(CollectionEnum.DAOJIA_HASHSET.getType()) ||
                                simpleName.equals(CollectionEnum.DAOJIA_TREESET.getType())) {
                            buffer.append("\"" + field.getName() + "\":[");
                            Set<Object> sets = (Set<Object>) object;
                            jsonIstrategy = new JsonIstrategy(new SetToJson());
                            buffer = jsonIstrategy.operate(set
                                    , buffer).append("]");
                        } else if (simpleName.equals(CollectionEnum.DAOJIA_HASHMAP.getType()) ||
                                simpleName.equals(CollectionEnum.DAOJIA_HASHTABLE.getType())) {
                            buffer.append("\"" + field.getName() + "\":");
                            jsonIstrategy = new JsonIstrategy(new MapToJson());
                            StringBuffer mapbuffer = new StringBuffer(jsonIstrategy.operate(object));
                            mapbuffer.deleteCharAt(0);
                            buffer.append(mapbuffer);
                        } else {
                            jsonIstrategy = new JsonIstrategy(new BeanToJson());
                            buffer = jsonIstrategy.operate(object, buffer).append(",");
                        }
                    }
                }
                buffer = new StringBuffer("" + buffer.substring(0, buffer.length() - 1) + "");
                buffer.append("},");
            }
        }

        buffer = new StringBuffer("" + buffer.substring(0, buffer.length() - 1) + "");
        return buffer.toString() + "}";
    }

    public StringBuffer toObjectJson(Object object, StringBuffer buffer) throws JsonException {
        return null;
    }
}

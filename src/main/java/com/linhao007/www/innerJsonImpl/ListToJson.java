package com.linhao007.www.innerJsonImpl;



import com.linhao007.www.IStrategy;
import com.linhao007.www.JsonIstrategy;
import com.linhao007.www.enums.ConstantEnum;
import com.linhao007.www.exception.JsonException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by www.linhao007.com on 2016-8-5.
 * List类型序列化成json 为内部转换 采用缺省标识
 */
class ListToJson implements IStrategy {


    public String toObjectJson(Object object) throws JsonException {
        return null;
    }

    public StringBuffer toObjectJson(Object list, StringBuffer buffer) throws JsonException, IllegalAccessException {
        JsonIstrategy jsonIstrategy = null;
        //遍历传过来的list数组
        for (Object object : (List) list) {
            //判断遍历出的值是否为空
            if (object == null) {
                buffer.append(",");
            } else {
                Class<? extends Object> class1 = object.getClass();
                String simpleName = class1.getSimpleName();

                if (simpleName.equals(ConstantEnum.DAOJIA_STRING.getType())) {

                    buffer.append("\"" + object.toString() + "\",");
                } else if (ConstantEnum.getConstantTypeByName(simpleName)) {
                    buffer.append("" + object.toString() + ",");
                } else if (simpleName.equals(ConstantEnum.DAOJIA_DATE.getType())) {
                    Date date = (Date) object;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String simdate = simpleDateFormat.format(date);
                    buffer.append("" + simdate + ",");
                } else {
                    Class<? extends Object> class2 = object.getClass();
                    Field[] fields = class2.getDeclaredFields();
                    Field.setAccessible(fields, true);
                    buffer.append("{");
                    //遍历对象中的所有字段获取字段值和字段名称拼成json字符串
                    for (Field field : fields) {
                        Object fieldobj = field.get(object);
                        String fieldName = field.getType().getSimpleName();
                        if (fieldobj == null) {
                            if (fieldName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                                buffer.append("\"" + field.getName() + "\":\"\",");
                            } else {
                                buffer.append("\"" + field.getName() + "\":null,");
                            }
                        } else {
                            String fsimpleName = fieldobj.getClass().getSimpleName();
                            if (fsimpleName.equals(ConstantEnum.DAOJIA_STRING.getType())) {
                                buffer.append("\"" + field.getName() + "\":\"" + field.get(object) + "\",");
                            } else if (ConstantEnum.getConstantTypeByName(fsimpleName)) {
                                buffer.append("\"" + field.getName() + "\":" + field.get(object) + ",");
                            } else if (fsimpleName.equals(ConstantEnum.DAOJIA_DATE.getType())) {
                                Date date = (Date) fieldobj;
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String simdate = simpleDateFormat.format(date);
                                buffer.append("\"" + field.getName() + "\":" + simdate + ",");
                            } else {
                                jsonIstrategy = new JsonIstrategy(new BeanToJson());
                                buffer = jsonIstrategy.operate(fieldobj, buffer).append(",");
                            }
                        }
                    }
                    buffer = new StringBuffer("" + buffer.substring(0, buffer.length() - 1) + "");
                    buffer.append("},");
                }
            }
        }
        buffer = new StringBuffer("" + buffer.substring(0, buffer.length() - 1) + "");
        buffer.append("]");
        return buffer;
    }
}

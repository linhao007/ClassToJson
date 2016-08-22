package com.linhao007.www.enums;

/**
 * Created by daojialinhu01 on 2016-8-9.
 * java常量枚举
 */
public enum ConstantEnum {
    DAOJIA_STRING(1000, "String"),

    DAOJIA_BIGDECIMAL(2000, "BigDecimal"),

    DAOJIA_BOOLEAN(3000, "Boolean"),
    DAOJIA_BOOLEAN_UNBOX(3010, "boolean"),

    DAOJIA_INTEGER(4000, "Integer"),
    DAOJIA_INTEGER_UNBOX(4010, "int"),

    DAOJIA_DOUBLE(5000, "Double"),
    DAOJIA_DOUBLE_UNBOX(5010, "double"),

    DAOJIA_FLOAT(6000, "Float"),
    DAOJIA_FLOAT_UNBOX(6010, "float"),

    DAOJIA_LONG(7000, "Long"),
    DAOJIA_LONG_UNBOX(7010, "long"),

    DAOJIA_BYTE(8000, "Byte"),
    DAOJIA_BYTE_UNBOX(8010, "byte"),

    DAOJIA_SHORT(9000, "Short"),
    DAOJIA_SHORT_UNBOX(9010, "short"),

    DAOJIA_DATE(0010, "Date");

    private int code;
    private String type;

    ConstantEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public static boolean getConstantTypeByName(String name){
        boolean flag = false;
        for (ConstantEnum typeEnum : ConstantEnum.values()){
            if(!name.equals("Date")&&!name.equals("String")){
                if (typeEnum.type.equals(name)){
                    flag=true;
                    break;
                }
            }
        }
        return flag;
    }
}

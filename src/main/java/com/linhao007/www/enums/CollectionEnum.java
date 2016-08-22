package com.linhao007.www.enums;

/**
 * Created by daojialinhu on 2016-8-9.
 * 集合枚举
 */
public enum CollectionEnum {
    DAOJIA_ARRAYLIST(1000, "ArrayList"),
    DAOJIA_LINKEDLIST(1010, "LinkedList"),

    DAOJIA_HASHSET(2000, "HashSet"),
    DAOJIA_TREESET(2010, "TreeSet"),

    DAOJIA_HASHMAP(3000, "HashMap"),
    DAOJIA_HASHTABLE(3010, "HashTable");

    private int code;
    private String type;

    CollectionEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }
}

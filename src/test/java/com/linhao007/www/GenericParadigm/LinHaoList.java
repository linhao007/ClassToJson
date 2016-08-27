package com.linhao007.www.GenericParadigm;


import java.util.ArrayList;

/**
 * Created by www.linhao007.com on 2016-8-25.
 */
public class LinHaoList<T extends Number> extends ArrayList<T> {

    public LinHaoList() {
        super();
    }

    public boolean add(T t) {
        return super.add(t);
    }

    public T get(int index){
        return (T) super.get(index);
    }

    @Override
    public String toString() {
        return "LinHaoList="+super.toString();
    }
}

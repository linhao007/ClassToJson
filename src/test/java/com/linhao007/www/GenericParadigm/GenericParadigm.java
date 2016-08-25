package com.linhao007.www.GenericParadigm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daojia on 2016-8-25.
 */
public class GenericParadigm {
    List<Number> list = new ArrayList<Number>();

    @Test
    public void testGenericParadigm() {
        this.addNumber(18);
        this.addNumber(27L);
        System.out.println(this.addNumber(1.345f));
    }

    private <T extends Number> List addNumber(T t) {
        list.add(t);
        return list;
    }


    @Test
    public void testLinHaoList() {
        LinHaoList<Number> linHaoList = new LinHaoList();
        linHaoList.add(1);
        linHaoList.add(2L);
        linHaoList.add(3.45f);
        System.out.println(linHaoList.get(2));
    }

}

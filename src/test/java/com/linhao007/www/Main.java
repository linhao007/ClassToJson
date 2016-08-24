package com.linhao007.www;

import com.linhao007.www.BuildersPattern.Persion;
import com.linhao007.www.TemplatePattern.BaseTemplate;
import com.linhao007.www.TemplatePattern.SpeakTemplate;
import org.junit.Test;

/**
 * Created by www.linhao007.com on 2016-6-22.
 */
public class Main {

    @Test
    public void testBuilderpattern() {
        Persion persion = new Persion.Builder().
                addAge(18).//
                addEducation("硕士").
                addName("linhao007").
                addSex("man").
                build();//建立该复杂对象
        System.out.println("这个人年龄："+persion.getAge());
    }

    @Test
    public void testTemplat(){
        BaseTemplate baseTemplate = new SpeakTemplate();
        baseTemplate.sayMessage();
    }
}

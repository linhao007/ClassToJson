package com.linhao007.www.TemplatePattern;

/**
 * Created by www.linhao007.com on 2016-8-24.
 */
public abstract class BaseTemplate {
    private void sayHello() {
        System.out.println("hello");
    }

    private void sayBaibai() {
        System.out.println("bai bai ");
    }

    public final void sayMessage() {
        sayHello();
        speak();
        sayBaibai();
    }
    protected abstract void speak();
}

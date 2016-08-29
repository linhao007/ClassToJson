package com.linhao007.www.CallBackPattern;

/**
 * Created by www.linhao007.com on 2016-8-27.
 */
public class SimpleTask extends Task {
    @Override
    public void execute() {
        System.out.println("I'm doing now");
    }

    public void call() {
        System.out.println("Perform some important activity.");
    }
}

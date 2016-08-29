package com.linhao007.www.CallBackPattern;

/**
 * Created by www.linhao007.com on 2016-8-27.
 */
public class Client {
    public static void main(String[] args) {
        Task task = new SimpleTask();
        task.executeWith(task);
    }
}

package com.linhao007.www.CallBackPattern;

/**
 * Created by www.linhao007.com on 2016-8-27.
 */
public abstract class Task implements Callback{
    public final void executeWith(Callback callback) {
        execute();
        if (callback != null) {
            callback.call();
        }
    }

    public abstract void execute();
}

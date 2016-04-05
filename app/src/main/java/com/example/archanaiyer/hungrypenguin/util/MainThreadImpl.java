package com.example.archanaiyer.hungrypenguin.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class MainThreadImpl implements MainThread {
    private Handler handler;

    public MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}

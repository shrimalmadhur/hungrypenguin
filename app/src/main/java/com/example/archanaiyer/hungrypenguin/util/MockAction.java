package com.example.archanaiyer.hungrypenguin.util;

/**
 * Created by archanaiyer on 4/4/16.
 */
public class MockAction implements Interactor{
    private MockActionCallback callback;
    private MainThread mainThread;

    public MockAction(MockActionCallback callback) {
        this.callback = callback;
        this.mainThread = new MainThreadImpl();
    }

    @Override
    public void run() {
        mockLoadingTime();
        notifyActionComplete();
    }

    private void mockLoadingTime() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Empty
        }
    }

    private void notifyActionComplete() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMockActionComplete();
            }
        });
    }
}

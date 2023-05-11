package com.zksg.kudoud.test;

public class PowerMockSample {

    private static final int STATE_NOT_READY = 0;
    private static final int STATE_READY = 1;

    private int mState = STATE_NOT_READY;

    public boolean doSomethingIfStateReady() {
        if (mState == STATE_READY) {
            // DO some thing
            return true;
        } else {
            return false;
        }
    }


}

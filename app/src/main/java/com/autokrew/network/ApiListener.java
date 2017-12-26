package com.autokrew.network;

/**
 * Created by kumarpalsinh on 9/1/17.
 */

public interface ApiListener {

    void onApiSuccess(Object mObject);

    void onApiFailure(Throwable mThrowable);

}

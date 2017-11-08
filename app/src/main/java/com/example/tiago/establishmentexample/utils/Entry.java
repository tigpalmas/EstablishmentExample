package com.example.tiago.establishmentexample.utils;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by tiago on 07/11/2017.
 */

public class Entry extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}

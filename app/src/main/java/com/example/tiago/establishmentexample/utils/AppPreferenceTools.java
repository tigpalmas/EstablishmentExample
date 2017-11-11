package com.example.tiago.establishmentexample.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by tiago on 14/01/2017.
 */

public class AppPreferenceTools {
    private static final String PREF = "PREFERENCES";
    private SharedPreferences mPreference;
    private Context mContext;

    public static final String STRING_PREF_UNAVAILABLE = "string preference unavailable";

    public AppPreferenceTools(Context mContext) {
        this.mContext = mContext;
        this.mPreference = this.mContext.getSharedPreferences("app_preference", Context.MODE_PRIVATE);
    }

    //Save the user authentication model to pref at sing up|| sign in



    public void registerPush(boolean status, String oneSignalId){
        mPreference.edit()
                .putBoolean("status", status)
                .putString("oneSignalId", oneSignalId)
                .apply();

    }

    public boolean isAlreadyRegisterOneSinal(){
        return    mPreference.getBoolean("status", false);
    }

    public void saveOrder(String orderId){
        mPreference.edit().putString("orderNumber", orderId);
    }

    public void getOrder(){
        mPreference.getString("orderNumber", null);
    }

    public String getOneSignalId(){
        return mPreference.getString("oneSignalId", null);
    }
}

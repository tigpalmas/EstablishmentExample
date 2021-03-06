package com.example.tiago.establishmentexample.utils;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tiago on 22/01/2017.
 */

public class Util {
    @TargetApi((Build.VERSION_CODES.M))
    public static boolean isSystemAlertPermissionGranted(Context ctx){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(ctx);
    }


    public static void showProgressDialog(Context ctx, ProgressDialog mProgressDialog, boolean active, String message){
        if(active){
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }else{
            mProgressDialog.dismiss();
        }
    }

    public static void showToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackBar(Context ctx, View view, String text){
        Snackbar mySnackbar = Snackbar.make(view,
                text, Snackbar.LENGTH_SHORT);
        mySnackbar.setAction("Sim", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mySnackbar.show();
    }

}

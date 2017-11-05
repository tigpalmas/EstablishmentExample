package com.example.tiago.establishmentexample.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

/**
 * Created by tiago on 10/10/2017.
 */


public class Order implements Serializable {
    public float shipping;
    public String objectId;
    public List<CartItem> itens;


    public float getTotal(){
        float total = 0;
        for(CartItem cartItem : itens){
            total = total+cartItem.getTotal();
        }
        return total;
    }

    public float getTotalDiscount(){
        float totalDiscount= 0;
        for(CartItem cartItem : itens){
            if(cartItem.promotion!=null){
                totalDiscount = totalDiscount+cartItem.getTotalDiscount();
            }

        }
        return totalDiscount;
    }


}

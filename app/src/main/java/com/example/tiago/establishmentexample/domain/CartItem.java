package com.example.tiago.establishmentexample.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.io.Serializable;

/**
 * Created by tiago on 10/10/2017.
 */

public class CartItem implements Serializable {

    public int quantity;
    public String productId;
    public String promotionId;
    public Product product;
    public Promotion promotion;


    public float getTotal(){
        if(promotion==null){
            return  quantity * product.price;
        }else{
            return (quantity * product.price) - (quantity * promotion.price);
        }
    }

    public float getTotalDiscount(){
        return (quantity * promotion.price);
    }



}

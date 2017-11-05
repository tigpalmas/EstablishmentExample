package com.example.tiago.establishmentexample.promotionalListRecycler;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tiago.establishmentexample.product.Product;

import java.io.Serializable;

/**
 * Created by tiago on 09/10/2017.
 */

public class Promotion implements Serializable {
    public String objectId;
    public Product productId;
    public float price;

    public Promotion() {
    }



}

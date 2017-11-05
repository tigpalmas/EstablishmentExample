package com.example.tiago.establishmentexample.product;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by tiago on 09/10/2017.
 */

public class Product implements   Serializable, Parcelable {
    public String objectId;
    public String name;
    public String description;
    public String brand;
    public Category categoryId;
    public Image image;
    public float price;

    public Product() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.objectId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.brand);
        dest.writeSerializable(this.categoryId);
        dest.writeSerializable(this.image);
        dest.writeFloat(this.price);
    }

    protected Product(Parcel in) {
        this.objectId = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.brand = in.readString();
        this.categoryId = (Category) in.readSerializable();
        this.image = (Image) in.readSerializable();
        this.price = in.readFloat();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

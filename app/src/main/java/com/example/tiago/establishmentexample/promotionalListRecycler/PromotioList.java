package com.example.tiago.establishmentexample.promotionalListRecycler;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 13/10/2017.
 */

public class PromotioList implements Parcelable {
    public List<Promotion> promotions;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.promotions);
    }

    public PromotioList() {
    }

    protected PromotioList(Parcel in) {
        this.promotions = new ArrayList<Promotion>();
        in.readList(this.promotions, Promotion.class.getClassLoader());
    }

    public static final Parcelable.Creator<PromotioList> CREATOR = new Parcelable.Creator<PromotioList>() {
        @Override
        public PromotioList createFromParcel(Parcel source) {
            return new PromotioList(source);
        }

        @Override
        public PromotioList[] newArray(int size) {
            return new PromotioList[size];
        }
    };
}

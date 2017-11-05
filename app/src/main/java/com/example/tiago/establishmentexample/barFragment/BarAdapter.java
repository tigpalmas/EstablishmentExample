package com.example.tiago.establishmentexample.barFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.EventActivity;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.Image;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.shopFragment.ItensShopAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tiago on 14/01/2016.
 */
public class BarAdapter extends RecyclerView.Adapter<BarAdapter.MyViewHolder> {
    private List<Product> mProducts;
    private Context c;
    private FragmentTransaction fragmentTransaction;


    public BarAdapter(Context c, List<Product> l) {
        mProducts = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bar, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, c, mProducts);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (product.image != null) {
            Image image = product.image;
            if (image.url != null) {
                Glide.with(c).load(image.url).into(holder.imgBand);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgBand;
        private Context ctx;


        public MyViewHolder(View itemView, Context ctx, List<Product> products) {
            super(itemView);
            imgBand = (ImageView)itemView.findViewById(R.id.img_band);
            this.ctx = ctx;


            itemView.setOnClickListener(this);
        }

        public void setAdapter(List<Product> products) {

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();



        }
    }

    public static String obterDataPorExtenso(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }


}

package com.example.tiago.establishmentexample.barFragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.product.Image;
import com.example.tiago.establishmentexample.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tiago on 14/01/2016.
 */
public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.MyViewHolder> {
    private List<Product> mProducts;
    private Context c;
    private FragmentTransaction fragmentTransaction;


    public PicturesAdapter(Context c, List<Product> l) {
        mProducts = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
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
        public CircleImageView imgBand;


        public MyViewHolder(View itemView, Context ctx, List<Product> products) {
            super(itemView);
            imgBand = (CircleImageView)itemView.findViewById(R.id.img_band);
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

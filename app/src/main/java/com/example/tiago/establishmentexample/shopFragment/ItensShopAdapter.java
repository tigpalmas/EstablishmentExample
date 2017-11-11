package com.example.tiago.establishmentexample.shopFragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogFragment;
import com.example.tiago.establishmentexample.product.Image;
import com.example.tiago.establishmentexample.product.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tiago on 14/01/2016.
 */
public class ItensShopAdapter extends RecyclerView.Adapter<ItensShopAdapter.MyViewHolder> {
    private List<Product> mProducts;
    private Context c;
    private FragmentTransaction fragmentTransaction;
    private final int CLOTHES = 0, SHOES = 1;


    public ItensShopAdapter(Context c, List<Product> l) {
        mProducts = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }


    @Override
    public int getItemViewType(int position) {
        Product product = mProducts.get(position);
        if (product.categoryId.category.equals("Sapatos")) {
            return SHOES;
        } else {
            return CLOTHES;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case SHOES:
                View v1 = inflater.inflate(R.layout.item_child_shop2, parent, false);
                viewHolder = new MyViewHolder(v1, c, mProducts);
                break;
            default:
                View v2 = inflater.inflate(R.layout.item_child_shop, parent, false);
                viewHolder = new MyViewHolder(v2, c, mProducts);
                break;


        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (product.image != null) {
            Image image = product.image;
            if (image.url != null) {
                Glide.with(c).load(image.url).into(holder.imgItem);
            }
        }


    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgItem;


        public MyViewHolder(View itemView, Context ctx, List<Product> products) {
            super(itemView);
            imgItem = (ImageView) itemView.findViewById(R.id.img_item);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Product product = mProducts.get(position);
            DialogFragment fragment = DialogFragment.novaInstancia(product);
            FragmentTransaction fragmentTransaction =  ((FragmentActivity)c).getSupportFragmentManager().beginTransaction();
            fragment.show(fragmentTransaction, "dialog");


        }
    }

    public static String obterDataPorExtenso(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }


}

package com.example.tiago.establishmentexample.promotionalListRecycler;

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
import com.example.tiago.establishmentexample.diagoFragment.DialogPromotionFragment;
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
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private List<Promotion> mPromotion;
    private Context c;
    private FragmentTransaction fragmentTransaction;


    public ItemAdapter(Context c, List<Promotion> l) {
        mPromotion = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, c, mPromotion);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         Promotion promotion = mPromotion.get(position);

        if(promotion.productId!=null){
            Product product = promotion.productId;
            holder.productName.setText(product.brand);
            holder.productDescripton.setText(product.description);
            holder.productPrice.setText("R$ "+product.price);
            holder.productDiscount.setText("R$ "+ (product.price - promotion.price));

            if(product.image!=null){
                Image image = product.image;
                if(image.url!=null){
                    Glide.with(c).load(image.url).into(holder.imgProduct);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mPromotion.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView productName;
        private TextView productDescripton;
        private TextView productPrice;
        private TextView productDiscount;
        private ImageView imgProduct ;
        List<Promotion> promotions;
        Context ctx;

        public MyViewHolder(View itemView, Context ctx, List<Promotion> promotions) {
            super(itemView);
            imgProduct = (ImageView)itemView.findViewById(R.id.iv_product);
            productName = (TextView)itemView.findViewById(R.id.txt_product_brand);
            productDescripton = (TextView)itemView.findViewById(R.id.txt_product_description);
            productPrice = (TextView)itemView.findViewById(R.id.txt_price);
            productDiscount = (TextView)itemView.findViewById(R.id.txt_product_discount);
            this.promotions = promotions;
            this.ctx = ctx;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Promotion promotion = this.promotions.get(position);
            DialogPromotionFragment fragment = DialogPromotionFragment.novaInstancia(promotion);
            FragmentTransaction fragmentTransaction = ((FragmentActivity)ctx).getSupportFragmentManager().beginTransaction();
            fragment.show(fragmentTransaction, "dialog");

        }
    }

    public static String obterDataPorExtenso(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }


}

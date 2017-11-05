package com.example.tiago.establishmentexample.product;

import android.content.Context;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogFragment;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


/**
 * Created by tiago on 07/06/2017.
 */

public class ProductViewHolder extends ChildViewHolder implements View.OnClickListener {
    private TextView productName;
    private TextView productDescripton;
    private TextView productPrice;
    private ImageView imgProduct ;
    private Context ctx;
    private Product product;

    public ProductViewHolder(Context ctx, View itemView) {
        super(itemView);
        this.ctx = ctx;
        imgProduct = (ImageView)itemView.findViewById(R.id.iv_product);
        productName = (TextView)itemView.findViewById(R.id.txt_product_brand);
        productDescripton = (TextView)itemView.findViewById(R.id.txt_product_description);
        productPrice = (TextView)itemView.findViewById(R.id.txt_price);
        imgProduct.setOnClickListener(this);

    }

    public void onBind(Product product){
        this.product = product;
        productName.setText(product.brand);
        productDescripton.setText(product.description);
        productPrice.setText("R$ "+product.price);
        if(product.image!=null){
            Image image = product.image;
            Glide.with(ctx).load(image.url).into(imgProduct);
            imgProduct.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        DialogFragment fragment = DialogFragment.novaInstancia(product);
        FragmentTransaction fragmentTransaction = ((FragmentActivity)ctx).getSupportFragmentManager().beginTransaction();
        fragment.show(fragmentTransaction, "dialog");
    }


}

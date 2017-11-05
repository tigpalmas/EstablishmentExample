package com.example.tiago.establishmentexample.cartItemFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.product.Image;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tiago on 14/01/2016.
 */
public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.MyViewHolder> {
    private List<CartItem> mItens;
    private CartItemFragment mFragment;
    private Context c;



    public CartItemAdapter(CartItemFragment fragment, Context c, List<CartItem> l) {
        mFragment = fragment;
        mItens = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, c, mItens);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CartItem iten = mItens.get(position);
        holder.onBind(iten);
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder  {

        private TextView productName;
        private TextView productPrice;
        private TextView txtNumber;
        private TextView txtTotal;
        private TextView txtDescription;
        private Button btnIncrement, btnDecrement;
        List<CartItem> itens;
        Context ctx;

        public MyViewHolder(View itemView, Context ctx, List<CartItem> itens) {
            super(itemView);
            productName = itemView.findViewById(R.id.txt_product);
            productPrice = itemView.findViewById(R.id.txt_price);
            txtNumber = itemView.findViewById(R.id.txt_number);
            btnIncrement = itemView.findViewById(R.id.btn_increment);
            btnDecrement = itemView.findViewById(R.id.btn_decrement);
            txtTotal = itemView.findViewById(R.id.txt_total);
            txtDescription = itemView.findViewById(R.id.txt_description);
            this.itens = itens;
            this.ctx = ctx;


        }

        public void onBind(final CartItem cartItem) {
            productName.setText(cartItem.product.brand);
            productPrice.setText("Valor unitário R$ " + cartItem.product.price);
            txtDescription.setText(cartItem.product.description);
            updateTotal(cartItem);

            btnDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cartItem.quantity ==1){
                        itens.remove(cartItem);
                        int newPosition = getAdapterPosition();
                        notifyItemRemoved(
                                newPosition);
                    }
                    if(cartItem.quantity>1){
                        --cartItem.quantity;
                        updateTotal(cartItem);
                    }

                    mFragment.updateValues();

                }
            });

            btnIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cartItem.quantity<10){
                        ++cartItem.quantity;
                        updateTotal(cartItem);
                    }
                    mFragment.updateValues();
                }
            });
        }

        private void updateTotal(CartItem cartItem){
            txtTotal.setText("R$ "+cartItem.getTotal());
            txtNumber.setText(cartItem.quantity+"");
        }


    }

    public static String obterDataPorExtenso(Date dataAtual) {
        Locale BRAZIL = new Locale("pt", "BR");
        DateFormat dfmt = new SimpleDateFormat("EEEE, d 'de' MMMM ", BRAZIL);
        return dfmt.format(dataAtual);
    }


}

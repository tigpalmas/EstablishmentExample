package com.example.tiago.establishmentexample.shopFragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.ItemAdapter;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tiago on 14/01/2016.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private List<Genre> mGenre;
    private Context c;
    private FragmentTransaction fragmentTransaction;


    public ShopAdapter(Context c, List<Genre> l) {
        mGenre = l;
        this.c = c;
        if (c == null) {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent_shop, parent, false);
        MyViewHolder mvh = new MyViewHolder(v, c, mGenre);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         Genre genre = mGenre.get(position);
        holder.txtTitle.setText(genre.getTitle());
        holder.setAdapter(genre.getItems());



    }

    @Override
    public int getItemCount() {
        return mGenre.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtTitle;
        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private ItensShopAdapter mAdapter;

        private List<Product> mProducts;



        public MyViewHolder(View itemView, Context ctx, List<Genre> genres) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_itens);

            mLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);

            itemView.setOnClickListener(this);
        }

        public void setAdapter(List<Product> products){
            mAdapter = new ItensShopAdapter(c, products);
            mRecyclerView.setAdapter(mAdapter);
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

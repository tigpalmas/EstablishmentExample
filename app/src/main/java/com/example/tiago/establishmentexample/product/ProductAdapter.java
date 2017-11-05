package com.example.tiago.establishmentexample.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tiago.establishmentexample.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by tiago on 07/06/2017.
 */

public class ProductAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ProductViewHolder> {
    private Context ctx;

    public ProductAdapter(Context ctx, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.ctx = ctx;
    }

    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_cardapio_parent, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public ProductViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_cardapio_child, parent, false);
        return new ProductViewHolder(ctx, view);
    }

    @Override
    public void onBindChildViewHolder(ProductViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Product product = (Product)(group).getItems().get(childIndex);
        holder.onBind(product);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition, ExpandableGroup group) {
         holder.setGenreTitle(group);
    }
}

package com.example.tiago.establishmentexample.product;

import android.view.View;
import android.widget.TextView;


import com.example.tiago.establishmentexample.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by tiago on 07/06/2017.
 */

public class GenreViewHolder extends GroupViewHolder {
    private TextView genreTitle;

    public GenreViewHolder(View itemView) {
        super(itemView);
        genreTitle = (TextView)itemView.findViewById(R.id.tv_group);
    }


    public void setGenreTitle(ExpandableGroup group) {
     genreTitle.setText(group.getTitle());
    }
}

package com.example.tiago.establishmentexample.product;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by tiago on 07/06/2017.
 */

public class Genre extends ExpandableGroup<Product> {

    public Genre(String title, List<Product> items) {
        super(title, items);
    }
}

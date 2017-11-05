package com.example.tiago.establishmentexample.barFragment;

import android.content.Context;

import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.product.ProductList;
import com.example.tiago.establishmentexample.shopFragment.ModelShop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 09/10/2017.
 */

public class PresenterBar implements MVP.PresenterShop {
    private List<Product> mProducts = new ArrayList<>();
    private MVP.ModelShop model;
    private MVP.ViewShop view;
    private Context context;

    public PresenterBar(Context context) {
        this.context = context;
        model = new ModelBar(this);
    }

    @Override
    public void retrieveProducts() {
        model.retrieveProducts();
    }

    @Override
    public List<Product> getProducts() {
        return this.mProducts;
    }

    @Override
    public void setView(MVP.ViewShop view) {
        this.view = view;
    }

    @Override
    public void setProducts(ProductList products) {
        updateListaRecycler(products.products);
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void updateListaRecycler(List<Product> products) {
        mProducts.clear();
        mProducts.addAll(products);

        final List<String> categories = new ArrayList<>();
        for (Product object : products) {
            if (!categories.contains(object.categoryId.category)) {
                categories.add(object.categoryId.category);
            }
        }

        List<Genre> genres = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            List<Product> list = new ArrayList<Product>();
            for (Product object : products) {
                if (object.categoryId.category.equals(categories.get(i))) {
                    list.add(object);
                }
            }
            Genre genre = new Genre(categories.get(i), list);
            genres.add(genre);
        }

        view.updateListaRecycler(genres, products);
    }

    @Override
    public void showLoadProgress(boolean status, String message) {
        view.showLoadProgress(status, message);
    }
}

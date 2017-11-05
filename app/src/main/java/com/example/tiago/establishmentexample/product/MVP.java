package com.example.tiago.establishmentexample.product;

import android.content.Context;

import com.example.tiago.establishmentexample.domain.Contato;

import java.util.List;

/**
 * Created by tiago on 09/10/2017.
 */

public interface MVP {

    //PRODUCTS
    interface ModelProducts{
        void retrieveProducts();
    }

    interface PresenterProduct{
        void retrieveProducts();
        List<Product> getProducts();
        void setView(MVP.ViewProduct view);
        void setProducts(ProductList products);
        Context getContext();
        void updateListaRecycler(List<Product> products);
        void showLoadProgress(boolean status, String message);

    }

    interface ViewProduct{
        void updateListaRecycler(List<Genre> genres, List<Product> products);
        void showLoadProgress(boolean status, String message);
    }

    interface ModelShop{
        void retrieveProducts();
    }

    interface PresenterShop{
        void retrieveProducts();
        List<Product> getProducts();
        void setView(MVP.ViewShop view);
        void setProducts(ProductList products);
        Context getContext();
        void updateListaRecycler(List<Product> products);
        void showLoadProgress(boolean status, String message);

    }

    interface ViewShop{
        void updateListaRecycler(List<Genre> genres, List<Product> products);
        void showLoadProgress(boolean status, String message);
    }

    interface ModelContato{
        void postContato(Contato contato);
    }

    interface PresenterContato{
        void postContato(Contato contato);

        void setView(MVP.ViewContato view);
    }

    interface  ViewContato{


    }




}

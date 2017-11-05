package com.example.tiago.establishmentexample.promotionalListRecycler;

import android.content.Context;

import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.Product;

import java.util.List;

/**
 * Created by tiago on 09/10/2017.
 */

public interface MVPItens {

    //PRODUCTS
    interface ModelItens{
        void retrieveItens();
    }

    interface PresenterItens{
        void retrieveItens();
        List<Promotion> getItens();
        void setView(MVPItens.ViewItens view);
        Context getContext();
        void updateListaRecycler(List<Promotion> promotions);
        void showLoadProgress(boolean status, String message);

        void setListPromotions(PromotioList mPromotionList);
    }

    interface ViewItens{
        void updateListaRecycler(List<Promotion> promotions);
        void showLoadProgress(boolean status, String message);
    }




}

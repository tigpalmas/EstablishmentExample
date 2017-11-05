package com.example.tiago.establishmentexample.promotionShop;

import android.content.Context;

import com.example.tiago.establishmentexample.promotionalListRecycler.MVPItens;
import com.example.tiago.establishmentexample.promotionalListRecycler.ModelItens;
import com.example.tiago.establishmentexample.promotionalListRecycler.PromotioList;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 09/10/2017.
 */

public class PresenterPromotionShop implements MVPItens.PresenterItens {
    private List<Promotion> mPromotions = new ArrayList<>();
    private MVPItens.ModelItens model;
    private MVPItens.ViewItens view;
    private Context context;

    public PresenterPromotionShop(Context context) {
        this.context = context;
        model = new ModelPromotionShop(this);
    }

    @Override
    public void retrieveItens() {
        model.retrieveItens();
    }

    @Override
    public List<Promotion> getItens() {
        return this.mPromotions;
    }

    @Override
    public void setView(MVPItens.ViewItens view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void updateListaRecycler(List<Promotion> promotions) {

        view.updateListaRecycler(promotions);
    }

    @Override
    public void showLoadProgress(boolean status, String message) {
        view.showLoadProgress(false, message);
    }

    @Override
    public void setListPromotions(PromotioList mPromotionList) {
        updateListaRecycler(mPromotionList.promotions);
    }
}

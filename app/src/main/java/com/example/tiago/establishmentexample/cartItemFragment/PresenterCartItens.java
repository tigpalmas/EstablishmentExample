package com.example.tiago.establishmentexample.cartItemFragment;

import android.content.Context;

import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.Order;

import java.util.List;

/**
 * Created by tiago on 11/10/2017.
 */

public class PresenterCartItens implements MVPCartItens.PresenterItens {
    private Order mOrder;
    private List<CartItem> itens;
    private MVPCartItens.ViewItens mView;
    private MVPCartItens.ModelItens mModel;
    private Context ctx;


    public PresenterCartItens( Context ctx) {
        this.ctx = ctx;
        mModel = new ModelCartItens(this);

    }

    @Override
    public void setCartItens(Order order) {
        mOrder = order;
        itens = order.itens;
        updateList();
    }

    @Override
    public void setView(MVPCartItens.ViewItens view) {
        mView = view;
    }

    @Override
    public Context getContext() {
        return this.ctx;
    }

    @Override
    public void updateList() {
        mView.updateList(itens);
    }

    @Override
    public void postOrder() {
        mOrder.shipping = 8;
        mModel.postOrder(mOrder);
    }

    @Override
    public void goBackMainPage() {
        mView.goBackMainPage();
    }
}

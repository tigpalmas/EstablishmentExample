package com.example.tiago.establishmentexample.cartItemFragment;

import android.content.Context;

import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.List;

/**
 * Created by tiago on 09/10/2017.
 */

public interface MVPCartItens {

    //PRODUCTS
    interface ModelItens{
        void postOrder(Order order);
    }

    interface PresenterItens{
        void setCartItens(Order order);
        void setView(MVPCartItens.ViewItens view);
        Context getContext();
        void updateList();
        void postOrder();
        void orderFinished(boolean status, String objectId);
    }

    interface ViewItens{
        void updateList(List<CartItem> itens);
        void orderFinished();
    }






}

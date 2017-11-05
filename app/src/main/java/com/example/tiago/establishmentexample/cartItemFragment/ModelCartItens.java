package com.example.tiago.establishmentexample.cartItemFragment;

import android.util.Log;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.domain.OrderRetrofit;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 21/10/2017.
 */

public class ModelCartItens implements MVPCartItens.ModelItens {
    private EstablishmentService mTService;
    private MVPCartItens.PresenterItens mPresenter;

    public ModelCartItens(MVPCartItens.PresenterItens mPresenter) {
        this.mPresenter = mPresenter;
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
    }



    @Override
    public void postOrder(Order order) {

        order.shipping = (float) 2.5;
        postOrderToBack(order);
    }

    public void postOrderToBack(Order order){
       Call<Order> call  = mTService.postOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccess()) {
                    Order order = response.body();
                    Log.i("teste", order.toString());
                }else{
                    Log.i("teste", response.message());

                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });


    }

}

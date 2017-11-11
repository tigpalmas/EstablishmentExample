package com.example.tiago.establishmentexample.cartItemFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.domain.OrderRetrofit;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.utils.AppPreferenceTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 21/10/2017.
 */

public class ModelCartItens implements MVPCartItens.ModelItens {
    private EstablishmentService mTService;
    private MVPCartItens.PresenterItens mPresenter;
    private AppPreferenceTools mAppPreferenceTools;


    public ModelCartItens(MVPCartItens.PresenterItens presenter) {
        this.mPresenter = presenter;
        Context ctx = presenter.getContext();
        mAppPreferenceTools = new AppPreferenceTools(ctx);
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
    }



    @Override
    public void postOrder(Order order) {

        order.shipping = (float) 8.0;
        postOrderToBack(order);
    }

    public void postOrderToBack(Order order){
       Call<Order> call  = mTService.postOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccess()) {
                    Order order = response.body();
                    mAppPreferenceTools.saveOrder(order.objectId);
                    mPresenter.orderFinished(true, order.objectId);
                    sendMessage();
                }else{
                    Log.i("teste", response.message());

                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });

    }


    private void sendMessage(){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();

        MessagePush push = new MessagePush();
        push.key = "tag1";
        push.value = mAppPreferenceTools.getOneSignalId();
        push.title = "Seu estabelecimento";
        push.message = "Seu pedido já está sendo empacotado...";

        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){

                }else{
                    Log.i("teste", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MessagePush> call, Throwable t) {

            }
        });
    }

}

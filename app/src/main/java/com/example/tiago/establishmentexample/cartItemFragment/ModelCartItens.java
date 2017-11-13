package com.example.tiago.establishmentexample.cartItemFragment;

import android.app.ProgressDialog;
import android.util.Log;

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
import com.example.tiago.establishmentexample.utils.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 21/10/2017.
 */

public class ModelCartItens implements MVPCartItens.ModelItens {
    private EstablishmentService mTService;
    private MVPCartItens.PresenterItens mPresenter;
    private ProgressDialog progressDialog;
    private AppPreferenceTools mAppPreferenceTools;

    public ModelCartItens(MVPCartItens.PresenterItens mPresenter) {
        this.mPresenter = mPresenter;
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
        progressDialog = new ProgressDialog(mPresenter.getContext());
        mAppPreferenceTools = new AppPreferenceTools(mPresenter.getContext());
    }



    @Override
    public void postOrder(Order order) {
        Util.showProgressDialog(mPresenter.getContext(), progressDialog, true, "Solicitando Order, aguarde");
        order.shipping = (float) 2.5;
        postOrderToBack(order);
    }

    public void postOrderToBack(Order order){
       Call<Order> call  = mTService.postOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Util.showProgressDialog(mPresenter.getContext(), progressDialog, false, "");
                if(response.isSuccess()) {
                    Order order = response.body();
                    Util.showToast(mPresenter.getContext(), "Pedido Solicitado com Sucesso");
                    sendMessage();
                    mPresenter.goBackMainPage();
                }else{
                    Log.i("teste", response.message());
                    Util.showToast(mPresenter.getContext(), "Ops something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Util.showProgressDialog(mPresenter.getContext(), progressDialog, false, "");
            }
        });


    }


    private void sendMessage(){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();

        MessagePush push = new MessagePush();
        push.key = "tag1";
        push.value = mAppPreferenceTools.getOneSignalId();
        push.title = "Obrigado Pelo Pedido ";
        push.message = "Seu pedido já foi encaminhado para o nosso setor de entregas";


        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){
                    Log.i("teste", "sucesso");
                }else{
                    Log.i("teste", "erro");
                }
            }

            @Override
            public void onFailure(Call<MessagePush> call, Throwable t) {

            }
        });
    }

}

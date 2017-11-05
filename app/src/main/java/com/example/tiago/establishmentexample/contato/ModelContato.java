package com.example.tiago.establishmentexample.contato;

import android.util.Log;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.Contato;
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 02/11/2017.
 */

public class ModelContato implements MVP.ModelContato {
    private EstablishmentService mTService;
    private MVP.PresenterContato presenter;



    public ModelContato(MVP.PresenterContato presenter) {
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
        this.presenter = presenter;
    }

    @Override
    public void postContato(Contato contato) {
        postToServer(contato);
    }

    private void postToServer(Contato contato){

        Call<Contato> call = mTService.postContact(contato);
        call.enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {

                if(response.isSuccess()){
                   Contato contato = response.body();

                }else{
                    ErrorModel errorModel = ErrorUtils.parseError(response);

                }
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {

            }
        });
    }
}

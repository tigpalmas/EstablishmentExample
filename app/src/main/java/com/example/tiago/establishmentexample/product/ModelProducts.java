package com.example.tiago.establishmentexample.product;

import android.content.Context;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 09/10/2017.
 */

public class ModelProducts implements MVP.ModelProducts {
    private EstablishmentService mTService;
    private MVP.PresenterProduct mPresenter;
    private Context ctx;

    public ModelProducts(MVP.PresenterProduct mPresenter) {
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
        this.mPresenter = mPresenter;
        ctx = mPresenter.getContext();
    }

    @Override
    public void retrieveProducts() {
        getProducts();
    }

    public void getProducts(){
        Call<List<Product>> call = mTService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccess()){
                    List<Product> products = response.body();
                    if(products!=null){
                        if(products.size()>0){
                           mPresenter.showLoadProgress(false, "");
                            mPresenter.updateListaRecycler(products);
                        }else{
                            mPresenter.showLoadProgress(false, ctx.getResources().getString(R.string.empty_menu));
                        }
                    }
                }else{
                    ErrorModel errorModel = ErrorUtils.parseError(response);
                    mPresenter.showLoadProgress(false,ctx.getResources().getString(R.string.error_message)+errorModel.message);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mPresenter.showLoadProgress(false,ctx.getResources().getString(R.string.error_message)+t.getMessage());
            }
        });

    }

   
}

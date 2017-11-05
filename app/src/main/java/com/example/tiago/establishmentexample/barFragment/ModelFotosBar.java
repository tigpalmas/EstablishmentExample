package com.example.tiago.establishmentexample.barFragment;

import android.content.Context;

import com.example.tiago.establishmentexample.R;
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
 * Created by tiago on 09/10/2017.
 */

public class ModelFotosBar implements MVP.ModelShop {
    private EstablishmentService mTService;
    private MVP.PresenterShop mPresenter;
    private Context ctx;

    public ModelFotosBar(MVP.PresenterShop mPresenter) {
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
        Call<List<Product>> call = mTService.getPicturesBar();
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

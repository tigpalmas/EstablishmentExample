package com.example.tiago.establishmentexample.promotionShop;

import android.content.Context;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.ErrorModel;
import com.example.tiago.establishmentexample.domain.ErrorUtils;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.promotionalListRecycler.MVPItens;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 09/10/2017.
 */

public class ModelPromotionShop implements MVPItens.ModelItens {
    private EstablishmentService mTService;
    private MVPItens.PresenterItens mPresenter;
    private Context ctx;

    public ModelPromotionShop(MVPItens.PresenterItens mPresenter) {
        this.mPresenter = mPresenter;
        ctx = mPresenter.getContext();
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
    }

    @Override
    public void retrieveItens() {
        Call<List<Promotion>> call = mTService.getPromotionsShop();
        call.enqueue(new Callback<List<Promotion>>() {
            @Override
            public void onResponse(Call<List<Promotion>> call, Response<List<Promotion>> response) {
                if(response.isSuccess()){
                    List<Promotion> promotions = response.body();
                    if(promotions!=null){
                        if(promotions.size()>0){
                            mPresenter.showLoadProgress(false, "");
                            mPresenter.updateListaRecycler(promotions);
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
            public void onFailure(Call<List<Promotion>> call, Throwable t) {
                mPresenter.showLoadProgress(false,ctx.getResources().getString(R.string.error_message)+t.getMessage());
            }
        });
    }
}

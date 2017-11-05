package com.example.tiago.establishmentexample.promotionalListRecycler;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.product.ProductList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment implements MVPItens.ViewItens {
    public static final String EXTRA_PROMOTIONS = "extra_promotions";
    private PromotioList mPromotionList;

    private MVPItens.PresenterItens mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txtLoading;
    private ProgressBar pbLoading;
    private ItemAdapter mAdapter;

    public static RecyclerFragment novaInstancia(PromotioList promotions) {
        Bundle parametros = new Bundle();
        parametros.putParcelable(EXTRA_PROMOTIONS, promotions);
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(parametros);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterItens(getContext());
        mPresenter.setView(this);
        setRetainInstance(true);
        if(getArguments()!=null){
            if (getArguments().getParcelable(EXTRA_PROMOTIONS) != null) {
                mPromotionList = (PromotioList) getArguments().getParcelable(EXTRA_PROMOTIONS);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.rv_list);
        txtLoading = (TextView) layout.findViewById(R.id.txt_loading);
        pbLoading = (ProgressBar) layout.findViewById(R.id.pb_loading);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mPromotionList.promotions==null){
            mPresenter.retrieveItens();
        }else{
            mPresenter.setListPromotions(mPromotionList);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Promoções");

    }

    @Override
    public void updateListaRecycler(List<Promotion> promotions) {
        /*PromotionsFragmentListener activity = (PromotionsFragmentListener) getActivity();
        activity.onReturnListPromotions(promotions);*/

        mAdapter = new ItemAdapter(getContext(), promotions);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoadProgress(boolean status, String message) {
        txtLoading.setText(message);
        if (status) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }

    public interface PromotionsFragmentListener {
        public void onReturnListPromotions(List<Promotion> promotions);
    }
}

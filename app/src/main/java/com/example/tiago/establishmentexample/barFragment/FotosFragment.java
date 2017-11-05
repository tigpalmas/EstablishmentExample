package com.example.tiago.establishmentexample.barFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.utils.SpacesItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotosFragment extends Fragment implements MVP.ViewShop{
    private MVP.PresenterShop mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txtLoading;
    private ProgressBar pbLoading;
    private PicturesAdapter
            mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterFotosBar(getContext());
        mPresenter.setView(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_bar, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.rv_list);
        txtLoading = (TextView) layout.findViewById(R.id.txt_loading);
        pbLoading = (ProgressBar) layout.findViewById(R.id.pb_loading);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(20));
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.retrieveProducts();
    }

    @Override
    public void updateListaRecycler(List<Genre> genres, List<Product> products) {
        mAdapter = new PicturesAdapter(getContext(), products);
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
}

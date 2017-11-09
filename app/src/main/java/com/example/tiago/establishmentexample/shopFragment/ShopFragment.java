package com.example.tiago.establishmentexample.shopFragment;


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
import com.example.tiago.establishmentexample.product.Genre;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.product.PresenterProducts;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.product.ProductAdapter;
import com.example.tiago.establishmentexample.product.ProductList;
import com.example.tiago.establishmentexample.promotionalListRecycler.ItemAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements MVP.ViewShop{
    public static final String EXTRA_PRODUCTS = "extra_products";
    private ProductList mProductList;
    private MVP.PresenterShop mPresenter;


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txtLoading;
    private ProgressBar pbLoading;
    private ShopAdapter mAdapter;


    public static ShopFragment novaInstancia(ProductList products) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PRODUCTS, products);
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterShop(getContext());
        mPresenter.setView(this);

        setRetainInstance(true);
        if(getArguments()!=null){
            if (getArguments().getSerializable(EXTRA_PRODUCTS) != null) {
                mProductList = (ProductList) getArguments().getSerializable(EXTRA_PRODUCTS);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_shop, container, false);
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
        if(mProductList.products==null){
            mPresenter.retrieveProducts();
        }else{
            mPresenter.setProducts(mProductList);
            this.showLoadProgress(false, "");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Produtos");

    }



    @Override
    public void updateListaRecycler(List<Genre> genres, List<Product> products) {
        ProductsFragmentListener activity = (ProductsFragmentListener) getActivity();
        activity.onReturnListProducts(products);

        mAdapter = new ShopAdapter(getContext(), genres);
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

    public interface ProductsFragmentListener {
        public void onReturnListProducts(List<Product> products);
    }
}

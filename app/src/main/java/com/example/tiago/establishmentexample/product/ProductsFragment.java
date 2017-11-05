package com.example.tiago.establishmentexample.product;


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
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogFragment;
import com.example.tiago.establishmentexample.domain.CartItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements MVP.ViewProduct{
    public static final String EXTRA_PRODUCTS = "extra_products";
    private ProductList mProductList;
    private MVP.PresenterProduct mPresenter;

    private RecyclerView expandableListView;
    private TextView txtLoading;
    private ProgressBar pbLoading;


    public static ProductsFragment novaInstancia(ProductList products) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PRODUCTS, products);
        ProductsFragment fragment = new ProductsFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PresenterProducts(getContext());
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
        View layout =  inflater.inflate(R.layout.fragment_products, container, false);
        txtLoading =  layout.findViewById(R.id.txt_loading_cardapio);
        pbLoading =  layout.findViewById(R.id.pb_cardapio);
        expandableListView =  layout.findViewById(R.id.expandable_list_view);


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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Cardápio");

    }



    @Override
    public void updateListaRecycler(List<Genre> genres, List<Product> products) {


        ProductAdapter adapter = new ProductAdapter(getActivity(),genres);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        expandableListView.setLayoutManager(layoutManager);
        expandableListView.setAdapter(adapter);
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

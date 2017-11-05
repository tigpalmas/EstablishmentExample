package com.example.tiago.establishmentexample.cartItemFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogAddressFragment;
import com.example.tiago.establishmentexample.diagoFragment.DialogCreditCartFragment;
import com.example.tiago.establishmentexample.diagoFragment.DialogFragment;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.promotionalListRecycler.ItemAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartItemFragment extends Fragment implements MVPCartItens.ViewItens {
    public static final String EXTRA_CARTITENS = "extra_cart_itens";
    private MVPCartItens.PresenterItens mPresenter;
    private Order mOrder;


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
;
    private CartItemAdapter mAdapter;

    private TextView txtTotal;
    private TextView txtTotalDiscount;
    private Button btnFinish;

    public static CartItemFragment novaInstancia(Order listCartItens) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_CARTITENS, listCartItens);
        CartItemFragment fragment = new CartItemFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        if(getArguments()!=null){
            if (getArguments().getSerializable(EXTRA_CARTITENS) != null) {
                mOrder = (Order) getArguments().getSerializable(EXTRA_CARTITENS);
                mPresenter = new PresenterCartItens( getContext());
                mPresenter.setView(this);
            }
        }else{
            Toast.makeText(getActivity(), "Something went Wrong :(", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_cart_itens, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.rv_list);
        txtTotalDiscount = (TextView) layout.findViewById(R.id.txt_total_discount);
        txtTotal = (TextView) layout.findViewById(R.id.txt_total);
        btnFinish = (Button) layout.findViewById(R.id.btn_finish);

        updateValues();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.setCartItens(mOrder);


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.postOrder();
            }
        });

        return layout;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuPayment = menu.findItem(R.id.action_payment);
        MenuItem menuDelivery = menu.findItem(R.id.action_delivery);
        MenuItem menuCart = menu.findItem(R.id.action_cart);
        menuPayment.setVisible(true);
        menuDelivery.setVisible(true);
        menuCart.setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_payment:
                DialogCreditCartFragment fragment = new DialogCreditCartFragment();
                FragmentTransaction fragmentTransaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fragment.show(fragmentTransaction, "dialog");
                break;
            case R.id.action_delivery:
                DialogAddressFragment fragmentAddress = new DialogAddressFragment();
                FragmentTransaction fragmentTransaction2 = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fragmentAddress.show(fragmentTransaction2, "dialog");
                break;


            default:
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Seu Pedido");

    }

    @Override
    public void updateList(List<CartItem> itens) {
        mAdapter = new CartItemAdapter(this,getContext(), itens);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void updateValues(){
        txtTotal.setText("R$ "+(mOrder.getTotal()+8.0f));
        txtTotalDiscount.setText("economizando R$ "+mOrder.getTotalDiscount());

    }
}

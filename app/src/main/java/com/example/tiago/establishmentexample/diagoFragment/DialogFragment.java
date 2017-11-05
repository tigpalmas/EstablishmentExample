package com.example.tiago.establishmentexample.diagoFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.product.Product;



/**
 * Created by tiago on 17/11/2016.
 */

public class DialogFragment extends android.support.v4.app.DialogFragment {
    public static final String EXTRA_PRODUCT = "extra_product";
    private Product mProduct;

    private ImageView ivProduct;
    private TextView txt_brand, txtPrice, txtTotal,txtNumber;
    private Button btnCancel, btnSave ,btnIncrement, btnDecrement;


    private int count = 1;



    public static DialogFragment novaInstancia(Product product) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PRODUCT, product);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(parametros);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments()!=null){
            if (getArguments().getSerializable(EXTRA_PRODUCT) != null) {
                mProduct = (Product) getArguments().getSerializable(EXTRA_PRODUCT);
            }
        }else{
            Toast.makeText(getActivity(), "Something went Wrong :(", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }

    }


    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.number_pickera_layout, container, false);

        txt_brand = layout.findViewById(R.id.txt_product);
        txtPrice = layout.findViewById(R.id.txt_price);
        txtTotal = layout.findViewById(R.id.txt_total);

        btnCancel = layout.findViewById(R.id.btn_cancel);
        btnSave = layout.findViewById(R.id.btn_save);
        btnIncrement = layout.findViewById(R.id.btn_increment);
        btnDecrement = layout.findViewById(R.id.btn_decrement);
        txtNumber = layout.findViewById(R.id.txt_number);
        txtNumber.setText(count+"");




        if(mProduct!=null){
            txt_brand.setText(mProduct.brand);
            txtPrice.setText(" Valor unitário R$ "+mProduct.price);
            txtTotal.setText("Total R$ "+mProduct.price);
        }




        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>1){
                    --count;
                    txtNumber.setText(count+"");
                    txtTotal.setText("Total R$ "+count*mProduct.price);
                }
            }
        });

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<10){
                    ++count;
                    txtNumber.setText(count+"");
                    txtTotal.setText("Total R$ "+count*mProduct.price);
                }
            }
        });





        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItem cartItem = new CartItem();
                cartItem.quantity = count;
                cartItem.product = mProduct;
                cartItem.productId = mProduct.objectId;

                getDialog().dismiss();
                MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
                activity.onReturnFromDialog(cartItem);

            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface MyDialogFragmentListener {
        public void onReturnFromDialog(CartItem cartItem);
    }




}

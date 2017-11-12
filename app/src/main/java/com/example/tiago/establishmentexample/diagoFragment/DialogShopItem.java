package com.example.tiago.establishmentexample.diagoFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.product.Image;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.utils.Touch;


/**
 * Created by tiago on 17/11/2016.
 */

public class DialogShopItem extends android.support.v4.app.DialogFragment {
    public static final String EXTRA_PRODUCT = "extra_product";
    private Product mProduct;
    private Button btnAdicionar;

    private ImageView imgProduct;
    public static DialogShopItem novaInstancia(Product product) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PRODUCT, product);
        DialogShopItem fragment = new DialogShopItem();
        fragment.setArguments(parametros);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            if (getArguments().getSerializable(EXTRA_PRODUCT) != null) {
                mProduct = (Product) getArguments().getSerializable(EXTRA_PRODUCT);
            }
        } else {
            Toast.makeText(getActivity(), "Something went Wrong :(", Toast.LENGTH_SHORT).show();
            getDialog().dismiss();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_shop_item, container, false);
        imgProduct = (ImageView) layout.findViewById(R.id.img_product);
        btnAdicionar = (Button) layout.findViewById(R.id.btn__add);
        if (mProduct.image != null) {
            Image image = mProduct.image;
            Glide.with(getContext()).load(image.url).centerCrop().into(imgProduct);
        }

        imgProduct.setOnTouchListener(new Touch());

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItem cartItem = new CartItem();
                cartItem.quantity = 1;
                cartItem.product = mProduct;
                cartItem.productId = mProduct.objectId;

                getDialog().dismiss();
                DialogShopItem.MyDialogFragmentListener activity = (DialogShopItem.MyDialogFragmentListener) getActivity();

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

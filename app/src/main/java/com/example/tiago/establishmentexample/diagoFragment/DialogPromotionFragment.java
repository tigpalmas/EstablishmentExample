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

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;


/**
 * Created by tiago on 17/11/2016.
 */

public class DialogPromotionFragment extends android.support.v4.app.DialogFragment {
    public static final String EXTRA_PROMOTION = "extra_promotion";
    private Promotion mPromotion;
    private Product mProduct;


    private TextView txt_brand, txtPrice, txtTotal, txtNumber, txtDiscount;
    private Button btnCancel, btnSave, btnIncrement, btnDecrement;


    private int count = 1;


    public static DialogPromotionFragment novaInstancia(Promotion promotion) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PROMOTION, promotion);
        DialogPromotionFragment fragment = new DialogPromotionFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            if (getArguments().getSerializable(EXTRA_PROMOTION) != null) {
                mPromotion = (Promotion) getArguments().getSerializable(EXTRA_PROMOTION);
                if (mPromotion.productId != null) {
                    mProduct = mPromotion.productId;
                }
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
        txtDiscount = layout.findViewById(R.id.txt_discount);

        btnCancel = layout.findViewById(R.id.btn_cancel);
        btnSave = layout.findViewById(R.id.btn_save);
        btnIncrement = layout.findViewById(R.id.btn_increment);
        btnDecrement = layout.findViewById(R.id.btn_decrement);
        txtNumber = layout.findViewById(R.id.txt_number);


        txtNumber.setText(count + "");

        if (mProduct != null) {
            txt_brand.setText(mProduct.brand);
            setTextPrices(count);
        }

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1) {
                    --count;
                    setTextPrices(count);
                }
            }
        });

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count < 10) {
                    ++count;
                    setTextPrices(count);
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
                createCartIten();
            }
        });

        return layout;
    }

    private void setTextPrices(int count) {
        txtPrice.setText("Valor unitário R$ "+mProduct.price);
        txtNumber.setText(count + "");
        txtTotal.setText("Total R$ " + ((count * mProduct.price) - (count * mPromotion.price)));
        txtDiscount.setText("Bônus -R$ " + count * mPromotion.price);
    }

    private void createCartIten() {
        CartItem cartItem = new CartItem();
        cartItem.quantity = count;
        cartItem.product = mProduct;
        cartItem.promotion = mPromotion;
        cartItem.productId = mProduct.objectId;
        cartItem.promotionId = mPromotion.objectId;

        getDialog().dismiss();
        MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
        activity.onReturnFromDialog(cartItem);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface MyDialogFragmentListener {
        public void onReturnFromDialog(CartItem cartItem);
    }


}

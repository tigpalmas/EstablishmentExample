package com.example.tiago.establishmentexample.shopFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogOrcamento;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.GenreViewHolder;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.utils.AppPreferenceTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeginShopFragment extends Fragment {
    private AppPreferenceTools mAppPreferenceTools;
    private EstablishmentService mTService;
    private MVP.PresenterProduct mPresenter;

    private Button txtRequest, btnTextPush;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getActivity().findViewById(R.id.main_view);
        view.setVisibility(View.GONE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_begin_shop, container, false);
        mAppPreferenceTools = new AppPreferenceTools(getContext());
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();
        txtRequest = (Button)layout.findViewById(R.id.btn_dialog);
        btnTextPush = (Button)layout.findViewById(R.id.btn_push);
        txtRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOrcamento fragment = new DialogOrcamento();
                FragmentTransaction fragmentTransaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fragment.show(fragmentTransaction, "dialog");
            }
        });

        btnTextPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        View view = getActivity().findViewById(R.id.main_view);
        view.setVisibility(View.VISIBLE);

    }


    private void sendMessage(){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();

        MessagePush push = new MessagePush();
        push.key = "tag1";
        push.value = mAppPreferenceTools.getOneSignalId();
        push.title = "Sua loja aqui";
        push.message = "Envie novidades para seus clientes";
        push.bigPictureURL = "http://beleza-moda.net/wp-content/gallery/roupas-femininas-2014/roupas-femininas-2014-5.jpg";

        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){
                    Log.i("teste", "sucesso");
                }else{
                    Log.i("teste", "erro");
                }
            }

            @Override
            public void onFailure(Call<MessagePush> call, Throwable t) {

            }
        });
    }
}

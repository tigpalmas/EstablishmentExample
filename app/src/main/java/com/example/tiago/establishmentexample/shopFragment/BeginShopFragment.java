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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogOrcamento;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.utils.AppPreferenceTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeginShopFragment extends Fragment {

    private TextView txtRequest;
    private TextView txtNotification;

    private AppPreferenceTools mAppPreferenceTools;
    private EstablishmentService mTService;

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
        txtRequest = (TextView)layout.findViewById(R.id.txt_request_orcamento);
        txtNotification = (TextView)layout.findViewById(R.id.txt_notificar);
        txtRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOrcamento fragment = new DialogOrcamento();
                FragmentTransaction fragmentTransaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fragment.show(fragmentTransaction, "dialog");
            }
        });

        txtNotification.setOnClickListener(new View.OnClickListener() {
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
        push.message = "Estamos com novidades na loja, venha aproveitar";
        push.bigPictureURL = "https://ph-cdn1.ecosweb.com.br/Web/posthaus/foto/moda-feminina/blusas-manga-longa/blusa-manga-longa-vazada-branca_176544_301_1.jpg";

        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){
                    Toast.makeText(getActivity(), "Notificação enviada com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("teste", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MessagePush> call, Throwable t) {

            }
        });
    }
}

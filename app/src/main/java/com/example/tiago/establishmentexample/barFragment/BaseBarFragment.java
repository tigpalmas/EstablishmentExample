package com.example.tiago.establishmentexample.barFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.domain.MessagePush;
import com.example.tiago.establishmentexample.network.EstablishmentProvider;
import com.example.tiago.establishmentexample.network.EstablishmentService;
import com.example.tiago.establishmentexample.product.MVP;
import com.example.tiago.establishmentexample.utils.AppPreferenceTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseBarFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerCasaDetalheAdapter viewPagerCasaDetalheAdapter;
    private Button btnPush;

    private AppPreferenceTools mAppPreferenceTools;
    private EstablishmentService mTService;
    private MVP.PresenterProduct mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_base_bar, container, false);
        mAppPreferenceTools = new AppPreferenceTools(getContext());
        tabLayout = (TabLayout) layout.findViewById(R.id.tabs);
        viewPager = (ViewPager) layout.findViewById(R.id.viewPager);
        btnPush = (Button) layout.findViewById(R.id.btn_orcamento);

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPagerCasaDetalheAdapter = new ViewPagerCasaDetalheAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(3);


        viewPagerCasaDetalheAdapter.addFragments(new BarFragment(), "Programação");
        viewPagerCasaDetalheAdapter.addFragments(new MapFragment(), "Localização");
        viewPagerCasaDetalheAdapter.addFragments(new FotosFragment(), "Comentários");

        viewPager.setAdapter(viewPagerCasaDetalheAdapter);
        tabLayout.setupWithViewPager(viewPager);
      tabLayout.getTabAt(0).setIcon(R.drawable.ic_calendar_red);
      tabLayout.getTabAt(1).setIcon(R.drawable.ic_local_red);
      tabLayout.getTabAt(2).setIcon(R.drawable.ic_coment_red);

    }


    private void sendMessage(){
        EstablishmentProvider provider  = new EstablishmentProvider();
        mTService = provider.getmService();

        MessagePush push = new MessagePush();
        push.key = "tag1";
        push.value = mAppPreferenceTools.getOneSignalId();
        push.title = "Seu Bar aqui";
        push.message = "Que tal uma cerveja grátis hoje? Envie notificaçãoes para todos os seus clientes,";

        Call<MessagePush> call = mTService.sendPushMessage(push);
        call.enqueue(new Callback<MessagePush>() {
            @Override
            public void onResponse(Call<MessagePush> call, Response<MessagePush> response) {
                if(response.isSuccess()){
                    Toast.makeText(getActivity(), "Notificação enviada com sucesso", Toast.LENGTH_SHORT).show();
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

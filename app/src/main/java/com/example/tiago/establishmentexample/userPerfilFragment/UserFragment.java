package com.example.tiago.establishmentexample.userPerfilFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.barFragment.BarFragment;
import com.example.tiago.establishmentexample.barFragment.FotosFragment;
import com.example.tiago.establishmentexample.barFragment.MapFragment;
import com.example.tiago.establishmentexample.barFragment.ViewPagerCasaDetalheAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerCasaDetalheAdapter viewPagerCasaDetalheAdapter;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =   inflater.inflate(R.layout.fragment_user, container, false);
        tabLayout = (TabLayout) layout.findViewById(R.id.tabs);
        viewPager = (ViewPager) layout.findViewById(R.id.viewPager);



        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPagerCasaDetalheAdapter = new ViewPagerCasaDetalheAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(3);


        viewPagerCasaDetalheAdapter.addFragments(new UserOrdersFragment(), "Seus Pedidos");
        viewPagerCasaDetalheAdapter.addFragments(new MapFragment(), "Endereço de Entrega");
        viewPagerCasaDetalheAdapter.addFragments(new FotosFragment(), "Meio de pagamento");

        viewPager.setAdapter(viewPagerCasaDetalheAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Seus Perfil");

    }

}

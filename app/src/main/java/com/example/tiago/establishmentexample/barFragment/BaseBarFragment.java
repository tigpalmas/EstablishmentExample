package com.example.tiago.establishmentexample.barFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiago.establishmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseBarFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerCasaDetalheAdapter viewPagerCasaDetalheAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_base_bar, container, false);
        tabLayout = (TabLayout) layout.findViewById(R.id.tabs);
        viewPager = (ViewPager) layout.findViewById(R.id.viewPager);

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
}

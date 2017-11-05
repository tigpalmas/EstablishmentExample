package com.example.tiago.establishmentexample.promotionShop;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.promotionalListRecycler.ItemAdapter;
import com.example.tiago.establishmentexample.promotionalListRecycler.MVPItens;
import com.example.tiago.establishmentexample.promotionalListRecycler.PresenterItens;
import com.example.tiago.establishmentexample.promotionalListRecycler.PromotioList;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionShopFragment extends Fragment implements MVPItens.ViewItens {
    private ViewPager viewPagerCovers;
    private TabLayout tabLayout;
    private ViewPagerCoversAdapter viewPagerCoversAdapter;
    private MVPItens.PresenterItens mPresenter;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mPresenter = new PresenterPromotionShop(getContext());
        mPresenter.setView(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_promotion_shop, container, false);
        viewPagerCovers = (ViewPager) layout.findViewById(R.id.vp_covers);
        tabLayout = (TabLayout) layout.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPagerCovers);


        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.retrieveItens();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Promoções");

    }

    @Override
    public void updateListaRecycler(List<Promotion> promotions) {
      /*  PromotionsFragmentListener activity = (PromotionsFragmentListener) getActivity();
        activity.onReturnListPromotions(promotions);*/

        viewPagerCoversAdapter = new ViewPagerCoversAdapter(getFragmentManager());
        for(Promotion cover: promotions){
            CoverFragment coverFragment = CoverFragment.novaInstancia(cover.productId.image.url);
            viewPagerCoversAdapter.addFragments(coverFragment, "");
        }
        viewPagerCovers.setAdapter(viewPagerCoversAdapter);




    }

    @Override
    public void showLoadProgress(boolean status, String message) {
       /* txtLoading.setText(message);
        if (status) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }*/
    }

    public interface PromotionsFragmentListener {
        public void onReturnListPromotions(List<Promotion> promotions);
    }
}

package com.example.tiago.establishmentexample.PerfilFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.barFragment.MapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private ImageView imgPerfil;
    private Toolbar mToolBar;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_perfil, container, false);
        imgPerfil = layout.findViewById(R.id.iv_perfil);

        MapFragment fragment1 = new MapFragment();
        loadFragmentNoBackStack(fragment1, "promotional");
      /*  ((AppCompatActivity) getActivity()).getSupportActionBar().hide();*/
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgPerfil);
        Glide.with(this).load(R.drawable.restaurant).into(imageViewTarget);

        return layout;
    }

    private void loadFragmentNoBackStack(Fragment fragment, String tag) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerMap, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }


}

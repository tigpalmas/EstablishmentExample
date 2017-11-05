package com.example.tiago.establishmentexample.promotionShop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoverFragment extends Fragment {
    public static final String EXTRA_COVER = "extra_cover";
    private String mCover;
    private ImageView imgCover;
    private ProgressBar pbCover;


    public static CoverFragment novaInstancia(String urlCover) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_COVER, urlCover);
        CoverFragment fragment = new CoverFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getSerializable(EXTRA_COVER) != null) {
            mCover = getArguments().getString(EXTRA_COVER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_cover, container, false);
        imgCover = (ImageView) layout.findViewById(R.id.img_cover);

        if (mCover != null) {
            Glide.with(getActivity()).load(mCover).into(imgCover);
        }
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* CoverDialog coverDialog =CoverDialog.novaInstancia(mCover);
                FragmentTransaction fragmentTransaction = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                coverDialog.show(fragmentTransaction, "DialogCover");*/
            }
        });

        return layout;
    }

}

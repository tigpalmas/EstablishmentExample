package com.example.tiago.establishmentexample.shopFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiago.establishmentexample.R;
import com.example.tiago.establishmentexample.diagoFragment.DialogOrcamento;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeginShopFragment extends Fragment {

    private TextView txtRequest;


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
        txtRequest = (TextView)layout.findViewById(R.id.txt_request_orcamento);
        txtRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOrcamento fragment = new DialogOrcamento();
                FragmentTransaction fragmentTransaction = ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                fragment.show(fragmentTransaction, "dialog");
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
}

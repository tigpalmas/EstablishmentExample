package com.example.tiago.establishmentexample.fragmentInformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiago.establishmentexample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class informationEnterpriseFragment extends Fragment {


    public informationEnterpriseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_enterprise, container, false);
    }

}

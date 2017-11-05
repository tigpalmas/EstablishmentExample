package com.example.tiago.establishmentexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tiago.establishmentexample.PerfilFragment.PerfilFragment;
import com.example.tiago.establishmentexample.barFragment.BaseBarFragment;

public class MainActivityBar extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bar);
        BaseBarFragment fragment = new BaseBarFragment();
        loadFragment(fragment, "bar");
    }

    private void loadFragment(Fragment fragment, String tag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag);

        fragmentTransaction.commitAllowingStateLoss();
    }

}

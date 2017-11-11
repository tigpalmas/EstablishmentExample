package com.example.tiago.establishmentexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tiago.establishmentexample.barFragment.BaseBarFragment;
import com.example.tiago.establishmentexample.weddingFragment.BaseWeddingFragment;

public class MainActivityWeeding extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pizza);
        BaseWeddingFragment fragment = new BaseWeddingFragment();
        loadFragment(fragment, "wedding");
    }

    private void loadFragment(Fragment fragment, String tag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag);

        fragmentTransaction.commitAllowingStateLoss();
    }

}

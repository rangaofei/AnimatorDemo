package com.example.administrator.animatordemo;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.animatordemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        fm = getSupportFragmentManager();
        Fragment f = new ValueAnimatorFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, f).commit();
        binding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onClickSimple(View view) {
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, new ValueAnimatorFragment()).commit();
        }

        public void onClickMulty(View view) {
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, new MultyFragment()).commit();
        }
    }
}

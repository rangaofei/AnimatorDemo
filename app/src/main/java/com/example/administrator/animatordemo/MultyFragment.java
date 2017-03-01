package com.example.administrator.animatordemo;


import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.animatordemo.databinding.FragmentMultyBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultyFragment extends Fragment {

    private FragmentMultyBinding binding;

    public MultyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_multy, container, false);
        binding.setPresenter(new Presenter());
        return binding.getRoot();
    }

    public class Presenter {
        public void onClickRotate(View view) {
            Log.d("aaa", "jiajai");
            ObjectAnimator animator=ObjectAnimator.ofFloat(binding.imageRotate,"rotation",0,360);
            animator.setDuration(1000);
            animator.start();
        }

        public void onClickChange(View view) {
//            ObjectAnimator animator=ObjectAnimator.ofFloat()
        }

        public void onClickComm(View view) {

        }
    }

}

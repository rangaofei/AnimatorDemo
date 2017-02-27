package com.example.administrator.animatordemo;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValueAnimatorFragment extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private Spinner spinner2;
    private Button startAnim;
    private ImageView imageView;
    private List<ObjectAnimator> animators = new ArrayList<>();
    private List<Interpolator> interpolators = new ArrayList<>();
    private LinearLayout animContainer;
    private float x, y;
    private AnimatorSet animatorSet;
    private AnimatorSet recAnimSet;
    private AnimatorSet alphaSet;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_value_animator, container, false);
        startAnim = (Button) view.findViewById(R.id.btn_startAnim);
        imageView = (ImageView) view.findViewById(R.id.image_anim);
        spinner = (Spinner) view.findViewById(R.id.fragment_spinner);
        spinner2 = (Spinner) view.findViewById(R.id.fragment_spinner2);
        animContainer = (LinearLayout) view.findViewById(R.id.ll_container);
        String[] items = getActivity().getResources().getStringArray(R.array.animator_names);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);
        String[] items2 = getActivity().getResources().getStringArray(R.array.interpolator_names);
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, items2);
        spinner2.setAdapter(spinnerAdapter2);
        x = getActivity().getWindowManager().getDefaultDisplay().getWidth() - 200;
        y = getActivity().getWindowManager().getDefaultDisplay().getHeight() - 800;
        Log.d("X+Y", x + "------" + y);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "x", 0, x);
        animatorX.setDuration(1000);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "y", 0, y);
        animatorY.setDuration(1000);
        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(imageView, "x", x, 0);
        animatorX1.setDuration(1000);
        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(imageView, "y", y, 0);
        animatorY1.setDuration(1000);
        animators.add(animatorX);
        animators.add(animatorY);
        animatorSet = new AnimatorSet();
        animatorSet.play(animatorX).with(animatorY);
        startAnim.setOnClickListener(this);
        recAnimSet = new AnimatorSet();
        recAnimSet.play(animatorX).before(animatorY);
        recAnimSet.play(animatorY).before(animatorX1);
        recAnimSet.play(animatorX1).before(animatorY1);

        alphaSet = new AnimatorSet();
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
        animatorAlpha.setDuration(1000);
        ObjectAnimator animatorAlpha1 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        animatorAlpha1.setDuration(1000);
        ObjectAnimator animatorAlpha2 = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
        animatorAlpha.setDuration(1000);
        ObjectAnimator animatorAlpha3 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        alphaSet.play(animatorX).with(animatorAlpha);
        alphaSet.play(animatorY).with(animatorAlpha1);
        alphaSet.play(animatorY).after(animatorX);
        alphaSet.play(animatorY).before(animatorX1);
        alphaSet.play(animatorX1).before(animatorY1);
        alphaSet.play(animatorX1).with(animatorAlpha2);
        alphaSet.play(animatorY1).with(animatorAlpha3);
        ImageViewWrapper i = new ImageViewWrapper(imageView);
        Property p = Property.of(ImageViewWrapper.class, Float.class, "width");
        ObjectAnimator animator = ObjectAnimator.ofFloat(i, p, 0, 100);
        animator.setDuration(1000);
        animators.add(animator);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(i, "height", 0, 100);
        animator1.setDuration(1000);
        animators.add(animator1);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 1);
        animator2.setDuration(1000);
        animators.add(animator2);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 1);
        animator2.setDuration(1000);
        animators.add(animator3);
        Path path=new Path();
        path.addCircle(200,200,100, Path.Direction.CW);
        ObjectAnimator animator4=ObjectAnimator.ofFloat(imageView,"X","Y",path);
        animator.setDuration(5000);
        animators.add(animator4);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startAnim:
                Log.d("aaa", "点击");
                if (spinner2.getSelectedItemPosition() == 0) {
                    for (Animator a : animators) {
                        a.setInterpolator(new AccelerateDecelerateInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 1) {
                    for (Animator a : animators) {
                        a.setInterpolator(new AccelerateInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 2) {
                    for (Animator a : animators) {
                        a.setInterpolator(new AnticipateInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 3) {
                    for (Animator a : animators) {
                        a.setInterpolator(new AnticipateOvershootInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 4) {
                    for (Animator a : animators) {
                        a.setInterpolator(new BounceInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 5) {
                    for (Animator a : animators) {
                        a.setInterpolator(new CycleInterpolator(2));
                    }
                }

                if (spinner2.getSelectedItemPosition() == 6) {
                    for (Animator a : animators) {
                        a.setInterpolator(new DecelerateInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 7) {
                    for (Animator a : animators) {
                        a.setInterpolator(new LinearInterpolator());
                    }
                }

                if (spinner2.getSelectedItemPosition() == 8) {
                    for (Animator a : animators) {
                        a.setInterpolator(new OvershootInterpolator());
                    }
                }

                if (spinner.getSelectedItemPosition() == 0) {
                    animators.get(0).start();
                } else if (spinner.getSelectedItemPosition() == 1) {
                    animators.get(1).start();
                } else if (spinner.getSelectedItemPosition() == 2) {
                    animatorSet.start();
                } else if (spinner.getSelectedItemPosition() == 3) {
                    recAnimSet.start();
                } else if (spinner.getSelectedItemPosition() == 4) {
                    alphaSet.start();
                } else if (spinner.getSelectedItemPosition() == 5) {
                    animators.get(2).start();
                } else if (spinner.getSelectedItemPosition() == 6) {
                    animators.get(3).start();
                } else if (spinner.getSelectedItemPosition() == 7) {
                    animators.get(4).start();
                } else if (spinner.getSelectedItemPosition() == 8) {
                    animators.get(5).start();
                }else if (spinner.getSelectedItemPosition() == 9) {
                    animators.get(6).start();
                }
                break;
        }
    }

}

package com.example.vishop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FRAMGIA\nguyen.van.mui on 10/03/2016.
 */
public abstract class BaseFragment extends Fragment {
    private View rootView;
    private boolean initialized;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract int getLayoutSource();
    public abstract void createView(View view);
    public abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutSource(), container, false);
        }

        createView(rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!initialized) {
            initialized = true;
            initView();
        }
    }
}

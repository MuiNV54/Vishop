package com.example.vishop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.vishop.MainActivity;
import com.example.vishop.R;
import com.example.vishop.utils.Const;

/**
 * Created by FRAMGIA\nguyen.van.mui on 10/03/2016.
 */
public class AgencyFragment extends BaseFragment {
    public static final String TAG = "AgencyFragment";

    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public int getLayoutSource() {
        return R.layout.fragment_agency;
    }

    @Override
    public void createView(View view) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setScreenTitle(getString(R.string.main_menu_agency));
        mainActivity.setCurrentFragment(TAG);
        mainActivity.setViewPagerForTab(null, Const.TAB_LAYOUT_FIXED);
    }
}

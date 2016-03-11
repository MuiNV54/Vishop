package com.example.vishop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.vishop.MainActivity;
import com.example.vishop.R;
import com.example.vishop.adapter.ViewPagerAdapter;
import com.example.vishop.utils.Const;

/**
 * Created by FRAMGIA\nguyen.van.mui on 10/03/2016.
 */
public class StaffFragment extends BaseFragment {
    public static final String TAG = "StaffFragment";

    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;

    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public int getLayoutSource() {
        return R.layout.fragment_staff;
    }

    @Override
    public void createView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.staff_view_pager);
        setupViewPager();
    }

    @Override
    public void initView() {
        setupViewPager();
    }

    private void setupViewPager() {
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new StaffListFragment(), getString(R.string.staff_list));
        pagerAdapter.addFragment(new StaffGroupFragment(), getString(R.string.staff_group));
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setScreenTitle(getString(R.string.main_menu_staff_manage));
        mainActivity.setCurrentFragment(TAG);
        mainActivity.setViewPagerForTab(viewPager, Const.TAB_LAYOUT_FIXED);
    }
}

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
public class ReportFragment extends BaseFragment {
    public static final String TAG = "ReportFragment";

    private MainActivity mainActivity;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public int getLayoutSource() {
        return R.layout.fragment_report;
    }

    @Override
    public void createView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.report_view_pager);
    }

    @Override
    public void initView() {
        setAdapter();
    }

    private void setAdapter() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ReportDayFragment(), getString(R.string.report_day));
        adapter.addFragment(new ReportSellFragment(), getString(R.string.report_sell));
        adapter.addFragment(new ReportProductFragment(), getString(R.string.report_product));
        adapter.addFragment(new ReportCustomerFragment(), getString(R.string.report_customer));
        adapter.addFragment(new ReportSupplierFragment(), getString(R.string.report_supplier));
        adapter.addFragment(new ReportStaffFragment(), getString(R.string.report_staff));
        adapter.addFragment(new ReportEconomicFragment(), getString(R.string.report_economic));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setScreenTitle(getString(R.string.main_menu_report));
        mainActivity.setCurrentFragment(TAG);
        mainActivity.setViewPagerForTab(viewPager, Const.TAB_LAYOUT_SCROLL);
    }
}

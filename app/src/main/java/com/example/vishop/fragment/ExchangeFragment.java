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
public class ExchangeFragment extends BaseFragment {
    public static final String TAG = "ExchangeFragment";

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
        return R.layout.fragment_exchange;
    }

    @Override
    public void createView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.exchange_view_pager);
    }

    @Override
    public void initView() {
        setupViewPager();
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ExchangeBillFragment(), getString(R.string.exchange_bill));
        adapter.addFragment(new ExchangeSellFragment(), getString(R.string.exchange_sell));
        adapter.addFragment(new ExchangeBuyFragment(), getString(R.string.exchange_buy));
        adapter.addFragment(new ExchangeReturnBuyFragment(), getString(R.string.exchange_return_buy));
        adapter.addFragment(new ExchangeReturnSellFragment(), getString(R.string.exchange_return_sell));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setScreenTitle(getString(R.string.main_menu_exchange));
        mainActivity.setCurrentFragment(TAG);
        mainActivity.setViewPagerForTab(viewPager, Const.TAB_LAYOUT_SCROLL);
    }
}

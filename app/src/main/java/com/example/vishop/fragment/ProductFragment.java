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
public class ProductFragment extends BaseFragment {
    public static final String TAG = "ProductFragment";

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
        return R.layout.fragment_product;
    }

    @Override
    public void createView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.product_view_pager);
    }

    @Override
    public void initView() {
        setViewPager();
    }

    private void setViewPager() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ProductLibraryFragment(), getString(R.string.product_library));
        adapter.addFragment(new ProductListFragment(), getString(R.string.product_list));
        adapter.addFragment(new ProductPriceFragment(), getString(R.string.product_setup_price));
        adapter.addFragment(new ProductTypeFragment(), getString(R.string.product_type));
        adapter.addFragment(new ProductUnitFragment(), getString(R.string.product_unit));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setScreenTitle(getString(R.string.main_menu_product_manage));
        mainActivity.setCurrentFragment(TAG);
        mainActivity.setViewPagerForTab(viewPager, Const.TAB_LAYOUT_SCROLL);
    }
}

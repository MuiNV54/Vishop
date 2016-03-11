package com.example.vishop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.vishop.adapter.MainMenuAdapter;
import com.example.vishop.fragment.AgencyFragment;
import com.example.vishop.fragment.ExchangeFragment;
import com.example.vishop.fragment.OverViewFragment;
import com.example.vishop.fragment.PartnerFragment;
import com.example.vishop.fragment.ProductFragment;
import com.example.vishop.fragment.ReportFragment;
import com.example.vishop.fragment.StaffFragment;
import com.example.vishop.utils.Const;

public class MainActivity extends AppCompatActivity {
    private int titles[] = {R.string.main_menu_overview, R.string.main_menu_exchange,
            R.string.main_menu_product_manage, R.string.main_menu_partner,
            R.string.main_menu_staff_manage, R.string.main_menu_agency, R.string.main_menu_report};
    private int icons[] = {R.mipmap.ic_home, R.mipmap.ic_business, R.mipmap.ic_calendar,
            R.mipmap.ic_forum, R.mipmap.ic_compose, R.mipmap.ic_featured, R.mipmap.ic_statistic};

    String NAME = "Dluffy Hades";
    String EMAIL = "nguyen.van.mui@framgia.com";
    private int PROFILE = R.mipmap.img_avatar;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MainMenuAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;
    private String currentFragment;

    private TabLayout mainTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.main_menu_open_drawer, R.string.main_menu_close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        adapter = new MainMenuAdapter(this, titles, icons, NAME, EMAIL, PROFILE);
        recyclerView.setAdapter(adapter);
        adapter.setItemClick(new MainMenuAdapter.MainMenuItemClick() {
            @Override
            public void onItemClick(int position) {
                onMenuItemClick(position);
            }
        });

        openBaseFragment(new OverViewFragment(), OverViewFragment.TAG);

        mainTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void setScreenTitle(String title) {
//        ((TextView) findViewById(R.id.screen_title_txt)).setText(title);
        toolbar.setTitle(title);
    }

    public void setCurrentFragment(String fragment) {
        currentFragment = fragment;
    }

    public void openBaseFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(title) != null) {
            fragment = fragmentManager.findFragmentByTag(title);
        }

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.main_container, fragment, title)
                .addToBackStack(title)
                .commit();
    }

    private void onMenuItemClick(int position) {
        adapter.setItemSelected(position);

        switch (position) {
            case Const.OVERVIEW:
                openBaseFragment(new OverViewFragment(), OverViewFragment.TAG);
                break;
            case Const.EXCHANGE:
                openBaseFragment(new ExchangeFragment(), ExchangeFragment.TAG);
                break;
            case Const.PRODUCT:
                openBaseFragment(new ProductFragment(), ProductFragment.TAG);
                break;
            case Const.PARTNER:
                openBaseFragment(new PartnerFragment(), PartnerFragment.TAG);
                break;
            case Const.STAFF:
                openBaseFragment(new StaffFragment(), StaffFragment.TAG);
                break;
            case Const.AGENCY:
                openBaseFragment(new AgencyFragment(), AgencyFragment.TAG);
                break;
            case Const.REPORT:
                openBaseFragment(new ReportFragment(), ReportFragment.TAG);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void backToHome() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        openBaseFragment(new OverViewFragment(), OverViewFragment.TAG);
    }

    public void setViewPagerForTab(ViewPager viewPager, int tabType) {
        if (viewPager == null) {
            mainTabLayout.setVisibility(View.GONE);
        } else {
            mainTabLayout.setupWithViewPager(viewPager);
            mainTabLayout.setVisibility(View.VISIBLE);

            if (tabType == Const.TAB_LAYOUT_FIXED) {
                mainTabLayout.setTabMode(TabLayout.MODE_FIXED);
            } else {
                mainTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            switch (currentFragment) {
                case OverViewFragment.TAG:
                    finish();
                    break;
                case AgencyFragment.TAG:
                    backToHome();
                    break;
                case ExchangeFragment.TAG:
                    backToHome();
                    break;
                case PartnerFragment.TAG:
                    backToHome();
                    break;
                case ProductFragment.TAG:
                    backToHome();
                    break;
                case ReportFragment.TAG:
                    backToHome();
                    break;
                case StaffFragment.TAG:
                    backToHome();
                    break;
                default:
                    super.onBackPressed();
                    break;
            }
        }
    }
}

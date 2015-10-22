package yan.com.taobaodb.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.Locale;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import yan.com.taobaodb.R;
import yan.com.taobaodb.activity.Fragment.ShowMerchandise;
import yan.com.taobaodb.activity.util.FragmentText;

/**
 * 我的商店活动
 * **/

public class MyShop extends ActionBarActivity implements MaterialTabListener{

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myshop_layout);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager );

        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        //在这里添加页面
        public Fragment getItem(int num) {
            switch (num){
                case 0:
                    return new ShowMerchandise();
                default:
                    return  new FragmentText();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "我的商品".toUpperCase(l);
                case 1:
                    return "添加商品".toUpperCase(l);
                case 2:
                    return "查看信息".toUpperCase(l);

            }

            return null;
        }

    }
}

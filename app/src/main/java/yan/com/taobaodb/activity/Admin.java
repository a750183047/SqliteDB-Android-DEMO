package yan.com.taobaodb.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import yan.com.taobaodb.R;
import yan.com.taobaodb.activity.Fragment.ChangePassword;
import yan.com.taobaodb.activity.Fragment.ShowAllAccount;
import yan.com.taobaodb.activity.Fragment.ShowAllMerchants;
import yan.com.taobaodb.activity.util.FragmentText;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Merchants;

/**
 * 管理员活动
 * Created by yan on 2015/11/1.
 */
public class Admin extends ActionBarActivity implements MaterialTabListener{
    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    private TBDataBase tbDataBase;
    private Merchants merchants;
    private TextView toobarTatel;
    private ShowAllMerchants showAllMerchandise;
    private ShowAllAccount showAllAccount;
    private ChangePassword changePassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);
        toobarTatel = (TextView) findViewById(R.id.toolbarLabel);

        //
        showAllMerchandise = new ShowAllMerchants();
        showAllAccount = new ShowAllAccount();
        changePassword = new ChangePassword();


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
                    return showAllMerchandise;
                case 1:
                    return  showAllAccount;
                case 3:
                    return new FragmentText();

                default:
                    return  changePassword;
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
                    return "所有商店".toUpperCase(l);
                case 1:
                    return "所有账号".toUpperCase(l);
                case 2:
                    return "修改密码".toUpperCase(l);

            }

            return null;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Admin","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
        Log.e("Admin","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Admin","onDestroy");
    }
}

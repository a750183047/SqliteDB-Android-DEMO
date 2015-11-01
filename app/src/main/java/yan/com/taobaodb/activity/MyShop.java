package yan.com.taobaodb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import yan.com.taobaodb.R;
import yan.com.taobaodb.activity.Fragment.AddGoods;
import yan.com.taobaodb.activity.Fragment.ShowMerchandise;
import yan.com.taobaodb.activity.Fragment.WatchNotes;
import yan.com.taobaodb.activity.util.FragmentText;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Merchants;

/**
 * 我的商店活动
 * **/

public class MyShop extends ActionBarActivity implements MaterialTabListener{

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    private TBDataBase tbDataBase;
    private Merchants  merchants;
    private TextView toobarTatel;
    private AddGoods addGoods; //= new AddGoods();
    private ShowMerchandise showMerchandise; //= new ShowMerchandise();
    private WatchNotes watchNotes;// = new WatchNotes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myshop_layout);

        addGoods = new AddGoods();
        showMerchandise = new ShowMerchandise();
        watchNotes = new WatchNotes();

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager );
        toobarTatel = (TextView) findViewById(R.id.toolbarLabel);

        tbDataBase = TBDataBase.getInstance(this);
        tbDataBase.openDB(this);

        Intent intent = getIntent();
        String MerchantsId = intent.getStringExtra("MerchantsId");
        addGoods.setMerchantsId(MerchantsId);
        showMerchandise.setMerchantsId(MerchantsId);
        watchNotes.setMerchantsId(MerchantsId);
        merchants = tbDataBase.loadAMerchant(MerchantsId);
        watchNotes.getMerchantsObj(merchants);
        Log.e("MyShop",merchants.getMId()+"  "+merchants.getMName()+"  "+merchants.getMLevel()+"  "+merchants.getMNote());
        if (merchants.getMName()!=null){
            Toast.makeText(MyShop.this,"有数据",Toast.LENGTH_SHORT).show();
            Log.e("MyShop", merchants.getMName() + "  " + merchants.getMId() + "  " + merchants.getMLevel());
            toobarTatel.setText(merchants.getMName());


        }else {
            Intent intent1 = new Intent(MyShop.this,CreateMerchants.class);
            intent1.putExtra("MerchantsId",MerchantsId);
            startActivity(intent1);
            Toast.makeText(MyShop.this,"没有数据",Toast.LENGTH_SHORT).show();
        }
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
                    return showMerchandise;
                case 1:
                    return  addGoods;
                case 3:
                    return new FragmentText();

                default:
                    return  watchNotes;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  tbDataBase.closeDB();
    }
}

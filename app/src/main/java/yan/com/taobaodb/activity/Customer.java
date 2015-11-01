package yan.com.taobaodb.activity;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;

import yan.com.taobaodb.R;
import yan.com.taobaodb.adapter.AllGoodsAdapter;
import yan.com.taobaodb.adapter.util.DividerItemDecoration;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Goods;

/**
 * 客户活动
 * Created by yan on 2015/10/22.
 */
public class Customer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Goods> mDataSet;
    private TBDataBase tbDataBase;
    private List<Goods> goodsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //显示标题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            ActionBar actionBar = getActionBar();
            if (actionBar != null){
                actionBar.setTitle("RecycleView");
            }
        }

        //获取数据库实例
        tbDataBase = TBDataBase.getInstance(this);
        //Layout 信息
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Item 属性
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        //recyclerView.setItemAnimator(new FadeInLeftAnimator());   // FadeInLeftAnimator 类没有找到

        //Adapter

        goodsList = tbDataBase.loadAllGoods();
        mDataSet = new ArrayList<Goods>();
        for (int i = 0;i<goodsList.size();i++){
            mDataSet.add(goodsList.get(i));
        }
        mAdapter = new AllGoodsAdapter(this, mDataSet);
        ((AllGoodsAdapter) mAdapter).setMode(Attributes.Mode.Single);
        recyclerView.setAdapter(mAdapter);


        //设置监听器
        recyclerView.setOnScrollListener(onScrollListener);

    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener(){
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.e("ListView", "onScrollStateChanged");
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // Could hide open views here if you wanted. //

        }
    };
}


















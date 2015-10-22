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
import java.util.Arrays;

import yan.com.taobaodb.R;
import yan.com.taobaodb.adapter.RecyclerViewAdapter;
import yan.com.taobaodb.adapter.util.DividerItemDecoration;

/**
 * RecyclerView Activity
 * Created by yan on 2015/10/22.
 */
public class RecycleView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDataSet;

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
        //Layout 信息
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Item 属性
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        //recyclerView.setItemAnimator(new FadeInLeftAnimator());   // FadeInLeftAnimator 类没有找到

        //Adapter
        String[] adapterData = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        mDataSet = new ArrayList<String>(Arrays.asList(adapterData));
        mAdapter = new RecyclerViewAdapter(this, mDataSet);
        ((RecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
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


















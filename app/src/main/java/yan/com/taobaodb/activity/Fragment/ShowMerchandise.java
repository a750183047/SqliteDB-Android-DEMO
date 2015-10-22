package yan.com.taobaodb.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.Arrays;

import yan.com.taobaodb.R;
import yan.com.taobaodb.adapter.RecyclerViewAdapter;
import yan.com.taobaodb.adapter.util.DividerItemDecoration;

/**
 * 展示商店商品信息
 * Created by yan on 2015/10/22.
 */
public class ShowMerchandise extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<String> mDataSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview,container,false);



        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        //Layout 信息
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Item 属性
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        //recyclerView.setItemAnimator(new FadeInLeftAnimator());   // FadeInLeftAnimator 类没有找到

        //Adapter
        String[] adapterData = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        mDataSet = new ArrayList<String>(Arrays.asList(adapterData));
        mAdapter = new RecyclerViewAdapter(getActivity(), mDataSet);
        ((RecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
        recyclerView.setAdapter(mAdapter);

        //设置监听器
        recyclerView.setOnScrollListener(onScrollListener);

        return view;
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



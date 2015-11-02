package yan.com.taobaodb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

import yan.com.taobaodb.R;


/**
 * RecyclerView 适配器
 * Created by yan on 2015/10/22.
 */
public class RecyclerViewAdapter extends RecyclerSwipeAdapter<RecyclerViewAdapter.SimpleViewHolder> {

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item,viewGroup,false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder simpleViewHolder, final int i) {

        String item = mDataset.get(i);
        simpleViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        simpleViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

//                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        /***
         * 侧滑单页删除按钮的实现和监听
         * **/
//        //双击监听
//        simpleViewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
//            @Override
//            public void onDoubleClick(SwipeLayout swipeLayout, boolean b) {
//                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //单击监听
//        simpleViewHolder.swipeLayout.setOnClickListener(new SwipeLayout.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toast.makeText(mContext, "Click", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //长按监听
//        simpleViewHolder.swipeLayout.setOnLongClickListener(new SwipeLayout.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(mContext, "Click" + i, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        simpleViewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mItemManger.removeShownLayouts(simpleViewHolder.swipeLayout);
//                mDataset.remove(i);
//                notifyItemRemoved(i);
//                mItemManger.closeAllItems();
//                Toast.makeText(v.getContext(), "Deleted " + simpleViewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
//
//            }
//        });



        /***
         * 左滑三个图标的实现和监听
         * */
        //左滑三个图标的实现和监听
        simpleViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        simpleViewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, simpleViewHolder.swipeLayout.findViewWithTag("Bottom2"));

        //第二个图标监听
        simpleViewHolder.swipeLayout.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "赞一个", Toast.LENGTH_SHORT).show();
            }
        });

        //第三个图标的监听
        simpleViewHolder.swipeLayout.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(mContext, "Trash Bin", Toast.LENGTH_SHORT).show();mItemManger.removeShownLayouts(simpleViewHolder.swipeLayout);
                mDataset.remove(i);
                notifyItemRemoved(i);
                mItemManger.closeAllItems();
               Toast.makeText(v.getContext(), "Deleted " + simpleViewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        //第一个图标的监听
        simpleViewHolder.swipeLayout.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Magnifier", Toast.LENGTH_SHORT).show();
            }
        });

        //这里实现了列表的监听
        simpleViewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Click on surface"+i, Toast.LENGTH_SHORT).show();
            }
        });



        //加载数据
        TextView textView = (TextView) simpleViewHolder.swipeLayout.findViewById(R.id.magnifier);
        textView.setText(item);

        simpleViewHolder.textViewPos.setText((i + 1) + ".");
        simpleViewHolder.textViewData.setText(item);
        mItemManger.bindView(simpleViewHolder.itemView, i);

    }

    //得到数据的大小
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    //加载数据的类
    public static class SimpleViewHolder extends RecyclerView.ViewHolder{

        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        Button buttonDelete;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            textViewPos = (TextView) itemView.findViewById(R.id.position);
            textViewData = (TextView) itemView.findViewById(R.id.text_data);
         //   buttonDelete = (Button) itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(v.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private Context mContext;
    private ArrayList<String> mDataset;    //要写入表中的数据在这里加载

    public RecyclerViewAdapter(Context context,ArrayList<String> objects){
        this.mContext = context;
        this.mDataset = objects;
    }
}












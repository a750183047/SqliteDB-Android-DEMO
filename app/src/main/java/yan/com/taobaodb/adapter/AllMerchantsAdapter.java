package yan.com.taobaodb.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

import yan.com.taobaodb.R;
import yan.com.taobaodb.activity.Admin;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Merchants;

/**
 * 所有商店列表适配器
 * Created by yan on 2015/11/1.
 */
public class AllMerchantsAdapter extends RecyclerSwipeAdapter<AllMerchantsAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<Merchants> mDataset;
    private TBDataBase tbDataBase = TBDataBase.getInstance(mContext);
    private int level;



    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_merchants_list_item,viewGroup,false);
        return new AllMerchantsAdapter.SimpleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder simpleViewHolder, final int i) {

        //读取数据
        final String name = mDataset.get(i).getMName();
        level = mDataset.get(i).getMLevel();
        final String mid = mDataset.get(i).getMId();
        String merchantsUid = mDataset.get(i).getMId();



        //设置模式等
        simpleViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        simpleViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });


        /***
         * 侧滑单页删除按钮的实现和监听
         * **/
        //双击监听
        simpleViewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout swipeLayout, boolean b) {
                //创建一个弹窗 警告是否要增加等级
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("确认要减少店铺的等级吗");
                builder.setMessage("你确认要减少名为 " + name + " 的商店等级吗？");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level = level - 1;
                        tbDataBase.setMLevel(mid,level);
                        Intent intent = new Intent(mContext, Admin.class);
                        mContext.startActivity(intent);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        //单击监听
        simpleViewHolder.swipeLayout.setOnClickListener(new SwipeLayout.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mContext, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        //长按监听
        simpleViewHolder.swipeLayout.setOnLongClickListener(new SwipeLayout.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //创建一个弹窗 警告是否要增加等级
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("确认要增加店铺的等级吗");
                builder.setMessage("你确认要增加名为 " + name + " 的商店等级吗？");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level = level+1;
                        tbDataBase.setMLevel(mid,level);
                        Intent intent = new Intent(mContext, Admin.class);
                        mContext.startActivity(intent);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });
        //删除按钮的监听
        simpleViewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(simpleViewHolder.swipeLayout);
                mDataset.remove(i);
                notifyItemRemoved(i);
                mItemManger.closeAllItems();
                tbDataBase.setMNotes(mid,"您的店铺被管理员屏蔽了，请联系管理员修改");


            }
        });

        /***
         * 左滑三个图标的实现和监听
         * *
        //左滑三个图标的实现和监听
        simpleViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        simpleViewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, simpleViewHolder.swipeLayout.findViewWithTag("Bottom2"));

        //第二个图标监听
        simpleViewHolder.swipeLayout.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleViewHolder.goodsPraise.setImageResource(R.drawable.starall);
                praise = praise +1;
                tbDataBase.setGpraise(goodsId,praise);
                Toast.makeText(mContext, "Star", Toast.LENGTH_SHORT).show();
                simpleViewHolder.swipeLayout.findViewById(R.id.star).setEnabled(false);
            }
        });

        //第三个图标的监听
        simpleViewHolder.swipeLayout.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //创建一个弹窗 警告是否要删除数据
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("确认删除商品吗");
                builder.setMessage("你确认要删除名为 " + goodsName + "的商品吗？该操作无法恢复！");
                builder.setCancelable(false);
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDatas.remove(i);
                        notifyItemRemoved(i);
                        mItemManger.closeAllItems();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
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
                Toast.makeText(mContext, "Click on surface" + i, Toast.LENGTH_SHORT).show();
            }
        });
*/


        //加载数据

        simpleViewHolder.merchantsName.setText(name);
        simpleViewHolder.merchantsLevel.setText(String.valueOf(level));
        simpleViewHolder.merchantsUid.setText(merchantsUid);


        mItemManger.bindView(simpleViewHolder.itemView, i);

    }


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

        TextView merchantsName;
        TextView merchantsLevel;
        TextView merchantsUid;
        Button buttonDelete;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);

            merchantsName = (TextView) itemView.findViewById(R.id.position);
            merchantsLevel = (TextView) itemView.findViewById(R.id.all_merchants_level);
            buttonDelete = (Button) itemView.findViewById(R.id.delete);
            merchantsUid = (TextView) itemView.findViewById(R.id.merchants_uid);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });

        }
    }
    public AllMerchantsAdapter(Context context,ArrayList<Merchants> merchants){
        this.mContext = context;
        this.mDataset = merchants;
    }
}

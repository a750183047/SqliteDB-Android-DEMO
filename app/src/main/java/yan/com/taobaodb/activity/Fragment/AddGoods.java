package yan.com.taobaodb.activity.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yan.com.taobaodb.R;
import yan.com.taobaodb.activity.MyShop;
import yan.com.taobaodb.activity.TakePhoto;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Goods;

/**
 * 添加商品信息
 * Created by yan on 2015/10/30.
 */
public class AddGoods extends Fragment implements TextWatcher{

    private Button takePhoto;
    private Button createGoods;
    private ImageView picture;
    private Uri imageUri;
    private Bitmap goodsBitmap;
    private TextView pictureText;
    private String MerchantsId;   //商店的Id
    private EditText goodsNameInput;
    private EditText goodsPriceInput;
    private EditText goodsIntroductionInput;
    private TBDataBase tbDataBase;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_goos_layout, container, false);

        //控件实例化
        tbDataBase = TBDataBase.getInstance(getActivity());
        tbDataBase.openDB(getActivity());

        takePhoto = (Button) view.findViewById(R.id.button_take_photo);
        picture = (ImageView) view.findViewById(R.id.goods_picture);
        pictureText = (TextView) view.findViewById(R.id.goods_picture_text);
        createGoods = (Button) view.findViewById(R.id.button_create_goods);
        goodsNameInput = (EditText) view.findViewById(R.id.goods_name_input);
        goodsPriceInput = (EditText) view.findViewById(R.id.goods_price_input);
        goodsIntroductionInput = (EditText) view.findViewById(R.id.goods_introduction_input);

        //拍照按键监听

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picture.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getActivity(), TakePhoto.class);   //打开拍照活动
                intent.putExtra("MerchantsId", MerchantsId);
                startActivity(intent);
            }
        });

        pictureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsBitmap = getLoacalBitmap("/storage/sdcard0/output_photo.jpg");   // 获取本地图片
                picture.setImageBitmap(goodsBitmap);    //显示获取到的图片
                createGoods.setEnabled(true);
            }
        });

        createGoods.setOnClickListener(new View.OnClickListener() {    //储存商品
            @Override
            public void onClick(View v) {
                final ByteArrayOutputStream goodsPicture = new ByteArrayOutputStream();
                goodsBitmap.compress(Bitmap.CompressFormat.JPEG, 100, goodsPicture);   //把位图文件转换成数据流
                Goods goods = new Goods();
                goods.setMId(MerchantsId);
                goods.setGName(goodsNameInput.getText().toString());
                goods.setGPrice(goodsPriceInput.getText().toString());
                goods.setGIntroduction(goodsIntroductionInput.getText().toString());
                goods.setGPicture(goodsPicture.toByteArray());
                goods.setGPraise(0);
                tbDataBase.saveGoods(goods);
                Toast.makeText(getActivity(),"商品创建成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyShop.class);
                intent.putExtra("MerchantsId",MerchantsId);
                startActivity(intent);
                createGoods.setEnabled(false);
        //        tbDataBase.closeDB();
                getActivity().finish();

            }
        });

        return view;
    }

    public void setMerchantsId(String MerchantsId) {
        this.MerchantsId = MerchantsId;
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (TextUtils.isEmpty(goodsNameInput.getText().toString())
                &&TextUtils.isEmpty(goodsPriceInput.getText().toString())
                &&TextUtils.isEmpty(goodsIntroductionInput.getText().toString())){
            createGoods.setEnabled(false);
        }else {
            createGoods.setEnabled(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ShowMerchandise", "执行了关闭数据库的方法 add");
      //  tbDataBase.closeDB();
    }
}
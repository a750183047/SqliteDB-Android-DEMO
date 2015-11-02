package yan.com.taobaodb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import it.neokree.materialtabs.MaterialTabHost;
import yan.com.taobaodb.R;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Merchants;

/**
 * 创建商店活动
 * Created by yan on 2015/10/28.
 */
public class CreateMerchants extends Activity {
    MaterialTabHost tabHost;

    private EditText merchantsName;
    private String merchantsId;
    private TBDataBase tbDataBase;
    private Button createMerchants;
    private Merchants merchants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_merchant_layout);
        merchantsName = (EditText) findViewById(R.id.merchants_name);
        createMerchants = (Button) findViewById(R.id.button_create_merchants);
        merchants = new Merchants();

        //获取数据库实例
        tbDataBase = TBDataBase.getInstance(this);
        //获得传递过来的数据
        final Intent intent = getIntent();
        merchantsId = intent.getStringExtra("MerchantsId");

        //设置输入框监听
        merchantsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (TextUtils.isEmpty(merchantsName.getText().toString())){
                    createMerchants.setEnabled(false);
                }else {
                    createMerchants.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //储存数据
        createMerchants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merchants.setMId(merchantsId);
                merchants.setMName(merchantsName.getText().toString());
                merchants.setMLevel(1);
                merchants.setMNote("无");
                tbDataBase.saveMerchants(merchants);
                Intent intent1 = new Intent(CreateMerchants.this,MyShop.class);
                intent1.putExtra("MerchantsId",merchants.getMId());
                startActivity(intent1);
                finish();
            }
        });




    }
}

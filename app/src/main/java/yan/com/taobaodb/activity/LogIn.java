package yan.com.taobaodb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import yan.com.taobaodb.R;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Account;

/**
 * 登陆活动
 * Created by yan on 2015/10/26.
 */
public class LogIn extends Activity implements TextWatcher{

    private Account account;
    private TBDataBase tbDataBase;
    private String accountInput;
    private String passwordInput;
    private EditText account1;
    private EditText password;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //获取实例
         account1 = (EditText) findViewById(R.id.edit_account_id);
         password = (EditText) findViewById(R.id.edit_account_password);
        CheckBox rememberPassword = (CheckBox) findViewById(R.id.check_remember_password);
        Button signUp = (Button) findViewById(R.id.button_account_sign_up);
        logIn = (Button) findViewById(R.id.button_account_log_in);
        //获取完毕

        //检查是否选择记住密码



        //获取数据库实例
        tbDataBase = TBDataBase.getInstance(this);
        //校验账号是否存在
        //设置输入框监听
        password.addTextChangedListener(this);
        account1.addTextChangedListener(this);
        //登陆按钮监听
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入数据
                accountInput = account1.getText().toString();
                passwordInput = password.getText().toString();
                account = tbDataBase.checkPassword(accountInput);
                if (account != null && account.getAccountClass()!=0) {
                    Log.e("LogIn", account.getUId() + "   " + account.getPassword() + "  " + account.getAccountClass());
                    if (account.getPassword().equals(passwordInput)) {
                        switch (account.getAccountClass()) {
                            case Account.ACCOUNT_CLASS_CONSUMER:
                                Toast.makeText(LogIn.this, "成功", Toast.LENGTH_SHORT).show();
                                Intent intentCustomer = new Intent(LogIn.this,Customer.class);
                                startActivity(intentCustomer);
                                break;
                            case Account.ACCOUNT_CLASS_MERCHANTS:
                                Intent intent = new Intent(LogIn.this,MyShop.class);
                                intent.putExtra("MerchantsId",account.getUId());
                                startActivity(intent);
                                break;
                            case Account.ACCOUNT_CLASS_ADMIN:

                            default:
                                break;
                        }
                    } else {
                        Toast.makeText(LogIn.this, "密码错误，请检查输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogIn.this, "账号不存在，请检查或注册新账号", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //设置输入框监听
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(account1.getText().toString())
                &&TextUtils.isEmpty(password.getText().toString())){
            logIn.setEnabled(false);
        }else {
            logIn.setEnabled(true);
        }
    }
}

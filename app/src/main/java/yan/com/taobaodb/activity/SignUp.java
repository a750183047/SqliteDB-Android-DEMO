package yan.com.taobaodb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import yan.com.taobaodb.R;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Account;

/**
 * 注册活动页面
 * Created by yan on 2015/10/27.
 */
public class SignUp extends Activity implements TextWatcher{



    private static final String ADMINACCOUNTKEY = "SONGADMINKEY";
    private Spinner chooseAccountClass;

    private TBDataBase tbDataBase;



    private int whatItemSelect;
    private TextView adminAccountKey;
    private EditText adminAccountKeyInput;//管理员码
    private EditText accountIdInput;    //输入的账号
    private EditText accountPasswordInput;//输入的密码
    private EditText chickPasswordInput;//输入的确认密码
    private Button chickAccountSignUp;//确认注册按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        tbDataBase = TBDataBase.getInstance(this);

        //实例化各个控件
        adminAccountKey = (TextView) findViewById(R.id.admin_account_key);
        adminAccountKeyInput = (EditText) findViewById(R.id.admin_account_key_input);
        accountIdInput = (EditText) findViewById(R.id.sign_up_account_id);
        accountPasswordInput = (EditText) findViewById(R.id.sign_up_account_password);
        chickPasswordInput = (EditText) findViewById(R.id.sign_up_account_chick_password);
        chickAccountSignUp = (Button) findViewById(R.id.button_chick_account_sign_up);

        //初始化各个输入框
        adminAccountKeyInput.addTextChangedListener(this);
        accountIdInput.addTextChangedListener(this);
        accountPasswordInput.addTextChangedListener(this);
        chickPasswordInput.addTextChangedListener(this);
        //默认不显示管理员码输入框
        adminAccountKey.setVisibility(View.GONE);
        adminAccountKeyInput.setVisibility(View.GONE);

        chooseAccountClass = (Spinner) findViewById(R.id.choose_account_class);
        chooseAccountClass.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Account.ACCOUNT_CLASS));
        chooseAccountClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Sign", ":" + Account.ACCOUNT_CLASS[position]);
                switch (position) {
                    case 0:
                        whatItemSelect = Account.ACCOUNT_CLASS_CONSUMER;    //标识账号类型为消费者
                        adminAccountKey.setVisibility(View.GONE);
                        adminAccountKeyInput.setVisibility(View.GONE);
                        break;
                    case 1:
                        whatItemSelect = Account.ACCOUNT_CLASS_MERCHANTS;   //标识账号类型为商户
                        adminAccountKey.setVisibility(View.GONE);
                        adminAccountKeyInput.setVisibility(View.GONE);
                        break;
                    case 2:
                        whatItemSelect = Account.ACCOUNT_CLASS_ADMIN;      //标识账号类型为管理员
                        adminAccountKey.setVisibility(View.VISIBLE);
                        adminAccountKeyInput.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chickAccountSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountIdInputText = accountIdInput.getText().toString();
                   switch (whatItemSelect){
                       case Account.ACCOUNT_CLASS_CONSUMER :
                            if (!(accountIdInputText.equals(""))){
                                if (!(accountPasswordInput.getText().toString().equals(""))){
                                    if (accountPasswordInput.getText().toString().equals(chickPasswordInput.getText().toString())){
                                        if (tbDataBase.isAccountOnly(accountIdInputText)){
                                            Account account = new Account();
                                            account.setUId(accountIdInputText);
                                            account.setPassword(accountPasswordInput.getText().toString());
                                            account.setAccountClass(Account.ACCOUNT_CLASS_CONSUMER);
                                            tbDataBase.saveAccount(account);
                                            Toast.makeText(SignUp.this,"账号创建成功",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUp.this,LogIn.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(SignUp.this,"账号已经被注册，请更换账号",Toast.LENGTH_SHORT).show();
                                        }

                                    }else {
                                        Toast.makeText(SignUp.this,"两次密码不相同，请检查",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(SignUp.this,"密码为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(SignUp.this,"账号为空",Toast.LENGTH_SHORT).show();
                            }
                       break;
                       case Account.ACCOUNT_CLASS_MERCHANTS:
                           if (!(accountIdInputText.equals(""))){
                               if (!(accountPasswordInput.getText().toString().equals(""))){
                                   if (accountPasswordInput.getText().toString().equals(chickPasswordInput.getText().toString())){
                                       if (accountPasswordInput.getText().toString().equals(chickPasswordInput.getText().toString())){
                                           Account account = new Account();
                                           account.setUId(accountIdInputText);
                                           account.setPassword(accountPasswordInput.getText().toString());
                                           account.setAccountClass(Account.ACCOUNT_CLASS_MERCHANTS);
                                           tbDataBase.saveAccount(account);
                                           Toast.makeText(SignUp.this,"账号创建成功",Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(SignUp.this,LogIn.class);
                                           startActivity(intent);
                                           finish();
                                       }else {
                                           Toast.makeText(SignUp.this,"账号已经被注册，请更换账号",Toast.LENGTH_SHORT).show();
                                       }

                                   }else {
                                       Toast.makeText(SignUp.this,"两次密码不相同，请检查",Toast.LENGTH_SHORT).show();
                                   }
                               }else {
                                   Toast.makeText(SignUp.this,"密码为空",Toast.LENGTH_SHORT).show();
                               }
                           }else {
                               Toast.makeText(SignUp.this,"账号为空",Toast.LENGTH_SHORT).show();
                           }
                       break;
                       case Account.ACCOUNT_CLASS_ADMIN:
                           if (!(accountIdInputText.equals(""))){
                               if (!(accountPasswordInput.getText().toString().equals(""))){
                                   if (accountPasswordInput.getText().toString().equals(chickPasswordInput.getText().toString())){
                                       if (adminAccountKeyInput.getText().toString().equals(ADMINACCOUNTKEY)){
                                           if (accountPasswordInput.getText().toString().equals(chickPasswordInput.getText().toString())){
                                               Account account = new Account();
                                               account.setUId(accountIdInputText);
                                               account.setPassword(accountPasswordInput.getText().toString());
                                               account.setAccountClass(Account.ACCOUNT_CLASS_ADMIN);
                                               tbDataBase.saveAccount(account);
                                               Toast.makeText(SignUp.this,"账号创建成功",Toast.LENGTH_SHORT).show();
                                               Intent intent = new Intent(SignUp.this,LogIn.class);
                                               startActivity(intent);
                                               finish();
                                           }else {
                                               Toast.makeText(SignUp.this,"账号已经被注册，请更换账号",Toast.LENGTH_SHORT).show();
                                           }

                                       }else{
                                           Toast.makeText(SignUp.this,"管理员码错误",Toast.LENGTH_SHORT).show();
                                       }
                                   }else {
                                       Toast.makeText(SignUp.this,"两次密码不相同，请检查",Toast.LENGTH_SHORT).show();
                                   }
                               }else {
                                   Toast.makeText(SignUp.this,"密码为空",Toast.LENGTH_SHORT).show();
                               }
                           }else {
                               Toast.makeText(SignUp.this,"账号为空",Toast.LENGTH_SHORT).show();
                           }
                           break;
                    }

            } //消费者账号

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //输入框监听
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (TextUtils.isEmpty(accountIdInput.getText().toString())
                &&TextUtils.isEmpty(accountPasswordInput.getText().toString())
                &&TextUtils.isEmpty(chickPasswordInput.getText().toString())){
            chickAccountSignUp.setEnabled(false);
        }else {
            chickAccountSignUp.setEnabled(true);
        }
    }
}

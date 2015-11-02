package yan.com.taobaodb.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import yan.com.taobaodb.R;
import yan.com.taobaodb.databsse.TBDataBase;

/**
 * 修改密码
 * Created by yan on 2015/11/2.
 */
public class ChangePassword extends Fragment{

    private EditText accountUid;
    private EditText accountPassword;
    private EditText accountPasswordChick;
    private EditText adminKey;
    private TBDataBase tbDataBase;
    private Button changePassword;
    private static final String ADMINKEY = "CHANGE";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password,container,false);

        //获取各个控件的实例
        accountUid = (EditText) view.findViewById(R.id.sign_up_account_id);
        accountPassword = (EditText) view.findViewById(R.id.sign_up_account_password);
        accountPasswordChick = (EditText) view.findViewById(R.id.sign_up_account_chick_password);
        adminKey = (EditText) view.findViewById(R.id.admin_account_key_input);
        changePassword = (Button) view.findViewById(R.id.button_chick_account_sign_up);

        tbDataBase = TBDataBase.getInstance(getActivity());



        changePassword.setEnabled(true);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(accountUid.getText().toString().equals(""))){
                    if (!(accountPassword.getText().toString().equals(""))){
                        if (accountPasswordChick.getText().toString().equals(accountPassword.getText().toString())){
                            if (adminKey.getText().toString().equals(ADMINKEY)){
                                if (!tbDataBase.isAccountOnly(accountUid.getText().toString())){
                                      //这里写逻辑
                                    tbDataBase.updataPassword(accountUid.getText().toString(),accountPassword.getText().toString());
                                    Toast.makeText(getActivity(),"密码修改成功",Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(getActivity(),"账号不存在，请检查",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(),"权限码错误，请检查",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(),"两次密码不相同，请检查",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),"密码为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(),"账号为空",Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}

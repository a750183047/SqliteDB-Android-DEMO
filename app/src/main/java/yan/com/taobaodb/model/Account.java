package yan.com.taobaodb.model;

/**
 * 账户实体类
 * Created by yan on 2015/10/23.
 */
public class Account  {

    public static final int ACCOUNT_CLASS_CONSUMER = 1;  //消费者账号标识
    public static final int ACCOUNT_CLASS_MERCHANTS = 2;  //商户账号标识
    public static final int ACCOUNT_CLASS_ADMIN = 3;     //管理员账号标识
    public static final String[] ACCOUNT_CLASS= new String[]{"消费者","商户","管理员"};
    private String UId;
    private String Password;
    private int Class;

    public void setUId(String UId){
        this.UId = UId;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public void setAccountClass(int Class){
        this.Class = Class;
    }
    public String getUId(){
        return UId;
    }
    public int getAccountClass(){
        return Class;
    }
    public String getPassword(){
        return Password;
    }

    public Account(){

    }


}

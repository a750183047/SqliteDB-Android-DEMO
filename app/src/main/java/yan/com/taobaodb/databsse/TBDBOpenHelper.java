package yan.com.taobaodb.databsse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库管理类
 * Created by yan on 2015/10/22.
 */
public class TBDBOpenHelper extends SQLiteOpenHelper {


    /**
     * 账号表创建语句 account
     * **/
    public static final String CREATE_ACCOUNT = "create table Account ("   //
            +"Id integer primary key autoincrement,"                       //自增主键
            +"UId text,"                                                    // 账户ID
            +"Password text,"                                               // 账户密码
            +"Class integer"
            +")";                                                  //账户类型

    /**
     * 商户表创建语句 merchants
     * */
    public static final String CREATE_MERCHANTS = "create table Merchants ("
            +"MId text primary key,"                                        //商户ID
            +"MName text,"                                                  //商户名
            +"MLevel integer,"                                                  //商户等级
            +"MNote text"                                                    //备注信息
            +")";

    /**
     *商品表创建语句 goods
     **/
    public static final String CREATE_GOODS = "create table Goods ("
            +"GId integer primary key autoincrement,"                    //商品Id,自增主键
            +"MId text,"                                                //商品所属商户Id
            +"GName text,"                                              //商品名
            +"GPrice text,"                                             //商品价格
            +"GIntroduction text,"                                      //商品详情
            +"GPraise int,"                                             //商品赞数
            +"GPicture blob"                                            //商品图片
            +")";
    /**
     * 商家和商品的联系表 Contact
     *
    public static final String CREATE_CONTACT = "create table Contact ("
            +"MId text,"
            +"GId text,"
            +"primary key(MId,GId)"
            +")";
    */
    public TBDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ACCOUNT);
        db.execSQL(CREATE_MERCHANTS);
        db.execSQL(CREATE_GOODS);
      //  db.execSQL(CREATE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

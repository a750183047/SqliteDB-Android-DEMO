package yan.com.taobaodb.databsse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import yan.com.taobaodb.model.Account;
import yan.com.taobaodb.model.Goods;
import yan.com.taobaodb.model.Merchants;

/**
 * 数据库操作类
 * Created by yan on 2015/10/23.
 */
public class TBDataBase  {
    /**
     * 数据库名
     * */
    public static final String DB_NAME = "TB_BD.db";
    /**
     * 数据库版本信息
     * */
    public static final int VERSION = 1;
    private static TBDataBase tbDataBase;
    private SQLiteDatabase db;
    /**
     * 将构造方法私有化
     * */
    public TBDataBase(Context context){
        TBDBOpenHelper tbdbOpenHelper = new TBDBOpenHelper(context,DB_NAME,null,VERSION);
        db = tbdbOpenHelper.getWritableDatabase();
    }
    /**
     * 获取数据库实例
     * */
    public synchronized static TBDataBase getInstance(Context context){
        if (tbDataBase == null){
            tbDataBase = new TBDataBase(context);
        }
        return tbDataBase;
    }
    /**
     * 将账号实例储存在数据库中
     * **/
    public void saveAccount(Account account){
        if (account != null){
            ContentValues values = new ContentValues();
            values.put("UId",account.getUId());
            values.put("Password",account.getPassword());
            values.put("Class", account.getAccountClass());
            db.insert("Account",null,values);
        }
    }

    /**
     * 获取所有账号的列表
     * **/
    public List<Account> loadAllAccount(){
        List<Account> list = new ArrayList<Account>();
        Cursor cursor = db.query("Account",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Account account = new Account();
                account.setUId(cursor.getString(cursor.getColumnIndex("UId")));
                account.setAccountClass(cursor.getInt(cursor.getColumnIndex("Class")));
                list.add(account);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /**
     * 删除一个账号信息
     * */
    public void deleteAAccount(String UId){

        db.delete("Account", "UId = ?", new String[]{UId});
    }
    /**
     * 从数据库中验证账号密码和类型
     * */
    public Account checkPassword(String UId){
        Account account = new Account();
        Cursor cursor = db.query("Account",null,"UId = ?",   //查询 列 Password where UId = UId
                new String[]{UId},null,null,null);
      //  if (cursor.getCount() != 1){                                       //判断是不是只有一个用户
      //      return null;
      //  }else
            if (cursor.moveToFirst()){
                do {
                    account.setUId(UId);
                    account.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
                    account.setAccountClass(cursor.getInt(cursor.getColumnIndex("Class")));
                }while(cursor.moveToNext());

        }
        return account;
    }
    /**
     * 检查账号是否唯一
     * */
    public boolean isAccountOnly(String UId){
        Cursor cursor = db.query("Account",null,"UId = ?",   //查询  where UId = UId
                new String[]{UId},null,null,null);
        Account account = new Account();
        if (cursor.moveToFirst()){
            account.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
        }
        cursor.close();
        return !(account.getPassword() != null && !(account.getPassword().equals("")));

    }
    /**
     * 更新密码
     * */
    public void updataPassword(String UId,String Password){
        ContentValues values = new ContentValues();
        values.put("Password",Password);
        db.update("Account",values,"UId = ?",new String[]{UId});
    }
    /**
     * 将商户实例储存在数据库中
     * */
    public void saveMerchants(Merchants merchants){
        if (merchants != null){
            ContentValues values = new ContentValues();
            values.put("MId",merchants.getMId());
            values.put("MName",merchants.getMName());
            values.put("MLevel",merchants.getMLevel());
            values.put("MNote",merchants.getMNote());
            db.insert("Merchants",null,values);
        }
    }
    /**
     * 获取商户列表
     * */
    public List<Merchants> loadMerchants(){
        List<Merchants> list = new ArrayList<Merchants>();
        Cursor cursor = db.query("Merchants",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Merchants merchants = new Merchants();
                merchants.setMId(cursor.getString(cursor.getColumnIndex("MId")));
                merchants.setMName(cursor.getString(cursor.getColumnIndex("MName")));
                merchants.setMLevel(cursor.getInt(cursor.getColumnIndex("MLevel")));
                merchants.setMNote(cursor.getString(cursor.getColumnIndex("MNote")));
                list.add(merchants);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /**
     * 获取某一商户信息
     * */
    public Merchants loadAMerchant(String MId){
        Merchants merchants = new Merchants();
        Cursor cursor = db.query("Merchants",null,"MId = ?",new String[]{MId},null,null,null);
        if (cursor.moveToFirst()){
            merchants.setMId(MId);
            merchants.setMName(cursor.getString(cursor.getColumnIndex("MName")));
            merchants.setMLevel(cursor.getInt(cursor.getColumnIndex("MLevel")));
            merchants.setMNote(cursor.getString(cursor.getColumnIndex("MNote")));
        }
        cursor.close();
        return merchants;
    }
    /**
     * 将商品实例储存在数据库中
     * */
    public void saveGoods(Goods goods){
        if (goods != null){
            ContentValues values = new ContentValues();
            values.put("GId",goods.getGId());
            values.put("MId",goods.getMId());
            values.put("GName",goods.getGName());
            values.put("GPrice",goods.getGPrice());
            values.put("GIntroduction", goods.getGIntroduction());
            values.put("GPicture", goods.getGPicture());
            values.put("GPraise", goods.getGPraise());
            db.insert("Goods", null, values);
        }

    }
    /**
     * 更新点赞数
     * **/
    public void setGpraise(String GId,int GPraise){
        ContentValues values = new ContentValues();
        values.put("GPraise",GPraise);
        db.update("Goods",values,"GId = ?",new String[]{GId});
    }
    /**
     * 更新商店的信息
     * */
    public void setMNotes(String MId,String notes){
        ContentValues values = new ContentValues();
        values.put("MNote",notes);
        db.update("Merchants",values,"MId = ?", new String[]{MId});
    }

    /**
     * 更新商店等级的信息
     * */
    public void setMLevel(String MId,int level){
        ContentValues values = new ContentValues();
        values.put("MLevel",level);
        db.update("Merchants",values,"MId = ?",new String[]{MId});
    }

    /**
     * 获取某个商家的所有商品
     * */
    public List<Goods> loadAMerchantsAllGoods(String MId){
        List<Goods> list  = new ArrayList<Goods>();
        Cursor cursor = db.query("Goods",null,"MId = ?",new String[]{MId},null,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    Goods goods = new Goods();
                    goods.setMId(MId);
                    goods.setGId(cursor.getInt(cursor.getColumnIndex("GId")));
                    goods.setGName(cursor.getString(cursor.getColumnIndex("GName")));
                    goods.setGPrice(cursor.getString(cursor.getColumnIndex("GPrice")));
                    goods.setGIntroduction(cursor.getString(cursor.getColumnIndex("GIntroduction")));
                    goods.setGPraise(cursor.getInt(cursor.getColumnIndex("GPraise")));
                    goods.setGPicture(cursor.getBlob(cursor.getColumnIndex("GPicture")));
                    list.add(goods);
                }while(cursor.moveToNext());

            }
        }else {
            return null;
        }
        cursor.close();
        return list;
    }
    /**
     * 获取所有商品
     * */
    public List<Goods> loadAllGoods(){
        List<Goods> list = new ArrayList<Goods>();
        Cursor cursor = db.query("Goods",null,null,null,null,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    Goods goods = new Goods();
                    goods.setMId(cursor.getString(cursor.getColumnIndex("MId")));
                    goods.setGId(cursor.getInt(cursor.getColumnIndex("GId")));
                    goods.setGName(cursor.getString(cursor.getColumnIndex("GName")));
                    goods.setGPrice(cursor.getString(cursor.getColumnIndex("GPrice")));
                    goods.setGIntroduction(cursor.getString(cursor.getColumnIndex("GIntroduction")));
                    goods.setGPraise(cursor.getInt(cursor.getColumnIndex("GPraise")));
                    goods.setGPicture(cursor.getBlob(cursor.getColumnIndex("GPicture")));
                    list.add(goods);
                }while(cursor.moveToNext());
            }
        } else {
            return null;
        }
        cursor.close();
        return list;
    }
    /**
     * 删除一个商品
     * */
    public void delateAGoods(String goodsName){
        db.delete("Goods", "GName = ?", new String[]{goodsName});
    }

    /**
     * 获取某个商品的商家
     * */
    public String loadAGoodsMerchants(String merchantsId){
        String name = null;
        Cursor cursor = db.query("Merchants",null,"MId = ?",new String[]{merchantsId},null,null,null);
        if (cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex("MName"));
        }
        cursor.close();
        return name;


    }
    //关闭数据库
    public void closeDB(){
        db.close();
    }
    //打开数据库
    public void openDB(Context context){
        new TBDataBase(context);

    }



}








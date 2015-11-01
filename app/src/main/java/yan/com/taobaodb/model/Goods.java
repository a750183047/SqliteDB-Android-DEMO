package yan.com.taobaodb.model;

/**
 * 商品实体类
 * Created by yan on 2015/10/24.
 */
public class Goods {
    private String GId;
    private String GName;
    private String GPrice;
    private int GPraise;
    private String GIntroduction;
    private byte[] GPicture;
    private String MId;

    public void setGId(int GId){
        this.GId = String.valueOf(GId);
    }
    public void setGName(String GName){
        this.GName = GName;
    }
    public void setGPrice(String GPrice){
        this.GPrice = GPrice;
    }
    public void setGIntroduction(String GIntroduction){
        this.GIntroduction = GIntroduction;
    }
    public void setGPraise(int GPraise){
        this.GPraise = GPraise;
    }
    public void setGPicture(byte[] GPicture){
        this.GPicture = GPicture;
    }
    public void setMId(String MId){
        this.MId = MId;
    }
    public String getGId(){
        return GId;
    }
    public String getGName(){
        return GName;
    }
    public String getGPrice(){
        return GPrice;
    }
    public String getGIntroduction(){
        return GIntroduction;
    }
    public byte[] getGPicture(){
        return GPicture;
    }
    public int getGPraise(){
        return GPraise;
    }
    public String getMId(){
        return MId;
    }
}

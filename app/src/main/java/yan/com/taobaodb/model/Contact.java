package yan.com.taobaodb.model;

/**
 * 联系表
 * Created by yan on 2015/10/24.
 */
public class Contact {
    private String UId;
    private String GId;
    public void setUId(String UId){
        this.UId = UId;
    }
    public void setGId(String GId){
        this.GId = GId;
    }
    public String getUId(){
        return UId;
    }
    public String getGId(){
        return GId;
    }
}

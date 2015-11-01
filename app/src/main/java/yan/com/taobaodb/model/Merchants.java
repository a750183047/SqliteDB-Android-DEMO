package yan.com.taobaodb.model;

/**
 * 商户实体类
 * Created by yan on 2015/10/24.
 */
public class Merchants  {
    private String MId;
    private String MName;
    private String MLevel;
    private String MNote;

    public void setMId(String MId){
        this.MId = MId;
    }
    public void setMName(String MName){
        this.MName = MName;
    }
    public void setMLevel(String MLevel){
        this.MLevel = MLevel;
    }
    public void setMNote(String MNote){
        this.MNote = MNote;
    }
    public String getMId(){
        return MId;
    }
    public String getMName(){
        return MName;
    }
    public String getMLevel(){
        return MLevel;
    }
    public String getMNote(){
        return MNote;
    }
}


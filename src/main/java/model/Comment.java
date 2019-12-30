package model;

import java.util.Map;
import constant.Constant;
public class Comment {
    private String id = "";
    private String context = "";
    private String createTime = "";
    private String userId = "";
    private String fromId = "";

    public Comment(){

    }

    public Comment(Map<String, Object> map){
        setId((String) map.get(Constant.COM_ID));
        setContext((String) map.get(Constant.COM_CONTEXT));
        setCreateTime((String) map.get(Constant.CREATE_TIME));
        setUserId((String) map.get(Constant.COM_USERID));
        setFromId((String) map.get(Constant.COM_FROMID));
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setContext(String context){
        this.context = context;
    }
    public String getContext(){
        return  context;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return createTime;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }

    public void setFromId(String fromId){
        this.fromId = fromId;
    }
    public String getFromId(){
        return fromId;
    }

}

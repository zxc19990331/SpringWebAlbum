package model;

import constant.Constant;

import java.util.Map;

public class Follow {
    private String createTime = "";
    private String fromId = "";
    private String toId = "";

    public Follow(){

    }

    public Follow(Map<String, Object> map){
        setFromId((String) map.get(Constant.FOLLOW_FROMID));
        setCreateTime((String) map.get(Constant.CREATE_TIME));
        setToId((String) map.get(Constant.FOLLOW_TOID));
        System.out.println("SETFOLLOW1" + (String)map.get(Constant.FOLLOW_TOID));

        System.out.println("SETFOLLOW2" + toId);

    }

    public void setFromId(String id){
        this.fromId = id;
    }
    public String getFromId(){
        return fromId;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getCreateTime(){
        return createTime;
    }

    public void setToId(String toId){
        this.toId = toId;
    }
    public String getToId(){
        return toId;
    }


}

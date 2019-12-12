package model;

import constant.Constant;

import java.util.Date;
import java.util.Map;

public class Photo {
    private String id = "";
    private String name = "";
    private String createTime = "";
    private String url = "";
    private int praiseCount = 0;

    public Photo(Map<String, Object> map){
        setId((String) map.get(Constant.PHOTO_ID));
        setName((String)map.get(Constant.PHOTO_NAME));
        setCreateTime((String)map.get(Constant.CREATE_TIME));
        setUrl((String)map.get(Constant.URL));
        setPraiseCount((int)map.get(Constant.PRAISE_COUNT));
    }

    public Photo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }
}

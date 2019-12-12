package model;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import constant.Constant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;

public class Album {
    private String id = "";
    private String name = "";
    private String userId = "";
    private String createTime = "";
    private String coverId = "";
    private String descp = "";
    private String albumState = "";
    private int praiseCount = 0;

    public Album(){

    }

    public Album(Map<String, Object> map){
        setId((String)map.get(Constant.ALB_ID));
        setName((String)map.get(Constant.ALB_NAME));
        setUserId((String)map.get(Constant.ALB_UER_ID));
        setCreateTime((String)map.get(Constant.CREATE_TIME));
        setCoverId((String)map.get(Constant.COVER_ID));
        setDescp((String)map.get(Constant.DESCP));
        setAlbumState((String)map.get(Constant.ALBUM_STATE));
        setPraiseCount((int)map.get(Constant.PRAISE_COUNT));
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getAlbumState() {
        return albumState;
    }

    public void setAlbumState(String albumState) {
        this.albumState = albumState;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }
}

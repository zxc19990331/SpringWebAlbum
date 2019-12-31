package model;

import java.util.Map;
import constant.Constant;
/**
 * Created by hi on 2019/12/30.
 */
public class Message {
    private String id="";
    private String fromId="";
    private String toId="";
    private String context="";
    private String createTime="";
    private String extra="";
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Message(Map<String,Object> map){
        setId((String)map.get(Constant.Message_ID));
        setToId((String)map.get(Constant.Message_TOID));
        setFromId((String)map.get(Constant.Message_FROMID));
        setContext((String)map.get(Constant.Message_CONTEXT));
        setCreateTime((String)map.get(Constant.Message_CREATE_TIME));
        setExtra((String)map.get(Constant.Message_EXTRA));
    }

}

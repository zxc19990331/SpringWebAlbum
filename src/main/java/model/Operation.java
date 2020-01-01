package model;

import constant.Constant;

import java.util.Map;

public class Operation {
    private String id;
    private String fromId;
    private String toId;
    private String operate;
    private String operateName;
    private String note;
    private String createTime;

    public Operation(){

    }

    public Operation(Map<String,Object> map){
        setId((String)map.get(Constant.ID));
        setFromId((String)map.get(Constant.FROM_ID));
        setToId((String)map.get(Constant.TO_ID));
        setOperate((String)map.get(Constant.OPERATE));
        setNote((String)map.get(Constant.NOTE));
        setCreateTime((String)map.get(Constant.CREATE_TIME));
        //对应的中文 *那么为什么不直接用中文做值呢？？
        setOperateName(Constant.OP_NAME_MAP.get(getOperate()));
    }

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

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getNote() {
        return note;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }



}

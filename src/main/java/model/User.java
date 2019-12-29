package model;

import constant.Constant;

import java.util.Map;

public class User {
    private String id;
    private String password;
    private String name;
    private String userState;
    private String type;
    private String descp;
    private String createTime;


    public User(Map<String, Object> info){
        setId((String)info.get(Constant.USER_ID));
        setPassword((String)info.get(Constant.PASSWORD));
        setName((String)info.get(Constant.USER_NAME));
        setUserState((String)info.get(Constant.USER_STATE));
        setType((String)info.get(Constant.TYPE));
        setDescp((String)info.get(Constant.DESCP));
        setCreateTime((String)info.get(Constant.CREATE_TIME));
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public User(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
}

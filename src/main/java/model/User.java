package model;

import constant.Constant;

import java.util.Map;

public class User {
    private String id;
    private String password;
    private String name;
    private String userState;
    public User(Map<String, Object> info){
        setId((String)info.get(Constant.USER_ID));
        setPassword((String)info.get(Constant.PASSWORD));
        setName((String)info.get(Constant.USER_NAME));
        setUserState((String)info.get(Constant.USER_STATE));
    }
    public User(){

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

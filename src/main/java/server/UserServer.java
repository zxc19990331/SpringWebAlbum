package server;

import dao.UserDAO;
import entity.DataResult;
import model.User;
import util.DateHelper;

public class UserServer {
    public static DataResult getUserInfoById(String id){
        DataResult dataResult = new DataResult();
        if(!checkUserExist(id)){
            dataResult.setStatus(-1);
            dataResult.setMsg("find user fail");
        }else{
            dataResult.setStatus(0);
            dataResult.setMsg("find user success");
            dataResult.setData(new User(UserDAO.getUserInfoById(id)));
        }
        return dataResult;
    }
    public static boolean checkUserExist(String id){
        return UserDAO.isUserIdExist(id);
    }

    public static DataResult login(String id,String password){
        DataResult dataResult = new DataResult();
        if(!checkUserExist(id)){
            dataResult.setStatus(-1);
            dataResult.setMsg("user not exist");
        }else {
            User user = new User(UserDAO.getUserInfoById(id));
            if (user.getPassword().equals(password)) {
                dataResult.setStatus(0);
                dataResult.setMsg("login success");
                dataResult.setData(user);
            } else {
                dataResult.setStatus(-1);
                dataResult.setMsg("login fail");
            }
        }
        return dataResult;
    }

    public static DataResult register(String id,String password,String name){
        DataResult dataResult = new DataResult();
        if(checkUserExist(id)){
            dataResult.setStatus(-1);
            dataResult.setMsg("user exist");
        }else{
            UserDAO.addNewUser(id,password,name);
            dataResult.setMsg("register success");
            dataResult.setStatus(0);
            User user = new User();
            user.setName(id);
            user.setPassword(password);
            user.setName(name);
            dataResult.setData(user);
        }
        return dataResult;
    }

    public static DataResult editBaseInfo(String userId,String name,String desc){
        boolean res = UserDAO.editBaseInfo(userId,name,desc);
        if(res){
            return DataResult.success("edit base info success",(User)UserServer.getUserInfoById(userId).getData());
        }else{
            return DataResult.fail("edit base info fail");
        }
    }
}

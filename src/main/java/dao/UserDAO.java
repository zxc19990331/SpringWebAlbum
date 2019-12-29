package dao;

import util.DateHelper;

import java.util.List;
import java.util.Map;

public class UserDAO {
    public static boolean isUserIdExist(String id){
        List<Map<String, Object>> res = JDBCDAO.select(String.format("SELECT * FROM users WHERE id = '%s'",id));
        System.out.println("UserDAO id" + id);
        if (res == null)
            return false;
        System.out.println(res.size());
        return !(res.size() == 0) ;
    }

    public static Map<String, Object> getUserInfoById(String id){
        return JDBCDAO.select(String.format("SELECT * FROM users WHERE id = '%s'",id)).get(0);
    }

    public static void addNewUser(String id,String password,String name){
//        默认个人简介为空
        JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO users VALUES('%s','%s','%s','%s','%s','%s','%s')",
                id,name,password,"normal","user","", DateHelper.getCurrentDate()));
    }

    public static boolean editBaseInfo(String userId,String name,String desc){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE users SET name = '%s',descp = '%s' WHERE id = '%s'",name,desc,userId));
    }
}

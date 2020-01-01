package dao;

import constant.Constant;
import util.DateHelper;
import util.ShortUUID;

import java.util.List;
import java.util.Map;

public class FollowDAO {

    public static List<Map<String, Object>> getFollowInfoListById(String fromId){
        return JDBCDAO.select(String.format("SELECT * FROM dbo.follow WHERE from_id = '%s'",fromId));
    }

    public static boolean delFollow(String fromId,String toId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("DELETE follow where from_id = '%s' and to_id = '%s'",fromId,toId));
    }

    public static boolean addFollow(String fromId,String toId){
        String curDate = DateHelper.getCurrentDate();
        System.out.println("DAO: " + curDate);

        return JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO follow VALUES('%s','%s','%s')",
                fromId,toId,curDate));

    }

    public static boolean isFollow(String fromId,String toId){

        List<Map<String, Object>> obj =JDBCDAO.select(String.format("SELECT * FROM dbo.follow WHERE from_id = '%s' and to_id = '%s'",fromId,toId));
        if(obj.isEmpty()){
            return false;
        }else {
            return true;
        }

    }



}

package dao;

import util.DateHelper;

import java.util.HashMap;
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

    public static List<Map<String, Object>> getUserList(int page,int limit){
        int pre = (page - 1)*limit;
        //沙雕sqlserver
        return JDBCDAO.select(String.format("SELECT TOP %d * FROM users WHERE id NOT IN (SELECT TOP %d id FROM users ORDER BY create_time DESC) ORDER BY create_time DESC",limit,pre));
    }

    public static boolean setUserState(String userId,String state){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE users SET user_state = '%s' WHERE id = '%s'",state,userId));
    }

    public static Map<String, Integer> getStatisticsAll(String userId){
        Map<String,Integer> statistics = new HashMap<>();

        int AlbumCount =  JDBCDAO.select(String.format("SELECT * FROM album where user_id = '%s'",userId)).size();
        statistics.put("albumCount",AlbumCount);//发布的相册数量

        int CommentCount =  JDBCDAO.select(String.format("SELECT * FROM comment where user_id = '%s'",userId)).size();
        statistics.put("commentCount",CommentCount);//发布的评论数量

        int FollowCount =  JDBCDAO.select(String.format("SELECT * FROM follow where from_id = '%s'",userId)).size();
        statistics.put("followCount",FollowCount);//我关注的数量

        int FollowedCount =  JDBCDAO.select(String.format("SELECT * FROM follow where to_id = '%s'",userId)).size();
        statistics.put("followedCount",FollowedCount);//我被关注的数量

        int PhotoCount =  JDBCDAO.select(String.format("SELECT * from store where album_id in (select id from album where user_id = '%s')",userId)).size();
        statistics.put("photoCount",PhotoCount);//我的照片的数量

        return statistics;
    }
}

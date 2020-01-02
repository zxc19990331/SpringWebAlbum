package dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatisticsDAO {
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

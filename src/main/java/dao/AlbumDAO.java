package dao;

import constant.Constant;
import util.DateHelper;
import util.ShortUUID;

import java.util.List;
import java.util.Map;

public class AlbumDAO {

    public static  List<Map<String, Object>> getAlbumInfoListAll(){
        return JDBCDAO.select(String.format("SELECT * FROM album "));
    }

    public static List<Map<String, Object>> getAlbumInfoListAll(int page,int limit){
        int pre = limit*(page-1);
        return JDBCDAO.select(String.format("SELECT TOP %d * FROM album WHERE id NOT IN (SELECT TOP %d id FROM album)",limit,pre));
    }

    public static List<Map<String, Object>> getAlbumInfoListByUserId(String userId){
        return JDBCDAO.select(String.format("SELECT * FROM album WHERE user_id = '%s'",userId));
    }

    //有分页
    public static List<Map<String, Object>> getAlbumInfoListByUserId(String userId,int page,int limit){
        int pre = limit*(page - 1);
        //沙雕sqlserver 没有LIMIT
        String sql = String.format("SELECT TOP %d * FROM album WHERE id NOT IN (SELECT TOP %d id FROM album WHERE user_id = '%s' ORDER BY create_time DESC ) " +
                "AND user_id = '%s' ORDER BY create_time DESC  ",limit,pre,userId,userId);
        //System.out.println(sql);
        return JDBCDAO.select(sql);
    }

    public static List<Map<String, Object>> getPhotoInfoListByAlbumId(String albumId){
        return JDBCDAO.select(String.format("SELECT * FROM photo WHERE id IN (SELECT photo_id FROM store WHERE album_id = '%s')",albumId));
    }

//    沙雕sqlserver *2
    public static List<Map<String, Object>> getPhotoInfoListByAlbumId(String albumId,int page,int limit){
        int pre = limit*(page - 1);
        String sql = String.format("SELECT TOP %d * FROM photo WHERE id IN (SELECT photo_id FROM store WHERE album_id = '%s') " +
                "AND id NOT IN ( SELECT TOP %d id FROM photo WHERE id IN (SELECT photo_id FROM store WHERE album_id = '%s') )" +
                "ORDER BY create_time DESC ",limit,albumId,pre,albumId);
        System.out.println(sql);
        return JDBCDAO.select(sql);
    }

    public static List<Map<String, Object>> getPhotoInfoListByAlbumId(String albumId,String order){
        String o = "";
        switch (order){
            case Constant.ORDER_DEFAULT:
                break;
            case Constant.ORDER_DATE_DESC:
                o = "ORDER BY CONVERT(DATETIME,create_time) DESC";
                break;
            case Constant.ORDER_DATE_ASC:
                o = "ORDER BY CONVERT(DATETIME,create_time) ASC";
                break;
        }
        return JDBCDAO.select(String.format("SELECT * FROM photo WHERE id IN (SELECT photo_id FROM store WHERE album_id = '%s') %s",albumId,o));
    }

    public static Map<String, Object> getAlbumInfoById(String albumId){
        return JDBCDAO.select(String.format("SELECT * FROM album WHERE id = '%s'",albumId)).get(0);
    }

    public static String createNewAlbum(String userId,String albumName,String albumDescp,String category){
        String albumId = ShortUUID.generateShortUuid();
        String curDate = DateHelper.getCurrentDate();
        boolean res = JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO album VALUES('%s','%s','%s','%s','%s','%s','%s',%s,'%s')",
                albumId,albumName,userId,curDate,"default.jpg",albumDescp,"normal","0",category));
        if(res){
            return albumId;
        }else {
            return null;
        }
    }

    public static boolean editAlbumBaseInfo(String albumId,String albumName,String albumDescp,String category){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE album SET album_name = '%s',descp = '%s',category = '%s' WHERE id = '%s'"
                ,albumName,albumDescp,category,albumId));
    }

    public static boolean delAlbum(String albumId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("DELETE album WHERE id = '%s'",albumId));
    }


    public static boolean uploadPhotoToAlbum(String photoId,String albumId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO store VALUES('%s','%s')",photoId,albumId));
    }

    public static boolean setCover(String albumId,String photoId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE album SET cover_id = '%s' WHERE id = '%s'",photoId,albumId));
    }

    public static boolean setState(String albumId,String state){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE album SET album_state = '%s' WHERE id = '%s'",state,albumId));
    }

    public static boolean addPraise(String albumId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE album SET praise_count = praise_count + 1 WHERE id = '%s'",albumId));
    }

    public static boolean isAlbumExist(String albumId){
        List<Map<String, Object>> res = JDBCDAO.select(String.format("SELECT * FROM album WHERE id = '%s'",albumId));
        return !(res.size()==0);
    }
}

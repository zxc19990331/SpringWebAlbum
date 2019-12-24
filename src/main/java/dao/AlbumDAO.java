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

    public static List<Map<String, Object>> getAlbumInfoListByUserId(String userId){
        return JDBCDAO.select(String.format("SELECT * FROM album WHERE user_id = '%s'",userId));
    }

    public static List<Map<String, Object>> getPhotoInfoListByAlbumId(String albumId){
        return JDBCDAO.select(String.format("SELECT * FROM photo WHERE id IN (SELECT photo_id FROM store WHERE album_id = '%s')",albumId));
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

    public static String createNewAlbum(String userId,String albumName,String albumDescp){
        String albumId = ShortUUID.generateShortUuid();
        String curDate = DateHelper.getCurrentDate();
        boolean res = JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO album VALUES('%s','%s','%s','%s','%s','%s','%s',%s)",
                albumId,albumName,userId,curDate,"default.jpg",albumDescp,"normal","0"));
        if(res){
            return albumId;
        }else {
            return null;
        }
    }

    public static boolean uploadPhotoToAlbum(String photoId,String albumId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO store VALUES('%s','%s')",photoId,albumId));
    }


    public static boolean isAlbumExist(String albumId){
        List<Map<String, Object>> res = JDBCDAO.select(String.format("SELECT * FROM album WHERE id = '%s'",albumId));
        return !(res.size()==0);
    }
}

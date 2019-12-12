package dao;

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

    public static boolean isAlbumExist(String albumId){
        List<Map<String, Object>> res = JDBCDAO.select(String.format("SELECT * FROM album WHERE id = '%s'",albumId));
        return !(res.size()==0);
    }
}

package dao;

import util.DateHelper;
import util.ShortUUID;

public class PhotoDAO {
    public static String uploadPhoto(String name,String url){
        String id = ShortUUID.generateShortUuid();
        boolean res = JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO photo VALUES('%s','%s','%s','%s',%s)",
                id,name, DateHelper.getCurrentDate(),url,'0'));
        if(res){
            return id;
        }else{
            return null;
        }
    }

    //仅删除store
    public static boolean delPhotoFromAlbum(String albumId,String photoId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("DELETE store WHERE photo_id = '%s'AND album_id = '%s'",photoId,albumId));
    }

    //删除photo和store
    public static boolean delPhoto(String photoId){
        return  JDBCDAO.insertOrDeleteOrUpdate(String.format("DELETE photo WHERE id = '%s'",photoId));
    }

    //更改照片名称
    public static boolean changePhotoName(String name,String photoId){
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("UPDATE photo SET name = '%s' WHERE id = '%s'",name,photoId));
    }


}

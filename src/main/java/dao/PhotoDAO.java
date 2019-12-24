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


}

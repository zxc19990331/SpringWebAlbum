package dao;

import constant.Constant;
import util.DateHelper;
import util.ShortUUID;

public class OperationDAO {

    public static boolean addOp(String from_id,String to_id,String op,String note){
        String id = ShortUUID.generateShortUuid();
        String date = DateHelper.getCurrentDate();
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO operations VALUES('%s','%s','%s','%s','%s','%s',)",
                id,from_id,to_id,op,note,date));
    }

    public static boolean addBanUserOp(String from_id,String to_id,String note){
        return addOp(from_id,to_id, Constant.BAN_USR,note);
    }
}

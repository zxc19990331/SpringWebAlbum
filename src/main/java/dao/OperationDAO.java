package dao;

import constant.Constant;
import util.DateHelper;
import util.ShortUUID;

import java.util.List;
import java.util.Map;

public class OperationDAO {

    public static boolean addOp(String from_id,String to_id,String op,String note){
        String id = ShortUUID.generateShortUuid();
        String date = DateHelper.getCurrentDate();
        return JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO operation VALUES('%s','%s','%s','%s','%s','%s')",
                id,from_id,to_id,op,note,date));
    }

    public static List<Map<String,Object>> getOperationList(int page,int limit){
        int pre = (page-1)*limit;
        return JDBCDAO.select(String.format("SELECT TOP %d * FROM operation WHERE id NOT IN (SELECT TOP %d id FROM operation)",limit,pre));
    }

    public static boolean addBanUserOp(String from_id,String to_id,String note){
        return addOp(from_id,to_id, Constant.BAN_USR,note);
    }
}

package dao;

/**
 * Created by hi on 2019/12/30.
 */
import constant.Constant;
import util.DateHelper;
import util.ShortUUID;
import java.util.List;
import java.util.Map;

public class MessageDAO {
    public static List<Map<String ,Object>> getMessageBytoId(String toId){
        return JDBCDAO.select(String.format("SELECT * FROM message WHERE to_id = '%s'",toId));
    }
    public static List<Map<String ,Object>> getMessageByfromId(String fromId){
        return JDBCDAO.select(String.format("SELECT * FROM message WHERE from_id = '%s'",fromId));
    }
    public static void addNewMessage(String fromId,String toId,String context){
        String messageId = ShortUUID.generateShortUuid();
        JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO message VALUES('%s','%s','%s','%s','%s','%s')",
                messageId,fromId,toId,context,DateHelper.getCurrentDate()," "));
    }
}

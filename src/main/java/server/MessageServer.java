package server;

/**
 * Created by hi on 2019/12/30.
 */
import dao.MessageDAO;
import entity.DataResult;
import model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageServer {
    public static DataResult getMessageBytoId(String toId){
        DataResult dataResult=new DataResult();
        List<Map<String , Object>> maps=MessageDAO.getMessageBytoId(toId);
        List<Message> MessageList=new ArrayList<>();
        for(Map<String ,Object> map :maps){
            MessageList.add(new Message(map));
        }
        dataResult.setStatus(0);
        dataResult.setMsg("get Message ");
        dataResult.setData(MessageList);
        return dataResult;
    }
    public static DataResult getMessageByfromId(String fromId){
        DataResult dataResult=new DataResult();
        List<Map<String , Object>> maps=MessageDAO.getMessageByfromId(fromId);
        List<Message> MessageList=new ArrayList<>();
        for(Map<String ,Object> map :maps){
            MessageList.add(new Message(map));
        }
        dataResult.setStatus(0);
        dataResult.setMsg("get Message ");
        dataResult.setData(MessageList);
        return dataResult;
    }
    public static DataResult sendMessage(String fromId,String toId,String messageContext){
        MessageDAO.addNewMessage(fromId,toId,messageContext);
        return DataResult.success("send message success",null);
    }
}

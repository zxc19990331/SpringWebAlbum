package server;

import constant.Constant;
import dao.OperationDAO;
import entity.DataResult;
import model.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperationServer {

    public static DataResult getOperationList(int page,int limit){
        List<Map<String,Object>> maps =  OperationDAO.getOperationList(page,limit);
        if(maps == null){
            return DataResult.fail("get op lists fail");
        }
        List<Operation> ops = new ArrayList<>();
        for(Map<String,Object> map:maps){
            ops.add(new Operation(map));
        }
        return DataResult.success("get op list success",ops);
    }

    public static DataResult addChangeUserState(String opId,String userId,String note,String state, String op){
        int res = UserServer.setState(userId, state).getStatus();
        if(res == 0){
            boolean r = OperationDAO.addOp(opId,userId,op,note);
            if(r){
                return DataResult.success("change ban user success",null);
            }
        }
        return DataResult.fail("change ban user fail");
    }

    public static DataResult addChangeAlbumState(String opId,String albumId,String note,String state,String op){
        int res = AlbumServer.setState(albumId,state).getStatus();
        if(res == 0){
            boolean r = OperationDAO.addOp(opId,albumId,op,note);
            if(r){
                return DataResult.success("change ban album success",null);
            }
        }
        return DataResult.fail("change ban album fail");
    }

    public static DataResult banUser(String opId,String userId,String note){
        return addChangeUserState(opId,userId,note,Constant.BANNED,Constant.BAN_USR);
    }

    public static DataResult debanUser(String opId,String userId,String note){
        return addChangeUserState(opId,userId,note,Constant.NORMAL,Constant.DEBAN_USR);
    }

    public static DataResult banAlbum(String opId,String albumId,String note){
        return addChangeAlbumState(opId,albumId,note,Constant.BANNED,Constant.BAN_ALBUM);
    }

    public static DataResult debanAlbum(String opId,String albumId,String note){
        return addChangeAlbumState(opId,albumId,note,Constant.NORMAL,Constant.DEBAN_ALBUM);
    }


}

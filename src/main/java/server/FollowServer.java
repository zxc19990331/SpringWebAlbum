package server;


import dao.AlbumDAO;
import dao.CommentDAO;
import dao.FollowDAO;
import entity.DataResult;
import model.Follow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowServer {
    public static DataResult getFollowInfoById(String fromId){
        DataResult dataResult = new DataResult();

        dataResult.setStatus(0);
        dataResult.setMsg("get follow info");
        List<Follow> followList = new ArrayList<>();
        List<Map<String,Object>> maps = FollowDAO.getFollowInfoListById(fromId);
        for(Map<String,Object>map:maps){
            followList.add(new Follow(map));
        }

        dataResult.setMsg("get comment success");
        dataResult.setData(followList);

        return dataResult;
    }

    public static DataResult addFollow(String fromId,String toId){
        DataResult dataResult = new DataResult();
        boolean res = FollowDAO.addFollow(fromId,toId);
        if(res == true){
            dataResult.setStatus(0);
            dataResult.setMsg("add new follow success");
//            Follow follow = new Follow(FollowDAO.getFollowInfoListById(fromId,toId));
//            dataResult.setData(follow);
        }else{
            dataResult.setStatus(-1);
            dataResult.setMsg("add new follow error");
        }
        return dataResult;
    }

    public static DataResult deleteFollow(String fromId,String toId){
        DataResult dataResult = new DataResult();
        boolean res = FollowDAO.delFollow(fromId,toId);
        if(res!=false){
            dataResult.setStatus(0);
            dataResult.setMsg("delete follow success");
        }else{
            dataResult.setStatus(-1);
            dataResult.setMsg("delete follow error");
        }
        return dataResult;
    }

    public static DataResult checkFollow(String fromId,String toId){
        DataResult dataResult = new DataResult();
        boolean res = FollowDAO.isFollow(fromId,toId);
        if(res == true){
            System.out.println("CHECKSERVER IS");
            dataResult.setStatus(0);
            dataResult.setMsg("is followed");
        }else {
            System.out.println("CHECKSERVER NOT");
            dataResult.setStatus(-1);
            dataResult.setMsg("is not followed");
        }
        return dataResult;
    }
}

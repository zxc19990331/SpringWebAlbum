package server;

import dao.AlbumDAO;
import dao.CommentDAO;
import entity.DataResult;
import model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentServer {

    public static DataResult getCommentInfoByAlbumId(String albumId){
        DataResult dataResult = new DataResult();
        if(!checkAlbumExist(albumId)){
            dataResult.setStatus(-1);
            dataResult.setMsg("no such comment");
        }else{
            dataResult.setStatus(0);
            dataResult.setMsg("get comment info");
            List<Comment> commentList = new ArrayList<>();
            List<Map<String,Object>> maps = CommentDAO.getCommentInfoListByAlbumId(albumId);
            for(Map<String,Object>map:maps){
                commentList.add(new Comment(map));
            }
            dataResult.setMsg("get comment success");
            dataResult.setData(commentList);
        }
        return dataResult;
    }


    public static DataResult getCommentInfoListByUserId(String userId){
        DataResult dataResult = new DataResult();
        if(!UserServer.checkUserExist(userId)){
            dataResult.setStatus(-1);
            dataResult.setMsg("no such user");
        }else {
            List<Comment> commentList = new ArrayList<>();
            List<Map<String, Object>> maps = CommentDAO.getCommentInfoListByUserId(userId);
            for (Map<String, Object> map : maps) {
                commentList.add(new Comment(map));
            }
            dataResult.setStatus(0);
            dataResult.setMsg("get commentlist success");
            dataResult.setData(commentList);
        }
        return dataResult;
    }

    public static DataResult addNewComment(String userId,String albumName,String context){
        DataResult dataResult = new DataResult();
        // return album id
        String res = CommentDAO.createNewComment(userId,albumName,context);
        if(res!=null){
            dataResult.setStatus(0);
            dataResult.setMsg("add new comment success");
            Comment comment = new Comment(CommentDAO.getCommentInfoById(res));
            dataResult.setData(comment);
        }else{
            dataResult.setStatus(-1);
            dataResult.setMsg("add new comment error");
        }
        return dataResult;
    }

    public static DataResult delComment(String commentId){
        DataResult dataResult = new DataResult();
        boolean res = CommentDAO.delComment(commentId);
        if(res!=false){
            dataResult.setStatus(0);
            dataResult.setMsg("delete comment success");
        }else{
            dataResult.setStatus(-1);
            dataResult.setMsg("add new album error");
        }
        return dataResult;
    }


    public static boolean checkAlbumExist(String albumId){
        return AlbumDAO.isAlbumExist(albumId);
    }
}

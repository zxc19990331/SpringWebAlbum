package server;

import dao.AlbumDAO;
import entity.DataResult;
import model.Album;
import model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumServer {

    public static DataResult getAlbumInfoById(String albumId){
        DataResult dataResult = new DataResult();
        if(!checkAlbumExist(albumId)){
            dataResult.setStatus(-1);
            dataResult.setMsg("no such album");
        }else{
            dataResult.setStatus(0);
            dataResult.setMsg("get album info");
            dataResult.setData(new Album(AlbumDAO.getAlbumInfoById(albumId)));
        }
        return dataResult;
    }

    public static DataResult getAllAlbum(){
        DataResult dataResult = new DataResult();
        List<Album> albumList = new ArrayList<>();
        List<Map<String,Object>> maps = AlbumDAO.getAlbumInfoListAll();
        for (Map<String, Object> map : maps) {
            albumList.add(new Album(map));
        }
        dataResult.setStatus(0);
        dataResult.setMsg("get albumlist success");
        dataResult.setData(albumList);
        return dataResult;
    }

    public static DataResult getPhotoInfoListByAlbumId(String albumId){
       DataResult dataResult = new DataResult();
       if(!checkAlbumExist(albumId)){
           dataResult.setStatus(-1);
           dataResult.setMsg("no such album");
       }else{
           List<Map<String, Object>> maps = AlbumDAO.getPhotoInfoListByAlbumId(albumId);
           List<Photo> photoList = new ArrayList<>();
           for (Map<String, Object> map : maps) {
               photoList.add(new Photo(map));
           }
           dataResult.setStatus(0);
           dataResult.setMsg("get photolist success");
           dataResult.setData(photoList);
       }
       return dataResult;
    }

    public static DataResult getAlbumInfoListByUserId(String userId){
        DataResult dataResult = new DataResult();
        if(!UserServer.checkUserExist(userId)){
            dataResult.setStatus(-1);
            dataResult.setMsg("no such user");
        }else {
            List<Album> albumList = new ArrayList<>();
            List<Map<String, Object>> maps = AlbumDAO.getAlbumInfoListByUserId(userId);
            for (Map<String, Object> map : maps) {
                albumList.add(new Album(map));
            }
            dataResult.setStatus(0);
            dataResult.setMsg("get albumlist success");
            dataResult.setData(albumList);
        }
        return dataResult;
    }

    public static DataResult addNewAlbum(String userId,String albumName,String albumDescp){
            DataResult dataResult = new DataResult();
            // return album id
            String res = AlbumDAO.createNewAlbum(userId,albumName,albumDescp);
            if(res!=null){
                dataResult.setStatus(0);
                dataResult.setMsg("add new album success");
                Album album = new Album(AlbumDAO.getAlbumInfoById(res));
                dataResult.setData(album);
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

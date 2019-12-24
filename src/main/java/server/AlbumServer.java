package server;

import constant.Constant;
import dao.AlbumDAO;
import dao.PhotoDAO;
import entity.DataResult;
import model.Album;
import model.Photo;
import org.springframework.web.multipart.MultipartFile;
import util.DateHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumServer {


    public static DataResult uploadPhoto(MultipartFile[] files,String userId,String albumId) throws Exception{
        if(!isUserValid(userId,albumId)){
            return DataResult.fail("相册操作权限不合法");
        }
//        TODO:服务器存储图片的文件夹进行合理的分层，我想有生之年也不会做这种优化了
        Album album = (Album) getAlbumInfoById(albumId).getData();
        for(MultipartFile file:files){
            String rawName = file.getOriginalFilename();
            int len = rawName.length();
            rawName = rawName.substring(0,len>64?64:len);
            String newName = album.getName()+"_" + DateHelper.getCurrentDateStr() + "_" + rawName;
            String path = Constant.IMAGE_PATH;
            file.transferTo(new File(path + File.separator + newName));
            //上传photo表
            String photoId = PhotoDAO.uploadPhoto(newName,newName);
            if(photoId == null){
                return DataResult.fail("上传图片失败");
            }
            //上传store表
            boolean res = AlbumDAO.uploadPhotoToAlbum(photoId,albumId);
        }
        return DataResult.success("上传图片成功",null);
    }

    public static boolean isUserValid(String userId,String albumId){
        return true;
    }

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

    public static DataResult getPhotoInfoListByAlbumId(String albumId,String order){
       DataResult dataResult = new DataResult();
       if(!checkAlbumExist(albumId)){
           dataResult.setStatus(-1);
           dataResult.setMsg("no such album");
       }else{
           List<Map<String, Object>> maps = AlbumDAO.getPhotoInfoListByAlbumId(albumId,order);
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

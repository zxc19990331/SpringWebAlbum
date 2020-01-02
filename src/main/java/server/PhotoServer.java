package server;

import dao.PhotoDAO;
import entity.DataResult;

public class PhotoServer {
    public static DataResult delPhotoStore(String albumId,String photoId){
        boolean res = PhotoDAO.delPhotoFromAlbum(albumId,photoId);
        if(res){
            return DataResult.success("del photo store success",null);
        }else{
            return DataResult.fail("del photo store fail");
        }
    }

    public static DataResult delPhoto(String photoId){
        boolean res = PhotoDAO.delPhoto(photoId);
        if(res){
            return DataResult.success("del photo success",null);
        }else{
            return DataResult.fail("del photo fail");
        }
    }

    public static DataResult changePhotoName(String name,String photoId){
        boolean res = PhotoDAO.changePhotoName(name,photoId);
        return res?DataResult.success("change name success",null):DataResult.fail("change name fail");
    }
}

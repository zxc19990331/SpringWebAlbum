package util;

import constant.Constant;

public class ImageHelper {
    public static String getFilePath(String url){
        return Constant.IMAGE_PATH + "\\" + url;
    }

    public static String getAvatarPath(String id){
        return Constant.IMAGE_PATH + "\\avatar\\" + id + ".jpg";
    }
}

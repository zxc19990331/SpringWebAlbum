package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.x509.AVA;
import util.ImageHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class ImageController {

    public static final String DEFAULT_PATH = ImageHelper.getFilePath("default.jpg");
    public static final String DEAFULT_AVATAR = ImageHelper.getFilePath("avatar\\default.jpg");
    public static final int AVATAR = 0;
    public static final int IMG = 1;

    public void handleRp(HttpServletResponse rp,String filePath,int type) {
        File imageFile = new File(filePath);
        if(!imageFile.exists()) {
            imageFile = new File(type == IMG ?DEFAULT_PATH:DEAFULT_AVATAR);
        }
        if (imageFile.exists()) {
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(imageFile);
                os = rp.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((count = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                    os.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @RequestMapping(value = "/getDefaultImage")
    @ResponseBody
    public void getDefaultImage(HttpServletResponse rp) {
       String filePath = DEFAULT_PATH;
       handleRp(rp,filePath,IMG);
    }

    @RequestMapping(value = "/getImage")
    @ResponseBody
    public void getImage(@RequestParam("url")String url,HttpServletResponse rp){
        String filePath = ImageHelper.getFilePath(url);
        handleRp(rp,filePath, IMG);
        System.out.println(filePath);
    }

    @RequestMapping(value = "/getAvatar")
    @ResponseBody
    public void getAvatar(@RequestParam("id")String id,HttpServletResponse rp){
        String filePath = ImageHelper.getAvatarPath(id);
        handleRp(rp,filePath,AVATAR);
        System.out.println("avatar path:" + filePath);
    }
}

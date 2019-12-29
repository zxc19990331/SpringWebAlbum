package controller;

import constant.Constant;
import entity.DataResult;
import model.Album;
import model.Photo;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import server.AlbumServer;
import server.PhotoServer;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AlbumController {
    @RequestMapping("/submitNewAlbum")
    @ResponseBody
    public DataResult submitNewAlbum(@RequestParam("title")String title,@RequestParam("desc")String desc,
                                     @RequestParam("userId")String userId, @RequestParam("type")String category){
        return AlbumServer.addNewAlbum(userId,title,desc,category);
    }

    @RequestMapping("/album")
    public ModelAndView enterAlbum(@RequestParam("albumId")String albumId,Model model){
//        TODO:容错判断 排序功能
        List<Photo> photoList = (List<Photo>)AlbumServer.getPhotoInfoListByAlbumId(albumId, Constant.ORDER_DATE_ASC).getData();
        Album album = (Album) AlbumServer.getAlbumInfoById(albumId).getData();
        model.addAttribute("photoList",photoList);
        model.addAttribute("albumInfo",album);
        return new ModelAndView("album_content");
    }

    @RequestMapping("/editAlbum")
    public String editAlbum(@RequestParam("albumId")String albumId,Model model){
        Album album = (Album)AlbumServer.getAlbumInfoById(albumId).getData();
        model.addAttribute("album",album);
        return "album_edit";
    }

    @RequestMapping("/submitEditAlbum")
    @ResponseBody
    public DataResult submitEditAlbum(@RequestParam("title")String title,@RequestParam("desc")String desc,
                                  @RequestParam("userId")String userId, @RequestParam("albumId")String albumId,@RequestParam("type")String category){
        return AlbumServer.editAlbumBaseInfo(userId,albumId,title,desc,category);
    }

    @RequestMapping("/delAlbum")
    @ResponseBody
    public DataResult delAlbum(@RequestParam("albumId")String albumId, HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        System.out.println("seesion myInfo:" + user.getId());
        return AlbumServer.delAlbum(user.getId(),albumId);
    }


    //返回json格式的数据
    @RequestMapping("/upload")
    @ResponseBody
    public DataResult upload(@RequestParam("file") MultipartFile[] files,@RequestParam("userId")String userId,
                         @RequestParam("albumId")String albumId) throws Exception{
        return AlbumServer.uploadPhoto(files,userId,albumId);
    }

    @RequestMapping("/delPhoto")
    @ResponseBody
    public DataResult delPhotoStore(@RequestParam("albumId")String albumId,@RequestParam("photoId")String photoId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return PhotoServer.delPhotoStore(albumId,photoId);
    }

    @RequestMapping("/setCover")
    @ResponseBody
    public DataResult setCover(@RequestParam("albumId")String albumId,@RequestParam("photoId")String photoId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return AlbumServer.setCover(albumId,photoId);
    }

}

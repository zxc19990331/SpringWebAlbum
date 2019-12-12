package controller;

import entity.DataResult;
import model.Album;
import model.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import server.AlbumServer;

import java.util.List;

@Controller
public class AlbumController {
    @RequestMapping("/submitNewAlbum")
    @ResponseBody
    public DataResult submitNewAlbum(@RequestParam("title")String title,@RequestParam("desc")String desc,
                                     @RequestParam("userId")String userId){
        return AlbumServer.addNewAlbum(userId,title,desc);
    }

    @RequestMapping("/album")
    public ModelAndView enterAlbum(@RequestParam("albumId")String albumId,Model model){
//        TODO:容错判断
        List<Photo> photoList = (List<Photo>)AlbumServer.getPhotoInfoListByAlbumId(albumId).getData();
        Album album = (Album) AlbumServer.getAlbumInfoById(albumId).getData();
        model.addAttribute("photoList",photoList);
        model.addAttribute("albumInfo",album);
        return new ModelAndView("album_content");
    }


}

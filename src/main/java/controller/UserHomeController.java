package controller;

import model.Album;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.AlbumServer;
import server.UserServer;

import java.util.List;

@Controller
public class UserHomeController {

    @RequestMapping("/user")
    public String goToUserHome(@RequestParam("id")String id,Model model){
        User user = (User)UserServer.getUserInfoById(id).getData();
        List<Album> albumList = (List<Album>)AlbumServer.getAlbumInfoListByUserId(id).getData();
        model.addAttribute("userInfo",user);
        model.addAttribute("albumList",albumList);
        return "user_home";
    }

    @RequestMapping("/createAlbum")
    public String goToCreateAlbum(){
        return "album_create";
    }

    @RequestMapping("/uploadPhoto")
    public String goToUpload(@RequestParam("userId")String userId,Model model){
        List<Album> albumList = (List<Album>)AlbumServer.getAlbumInfoListByUserId(userId).getData();
        model.addAttribute("albumList",albumList);
        model.addAttribute("userId",userId);
        return "photo_upload";
    }

}

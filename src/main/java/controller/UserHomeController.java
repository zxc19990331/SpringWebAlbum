package controller;

import model.Album;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.AlbumServer;
import server.FollowServer;
import server.UserServer;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserHomeController {

    @RequestMapping("/user")
    public String goToUserHome(@RequestParam("id")String id,Model model,HttpSession session){
        User user = (User)UserServer.getUserInfoById(id).getData();
        List<Album> albumList = (List<Album>)AlbumServer.getAlbumInfoListByUserId(id).getData();

        User Myuser= (User) session.getAttribute("myInfo");
        int res = 0;
        if(Myuser!= null){
            if(FollowServer.checkFollow(Myuser.getId(),user.getId()).getStatus() == 0){
                res = 1;
            }
        }
        model.addAttribute("isFollow",res);
        model.addAttribute("userInfo",user);
        model.addAttribute("albumList",albumList);
        return "user_home";
    }

    @RequestMapping("/createAlbum")
    public String goToCreateAlbum(){
        return "album_create";
    }

    @RequestMapping("/uploadPhoto")
    public String goToUpload(Model model, HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        String userId = user.getId();
        List<Album> albumList = (List<Album>)AlbumServer.getAlbumInfoListByUserId(userId).getData();
        model.addAttribute("albumList",albumList);
        model.addAttribute("userId",userId);
        return "photo_upload";
    }

}

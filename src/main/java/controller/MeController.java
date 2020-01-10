package controller;

import entity.DataResult;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import server.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/me")
public class MeController {
    @RequestMapping("/info")
    public String enterMyInfo(HttpSession session,Model model){
        User user = (User)session.getAttribute("myInfo");
        model.addAttribute("myInfo",user);
        return "my_info";
    }

    @RequestMapping("/albums")
    public String enterMyAlbums(HttpSession session,Model model){
        User user = (User)session.getAttribute("myInfo");
        model.addAttribute("myInfo",user);
        return "my_albums";
    }

    @RequestMapping("/photos")
    public String enterMyPhotos(HttpSession session,Model model){
        User user = (User)session.getAttribute("myInfo");
        List<Album> albumList= (List<Album>)AlbumServer.getAlbumInfoListByUserId(user.getId()).getData();
        model.addAttribute("myInfo",user);
        model.addAttribute("albumList",albumList);
        return "my_photos";
    }

    @RequestMapping("/follow")
    public String enterMyFollows(HttpSession session,Model model){
          User user = (User)session.getAttribute("myInfo");

          List<User> FollowList = (List<User>) FollowServer.getFollowInfoById(user.getId()).getData();

          model.addAttribute("followList",FollowList);
          return "my_follow";
    }


    @RequestMapping("/messages")
    public String enterMyMessages(HttpSession session,Model model){
        User user = (User)session.getAttribute("myInfo");
        List<Message> reciveMessageList=(List<Message>) MessageServer.getMessageBytoId(user.getId()).getData();
        List<Message>sendMessageList=(List<Message>)MessageServer.getMessageByfromId(user.getId()).getData();
        model.addAttribute("myInfo",user);
        model.addAttribute("reciveMessageList",reciveMessageList);
        model.addAttribute("sendMessageList",sendMessageList);
        return "my_messages";
    }

    @RequestMapping("/editInfo")
    @ResponseBody
    public DataResult sumbitEditInfo(@RequestParam("name")String name,
                                     @RequestParam("desc")String desc, HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        DataResult dataResult =  UserServer.editBaseInfo(user.getId(),name,desc);
        if(dataResult.getStatus() == 0){
            //更新session
            session.setAttribute("myInfo",dataResult.getData());
        }
        return dataResult;
    }

    @RequestMapping("/changePhotoName")
    @ResponseBody
    public DataResult changePhotoName(@RequestParam("name")String name,@RequestParam("photoId")String photoId){
        return PhotoServer.changePhotoName(name,photoId);
    }

    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public DataResult uploadAvatar (@RequestParam("file") MultipartFile file,HttpSession session) throws Exception{
        User user = (User)session.getAttribute("myInfo");
        return UserServer.uploadAvatar(file,user.getId());
    }

    @RequestMapping("/getMyAlbum")
    @ResponseBody
    public DataResult getMyAlbum(@RequestParam("limit")int limit,
                                 @RequestParam("page")int page,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return AlbumServer.getAlbumInfoListByUserId(user.getId(),page,limit);
    }

    @RequestMapping("/getMyPhoto")
    @ResponseBody
    public DataResult getMyPhoto(@RequestParam("limit")int limit,
                                 @RequestParam("page")int page,@RequestParam("albumId")String albumId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return AlbumServer.getPhotoInfoListByAlbumId(albumId,page,limit);
    }

    @RequestMapping("/mysta")
    public String getStatistics(Model model,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        Statistics statistics =  (Statistics) StatisticsServer.getStatistics(user.getId()).getData();
        model.addAttribute("mystatis",statistics);
        return "my_statistics";
    }

}

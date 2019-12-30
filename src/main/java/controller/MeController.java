package controller;

import entity.DataResult;
import model.Album;
import model.Message;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.AlbumServer;
import server.MessageServer;
import server.PhotoServer;
import server.UserServer;

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
}

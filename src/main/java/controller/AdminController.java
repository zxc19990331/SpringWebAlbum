package controller;

import entity.DataResult;
import model.Operation;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.AlbumServer;
import server.OperationServer;
import server.UserServer;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/users")
    public String enterUsers(Model model, HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        model.addAttribute("myInfo",user);
        return "admin_manage_users";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public DataResult getUserList(@RequestParam("limit")int limit,
                                  @RequestParam("page")int page){
        return UserServer.getUserList(page,limit);
    }

    @RequestMapping("/operations")
    public String enterOperations(){
        return "admin_operations";
    }

    @RequestMapping("/getOperationList")
    @ResponseBody
    public DataResult getOperationList(@RequestParam("limit")int limit,
                                       @RequestParam("page")int page){
        return OperationServer.getOperationList(page,limit);
    }

    @RequestMapping("/albums")
    public String enterAlbums(){
        return "admin_manage_albums";
    }

    @RequestMapping("/getAlbumList")
    @ResponseBody
    public DataResult getAlbumList(@RequestParam("limit")int limit,
                                   @RequestParam("page")int page){
        return AlbumServer.getAllAlbum(page,limit);
    }

    @RequestMapping("/banUser")
    @ResponseBody
    public DataResult banUser(@RequestParam("userId")String userId,@RequestParam("note")String note,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return OperationServer.banUser(user.getId(),userId,note);
    }

    @RequestMapping("/debanUser")
    @ResponseBody
    public DataResult debanUser(@RequestParam("userId")String userId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return OperationServer.debanUser(user.getId(),userId,"");
    }

    @RequestMapping("/banAlbum")
    @ResponseBody
    public DataResult banAlbum(@RequestParam("albumId")String albumId,@RequestParam("note")String note,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return OperationServer.banAlbum(user.getId(),albumId,note);
    }

    @RequestMapping("/debanAlbum")
    @ResponseBody
    public DataResult debanAlbum(@RequestParam("albumId")String albumId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return OperationServer.debanAlbum(user.getId(),albumId,"");
    }


}

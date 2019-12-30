package controller;

import entity.DataResult;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.UserServer;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/setState")
    @ResponseBody
    public DataResult setState(@RequestParam("userId")String userId,@RequestParam("state")String state){
        return UserServer.setState(userId,state);
    }
}

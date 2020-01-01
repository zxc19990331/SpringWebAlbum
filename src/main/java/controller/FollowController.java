package controller;

import entity.DataResult;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.FollowServer;

import javax.servlet.http.HttpSession;

@Controller
public class FollowController {
    @RequestMapping("/delfow")
    @ResponseBody
    public DataResult delMyFollows(@RequestParam("TID")String tId,HttpSession session){
        System.out.println("DelFollow");
        User user= (User) session.getAttribute("myInfo");
        DataResult dataResult = FollowServer.deleteFollow(user.getId(),tId);
        System.out.println(dataResult.getStatus());
        return dataResult;
    }

    @RequestMapping("/addfow")
    @ResponseBody
    public DataResult addMyFollows(@RequestParam("TID")String tId, HttpSession session){
        User user= (User) session.getAttribute("myInfo");
        System.out.println("AddFollow" + user.getId() + " " + tId);
        DataResult dataResult = FollowServer.addFollow(user.getId(),tId);
        System.out.println(dataResult.getMsg());
        return dataResult;
    }
}

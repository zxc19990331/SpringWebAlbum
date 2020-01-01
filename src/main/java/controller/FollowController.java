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
    public DataResult delMyFollows(@RequestParam("FID")String fId,
                                   @RequestParam("TID")String tId){
        System.out.println("DelFollow");
        DataResult dataResult = FollowServer.deleteFollow(fId,tId);
        System.out.println(dataResult.getStatus());
        return dataResult;
    }

    @RequestMapping("/addfow")
    @ResponseBody
    public DataResult addMyFollows(@RequestParam("id")String tId, HttpSession session){
        System.out.println("AddFollow");
        User user= (User) session.getAttribute("myInfo");
        DataResult dataResult = FollowServer.addFollow(user.getId(),tId);
        System.out.println(dataResult.getMsg());
        return dataResult;
    }

}

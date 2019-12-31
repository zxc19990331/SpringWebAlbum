package controller;

import dao.MessageDAO;
import entity.DataResult;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import server.MessageServer;

import javax.servlet.http.HttpSession;

/**
 * Created by hi on 2019/12/30.
 */
@Controller
public class MessageController {
    @RequestMapping("/sendMessage")
    public String goToSendMessage(@RequestParam("id")String toId, Model model){
        model.addAttribute("toId",toId);
        return "send_message";
    }
    @RequestMapping(value="/sendmessageS",method = RequestMethod.POST)
    @ResponseBody
    public DataResult sendmessageS(@RequestParam("context")String context, @RequestParam("toId")String toId, HttpSession session){
        System.out.print("发送内容："+context);
        String fromId=((User)session.getAttribute("myInfo")).getId();
        return MessageServer.sendMessage(fromId,toId,context);
    }
}

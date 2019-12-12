package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import entity.DataResult;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import server.AlbumServer;
import server.UserServer;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(value = {"isLogin","myInfo"})
public class IndexController {

    @RequestMapping("/")
    public String main(){
        return "redirect:index";
    }

    @RequestMapping("/index")
    public ModelAndView index() throws Exception{
        return new ModelAndView("index");
    }

    @RequestMapping("/home")
    public ModelAndView goToHome(Model model){
        model.addAttribute("albumList", AlbumServer.getAllAlbum().getData());
        return new ModelAndView("album_list");
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public String register(@RequestParam("loginid")String loginname,
                           @RequestParam("password")String password,
                           @RequestParam("username")String username,Model model) {
        if(UserDAO.isUserIdExist(loginname)){

            model.addAttribute("msg","该用户名已存在！");
            System.out.println("已存在该用户名");
        }else{
            model.addAttribute("msg","该用户名可用！");
            System.out.println("该用户名可用！");
        }
        return "forward:index";
    }

    @RequestMapping(value="/registerJ",method = RequestMethod.POST)
    @ResponseBody
    public DataResult registerJ(@RequestParam("id")String id,
                                @RequestParam("password")String password,
                                @RequestParam("name")String name){
        return UserServer.register(id,password,name);
    }


    //登录
    @RequestMapping("/login")
    @ResponseBody
    public DataResult login(@RequestParam("id")String loginname,
                            @RequestParam("password")String password, Model model, HttpSession httpSession){
        DataResult dataResult =  UserServer.login(loginname,password);
        //登录成功了
        if(dataResult.getStatus() == 0){
            model.addAttribute("isLogin",true);
            model.addAttribute("myInfo",dataResult.getData());
        }
        return dataResult;
    }

    //获得用户信息
    @RequestMapping("/getUserInfoById")
    @ResponseBody
    public DataResult getUserInfoById(@RequestParam(name="id",required=true) String id){
        return UserServer.getUserInfoById(id);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(Model model,HttpSession httpSession){
        httpSession.invalidate();
        model.addAttribute("isLogin",false);
    }
}

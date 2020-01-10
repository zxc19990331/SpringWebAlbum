package controller;

import constant.Constant;
import entity.DataResult;
import model.Album;
import model.Comment;
import model.Photo;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import server.AlbumServer;
import server.CommentServer;
import server.FollowServer;
import server.PhotoServer;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AlbumController {
    @RequestMapping("/submitNewAlbum")
    @ResponseBody
    public DataResult submitNewAlbum(@RequestParam("title")String title,@RequestParam("desc")String desc,
                                     @RequestParam("userId")String userId, @RequestParam("type")String category){
        System.out.println("SUBMIT" + title + desc + userId + category);
        return AlbumServer.addNewAlbum(userId,title,desc,category);
    }

    @RequestMapping("/album")
    public ModelAndView enterAlbum(@RequestParam("albumId")String albumId,Model model,HttpSession session){
//        TODO:容错判断 排序功能
        List<Photo> photoList = (List<Photo>)AlbumServer.getPhotoInfoListByAlbumId(albumId, Constant.ORDER_DATE_ASC).getData();
        Album album = (Album) AlbumServer.getAlbumInfoById(albumId).getData();

        try {
            List<Comment> albumCommentList = (List<Comment>) CommentServer.getCommentInfoByAlbumId(albumId).getData();
            model.addAttribute("commentInfo",albumCommentList);
        }catch (Exception e){
            e.printStackTrace();
        }

        User user= (User) session.getAttribute("myInfo");
        int res = 0;
        if(user!= null){
            if(FollowServer.checkFollow(user.getId(),album.getUserId()).getStatus() == 0){
                res = 1;
            }
        }
        System.out.println("ALBUM FOLLOW:" + res);
        model.addAttribute("photoList",photoList);
        model.addAttribute("albumInfo",album);
        model.addAttribute("isFollow",res);
        return new ModelAndView("album_content");
    }

    @RequestMapping("/editAlbum")
    public String editAlbum(@RequestParam("albumId")String albumId,Model model){
        Album album = (Album)AlbumServer.getAlbumInfoById(albumId).getData();
        model.addAttribute("album",album);
        return "album_edit";
    }

    @RequestMapping("/submitEditAlbum")
    @ResponseBody
    public DataResult submitEditAlbum(@RequestParam("title")String title,@RequestParam("desc")String desc,
                                  @RequestParam("userId")String userId, @RequestParam("albumId")String albumId,@RequestParam("type")String category){
        return AlbumServer.editAlbumBaseInfo(userId,albumId,title,desc,category);
    }

    @RequestMapping("/delAlbum")
    @ResponseBody
    public DataResult delAlbum(@RequestParam("albumId")String albumId, HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        System.out.println("seesion myInfo:" + user.getId());
        return AlbumServer.delAlbum(user.getId(),albumId);
    }


    //返回json格式的数据
    @RequestMapping("/upload")
    @ResponseBody
    public DataResult upload(@RequestParam("file") MultipartFile[] files,@RequestParam("userId")String userId,
                         @RequestParam("albumId")String albumId) throws Exception{
        return AlbumServer.uploadPhoto(files,userId,albumId);
    }

    @RequestMapping("/delPhoto")
    @ResponseBody
    public DataResult delPhotoStore(@RequestParam("albumId")String albumId,@RequestParam("photoId")String photoId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return PhotoServer.delPhotoStore(albumId,photoId);
    }

    @RequestMapping("/setCover")
    @ResponseBody
    public DataResult setCover(@RequestParam("albumId")String albumId,@RequestParam("photoId")String photoId,HttpSession session){
        User user = (User)session.getAttribute("myInfo");
        return AlbumServer.setCover(albumId,photoId);
    }


    @RequestMapping("/addComment")
    @ResponseBody
    public DataResult addNewComment(@RequestParam("TEXT")String commentText, @RequestParam("AID")String aId,HttpSession session){
        User user= (User) session.getAttribute("myInfo");
        if(user!= null){
            System.out.println("AddComment添加评论");
            String uId = user.getId();
            return CommentServer.addNewComment(uId,aId,commentText);
        }else{
            System.out.println("AddComment 未登录");
            DataResult dataResult = new DataResult();
            dataResult.setStatus(2);
            dataResult.setMsg("没有登录");
            return dataResult;
        }
    }

    @RequestMapping("/delcomment")
    @ResponseBody
    public DataResult delComment(@RequestParam("CID")String cId, @RequestParam("UID")String uId,HttpSession session){
        User user= (User) session.getAttribute("myInfo");
        System.out.println("删除评论！！！！！" + uId + "***" + user.getId());
        if(user!= null && user.getId().equals(uId)){
            System.out.println("DelComment删除评论");
            return CommentServer.delComment(cId);
        }else{
            System.out.println("删除无效");
            DataResult dataResult = new DataResult();
            dataResult.setStatus(2);
            dataResult.setMsg("没有删除权限");
            return dataResult;
        }
    }

    @RequestMapping("/addPraise")
    @ResponseBody
    public DataResult addPraise(@RequestParam("albumId")String albumId){
        return AlbumServer.addAlbumPraise(albumId);
    }



}

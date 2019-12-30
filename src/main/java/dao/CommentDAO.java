package dao;

import util.DateHelper;
import util.ShortUUID;

import java.util.List;
import java.util.Map;

    public class CommentDAO {

        public static List<Map<String, Object>> getCommentInfoListAll(){
            return JDBCDAO.select(String.format("SELECT * FROM comment"));
        }

        public static List<Map<String, Object>> getCommentInfoListByUserId(String userId){
            return JDBCDAO.select(String.format("SELECT * FROM comment WHERE user_id = '%s'",userId));
        }

        public static List<Map<String, Object>> getcommentInfoListByAlbumId(String albumId){
            return JDBCDAO.select(String.format("SELECT * FROM comment WHERE from_id  = '%s'",albumId));
        }

        public static Map<String, Object> getcommentInfoById(String Id){
            return JDBCDAO.select(String.format("SELECT * FROM comment WHERE id = '%s'",Id)).get(0);
        }

        public static String createNewComment(String userId,String albumId,String context){
            String commentId = ShortUUID.generateShortUuid();
            String curDate = DateHelper.getCurrentDate();
            boolean res = JDBCDAO.insertOrDeleteOrUpdate(String.format("INSERT INTO comment VALUES('%s','%s','%s','%s','%s')",
                    commentId,context,curDate,userId,albumId));
            if(res){
                return commentId;
            }else {
                return null;
            }
        }

        public static boolean isCommentExist(String commentId){
            List<Map<String, Object>> res = JDBCDAO.select(String.format("SELECT * FROM album WHERE id = '%s'",commentId));
            return !(res.size()==0);
        }
    }



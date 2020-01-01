package model;

import constant.Constant;

import java.util.Map;

public class Statistics {
    private int AlbumCount = -1;
    private int CommentCount = -1;
    private int FollowCount = -1;
    private int FollowedCount = -1;
    private int PhotoCount = -1;

    public Statistics(){

    }

    public Statistics(Map<String,Integer> map){
        AlbumCount = map.get(Constant.STATISTICS_ALBUM);
        CommentCount = map.get(Constant.STATISTICS_COMMENT);
        FollowCount = map.get(Constant.STATISTICS_FOLLOW);
        FollowedCount = map.get(Constant.STATISTICS_FOLLOWED);
        PhotoCount = map.get(Constant.STATISTICS_PHOTO);
    }

    public void setAlbumCount(int albumCount) {
        this.AlbumCount = albumCount;
    }
    public int getAlbumCount() {
        return AlbumCount;
    }

    public void setCommentCount(int commentCount) {
        this.CommentCount = commentCount;
    }
    public int getCommentCount() {
        return CommentCount;
    }

    public void setFollowCount(int followCount) {
        this.FollowCount = followCount;
    }
    public int getFollowCount() {
        return FollowCount;
    }

    public void setFollowedCount(int followedCount) {
        this.FollowedCount = followedCount;
    }
    public int getFollowedCount() {
        return FollowedCount;
    }

    public void setPhotoCount(int photoCount) {
        this.PhotoCount = photoCount;
    }
    public int getPhotoCount() {
        return PhotoCount;
    }

}

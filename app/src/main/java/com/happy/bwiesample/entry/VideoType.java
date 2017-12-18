package com.happy.bwiesample.entry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/16
 * @Time 10:59
 */

public class VideoType {
    public String title;
    public String moreURL;
    public String pic;
    public String dataId;
    public String airTime;
    public String score;
    public String description;
    public String msg;
    public String phoneNumber;
    public String userPic;
    public String time;
    public String likeNum;
    public
    @SerializedName("childList")
    List<VideoInfo> childList;
}

package com.happy.bwiesample.entry;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 18:59
 */

public class VrImageItem {
    private String mName,imgUrl,musicUrl;

    public VrImageItem(String mName, String imgUrl, String musicUrl) {
        this.mName = mName;
        this.imgUrl = imgUrl;
        this.musicUrl = musicUrl;
    }
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
    @Override
    public String toString() {
        return "VrImageItem{" +
                "mName='" + mName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                '}';
    }
}

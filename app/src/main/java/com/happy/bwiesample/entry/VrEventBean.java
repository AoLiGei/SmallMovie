package com.happy.bwiesample.entry;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/19
 * @Time 13:39
 */

public class VrEventBean {
    private int type;
    private String resName;
    private String resUrl;
    private String musicUrl;

    public VrEventBean(int type, String resName, String resUrl, String musicUrl) {
        this.type = type;
        this.resName = resName;
        this.resUrl = resUrl;
        this.musicUrl = musicUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @Override
    public String toString() {
        return "VrEventBean{" +
                "type=" + type +
                ", resName='" + resName + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                '}';
    }
}

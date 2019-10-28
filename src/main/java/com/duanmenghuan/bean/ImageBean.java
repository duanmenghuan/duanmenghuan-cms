package com.duanmenghuan.bean;

import java.io.Serializable;

public class ImageBean implements Serializable {
    private static final long serialVersionUID = -2634054380172386995L;

    String desc;
    String picUrl;

    public ImageBean() {
    }

    public ImageBean(String desc, String picUrl) {
        this.desc = desc;
        this.picUrl = picUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "desc='" + desc + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }



}

package com.duanmenghuan.bean;

import java.util.Objects;

public class Blogroll {
    private  Integer id; //主键
    private  String  text; // 友情链接文本
    private  String  url; // 友情链接地址
    private  String created;// 时间 倒叙 DESC

    public Blogroll() {
    }

    public Blogroll(Integer id, String text, String url, String created) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("com.duanmenghuan.bean.Blogroll{");
        sb.append("id=").append(id);
        sb.append(",text='").append(text);
        sb.append(",url='").append(url);
        sb.append(",created='").append(created);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogroll blogroll = (Blogroll) o;
        return Objects.equals(id, blogroll.id) &&
                Objects.equals(text, blogroll.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}

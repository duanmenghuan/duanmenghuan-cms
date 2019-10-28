package com.duanmenghuan.bean;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Special {

    private int id;  //主键

    private String title; //文章标题

    private String  digest;

    private Date  create;

    private  Integer articleNum;

    List<Article> artilceList;

    public Special() {
    }

    public Special(int id, String title, String digest, Date create, Integer articleNum) {
        this.id = id;
        this.title = title;
        this.digest = digest;
        this.create = create;
        this.articleNum = articleNum;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Integer getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    public List<Article> getArtilceList() {
        return artilceList;
    }

    public void setArtilceList(List<Article> artilceList) {
        this.artilceList = artilceList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("com.duanmenghuan.bean.Special{");
        sb.append("id=").append(id);
        sb.append(",title='").append(title);
        sb.append(",digest='").append(digest);
        sb.append(",create=").append(create);
        sb.append(",articleNum=").append(articleNum);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return id == special.id &&
                title.equals(special.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}

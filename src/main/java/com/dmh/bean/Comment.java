package com.dmh.bean;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements  Serializable {


    private static final long serialVersionUID = 1957350226397653270L;
    private  Integer   id;
    private  Integer articleId;
    private  int userId;
    private  String  content;
    private  String  created;

    public Comment() {

    }


    public Comment(Integer articleId, int userId, String content, String created) {
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.created = created;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}

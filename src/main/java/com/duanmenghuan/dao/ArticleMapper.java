package com.duanmenghuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.duanmenghuan.bean.Article;
import com.duanmenghuan.bean.Tag;

/**
 * 文章管理
 *
 * @author duanmenghuan
 */
public interface ArticleMapper {

    List<Article> list(@Param("chnId") Integer chnId,
                       @Param("catId") Integer catId);

    List<Article> articleList();

    List<Article> lastlist(int i);

    //@Select("SELECT * FROM cms_article WHERE id =#{id} and deleted = '0' ")
    Article oneArticle(@Param("id") Integer id);

    int add(Article article);

    List<Article> listByUserId(@Param("id") Integer id);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @Update("update cms_article SET deleted=1 where id=#{id}")
    int remove(@Param("id") Integer id);

    /**
     * 修改文章
     *
     * @param article
     * @return
     */
    @Update("UPDATE cms_article set title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
            + "category_id=#{categoryId},updated=now() WHERE id=#{id}")
    int update(Article article);

    List<Article> listAdmin();

    @Update("UPDATE cms_article set status=#{status},updated=now() WHERE id=#{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") int status);

    @Update("UPDATE cms_article set hot=#{status},updated=now() WHERE id=#{id}")
    int updateHot(@Param("id") Integer id, @Param("status") int status);

    /**
     * 通过标签名字查找
     *
     * @param tag 传过来的名字
     * @return
     */
    @Select("SELECT * FROM cms_term WHERE display_name=#{tag} limit 1")
    Tag findTagByName(@Param("tag") String tag);

    /**
     * 曾加实体备案
     *
     * @param tagBean
     */
    void addTag(Tag tagBean);

    /**
     * 把  文章的id 和标签的id  添加到中间表
     *
     * @param id  文章的id
     * @param id2 标签的id
     */
    @Insert("INSERT INTO cms_article_term values(#{id},#{id2})")
    void addArticleTag(@Param("id") Integer id, @Param("id2") Integer id2);

    /**
     * 删除中间表
     *
     * @param id 中间表中的文章ID
     */
    @Delete("Delete from cms_article_term WHERE aid=#{value} ")
    void deleteByArticleId(Integer id);

    @Select("SELECT COUNT(1) FROM cms_special_article sa JOIN cms_article a on sa.aid=a.id WHERE sa.sid=#{value}")
    Integer getArticleNum(int id);

    /**
     * 根据主题id获取文章列表
     * @param id
     * @return
     *
     */
    @Select("SELECT a.id,a.title,a.created FROM cms_special_article sa JOIN cms_article a on sa.aid=a.id WHERE sa.sid=#{value}")
    List<Article> findBySepecailId(Integer id);
}

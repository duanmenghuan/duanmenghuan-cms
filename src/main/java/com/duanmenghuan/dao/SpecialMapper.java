package com.duanmenghuan.dao;

import com.duanmenghuan.bean.Special;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SpecialMapper {
    /**
     * 获取专题的列表
     * @return
     */
    @Select("SELECT id,title,abstract as digest,created FROM cms_special ORDER BY id desc")
    List<Special> list();

    /**
     * 添加专题
     * @param special
     * @return
     */
    @Insert("INSERT INTO cms_special (title,abstract,created) VALUES(#{title},#{digest},now())")
    int add(Special special);

    /**
     * 向专题加入文章
     * @param specId
     * @param articleId
     * @return
     */
    @Insert("INSERT INTO cms_special_article(sid,aid) VALUES(#{sid},#{aid})")
    int addArticle(@Param("sid") Integer specId, @Param("aid")Integer articleId);

    /**
     *
     * @param id
     * @return
     */
    @Select("SELECT id,title,abstract as digest,created FROM cms_special WHERE id =#{value}")
    Special findById(Integer id);

    @Delete(" DELETE FROM cms_special_article WHERE sid = #{sid} AND aid = #{aid}")
    int removeArticle(@Param("sid") Integer specId,@Param("aid") Integer articleId);

    /**
     * 修改专题
     * @param special
     * @return
     * UPDATE cms_special SET title=#{title},abstract=#{digest} "
     * 			+ " WHERE id=#{id}
     */
    @Update("UPDATE cms_special SET title=#{title},abstract=#{digest} WHERE id=#{id}")
    int update(Special special);
}

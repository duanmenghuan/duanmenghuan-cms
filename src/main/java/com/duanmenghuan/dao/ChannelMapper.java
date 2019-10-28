package com.duanmenghuan.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.duanmenghuan.bean.Channel;

public interface ChannelMapper {

    /**
     * 获取所有的频道
     *
     * @return
     */
    @Select("SELECT * FROM cms_channel ORDER BY id")
    List<Channel> listAll();

    @Select(" SELECT * FROM cms_channel WHERE id = #{id}")
    Channel findById(@Param("id") Integer id);
}

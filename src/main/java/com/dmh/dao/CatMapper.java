package com.dmh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dmh.bean.Cat;

@Mapper
public interface CatMapper {

	@Select("SELECT id,name,channel_id channelId " 
			+" FROM cms_category "
			+ " WHERE channel_id=#{id}")
	List<Cat> selectByChnlId(@Param("id")Integer id);
	
	@Select("SELECT * FROM cms_category WHERE id = #{id} ")
	Cat findById(@Param("id")Integer id);

}

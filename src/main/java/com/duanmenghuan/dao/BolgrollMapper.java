package com.duanmenghuan.dao;

import com.duanmenghuan.bean.Blogroll;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BolgrollMapper {

    @Select("SELECT * FROM cms_blogroll ORDER BY created DESC ")
    List<Blogroll> list();
    @Insert("INSERT INTO `cms`.`cms_blogroll` (`text`, `url`, `created`) VALUES (#{text},#{url},now())")
    int add(Blogroll blogroll);

}

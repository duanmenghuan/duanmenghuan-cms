package com.duanmenghuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.duanmenghuan.bean.User;


public interface UserMapper {

    @Insert("INSERT INTO cms_user(username,password,gender,create_time) "
            + "values(#{username},#{password},#{gender},now())")
    int add(User user);

    @Select("SELECT id,username,password,role,locked FROM cms_user where username=#{value} AND locked = '0'  limit 1")
    User findByName(String username);

    @Select("select * from cms_user where id=#{id}")
    User findUserById(@Param("id") Integer id);

    List<User> userlist(@Param("name") String name);


    @Update("update cms_user set locked=#{locked} WHERE id=#{userid}")
    int userlist1(@Param("locked") int locked, @Param("userid") int userid);

    @Select("SELECT * FROM cms_user WHERE username=#{name}")
    List<User> listusername(@Param("name") String name);


}

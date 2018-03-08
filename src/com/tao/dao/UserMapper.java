package com.tao.dao;

import com.tao.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Author: Douglass
 * Date: 2016/3/31
 * E-mail: xuetao_ni@163.com
 */

public interface UserMapper {
    //获取name="name"的行
    @Select("select * from user where Lname = #{name}")
    public Map<String, Object> selectUserByName(String name);

    public void addUser(UserEntity user);

    public UserEntity getUsersByLname(String Lname);
}

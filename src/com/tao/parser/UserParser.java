package com.tao.parser;

import com.tao.entity.UserEntity;
import com.tao.log.LogUtils;
import net.sf.json.JSONObject;

/**
 * 用户信息Json解析器
 * Author: Douglass
 * Date: 2016/3/28
 * E-mail: xuetao_ni@163.com
 */
public class UserParser implements Parser<UserEntity> {
    @Override
    public UserEntity build(JSONObject object) {
        UserEntity entity = new UserEntity();

        entity.setName(object.getString("Lname"));
        entity.setPasswd(object.getString("passwd"));
        //entity.setRole(object.getString("role"));
        LogUtils.I(entity.toString());
        return entity;
    }
}

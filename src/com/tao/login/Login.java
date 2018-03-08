package com.tao.login;

import com.tao.dao.UserMapper;
import com.tao.db.DBSession;
import com.tao.entity.UserEntity;
import com.tao.json.JsonOper;
import com.tao.log.LogUtils;
import com.tao.parser.ParserMap;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * Author: Douglass
 * Date: 2016/4/5
 * E-mail: xuetao_ni@163.com
 */
public class Login {
    public static void login(ChannelHandlerContext ctx, String data, int type){
        /**
         * login 处理登陆
         * @author: Douglass
         * @time: 2016/4/5 16:15
         * @params: [ctx, msg]
         * @return: void
         * @throw:
         */
        UserEntity entity1 = (UserEntity)ParserMap.get(type).build(JSONObject.fromObject(data));
        SqlSession session = DBSession.getInstance().getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, Object> entity2 = userMapper.selectUserByName(entity1.getName());
        System.out.println(entity2);

        if (entity2 == null){
            LogUtils.I("查无此人！");
            ctx.writeAndFlush("查无此人！\r\n");
        }else {
            if (entity1.getName().equals(entity2.get("Lname")) && entity1.getPasswd().equals(entity2.get("passwd"))){
                if(entity2.get("role").equals("m"))
                    LogUtils.I("管理员登陆成功！");
                else
                    LogUtils.I("医生登陆成功！");
                String ans = JsonOper.put2Json(entity2);
                LogUtils.I(ans);
                ctx.writeAndFlush(ans + "\r\n");
            }else {
                LogUtils.I("账户或密码错误！");
                ctx.writeAndFlush("账户或密码错误！\r\n");
            }
        }
        session.close();
    }
}

package com.tao.Docter;

import com.tao.dao.DocterMapper;
import com.tao.dao.UserMapper;
import com.tao.entity.DocterEntity;
import com.tao.entity.UserEntity;
import com.tao.json.JsonOper;
import com.tao.log.LogUtils;
import com.tao.parser.ParserMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.unix.Socket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LHL on 2016/8/4.
 */
public class DocterOperation {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    //根据医生姓名查询医生信息
    public static void getDocterList(ChannelHandlerContext ctx, String data, int type) {
        DocterEntity entity1 = (DocterEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            DocterMapper docterMapper = session.getMapper(DocterMapper.class);
            List<DocterEntity> docters = docterMapper.getDocterList(entity1.getDname());
            Map<String,Object> entity2=docterMapper.selectDocterByDname(entity1.getDname());
            System.out.println(entity2);
            // 显示Docters信息
            for (DocterEntity docter : docters) {
                System.out.println("Docter Id: " + docter.getDid());
                System.out.println("Docter Name: " + docter.getDname());
                System.out.println("Docter Sex: " + docter.getSex());
                System.out.println("Docter Depart: " + docter.getDepart());
                System.out.println("Docter Dphone: " + docter.getDphone());
                System.out.println("Docter Dlogname: " + docter.getDlogname());
                System.out.println("Docter Dpword: " + docter.getDpword());
            }
            String ans = JsonOper.put2Json(entity2);
            LogUtils.I(ans);
            ctx.writeAndFlush(ans + "\r\n");
            LogUtils.I("查询完成！");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //增加医生记录
    public static void addDocter(ChannelHandlerContext ctx, String data, int type) {
        /**
         * addDocter 增加医生
         * @params: [ctx, msg]
         * @return: void
         * @throw:
         */
        DocterEntity entity1 = (DocterEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        try {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();

            // 执行插入到医生表
            DocterMapper docterMapper = session.getMapper(DocterMapper.class);  // 获取Mapper
            DocterEntity docter = new DocterEntity();
            if(docterMapper.getDoctersByDname(entity1.getDname())==null){
                docter.setDname(entity1.getDname());
                docter.setSex(entity1.getSex());
                docter.setDepart(entity1.getDepart());
                docter.setDphone(entity1.getDphone());
                docter.setDlogname(entity1.getDlogname());
                docter.setDpword(entity1.getDpword());
                docterMapper.addDocter(docter);
                // 提交事务
                LogUtils.I("添加医生成功！");
            }
            else
                LogUtils.I("记录已存在！");

            //插入到用户表
            UserMapper userMapper=session.getMapper(UserMapper.class);
            UserEntity user=new UserEntity();
            if(userMapper.getUsersByLname(entity1.getDlogname())==null){
                user.setName(entity1.getDlogname());
                user.setPasswd(entity1.getDpword());
                user.setRole("d");
                userMapper.addUser(user);
                LogUtils.I("添加用户成功！");
            }
            else
                LogUtils.I("记录已存在！");

            session.commit();
        }
        catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    //更新医生记录
    public static void updateDocter(ChannelHandlerContext ctx, String data, int type) {
        /**
         * 修改后要commit
         * 更新数据表
         *  * @param [ctx, msg]
         */
        DocterEntity entity1 = (DocterEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            // 获取Session连接
            // 获取Mapper
            System.out.println("Test update start...");
            // 执行更新
            DocterMapper docterMapper = session.getMapper(DocterMapper.class);
            DocterEntity docter = docterMapper.getDoctersByDname(entity1.getDname());
            if(!docter.equals(" ")){
                docter.setDepart(entity1.getDepart());
                docterMapper.updateDocter(docter);
                // 提交事务
                LogUtils.I("更新成功！");
                session.commit();
            }
            else
                LogUtils.I("错误操作！");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //删除医生记录
    public static void deleteDocter(ChannelHandlerContext ctx, String data, int type) {
        /**
         * 删除后要commit.
         *删除医生信息
         * @param [ctx, msg]
         */
        DocterEntity entity1 = (DocterEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        SqlSession session = sqlSessionFactory.openSession();
        DocterMapper docterMapper = session.getMapper(DocterMapper.class);
        try {
            if(docterMapper.getDoctersByDname(entity1.getDname())!=null){
                docterMapper.deleteDocter(entity1.getDname());
                LogUtils.I("删除记录成功！");
                ctx.writeAndFlush("删除成功" + "\r\n");

            }
           else {
                LogUtils.I("记录不存在！");
                ctx.writeAndFlush("操作错误" + "\r\n");
            }
            session.commit();
        } finally {
            session.close();
        }
    }

    //查询所有医生信息
    public static void getAllDocters(ChannelHandlerContext ctx, int type){
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            DocterMapper docterMapper = session.getMapper(DocterMapper.class);
            // 显示Docter信息
            List<String> docterList = docterMapper.getDocterlist();
            for(int i=0;i<docterList.size();i++){
                DocterEntity entity=docterMapper.getDoctersByDname(docterList.get(i));
                Map<String,Object> entity2=docterMapper.selectDocterByDname(entity.getDname());
                String ans = JsonOper.put2Json(entity2);
                LogUtils.I(ans);
                ctx.writeAndFlush(ans + "\r\n");
            }
            System.out.println("Test Get start...");
            printDocters(docterMapper.selectAllDocters());
            System.out.println("Test Get finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //打印医生信息
    private static void printDocters(final List<DocterEntity> docters)
    {
        int count = 0;
        for (DocterEntity docter : docters)
        {
            System.out.println(MessageFormat.format("============= Docter[{0}]=================", ++count));
                System.out.println("Docter Id: " + docter.getDid());
                System.out.println("Docter Name: " + docter.getDname());
                System.out.println("Docter Sex: " + docter.getSex());
                System.out.println("Docter Depart: " + docter.getDepart());
                System.out.println("Docter Dphone: " + docter.getDphone());
                System.out.println("Docter Dlogname: " + docter.getDlogname());
                System.out.println("Docter Dpword: " + docter.getDpword());
            }
        }


}


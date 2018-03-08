package com.tao.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Author: Douglass
 * Date: 2016/4/5
 * E-mail: xuetao_ni@163.com
 */
public class DBSession {
    private static DBSession instance = null;
    private DBSession(){

    }

    public static DBSession getInstance(){
        if (instance == null){
            synchronized (DBSession.class){
                if (instance == null){
                    instance = new DBSession();
                }
            }
        }
        return instance;
    }

    public synchronized SqlSession getSession(){
        String resource = "config.xml";
        InputStream is = DBSession.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sessionFactory.openSession();
    }
}

package cn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class DataAccessTest {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载驱动成功");
            String sqlUrl = "jdbc:mysql://127.0.0.1:3306/test";
            Connection connection = DriverManager.getConnection(sqlUrl, "root", "");
            Statement statement = connection.createStatement();
            System.out.println("成功连接数据库");
            /*
                查询数据库
             */
            String sql = "select * from user";
            ResultSet set = statement.executeQuery(sql);

            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

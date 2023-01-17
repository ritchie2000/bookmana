package com.edu.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {  
    // 1、导入druid.jar包  
    // 2、在druid.properties文件中编写好配置  
  
    private static DataSource dataSource;  
  
    // 3、静态代码块在类加载时执行，且只执行一次  
    static {  
        // 创建properties对象，读取配置  
        Properties properties = new Properties();  
        try {  
        	// Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。 
//            properties.load(new FileInputStream("D:\\Users\\Documents\\eclipse-workspace\\bookmanage\\src\\druid.properties"));  
        	properties.load(JdbcUtils.class.getResourceAsStream("/druid.properties"));
            // 创建Druid连接池  
            dataSource = DruidDataSourceFactory.createDataSource(properties);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /**  
     * 从数据库连接池中获取连接  
     * @return  
     * @throws SQLException  
     */  
    public static Connection getConnection() throws SQLException {  
    	
        return dataSource.getConnection();  
    }  
  
    /**  
     * close是把connection连接对象放回到连接池中  
     * @param connection  
     * @param statement  
     * @param resultSet  
     */  
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {  
        DbUtils.closeQuietly(connection);  
        DbUtils.closeQuietly(statement);  
        DbUtils.closeQuietly(resultSet);  
    }  
    
    /**
     * 关闭连接
     * @param connection
     */
    public static void closeConnection(Connection connection) {  
        DbUtils.closeQuietly(connection);   
    } 
}
package com.briup.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接的工具类
 * @author faple
 *
 */
public class DBUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection connection;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	static {
		Properties properties = new Properties();
		//读取文件
		try {
			properties.load(DBUtil.class.getClassLoader()
					.getResourceAsStream("dbsorce.properties"));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
//			System.out.println(driver);
//			System.out.println(url);
//			System.out.println(username);
//			System.out.println(password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接对象
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 使用PreparedStatement执行查询语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql, Object... params ) {
		getConnection();
		try {
			pstmt = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i ++) {
				pstmt.setObject(i + 1, params[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	/**
	 * 使用PreparedStatement对象执行insert、update、delete语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		getConnection();
		int num = 0;
		try {
			pstmt = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i ++) {
				pstmt.setObject(i + 1, params[i]);
			}
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return num;
	}
	
	/**
	 * 释放资源
	 */
	public static void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
	}
}

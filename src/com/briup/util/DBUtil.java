package com.briup.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���ݿ����ӵĹ�����
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
		//��ȡ�ļ�
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
	 * ��ȡ���Ӷ���
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
	 * ʹ��PreparedStatementִ�в�ѯ���
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
	 * ʹ��PreparedStatement����ִ��insert��update��delete���
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
	 * �ͷ���Դ
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

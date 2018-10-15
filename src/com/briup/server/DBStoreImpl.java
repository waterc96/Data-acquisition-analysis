package com.briup.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.config.ModuleInit;
import com.briup.logger.Log;
import com.briup.logger.LogImpl;
import com.briup.util.DBUtil;

public class DBStoreImpl implements DBStore, ModuleInit{
	private int batchSize;
	private PreparedStatement pstmt;
	private Log log = new LogImpl();
	@Override
	public void init(Properties properties) {
		batchSize = Integer.parseInt
				(properties.getProperty("batchSize"));
	}

	@Override
	public void saveToDB(Collection<Environment> collection) {
		log.info("开始连接数据库..........");
		Connection connection = DBUtil.getConnection();
		//计算批处理条数
		int count = 0;
		//表示天数变化
		int nextDay = 0;
		log.info("开始入库................");
		for(Environment env:collection) {
			//获取日期
			int day = getDay(env);
			if(pstmt == null || nextDay != day) {
				nextDay = day;
				//处理是昨天的数据
				if(pstmt != null) {
					try {
						pstmt.executeBatch();
						pstmt.clearBatch();
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				String sql = "insert into e_detail_" + 
						day + " values(?,?,?,?,?,?,?,?,?)";
				try {
					pstmt = connection.prepareStatement(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				pstmt.setString(1, env.getName());
				pstmt.setString(2, env.getSrcId() + "");
				pstmt.setString(3, env.getDesId() + "");
				pstmt.setString(4, env.getSensorAddress() + "");
				pstmt.setInt(5, env.getCounter());
				pstmt.setString(6, env.getCmd() + "");
				pstmt.setInt(7, env.getStatus());
				pstmt.setDouble(8, env.getData());
				pstmt.setTimestamp(9, env.getTime());
				pstmt.addBatch();
				if(++count % batchSize == 0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}
		
		//处理残留数据
		if(pstmt != null) {
			try {
				pstmt.executeBatch();
				pstmt.clearBatch();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		log.info("入库结束............");
	}

	/**
	 * 获取每个对象对应的日期
	 * @param env 环境对象
	 * @return 日期
	 */
	private int getDay(Environment env) {
		//获取月份的某一天
		Timestamp time = env.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
}

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
		log.info("��ʼ�������ݿ�..........");
		Connection connection = DBUtil.getConnection();
		//��������������
		int count = 0;
		//��ʾ�����仯
		int nextDay = 0;
		log.info("��ʼ���................");
		for(Environment env:collection) {
			//��ȡ����
			int day = getDay(env);
			if(pstmt == null || nextDay != day) {
				nextDay = day;
				//���������������
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
		
		//�����������
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
		
		log.info("������............");
	}

	/**
	 * ��ȡÿ�������Ӧ������
	 * @param env ��������
	 * @return ����
	 */
	private int getDay(Environment env) {
		//��ȡ�·ݵ�ĳһ��
		Timestamp time = env.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
}

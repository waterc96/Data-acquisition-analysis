package com.briup.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.config.ModuleInit;
import com.briup.logger.Log;
import com.briup.logger.LogImpl;

public class GatherImpl implements Gather, ModuleInit {
	/** �ļ�·�� */
	private String filePath;

	/** �������󼯺� */
	private Collection<Environment> envs = new ArrayList<>();

	/** ����������ID ֵ�������� */
	private static Map<String, String> name = new HashMap<>();

	/** ������־log���� */
	private static Log log = new LogImpl();

	static {
		name.put("256", "illumination");
		name.put("1280", "co2");
		name.put("16", "temperature,humidity");
	}

	@Override
	public void init(Properties properties) {
		filePath = properties.getProperty("file");
	}

	@Override
	public Collection<Environment> gather() {
		// ���������ȡ������
		log.info("��ʼ��ȡ�ļ�.............");
		RandomAccessFile raf = null;
		String line = null;
		try {
			raf = new RandomAccessFile("radwtmp", "r");
			while ((line = raf.readLine()) != null) {
				lineToEnvironment(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					log.error("I/O�쳣���ļ��رճ���");
				}
			}
		}
		return envs;
	}

	/**
	 * �ַ���ת��ΪEnvironment����Ȼ��ŵ�������
	 * 
	 * @param line
	 *            ÿ���ַ���
	 */
	private void lineToEnvironment(String line) {
		String[] strs = line.split("[|]");
		Environment env = new Environment();

		if (name.containsKey(strs[3])) {
			if ("16".equals(strs[3])) {
				// ��ȡ�¶ȶ���
				env.setName(name.get(strs[3]).split("[,]")[0]);
				env.setData(Integer.parseInt(strs[6].substring(0, 4), 16) * 0.00268127 - 46.85);
				setValue(env, strs);
				// ��ȡʪ�ȶ���
				Environment humidity = new Environment();
				humidity.setName(name.get(strs[3]).split("[,]")[1]);
				humidity.setData(Integer.parseInt(strs[6].substring(4, 8), 16) * 0.00190735 - 6);
				setValue(humidity, strs);
			} else {
				env.setName(name.get(strs[3]));
				env.setData(Integer.parseInt(strs[6].substring(0, 4), 16) * 1.0);
				setValue(env, strs);
			}
		}
	}

	/**
	 * ����������ֵ���ŵ�������
	 * 
	 * @param env
	 *            ��������
	 * @param strs
	 *            �ַ�������
	 */
	private void setValue(Environment env, String... strs) {
		// ��ֵ
		env.setSrcId(Integer.parseInt(strs[0]));
		env.setDesId(Integer.parseInt(strs[1]));
		env.setDevId(Integer.parseInt(strs[2]));
		env.setSensorAddress(Integer.parseInt(strs[3]));
		env.setCounter(Integer.parseInt(strs[4]));
		env.setCmd(Integer.parseInt(strs[5]));
		env.setStatus(Integer.parseInt(strs[7]));
		env.setTime(new Timestamp(Long.parseLong(strs[8])));
		// �ŵ�������
		envs.add(env);
	}
}

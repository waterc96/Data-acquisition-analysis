package com.briup.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.config.ModuleInit;
import com.briup.logger.Log;
import com.briup.logger.LogImpl;

public class ServerImpl implements Server, ModuleInit {
	private int port;
	private Log log = new LogImpl();
	@Override
	public void init(Properties properties) {
		port = Integer.parseInt(properties.getProperty("port"));
	}

	@Override
	public Collection<Environment> receiver() {
		ServerSocket ss = null;
		Collection<Environment> collection = null;
		try {
			// �����������׽���
			log.info("��ʼ������������........");
			ss = new ServerSocket(port);
			// �ȴ��ͻ�������
			log.info("�ȴ��ͻ�������............");
			Socket socket = ss.accept();
			log.info("�ͻ��������Ϸ�������,��ʼ��������.......");
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object object = ois.readObject();
			log.info("�����������ݽ������........");
			if(object instanceof Collection) {
				collection = (Collection<Environment>) object;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return collection;
	}

}

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
			// 创建服务器套接字
			log.info("开始创建服务器端........");
			ss = new ServerSocket(port);
			// 等待客户端连接
			log.info("等待客户端连接............");
			Socket socket = ss.accept();
			log.info("客户端连接上服务器端,开始接收数据.......");
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			Object object = ois.readObject();
			log.info("服务器端数据接收完成........");
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

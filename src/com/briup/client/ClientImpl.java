package com.briup.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.config.ModuleInit;
import com.briup.logger.Log;
import com.briup.logger.LogImpl;

public class ClientImpl implements Client, ModuleInit{
	private String ip;
	private int port;
	private Log log = new LogImpl();
	
	@Override
	public void init(Properties properties) {
		ip = properties.getProperty("ip");
		port = Integer.parseInt(properties.getProperty("port"));
	}

	@Override
	public void send(Collection<Environment> collection) {
		Socket socket = null;
		try {
			log.info("客户端准备开始连接........");
			socket = new Socket(ip, port);
			log.info("客户端连接成功，开始发送数据........");
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(collection);
			log.info("客户端发送数据完成.......");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

package com.briup.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.logger.Log;
import com.briup.server.DBStore;
import com.briup.server.Server;

public class ConfigurationImpl implements Configuration {
	/**
	 * map ����Ϊÿ��ģ������  ֵÿ��ģ���Ӧ�Ķ���
	 */
	private static Map<String, ModuleInit> map = new HashMap<>();
	static {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.
					read("src/com/briup/config/config.xml");
			//��ȡ��Ԫ��
			Element root = document.getRootElement();
			//��ȡ��Ԫ�صļ���
			List<Element> elements = root.elements();
			for(Element e:elements) {
				Properties properties = new Properties();
				//�õ�ÿ��ģ���ȫ�޶���
				String value = e.attributeValue("class");
				Class<?> clazz = Class.forName(value);
				//������ģ���Ӧ��ʵ������
				Object object = clazz.newInstance();
				if(object instanceof ModuleInit) {
					ModuleInit module = (ModuleInit) object;
					//�����ģ����󵽼�����
					map.put(e.getName(), module);
					//��ø�ģ���µĲ���
					List<Element> elements2 = e.elements();
					for(Element e2:elements2) {
						properties.
							setProperty(e2.getName(), e2.getText());
					}
					module.init(properties);
				}
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Client getClient() {
		return (Client) map.get("client");
	}

	@Override
	public Server getServer() {
		return (Server) map.get("server");
	}

	@Override
	public Gather getGather() {
		return (Gather) map.get("gather");
	}

	@Override
	public DBStore getDBStore() {
		return (DBStore) map.get("dbStore");
	}

	@Override
	public Log getLog() {
		return (Log) map.get("logger");
	}
	
}

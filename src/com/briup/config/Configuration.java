package com.briup.config;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.logger.Log;
import com.briup.server.DBStore;
import com.briup.server.Server;

/**
 * 1. ��ʼ������ģ��
 * 2. ��ȡ����ģ��Ķ���
 * @author faple
 * @created 2018��9��26�� ����10:05:45
 */
public interface Configuration {
	Client getClient();
	Server getServer();
	Gather getGather();
	DBStore getDBStore();
	Log getLog();
}

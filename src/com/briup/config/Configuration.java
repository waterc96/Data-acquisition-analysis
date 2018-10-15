package com.briup.config;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.logger.Log;
import com.briup.server.DBStore;
import com.briup.server.Server;

/**
 * 1. 初始化其他模块
 * 2. 获取其他模块的对象
 * @author faple
 * @created 2018年9月26日 上午10:05:45
 */
public interface Configuration {
	Client getClient();
	Server getServer();
	Gather getGather();
	DBStore getDBStore();
	Log getLog();
}

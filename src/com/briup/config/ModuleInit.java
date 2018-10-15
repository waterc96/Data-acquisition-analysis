package com.briup.config;

import java.util.Properties;

/**
 * 初始化其余各个模块的基本参数
 * @author faple
 * @created 2018年9月25日 下午4:52:48
 */
public interface ModuleInit {
	
	void init(Properties properties);
}

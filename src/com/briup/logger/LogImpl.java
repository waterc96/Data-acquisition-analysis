package com.briup.logger;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.briup.config.ModuleInit;

public class LogImpl implements Log, ModuleInit{
	private Logger logger = Logger.getRootLogger();
	@Override
	public void init(Properties properties) {
		//指定log4j配置文件 加载路径
		PropertyConfigurator.configure(
				properties.getProperty("log_prop"));
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}
}

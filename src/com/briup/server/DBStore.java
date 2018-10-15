package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;

public interface DBStore {
	void saveToDB(Collection<Environment> collection);
}

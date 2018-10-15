package com.briup.server;

import java.util.Collection;

import com.briup.bean.Environment;

public interface Server {
	Collection<Environment> receiver();
}

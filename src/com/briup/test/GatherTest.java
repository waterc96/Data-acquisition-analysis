package com.briup.test;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.briup.bean.Environment;
import com.briup.client.Client;
import com.briup.client.ClientImpl;
import com.briup.client.Gather;
import com.briup.client.GatherImpl;
import com.briup.config.Configuration;
import com.briup.config.ConfigurationImpl;
import com.briup.server.DBStore;
import com.briup.server.DBStoreImpl;
import com.briup.server.Server;
import com.briup.server.ServerImpl;

public class GatherTest {
	private Configuration configuration;
	
	@Before
	public void init() {
		configuration = new ConfigurationImpl();
	}
	
	@Test
	public void client() {
//		Collection<Environment> collection = 
//				new GatherImpl().gather();
//		new ClientImpl().send(collection);
		Gather gather = configuration.getGather();
		Collection<Environment> collection = gather.gather();
		Client client = configuration.getClient();
		client.send(collection);
	}
	
	@Test
	public void server() {
//		Collection<Environment> collection = 
//				new ServerImpl().receiver();
//		new DBStoreImpl().
//			saveToDB(collection);
		Server server = configuration.getServer();
		Collection<Environment> collection = server.receiver();
		DBStore dbStore = configuration.getDBStore();
		dbStore.saveToDB(collection);
	}
}

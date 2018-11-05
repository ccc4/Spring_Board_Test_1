package mvc.util;

import java.sql.DriverManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit {
	private String JDBC_DRIVER_CLASS;
	private String JDBC_URL;
	private String JDBC_USER;
	private String JDBC_PASSWORD;
	private String JDBC_DB_NAME;
	
	public void setJDBC_DRIVER_CLASS(String jDBC_DRIVER_CLASS) {
		JDBC_DRIVER_CLASS = jDBC_DRIVER_CLASS;
	}

	public void setJDBC_URL(String jDBC_URL) {
		JDBC_URL = jDBC_URL;
	}

	public void setJDBC_USER(String jDBC_USER) {
		JDBC_USER = jDBC_USER;
	}

	public void setJDBC_PASSWORD(String jDBC_PASSWORD) {
		JDBC_PASSWORD = jDBC_PASSWORD;
	}

	public void setJDBC_DB_NAME(String jDBC_DB_NAME) {
		JDBC_DB_NAME = jDBC_DB_NAME;
	}

	public void init()  { 
    	loadJDBCDriver();
    	initConnectionPool();
    }

    private void loadJDBCDriver() {
		try {
			Class.forName(JDBC_DRIVER_CLASS);
			System.out.println(JDBC_DRIVER_CLASS + " 로딩 완료");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool() {
		try {

			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);			

			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = 
					(PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool(JDBC_DB_NAME, connectionPool);
			
			System.out.println("커넥션 풀 생성 완료");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
}
}

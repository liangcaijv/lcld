package org.lanqiao.sql.ijdbc.impl;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * 使用DBCP产生DataSource<br>
 * 
 * @author JBOSS
 * 
 */
enum DbcpDsFactory {
  me;
  public DataSource getDataSource() {
    DataSource dataSource;
    String connectURI = Config.getUrl();
    Properties props = new Properties();
    props.setProperty("user", Config.getName());
    props.setProperty("password", Config.getPwd());
    return setupDataSource(connectURI, props);
  }

  private static DataSource setupDataSource(String connectURI, Properties props) {
    //
    // First, we'll create a ConnectionFactory that the
    // pool will use to create Connections.
    // We'll use the DriverManagerConnectionFactory,
    // using the connect string passed in the command line
    // arguments.
    //
    ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
        connectURI, props);

    //
    // Next we'll create the PoolableConnectionFactory, which wraps
    // the "real" Connections created by the ConnectionFactory with
    // the classes that implement the pooling functionality.
    //
    PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
        connectionFactory, null);

    //
    // Now we'll need a ObjectPool that serves as the
    // actual pool of connections.
    //
    // We'll use a GenericObjectPool instance, although
    // any ObjectPool implementation will suffice.
    //
    ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(
        poolableConnectionFactory);

    // Set the factory's pool property to the owning pool
    poolableConnectionFactory.setPool(connectionPool);

    //
    // Finally, we create the PoolingDriver itself,
    // passing in the object pool we created.
    //
    PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(
        connectionPool);

    return dataSource;
  }

}

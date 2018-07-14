package com.uat.hbc.connectionPooling;

import java.sql.Connection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.mchange.v2.c3p0.ConnectionCustomizer;

public class ConnectionPoolLogger implements ConnectionCustomizer{
	 private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolLogger.class);
	
	    private int activeConnections = 0;
	    private int acquiredConnections = 0;

	    public void onAcquire(Connection c, String pdsIdt) {
	        logger.info("onAcquire: Connection acquired from database : " + c
	                + " [" + pdsIdt + "]");
	        acquiredConnections++;
	        logger.info("onAcquire: Total Open Connections in Pool : " + acquiredConnections);
	        logger.trace("onAcquire: Total Open Connections in Pool : " + acquiredConnections);
	        logger.debug("onAcquire: Total Open Connections in Pool : " + acquiredConnections);
	    }

	    public void onDestroy(Connection c, String pdsIdt) {
	        logger.info("onDestroy: Connection closed with database : " + c + " ["
	                + pdsIdt + "]");
	        acquiredConnections--;
	        logger.info("onDestroy: Total Open Connections in Pool : " + acquiredConnections);
	        logger.trace("onDestroy: Total Open Connections in Pool : " + acquiredConnections);
	        logger.debug("onAcquire: Total Open Connections in Pool : " + acquiredConnections);

	    }

	    public void onCheckOut(Connection c, String pdsIdt) {
	        logger.info("onCheckOut: Connection from pool provide to application : "
	                + c + " [" + pdsIdt + "]");
	        activeConnections++;
	        logger.info("onCheckOut: Total Active Connections in Pool : " + activeConnections);
	        logger.trace("onCheckOut: Total Active Connections in Pool : " + activeConnections);
	        logger.debug("onAcquire: Total Open Connections in Pool : " + acquiredConnections);
	    }

	    public void onCheckIn(Connection c, String pdsIdt) {
	        logger.info("onCheckIn: Connection returned to pool from application : "
	                + c + " [" + pdsIdt + "]");
	        activeConnections--;
	        logger.info("onCheckIn: Total Active Connections in Pool : " + activeConnections);
	        logger.trace("onCheckIn: Total Active Connections in Pool : " + activeConnections);
	        logger.debug("onAcquire: Total Open Connections in Pool : " + acquiredConnections);

	    }
}


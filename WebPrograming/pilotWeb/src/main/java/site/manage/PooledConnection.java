package site.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PooledConnection {
	private boolean inUse = false;
	Connection dbConn = null;
	
	protected PooledConnection(String url) {
		try{
			this.dbConn = DriverManager.getConnection(url);	
		}catch(SQLException e) {
			this.dbConn = null;
			e.printStackTrace();
		}
			}
	public boolean isInUse() {
		return this.inUse;
	}
	
	public void setInuse(boolean use) {
		this.inUse = use;
	}
}

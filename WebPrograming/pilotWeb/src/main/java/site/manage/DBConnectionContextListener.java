package site.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class
 * DBConnectionContextListener
 *
 */
public class DBConnectionContextListener implements ServletContextListener {
	//public static Connection dbConn = null;
	private static ArrayList<PooledConnection> pool = new ArrayList<PooledConnection>();
	protected static String driverURL = null;

	/**
	 * Default constructor.
	 */
	public DBConnectionContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		String driverName = sce.getServletContext().getInitParameter("dbclass");
		 driverURL = sce.getServletContext().getInitParameter("dburl");

		try {
			Class.forName(driverName);
			//dbConn = DriverManager.getConnection(driverURL);
			pool.add(new PooledConnection(driverURL));
		} catch (ClassNotFoundException e) {
			//dbConn = null;
			//out.println("driver class not found!");
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
	
	public static synchronized Connection getDBConnection() {
		System.out.println("poolsize:"+pool.size());
		Iterator<PooledConnection> itr = pool.iterator();
		
		while(itr.hasNext()) {
			PooledConnection aConn = itr.next();
			if(!aConn.isInUse()) { // 연결이 현재 사용중이면 true
				aConn.setInuse(true);
				return aConn.dbConn;
			}
		}
		
		PooledConnection newConn = new PooledConnection(driverURL);
		pool.add(newConn);
		return newConn.dbConn; //반복문을 모두 돌았는데도 사용 가능한 연결이 없는 경우, 새로운 PooledConnection 객체를 생성

	}

	public static synchronized void returnDBConnection(Connection conn) {
		Iterator<PooledConnection> itr = pool.iterator();
		while(itr.hasNext()) {
			PooledConnection aConn = itr.next();
			if(aConn.dbConn == conn) {
				aConn.setInuse(false);
				System.out.println("safe");
				return;
			}
		}

	}
}

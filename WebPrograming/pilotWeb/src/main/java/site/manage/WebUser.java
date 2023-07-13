package site.manage;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class WebUser implements HttpSessionBindingListener, Serializable {
	private static final Map<String, HttpSession> loginSessions = new ConcurrentHashMap<>();

	private static synchronized HttpSession replaceIfDuplicated(String userID, HttpSession newSession) {
		HttpSession oldSession = loginSessions.remove(userID);
		if (oldSession != null)
			oldSession.invalidate();
		loginSessions.put(userID, newSession);
		return oldSession;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession oldSession = replaceIfDuplicated(this.name, event.getSession());
		if (oldSession != null)
			System.out.println("Duplicated login(" + this.name + ") removed from " + oldSession.getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginSessions.remove(this.name);
		System.out.println("login(" + this.name + ") removed.");
	}

	private static String[][] validUsers = { { "user1", "pw1" }, { "user2", "pw2" }, { "user3", "pw3" } };
	private String name;
	private Calendar lastLoggedIN;

	public WebUser(String n) {
		this.name = n;
		this.lastLoggedIN = Calendar.getInstance();
	}

	public String getName() {
		return this.name + "'s name";
	}

	public String getLastLoggedIN() {
		return new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(lastLoggedIN.getTime());
	}

	public static boolean isValidUser(String id, String pw) {
		/*
		 * for (int i = 0; i < validUsers.length; i++) { if (validUsers[i][0].equals(id)
		 * && validUsers[i][1].equals(pw)) return true; }
		 */

		Connection conn = DBConnectionContextListener.getDBConnection();
		String sql = "select count(*) from userlogin where email = \"" + id + "\" and user_password = \"" + pw +"\";";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			int rowcount = 0;
			if (rs.last())
				rowcount = rs.getRow();
			System.out.println("count:" + rowcount);
			if (rowcount == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
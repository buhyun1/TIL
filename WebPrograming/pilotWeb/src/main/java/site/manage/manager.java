package site.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class manager
 */
public class manager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driverName = "org.mariadb.jdbc.Driver";
	String dbName = "manager";
	String dbName2 = "fileservice";
	String driverURL = "jdbc:mariadb://localhost:3306/" + dbName;
	String driverURL2 = "jdbc:mariadb://localhost:3306/" + dbName2;
	String dbusername = "root";
	String dbpassword = "secret";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		String userID = request.getParameter("userid");
		String password = request.getParameter("pw");
		
		String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);

		File userFolder = new File(UploadFolderPath);
		
		// pw.println(userID);
		// pw.println(password);

		String[] namepw = sqlManager();
		
		//pw.println(namepw[0]);
		if (userID.equals(namepw[0]) & password.equals(namepw[1])) {
			pw.println("hi");
			

	       
			 List<String> fileList = getFolderList(UploadFolderPath);
			 pw.print(fileList);
	        
		}
	}
	
	public String[] sqlManager() {
		String dbname=null;
		String dbpw=null;
		
		try (Connection conn = DriverManager.getConnection(driverURL, dbusername, dbpassword);
				Statement state = conn.createStatement();) {
			// pw.println("driverURL: " + driverURL + ", connected!<br>");
			String sql = "select username, password from manager";
			ResultSet rs = state.executeQuery(sql);
			rs.next();
			dbname = rs.getString(1);
			dbpw = rs.getString(2);
			// pw.println(dbname);
			// pw.println(dbpw);
			rs.close();
			state.close();
			conn.close();
		} catch (SQLException e) {
			//pw.println("Invalid SQL: check SQL");
			e.printStackTrace();
		}
		
		String [] namepw = new String[2];
		namepw[0] = dbname;
		namepw[1] = dbpw;
		return namepw;
	}
	
    private List<String> getFolderList(String folderPath) {
        List<String> folderList = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        folderList.add(file.getName());
                    }
                }
            }
        }

        return folderList;
    }
	/**
	public ArrayList<String> sqlFile() {
	String code=null;
	String ip=null;
	String time=null;
	String row = null;
	try (Connection conn = DriverManager.getConnection(driverURL2, dbusername, dbpassword);
		Statement state = conn.createStatement();) {
		//pw.println("driverURL: " + driverURL + ", connected!<br>");
		String sql = "select * from filelist";
		ResultSet rs = state.executeQuery(sql);
		
		while(rs.next()) {
			//pw.println(rs);
			code = rs.getString(1);
			ip = rs.getString(2);
			time = rs.getString(3);
			
			row = "code: " + code+  "  ip: " + ip+ "  time: " + time;
		}
	
		rs.close();
		state.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	//pw.println(code);
	ArrayList<String> sqlList = new ArrayList<String>();
	sqlList.add(row);
	return sqlList;
	} **/
}
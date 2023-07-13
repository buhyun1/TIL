package site.manage;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.Cookie;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileSweeper implements ServletContextListener {
	private Connection dbConn;
	private Timer timer;
	String UploadFolder;
	String UploadFolderPath;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 서버 시작 시 호출되는 메서드
		// 파일 삭제 작업을 처리하는 스레드를 생성하고 시작한다.
		// 데이터베이스 연결을 설정한다.

		String driverName = sce.getServletContext().getInitParameter("dbclass");
		String driverURL = sce.getServletContext().getInitParameter("dburl");
		UploadFolder = sce.getServletContext().getInitParameter("uploadFolder");
		UploadFolderPath = sce.getServletContext().getRealPath(UploadFolder);
		
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(driverURL);
			System.out.println("start");
			// Timer 객체 생성
			timer = new Timer();

			// 작업 예약 및 주기 설정
			timer.schedule(new FileSweeperTask(), 0, 5 * 60 * 1000); // 5분(5 * 60 * 1000 밀리초)마다 실행

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// 서버 종료 시 호출되는 메서드
		// 파일 삭제 작업을 처리하는 스레드를 종료시킨다.

		try {
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer.cancel();

		System.out.println(dbConn);
	}

	class FileSweeperTask extends TimerTask {
		@Override
		public void run() {
			// 파일 삭제 작업을 처리하는 코드를 작성한다.

			try {
				// 현재 시각으로부터 5분 이전의 업로드된 파일을 삭제한다.
				LocalDateTime currentTime = LocalDateTime.now();
				LocalDateTime fiveMinutesAgo = currentTime.minusMinutes(1);
				String checksql = "SELECT pass_code FROM filelist WHERE upload_time < '" + fiveMinutesAgo + "'";
				String sql = "DELETE FROM filelist WHERE upload_time < '" + fiveMinutesAgo + "'";
				Statement statement = dbConn.createStatement();
				
				ResultSet rs= statement.executeQuery(checksql);
				int rowsAffected = statement.executeUpdate(sql);

				System.out.println("Deleted " + rowsAffected + " rows");
				statement.close();

				while(rs.next()){
					File userFolder = new File(UploadFolderPath+rs.getString(1));
					System.out.println(UploadFolderPath+rs.getString(1));
				if (userFolder.exists()) {
					File[] files = userFolder.listFiles();
					if (files != null) {
						for (File file1 : files) {
							file1.delete(); // 폴더 내 파일 삭제
						}
					}
					userFolder.delete(); // 폴더 삭제
				}
				}
					
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		FileSweeperTask() {
			this.run();
		}
	}
}

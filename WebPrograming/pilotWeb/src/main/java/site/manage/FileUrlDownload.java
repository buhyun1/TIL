package site.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileUrlDownload extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String passcode = request.getParameter("passcode"); // URL 파라미터로부터 파일 이름 가져오기
      String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
      String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);

      // 파일 이름을 검증하고 적절한 경로를 생성
      String filePath = UploadFolderPath + "\\" + passcode; // 파일 경로를 알맞게 수정해야 합니다.
      File downloadFolder = new File(filePath);
      File downloadFile = null;

      String FileName = "";
      //pass_code 폴더가 존재하고 
      if (downloadFolder.exists()) {
         String[] Files = downloadFolder.list();
         //폴더 내부가 비어있지 않았을 때
         if (Files != null) {
            for (String file : Files) {
               downloadFile = new File(filePath + "\\" + file);
               System.out.println(filePath+"\\"+file);
               FileName = file;
            }
         }
      }

      // 파일이 존재하지 않거나 파일이 아닌 경우에는 만료화면 출력
      else {
         response.setContentType("text/html;charset=UTF-8");
         String message = "링크가 이미 만료되었습니다.";
         response.getWriter().println(message);
         return;
      }

      //파일이 존재하는 경우 
      FileInputStream inputStream = new FileInputStream(downloadFile);

      // 파일 다운로드를 위한 헤더 설정
      response.setContentType("application/octet-stream");
      response.setContentLength((int) downloadFile.length());
      response.setHeader("Content-Disposition", "attachment; filename=\"" + FileName + "\"");

      // 파일을 클라이언트로 전송
      ServletOutputStream outputStream = response.getOutputStream();
      byte[] buffer = new byte[4096];
      int bytesRead = -1;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
         outputStream.write(buffer, 0, bytesRead);
      }
      inputStream.close();
      outputStream.close();

      // 다운 후 삭제 작업
      // TxRequest 쿠키 삭제
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals("TxRequest")) {
               cookie.setMaxAge(0);
               response.addCookie(cookie);
            }
         }
      }

      // DB 연결
      Connection conn = DBConnectionContextListener.getDBConnection();
      // 폴더 삭제
      if (downloadFolder.exists()) {
         File[] files1 = downloadFolder.listFiles();
         if (files1 != null) {
            for (File file : files1) {
               file.delete(); // 폴더 내 파일 삭제
            }
         }
         downloadFolder.delete(); // 폴더 삭제
      }
      // DB에서 삭제
      try {
         String sql = "DELETE FROM filelist WHERE pass_code = ?";
         PreparedStatement statement = conn.prepareStatement(sql);
         statement.setString(1, passcode);
         int rowsAffected = statement.executeUpdate();
         if (rowsAffected > 0) {
            System.out.println(passcode + "행이 삭제되었습니다.");
         } else {
            System.out.println(passcode + "와" + "일치하는 행이 없습니다.");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBConnectionContextListener.returnDBConnection(conn);
      }

   }

}
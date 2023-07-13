<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

<div style="width:400px;" align="center"> 
  <strong><ins>Uploaded File Management</ins></strong><br><br>
</div>

<form action="deleteFiles.jsp" method="post">
  <fieldset style="width:400px">
    <legend>List of uploaded files</legend>	
    <%  
    String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
    String uploadPath = this.getServletContext().getRealPath(uploadFolder);
	java.io.File file=new java.io.File(uploadPath);
			String[] files=file.list();
			
			for (String fileName: files){
			out.print("<input type='checkbox' id="+fileName+" name="+fileName+" value="+fileName+">");
		    out.print("<label for="+fileName+">"+fileName+"</label>");
		    out.print("<a href=\"./upload/" +fileName+ "\" download>[다운받기]</a><br>");
		    //<a href=”/upload/Text File1.txt” download>다운받기</a>
			};
    %>
    
	<hr>
    <div style="width:400px;" align="right"> 
      <input type="submit" value="Delete">&nbsp;&nbsp;
      <input type="reset" value="Reset">
    </div> 	
  </fieldset><br>  
</form>
<br>
<form action="fileUpload.jsp" method="post" enctype="multipart/form-data">
  <fieldset style="width:400px">
    <legend>Upload a new file:</legend>
    <label for="upfile"><small>Choose Files:</small></label><br>
    <input type="file" name="upfile" id="upfile" multiple/><br>
    <hr>
    <div style="width:400px;" align="right"> 
      <input type="submit" value="Upload">&nbsp;&nbsp;
      <input type="reset" value="Clear">
    </div> 
  </fieldset>
</form>
</body>
</html>
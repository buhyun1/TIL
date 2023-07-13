<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Student Beans</title>
</head>
<body>
	<jsp:useBean id="person1" class="site.bean.Person" scope="request">
		<jsp:setProperty name="person1" property="name" param="name" />
		<jsp:setProperty name="person1" property="age" param="age" />
	</jsp:useBean>
	person1:
	<br> name:<jsp:getProperty name="person1" property="name" /><br>
	age:<jsp:getProperty name="person1" property="age" /><br>
	<hr>
	<jsp:useBean id="student1" class="site.bean.Student" scope="request">
		<jsp:setProperty name="student1" property="name" param="name" />
		<jsp:setProperty name="student1" property="age" param="age" />
		<jsp:setProperty name="student1" property="id" param="id" />
		<jsp:setProperty name="student1" property="major" param="major" />
		<jsp:setProperty name="student1" property="fullTime" param="fullTime" />
	</jsp:useBean>
	student1:
	<br> name:<jsp:getProperty name="student1" property="name" /><br>
	age:<jsp:getProperty name="student1" property="age" /><br> id:<jsp:getProperty
		name="student1" property="id" /><br> major:<jsp:getProperty
		name="student1" property="major" /><br> full-time:<jsp:getProperty
		name="student1" property="fullTime" /><br>
	<hr>
	<jsp:useBean id="student2" class="site.bean.Student" scope="request">
		<jsp:setProperty name="student2" property="*" />
	</jsp:useBean>
	student2:
	<br> name:<jsp:getProperty name="student2" property="name" /><br>
	age:<jsp:getProperty name="student2" property="age" /><br> id:<jsp:getProperty
		name="student2" property="id" /><br> major:<jsp:getProperty
		name="student2" property="major" /><br> full-time:<jsp:getProperty
		name="student2" property="fullTime" /><br>
</body>
</html>
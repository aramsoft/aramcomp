<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC Rest Demo</title>
</head>
<body>

<h2>Spring MVC Rest Demo</h2>

<table border="1">
<tr>
  <th>Id</th>
  <th>First Name</th>
  <th>Last Name</th>
</tr>
	<c:forEach var="row" varStatus="loop" items="${peopleList}">
      <tr>
		<td>${row.id}</td>
		<td>${row.firstName}</td>
		<td>${row.lastName}</td>
	  </tr>
	</c:forEach>
</table>

<p />

Created by Pas Apicella

</body>
</html>
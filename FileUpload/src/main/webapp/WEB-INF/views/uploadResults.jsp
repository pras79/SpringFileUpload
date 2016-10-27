<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <style>
 table {
    width:100%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color:#fff;
}
table#t01 th {
    background-color: black;
    color: white;
}
.error {
color: #D8000C;
background-color: #FFBABA;
background-image: url('error.png');
</style>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results Page</title>
</head>
<body>
</body>

<h1>Results</h1>
<table id="t01">
 <tr>
  <th>Date</th>
  <th>USD value</th>
  <th>Description</th>
 </tr>
 <c:forEach items="${fileRecords}" var="csvfileRecord">
  <tr>
   
   		<td><c:out value="${csvfileRecord.date}" /></td>
   
   
   		<td><c:out value="${csvfileRecord.value}" /></td>
   
   <td><c:out value="${csvfileRecord.description}" /></td>
   
  </tr>
 </c:forEach>
</table>
<div class="error"><c:out value ="${errorMessage}" /> </div>
<p>${greetings}</p></body>

</html>
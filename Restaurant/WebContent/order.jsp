<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Choise operation and click: <br>
<form action="">
	Enter dishes split ',' <input type="text" name="dishes">
	<input type="submit" value="order">
</form>
<br>
<form action="/Restaurant/paymentoperation" method='POST'>
	<input type='hidden' name="customerName" value="<%= request.getParameter("customerName") %>">
	idOrder<input type="text" name="idOrder">
	CostOrder<input type="text" name="costOrder">
	<input type="submit" value="pay">
</form>
</body>
</html>
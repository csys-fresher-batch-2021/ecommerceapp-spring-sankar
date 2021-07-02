<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>
<%
String username = (String) session.getAttribute("Logged_in_User");
%>

<jsp:include page="header.jsp"></jsp:include>

<body>
	<h3> Post Your issues or Queries Here</h3>
	<form action="CustomerEnquiriesServlet" method="post">

		Subject :<input type="text" placeholder="Enter subject" name="subject"
			title="subject field should not be empty" required><br />
		Message or Issues :<input type="text" placeholder="Enter your message"
			name="message" title="message field should not be empty"  required><br />
		username :<input type="text" name="username" value="<%=username%>"
			readonly required><br />

		<button type="submit" class="btn btn-success">Send</button>
	</form>
</body>
</html>
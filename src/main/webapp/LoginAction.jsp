<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Action</title>
<link rel="icon" type="image/jpg" href="img/logo.jpg" />
</head>
<body>

	<%
		
		String role_ID = request.getParameter("RoleID");
		session.setAttribute("roleID", role_ID);
		String user_id = (String) session.getAttribute("roleID");
		
	%>

	<%
		if (null == user_id) {
	%>

	<jsp:forward page="login.jsp" />

	<%
		} else {
	%>

	<jsp:forward page="home.jsp" />

	<%
		}
	%>
</body>
</html>

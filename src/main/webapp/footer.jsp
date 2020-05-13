<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img class="img-fluid logo_totalpage" src="common/images/hanbit1.png" alt="logo">
<%
		String user_id = (String) session.getAttribute("roleID");

		System.out.println("======>" + user_id);
	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
	%>
<!--Footer Start-->
	<nav
		class="navbar fixed-bottom navbar-light bg-light justify-content-center footer text-black">
		<a class="navbar-brand">&copy; Copyrights 2020 Hanbit<sup>&reg;</sup>. All Rights Reserved.</a>
	</nav>
	<!--Footer end-->
</body>
</html>
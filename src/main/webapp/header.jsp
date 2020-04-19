<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		String user_id = (String) session.getAttribute("roleID");

		System.out.println("======>" + user_id);
	%>

	<%
		if (null == user_id) {
			response.sendRedirect("login.jsp");
		}
	%>
<!--Header Start-->
	<nav class="navbar navbar-expand-lg fixed-top bg-info pl-4 p-0">
		<div class="float-left mt-2">
			<a href="#" class="button-left text-white"><span
				class="fa fa-fw fa-bars "></span></a>
		</div>

		<!-- Brand -->
		<a class="navbar-brand text-white col-md-2 mr-minus15  mr-auto"
			href="#"><img class="img-responsive logo"
			src="common/images/hanbit1.png" alt="logo"></a>
		<!-- Links -->

		<ul class="navbar-nav">
			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle text-white" href="#" id="navbardrop"
				data-toggle="dropdown"> Setting </a>
				<div class="dropdown-menu  dropdown-menu-right">
					<a class="dropdown-item" href="#">Help</a> <a class="dropdown-item"
						href="logout.jsp">Logout</a>
				</div></li>
		</ul>
	</nav>
	<!--Header end-->
</body>
</html>
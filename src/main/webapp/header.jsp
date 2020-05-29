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
	<nav class="navbar navbar-expand-lg fixed-top bg-info pl-4 p-0 navheader">
		<div class="float-left mt-2">
			<a href="#" class="button-left text-white"><span
				class="fa fa-fw fa-bars "></span></a>
		</div>

		<!-- Brand -->
		<a class="navbar-brand logo_sm
		 text-white col-md-1 mr-minus15"
			href="home.jsp"><img class="img-fluid logo"
			src="common/images/hanbit1.png" alt="logo" style="max-width: 140% !important;"></a>
			
		<a class="navbar-brand logo_sm
		 text-white col-md-2 mr-minus15"
			href="home.jsp"></a>
		<!-- Links -->

					<div class="m-auto text-center text-white smart_text">
					<h4 class="imgheader">  PAY AS YOU GO GAS <img class="img-fluid" src="common/images/gas.png"><img class="img-fluid" src="common/images/Webp.net-gifmaker.gif" style="max-width: 58% !important;"></h4>
					</div>

		<ul class="navbar-nav admindropdown">
			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle text-white icon" href="#" id="navbardrop" 
				data-toggle="dropdown">
				 </a>
				<div class="dropdown-menu  dropdown-menu-right">
					<a class="dropdown-item" href="#">Help</a>
					<a class="dropdown-item" href="myprofile.jsp">My Profile</a>
					 <a class="dropdown-item"
						href="logout.jsp">Logout</a>
				</div></li>
		</ul>
	</nav>
	<!--Header end-->
</body>

<script>

document.querySelector("#navbardrop").innerText = "  "+sessionStorage.getItem("userID");
</script>

</html>
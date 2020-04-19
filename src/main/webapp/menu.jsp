<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
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


 <!--sidebar start-->
        <div class="main">
          <aside>
            <div class="sidebar left ">
              <div class="user-panel">
                <div class="float-left image">
                  <img src="http://via.placeholder.com/160x160" class="rounded-circle" alt="User Image">
                </div>
                <div class="float-left info">
                  <p class="userNameDisplay"></p>
                  <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
              </div>
              <ul class="list-sidebar bg-defoult">
                <%
			if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("3") || user_id.equalsIgnoreCase("4") || user_id.equalsIgnoreCase("5")) {
				%>	
				<% if(!user_id.equalsIgnoreCase("2") || !user_id.equalsIgnoreCase("3")) {%>					
                <li> <a href="communityDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Community</span></a> </li>
                <%} if(!user_id.equalsIgnoreCase("3")) {%>
				<li> <a href="blockDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Block</span></a> </li>
				<%}%>
				<li> <a href="customerDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Customer</span></a> </li>
                <li> <a href="LiveDashBoard.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">DashBoard</span></a> </li>
                <li> <a href="topupStatus.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Topup Details</span></a> </li>
                <li> <a href="configuration.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Configuration</span></a> </li>
                <li> <a href="configurationStatus.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Configuration Status</span></a> </li>
                <li> <a href="userConsumptions.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Reports</span></a> </li>
                
                
                
                <li> <a href="#" data-toggle="collapse" data-target="#dashboard" class="collapsed active"> <i
                      class="fa fa-th-large"></i> <span class="nav-label"> Reports </span> <span
                      class="fa fa-chevron-left float-right"></span> </a>
                  <ul class="sub-menu collapse" id="dashboard">
                    <li class="active"><a href="userConsumptions.jsp">User Consumptions</a></li>
                    <li><a href="topupSummary.jsp">Top Up Summary</a></li>
                    <li><a href="financialreports.jsp">Financial Reports</a></li>
                    <li><a href="alarms.jsp">Alarms</a></li>
                  </ul>
                </li>
                
                <% } else if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("4") || user_id.equalsIgnoreCase("5")) {
                %>
                     
                <li> <a href="blockDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Block</span></a> </li>
				<li> <a href="customerDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Customer</span></a> </li>
                <li> <a href="LiveDashBoard.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">DashBoard</span></a> </li>
                <li> <a href="TopUp.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">TopUp</span></a> </li>
                <li> <a href="TopUpDetails.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Topup Details</span></a> </li>
                <% } else if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("3") || user_id.equalsIgnoreCase("4") || user_id.equalsIgnoreCase("5")) {%>
                
                <% } %>
                <li> <a href="tariff.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">Tariff</span></a> </li>
                <li> <a href="userManagement.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">User Management</span></a> </li>
                
                <!-- <li> <a href="#" data-toggle="collapse" data-target="#dashboard" class="collapsed active"> <i
                      class="fa fa-th-large"></i> <span class="nav-label"> Dashboards </span> <span
                      class="fa fa-chevron-left float-right"></span> </a>
                  <ul class="sub-menu collapse" id="dashboard">
                    <li class="active"><a href="#">CSS3 Animation</a></li>
                    <li><a href="#">General</a></li>
                    <li><a href="#">Buttons</a></li>
                    <li><a href="#">Tabs & Accordions</a></li>
                    <li><a href="#">Typography</a></li>
                    <li><a href="#">FontAwesome</a></li>
                    <li><a href="#">Slider</a></li>
                    <li><a href="#">Panels</a></li>
                    <li><a href="#">Widgets</a></li>
                    <li><a href="#">Bootstrap Model</a></li>
                  </ul>
                </li>
                <li> <a href="#" data-toggle="collapse" data-target="#tables" class="collapsed active"><i
                      class="fa fa-table"></i> <span class="nav-label">Tables</span><span
                      class="fa fa-chevron-left float-right"></span></a>
                  <ul class="sub-menu collapse" id="tables">
                    <li><a href=""> Static Tables</a></li>
                    <li><a href=""> Data Tables</a></li>
                    <li><a href=""> Foo Tables</a></li>
                    <li><a href=""> jqGrid</a></li>
                  </ul>
                </li>
                <li> <a href="#" data-toggle="collapse" data-target="#e-commerce" class="collapsed active"><i
                      class="fa fa-shopping-cart"></i> <span class="nav-label">E-commerce</span><span
                      class="fa fa-chevron-left float-right"></span></a>
                  <ul class="sub-menu collapse" id="e-commerce">
                    <li><a href=""> Products grid</a></li>
                    <li><a href=""> Products list</a></li>
                    <li><a href="">Product edit</a></li>
                    <li><a href=""> Product detail</a></li>
                    <li><a href="">Cart</a></li>
                    <li><a href=""> Orders</a></li>
                    <li><a href=""> Credit Card form</a> </li>
                  </ul>
                </li>
                <li> <a href="#"><i class="fa fa-laptop"></i> <span class="nav-label">Grid options</span></a> </li> -->
              </ul>
            </div>
          </aside>
        </div>
        <!--sidebar end-->


</body>
</html>
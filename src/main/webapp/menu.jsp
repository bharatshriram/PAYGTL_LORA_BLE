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

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>


 <!--sidebar start-->
        <div class="main">
          <aside>
            <div class="sidebar left">
              <ul class="float-left list-sidebar mt-4">
              <!--  bg-defoult -->
              <li> <a href="home.jsp" title="home"><img src=common/images/icons/home.png /> <span class="nav-label">Home</span></a> </li>
                <%
			if (user_id.equalsIgnoreCase("1") || user_id.equalsIgnoreCase("4")) {
				%>	
                
                
                <li> <a href="communityDetails.jsp" title="Community Management"><img src=common/images/icons/community.png /> <span class="nav-label">Community Management</span></a> </li>
				<li> <a href="blockDetails.jsp" title="Block Management"><img src=common/images/icons/2-block.png /> <span class="nav-label">Block Management</span></a> </li>
				<li> <a href="customerDetails.jsp" title="Customer Management"><img src=common/images/icons/customer.png /> <span class="nav-label">Customer Management</span></a> </li>
				<li> <a href="tariff.jsp" title="Tariff"><img src=common/images/icons/tariff.png /> <span class="nav-label">Tariff</span></a> </li>                
                <li> <a href="alert.jsp" title="Alerts Configuration"><img src=common/images/icons/alerts.png /> <span class="nav-label">Alerts Configuration</span></a> </li>
                <%if(user_id.equalsIgnoreCase("1")){ %>
                <li> <a href="configuration.jsp" title="Meter Commands"><img src=common/images/icons/configuration.png /> <span class="nav-label">Meter Commands </span></a> </li>
                <%}%>
                <li> <a href="configurationStatus.jsp" title="Meter Commands Status"><img src=common/images/icons/configurationdetails1.png /> <span class="nav-label">Meter Commands Status</span></a> </li>
                <li> <a href="LiveDashBoard.jsp" title="Customer Details"><img src=common/images/icons/dashboard.png /> <span class="nav-label">Customer Details</span></a> </li>
                  <%if(user_id.equalsIgnoreCase("1")){ %>
                <li> <a href="topup.jsp" title="ReCharge"><img src=common/images/icons/topup.png /> <span class="nav-label">ReCharge</span></a> </li>
                <%}%>
                <li> <a href="topupStatus.jsp" title="ReCharge Status"><img src=common/images/icons/toopupdetailss.png /> <span class="nav-label">ReCharge Status</span></a> </li>
                <!-- <li><a href="alarms.jsp" title="Alarm"><img src=common/images/icons/alarm.png /><span class="nav-label">Alarms</span></a></li> -->
                <li><a href="holiday.jsp" title="Vacation"><img src=common/images/icons/4-vacation.png /><span class="nav-label">Vacation</span></a></li>
                <li> <a href="#" data-toggle="collapse" data-target="#tables" class="collapsed"><i
                      class="fa fa-table"></i> <span class="nav-label">Reports</span><span
                      class="fa fa-chevron-left float-right"></span></a>
                  <ul class="sub-menu collapse" id="tables">
                	<li><a href="userConsumptions.jsp" title="User Consumptions"><img src=common/images/icons/userconsumption.png /><span class="nav-label">User Consumptions</span></a></li>
                    <li><a href="topupSummary.jsp" title="Top Up Summary"><img src=common/images/icons/topupsummary.png /><span class="nav-label">ReCharge Summary</span></a></li>
                    <li><a href="financialreports.jsp" title="Finanical Report"><img src=common/images/icons/financialreports.png /><span class="nav-label">Financial Reports</span></a></li>
                	<li><a href="alarmReport.jsp" title="Report Alarm"><img src=common/images/icons/3-alarmreport.png /><span class="nav-label">Alarms Report</span></a></li>
                   </ul>
                </li>
					
				
				<%if(user_id.equalsIgnoreCase("1")){ %>
                <li><a href="Mgmt.jsp" title="User Management"><img src=common/images/icons/usermanagement.png /><span class="nav-label">User Management</span></a></li>
                
                <%}%>	
					                
                <% } else if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("5")) {
                %>
 				<li> <a href="approval.jsp" title="List Approval"><img src=common/images/icons/listofapproval.png /> <span class="nav-label">List Of Approval</span></a> </li>
 				<!--  <li> <a href="communityDetails.jsp" title="Community"><img src=common/images/icons/community.png /> <span class="nav-label">Community Management</span></a> </li>
				<li> <a href="blockDetails.jsp" title="Block"><img src=common/images/icons/2-block.png /> <span class="nav-label">Block Management</span></a> </li> -->
				<li> <a href="customerDetails.jsp" title="Customer Management"><img src=common/images/icons/customer.png /> <span class="nav-label">Customer Management</span></a> </li>
				<li> <a href="tariff.jsp" title="Tariff"><img src=common/images/icons/tariff.png /> <span class="nav-label">Tariff</span></a> </li>                
                <li> <a href="alert.jsp" title="Alert Configuration"><img src=common/images/icons/alerts.png /> <span class="nav-label">Alerts Configuration</span></a> </li>
               <%if(user_id.equalsIgnoreCase("2")){ %>
                <li> <a href="configuration.jsp" title="Configuration"><img src=common/images/icons/configuration.png /> <span class="nav-label">Meter Commands </span></a> </li>
                <%}%>
                <li> <a href="configurationStatus.jsp" title="Configuration Status"><img src=common/images/icons/configurationdetails1.png /> <span class="nav-label">Meter Commands Status</span></a> </li>
                <li> <a href="LiveDashBoard.jsp" title="Customer Details"><img src=common/images/icons/dashboard.png /> <span class="nav-label">Customer Details</span></a> </li>
                  <%if(user_id.equalsIgnoreCase("2")){ %>
                <li> <a href="topup.jsp" title="ReCharge"><img src=common/images/icons/topup.png /> <span class="nav-label">ReCharge</span></a> </li>
                <%}%>
                <li> <a href="topupStatus.jsp" title="ReCharge Status"><img src=common/images/icons/toopupdetailss.png /> <span class="nav-label">ReCharge Status</span></a> </li>
    			<!-- <li><a href="alarms.jsp" title="Alarm"><img src=common/images/icons/alarm.png /><span class="nav-label">Alarms</span></a></li> -->
                   <li><a href="holiday.jsp" title="Vacation"><img src=common/images/icons/4-vacation.png /><span class="nav-label">Vacation</span></a></li>
                   <li> <a href="#" data-toggle="collapse" data-target="#tables" class="collapsed"><i
                      class="fa fa-table"></i> <span class="nav-label">Reports</span><span
                      class="fa fa-chevron-left float-right"></span></a>
                  <ul class="sub-menu collapse" id="tables">
                	<li><a href="userConsumptions.jsp" title="User Consumptions"><img src=common/images/icons/userconsumption.png /><span class="nav-label">User Consumptions</span></a></li>
                    <li><a href="topupSummary.jsp" title="Top Up Summary"><img src=common/images/icons/topupsummary.png /><span class="nav-label">ReCharge Summary</span></a></li>
                    <li><a href="financialreports.jsp" title="Finanical Report"><img src=common/images/icons/financialreports.png /><span class="nav-label">Financial Reports</span></a></li>
                	<li><a href="alarmReport.jsp" title="Report Alarm"><img src=common/images/icons/3-alarmreport.png /><span class="nav-label">Alarms Report</span></a></li>
                   </ul>
                </li>
    				
    				<%if(user_id.equalsIgnoreCase("2")){ %>
                <li><a href="feedbackStatus.jsp" title="Feedback"><img src=common/images/icons/4-vacation.png /><span class="nav-label">FeedBack Status</span></a></li>
                <%}%>
                <% } else if (user_id.equalsIgnoreCase("3")) {%>
                <!-- <li> <a href="customerDetails.jsp" title="Customer"><img src=common/images/icons/customer.png /> <span class="nav-label">Customer</span></a> </li> -->
<li class=""><a href="userConsumptions.jsp" title="User Consumption"><img src=common/images/icons/userconsumption.png /><span class="nav-label">User Consumptions</span></a></li>
<!--                 <li> <a href="LiveDashBoard.jsp" title="Dashboard"><img src=common/images/icons/dashboard.png /> <span class="nav-label">DashBoard</span></a> </li> -->
                <li> <a href="topup.jsp" title="ReCharge"><img src=common/images/icons/topup.png /> <span class="nav-label">ReCharge</span></a> </li>
                <li> <a href="topupStatus.jsp" title="ReCharge Status"><img src=common/images/icons/toopupdetailss.png /> <span class="nav-label">ReCharge Status</span></a> </li>
                
                <li><a href="feedback.jsp" title="Feedback"><img src=common/images/icons/4-vacation.png /><span class="nav-label">Feedback/Compliant</span></a></li>
                
                <li><a href="holiday.jsp" title="Vacation"><img src=common/images/icons/4-vacation.png /><span class="nav-label">Vacation</span></a></li>
                <% } %>
                
                </ul>
            </div>
          </aside>
        </div>
        <!--sidebar end-->
<%} %>
</body>
</html>
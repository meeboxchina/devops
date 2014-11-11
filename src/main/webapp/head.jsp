<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%  HttpSession httpsession = request.getSession(); %>
<div class="nav">
	<div id="logo" class="logo"><h1>DevOps</h1></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'"><h2>DNS</h2></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'"><h2>AutoMation</h2></div>
	<div id="user" class="user"><%=httpsession.getAttribute("commonname")%> | <a href="/devops/logout">logout</a></div>
</div>
</body>
</html>
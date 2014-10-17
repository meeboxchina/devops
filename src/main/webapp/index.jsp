<!DOCTYPE html>
<html>
<head>
<title></title>
<meta name="generator" content="Bluefish 2.2.5" >
<meta name="author" content="sunyu" >
<meta name="date" content="2014-10-17T16:25:12+0800" >
<meta name="copyright" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="css/nav.css">
</head>
<body>
<script type="text/javascript" language="javascript"> 
function iFrameHeight() {
	var ifm = document.getElementById("detailiframe"); 
	var subWeb = document.frames ? document.frames["detailiframe"].document:ifm.contentDocument;
	if(ifm != null && subWeb != null) { 
		document.getElementById("main_right").height = subWeb.body.scrollHeight;
		document.getElementById("main").height = subWeb.body.scrollHeight;
		ifm.height = subWeb.body.scrollHeight;
	} 
} 

function iframe(zone) {
	document.getElementById("detailiframe").src="jsp/records.jsp?zone="+zone;
	iFrameHeight();
} 

</script> 
<div class="nav">
	<div id="logo" class="logo"><h1>DevOps<h1></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'" onm><h2>DNS</h2></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'" onm><h2>AutoMation</h2></div>
</div>
<div id="main" class="main">
	<div id="main_left"  class="main_left">
		<div id="title" class="main_left_title">Domain List</div>
		<div id="list" class="main_left_list">
		<%
			java.sql.Connection sqlConn; //数据库连接对象 
			java.sql.Statement sqlStmt; //语句对象 
			java.sql.ResultSet rs; //结果集对象 

			String dbhost = "meeboxchina.mysql.rds.aliyuncs.com";
			String database = "bind";
			String user = "bind";
			String pass = "madhousedns";	
	
			String zone = "null";
			int count = 0;
	
			//登记JDBC驱动对象 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	
			//连接数据库 
			sqlConn= java.sql.DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+database,user,pass); 
	
			//创建语句对象 
			sqlStmt=sqlConn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY); 
	
			//执行Sql语句 
			String sqlQuery = "select zone,count(*) as count from records group by zone"; 
	
			rs = sqlStmt.executeQuery(sqlQuery); 
			
			while(rs.next())
			{	
				zone = rs.getString("zone");
				count = rs.getInt("count");
				//out.print("<li><a href='javascript:frame(\"" + zone +"\")'; style='color:black; text-decoration:none'>" + zone + "</a>(" + count + ")" + "</li>");
				out.print("<li><a href='javascript:iframe(\"" + zone +"\")'; style='color:black; text-decoration:none'>" + zone + "</a></li>");
			}
		%>
		  
		</div>
		<div class="main_left_add" style="margin:0px">
			<div float="left" width="100%" height="30px"  class="main_left_add_item" style="border-top:none; line-height:30px; color:grey" >add</div>
			<!--  
			<div float="left" width="100%" height="50px" class="main_left_add_item"  style="line-height:30px; margin:0px;"><input id="zone" name="zone" size="45" style="padding:0px;text-align:center; height:22px" ></div>
			<div display="none" float="left" class="main_left_add_item"><a href="javascript:add();" style="line-height:30px;color:black; text-decoration:none">submit</a> | <a href="javascript:hideAdd();" style="color:black; text-decoration:none">cancle</a></div>
			-->
		</div>
	</div>
	<div id="main_right" class="main_right"><iframe id="detailiframe" scrolling="no" frameborder="0" src="jsp/records.jsp?zone=<% out.print(zone); %>" width="100%" onload="iFrameHeight()"></iframe></div>
	
</div>

</body>
</html>
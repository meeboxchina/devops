<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf8" %> 
<%@ page language="java" %> 
<%@ page import="com.mysql.jdbc.Driver" %> 
<%@ page import="java.sql.*" %> 
<html>
<head>
<title>DNS Records</title>
<meta name="generator" content="Bluefish 2.2.5" >
<meta name="author" content="sunyu" >
<meta name="date" content="2014-10-17T18:30:25+0800" >
<meta name="copyright" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="../css/nav.css">
</head>
<body>
<div class="main_right_detail">
<%
	java.sql.Connection sqlConn; //数据库连接对象 
	java.sql.Statement sqlStmt; //语句对象 
	java.sql.ResultSet rs; //结果集对象 

	String dbhost = "127.0.0.1";
	String database = "bind";
	String user = "bind";
	String pass = "bind";	
	
	String host;
	String type;
	String view;
	String data;
	String ttl;
	String mx_priority;

	
	//登记JDBC驱动对象 
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	
	//连接数据库 
	sqlConn= java.sql.DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+database,user,pass); 
	
	//创建语句对象 
	sqlStmt=sqlConn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY); 
	
	//执行Sql语句 
	String sqlQuery = "select host,type,view,data,ttl,mx_priority from records"; 
	
	rs = sqlStmt.executeQuery(sqlQuery); 
	
	while(rs.next())
	{
			host = rs.getString("host");
			type = rs.getString("type");
			view = rs.getString("view");
			data = rs.getString("data");
			ttl = rs.getString("ttl");
			mx_priority = rs.getString("mx_priority");
			
			
			out.print("<div class='main_right_detail_item'><div style='width=15% float：left'>" +host + "</div><div style='width=10% float=left'>" + type + "</div><div style='width=15% float=left'>" + view + "  " +  data +   " " + ttl  +  "</div>" + "</div>");
	}
%> 

</div>	
	
</body>
</html>



	
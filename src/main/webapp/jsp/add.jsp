<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title></title>
</head>
<body>
<%

	String zone = request.getParameter("zone");
	String host = request.getParameter("host");
	String data = request.getParameter("data");
	String type = request.getParameter("type");
	String view = request.getParameter("view");
	String ttl  = request.getParameter("ttl");
	
	java.sql.Connection sqlConn; 
	java.sql.Statement sqlStmt;
	java.sql.ResultSet rs; 
	int result;
	
	String dbhost = "meeboxchina.mysql.rds.aliyuncs.com";
	String database = "bind";
	String user = "bind";
	String pass = "madhousedns";	
	
	int count;
	
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	
	sqlConn= java.sql.DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+database,user,pass); 
	
	sqlStmt=sqlConn.createStatement(); 
	
	String sqlInsert = "insert into records (zone,host,data,type,view,ttl) values ('" + zone + "','" + host + "','" + data + "','" + type + "','" + view + "'," + ttl + ")" ; 
	
	result = sqlStmt.executeUpdate(sqlInsert); 
	
	out.print(result);
	
	
	sqlConn.close();
	
	response.sendRedirect("records.jsp?zone=" + zone );
%>
</body>
</html>
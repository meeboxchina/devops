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
	String operation = request.getParameter("operation");
	String add = "add";
	String del = "del";
	String zone = request.getParameter("zone");
	String id   = request.getParameter("id");
	String host = request.getParameter("host");
	String data = request.getParameter("data");
	String type = request.getParameter("type");
	String view = request.getParameter("view");
	String ttl  = request.getParameter("ttl");
	
	out.print(operation);
	
	java.sql.Connection sqlConn; //数据库连接对象 
	java.sql.Statement sqlStmt; //语句对象 
	java.sql.ResultSet rs; //结果集对象 
	
	boolean result;
	
	String dbhost = "meeboxchina.mysql.rds.aliyuncs.com";
	String database = "bind";
	String user = "bind";
	String pass = "madhousedns";	
	
	int count;
	
	//登记JDBC驱动对象 
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	
	//连接数据库 
	sqlConn= java.sql.DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+database,user,pass); 
	
	//创建语句对象 
	sqlStmt=sqlConn.createStatement(); 
	
	
	if(operation == add ){
		String sqlInsert = "insert into records (zone,host,data,type,view,ttl) values ('" + zone + "','" + host + "','" + data + "','" + type + "','" + view + "'," + ttl + ")" ; 
		result = sqlStmt.execute(sqlInsert); 
	}else if(operation == "del"){
		out.print(zone);
		String sqlDel = "delete from records where id=" + id  + " and zone='" + zone + "'";
		result = sqlStmt.execute(sqlDel);
		if(result){
			out.print("删除失败");
		}else{
			out.print("删除成功");
		}
	}else{
		out.print(operation);
		out.print("error");
	}
	sqlConn.close();
%>
</body>
</html>
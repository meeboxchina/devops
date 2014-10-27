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
<script language="JavaScript">
function showAdd(){
	document.getElementById("add").style.display = "block";
}
function hideAdd(){
	document.getElementById("add").style.display = "none";
}

function add(){
	host = document.getElementById("host").value;
	type = document.getElementById("type").value;
	view = document.getElementById("view").value;
	data = document.getElementById("data").value;
	ttl = document.getElementById("ttl").value;
	zone = document.getElementById("zone").value;
	if( host=="" || data=="" || ttl==""){
		alert("输入错误，请重新输入");
	}else{
		record.action = "add.jsp";  
		record.submit();   
	}
}

function del(id){
	document.getElementById("id").value = id;
	record.action = "del.jsp";  
	record.submit();   
}


</script>
<%
	String zone = request.getParameter("zone");

	java.sql.Connection sqlConn; //数据库连接对象 
	java.sql.Statement sqlStmt; //语句对象 
	java.sql.ResultSet rs; //结果集对象 

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
	sqlStmt=sqlConn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY); 
	
	//执行Sql语句 
	String sqlQuery = "select count(*) as count from records where zone='" + zone + "' and type<>'SOA' order by type" ; 
	
	rs = sqlStmt.executeQuery(sqlQuery); 
	
	if(rs.next())
	{
		count = rs.getInt("count");
	}else{
		count = 0;
	}
%> 
<form id="record" method="post">
<div class="main_right_detail">
  <div id="count" class='main_right_detail_head'>
    <div style='width:35%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><strong><% out.print(zone);%></strong> 共有<% out.print(count); %>条记录</div>
    <div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'></div>
    <div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">添加记录</a></div>
    <div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">暂停</a></div>
    <div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">启用</a></div>
    <div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">删除</a></div>
  </div>

  <div class='main_right_detail_item'>
  	<input name="zone" id="zone" type="hidden" value="<% out.print(zone); %>">
  	<input name="id" id="id" type="hidden" value="0">
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>主机记录</div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>记录类型</div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>线路类型</div>
	<div style='width:45%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>记录值</div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>TTL</div>
	<div style='width:15%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>操作</div>
  </div>
  
  <div id="add" class='main_right_detail_add' style="display:none;background-color:#f8f8f8">
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="host" name="host" size="6" style="text-align:center;" /></div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>
	  <select id="type" name="type"> 
		<option value="A">A</option> 
		<option value="CNAME">CNAME</option>
		<option value="MX">MX</option>
		<option value="NS">NS</option>
		<option value="AAAA">AAAA</option> 
	  </select>
	</div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>
	  <select id="view" name="view"> 
		<option value="any">默认</option> 
		<option value="telcom">电信</option>
		<option value="unicom">联通</option>
		<option value="cmcc">移动</option>
		<option value="cernet">教育网</option> 
	  </select>
	</div>
	<div style='width:45%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="data" name="data" size="40" style="text-align:center;" /></div>
	<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="ttl" name="ttl" size="5" value="600" style="text-align:center;" /></div>
	<div style='width:15%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:add();" style="color:black; text-decoration:none">提交</a> | <a href="javascript:hideAdd();" style="color:black; text-decoration:none">取消</a></div>
  </div>
	
<%
	int id;
	String host;
	String type;
	String view;
	String data;
	String ttl;
	String mx_priority;

	String viewname;
	
	//执行Sql语句 
	sqlQuery = "select id,host,type,view,data,ttl,mx_priority from records where zone='" + zone + "' and type<>'SOA' order by type,id"; 

	rs = sqlStmt.executeQuery(sqlQuery); 
	
	while(rs.next())
	{
			id = rs.getInt("id");
			host = rs.getString("host");
			type = rs.getString("type");
			view = rs.getString("view");
			data = rs.getString("data");
			ttl = rs.getString("ttl");
			mx_priority = rs.getString("mx_priority");

			/*
			switch (view) {
				case "any": viewname="默认";
				case "telcom": viewname="电信";
				case "unicom": viewname="联通";
				case "cmcc": viewname="移动";
				case "cernet": viewname="教育网";
				default: viewname="默认";
			}
			*/

			out.print("<div class='main_right_detail_item'>");
			out.print("<div style='width:10%;float:left;text-align:center;line-height:30px;'>" + host + "</div>");
			out.print("<div style='width:10%;float:left;text-align:center;line-height:30px;'>" + type + "</div>");
			out.print("<div style='width:10%;float:left;text-align:center;line-height:30px;'>" + view + "</div>");
			out.print("<div style='width:45%;float:left;text-align:center;line-height:30px;'>" +  data +  "</div>");
			out.print("<div style='width:10%;float:left;text-align:center;line-height:30px;'>" + ttl  +  "</div>");
			out.print("<div style='width:15%;float:left;text-align:center;line-height:30px;'><a href='javascript:del(" + id +")'; style='color:black; text-decoration:none'>删除</a></div>");
			out.print("</div>");
	}
	
	sqlConn.close();
%> 

</div>	
</form>
</body>
</html>



	

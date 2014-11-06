<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta name="generator" content="Bluefish 2.2.5" >
<meta name="author" content="sunyu" >
<meta name="date" content="2014-11-06T16:58:33+0800" >
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

var zonelist;
var zones;
var zoneListHTML;

function showNewZone(){
	document.getElementById('newzone').style.display="block"; 
	document.getElementById('zonelist').style.display="none";  	
}

function showZoneList(){
	document.getElementById('list').innerHTML=zoneListHTML; 
	document.getElementById('newzone').style.display="none"; 
	document.getElementById('zonelist').style.display="block";   	
}


function getZoneList() {
	zonelist = new XMLHttpRequest();
	zonelist.onreadystatechange=callback;
	zonelist.open("POST","/devops/zone",true);
	zonelist.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	zonelist.send("action=getlist");
}

function callback(){
	if(zonelist.readyState==4){
		if(zonelist.status==200){
			eval("var zonesResponse =" + zonelist.responseText); 
			zoneListHTML = "";
			for(i=0; i<zonesResponse.data.length; i++){
				zoneListHTML += "<li><a style='color:black; text-decoration:none; font-size:11;' href='javascript:getRecords(\"" + zonesResponse.data[i] + "\")'>" + zonesResponse.data[i] + "</a></li>";
			}
			showZoneList();
			
		}else{		
			alert("got failed 1");
		}
	}
}

var xmlhttp;
function addZone(){
	var zone = document.getElementById('newzone.zone').value;
	var ttl = document.getElementById('newzone.ttl').value;
	var expire = document.getElementById('newzone.expire').value;
	var refresh = document.getElementById('newzone.refresh').value;
	var retry = document.getElementById('newzone.retry').value;
	var serial = document.getElementById('newzone.serial').value;
	var minimum = document.getElementById('newzone.minimum').value;
	var resp_person = document.getElementById('newzone.resp_person').value;
	var primary_ns = document.getElementById('newzone.primary_ns').value;

	if(zone==null || ttl==""){
		alert("参数错误");	
	}
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=cbAddZone;
	xmlhttp.open("POST","/devops/zone",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	xmlhttp.send(
		"action=add" +
		"&zone=" + zone +
		"&ttl=" + ttl +
		"&expire=" + expire +
		"&refresh=" + refresh +
		"&retry=" + retry +
		"&serial=" + serial +
		"&minimum=" + minimum +
		"&resp_person=" + resp_person +
		"&primary_ns=" + primary_ns
		);
}

function cbAddZone(){
	if(xmlhttp.readyState==4){
		if(xmlhttp.status==200){
			alert("Add Successfully.");
			getZoneList();
		}else{
			alert("Add Failed, Please check and try again.");
		}
	}
}

getZoneList();



var xmlhttpRecords;
var recordsHTML;
function getRecords(zone){
	document.getElementById('record.zone').value=zone;
	xmlhttpRecords = new XMLHttpRequest();
	xmlhttpRecords.onreadystatechange=cbGetRecords;
	xmlhttpRecords.open("POST","/devops/records",true);
	xmlhttpRecords.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	xmlhttpRecords.send(
		"action=get" +
		"&zone=" + zone
		);
}

function cbGetRecords(){
	if(xmlhttpRecords.readyState==4){
		if(xmlhttpRecords.status==200){
			eval("var recordsResponse =" + xmlhttpRecords.responseText); 
			recordsHTML="";
			
			document.getElementById('recordscount').innerHTML = document.getElementById('record.zone').value + " 共有：" + recordsResponse.data.length + " 条记录";
			if(recordsResponse.data.length>0)
			{
				for(i=0; i<recordsResponse.data.length; i++){
					recordsHTML += "<div id=record" + recordsResponse.data[i].id + " class='main_right_detail_item'>"
						+ "<div id=record" + recordsResponse.data[i].id +".host style='width:10%;float:left;text-align:center;line-height:30px;' onclick='javascript:editRecord(" + recordsResponse.data[i].id + ")'>" + recordsResponse.data[i].host + "</div>"
						+ "<div id=record" + recordsResponse.data[i].id +".type style='width:10%;float:left;text-align:center;line-height:30px;' onclick='javascript:editRecord(" + recordsResponse.data[i].id + ")'>" + recordsResponse.data[i].type + "</div>"
						+ "<div id=record" + recordsResponse.data[i].id +".view style='width:10%;float:left;text-align:center;line-height:30px;' onclick='javascript:editRecord(" + recordsResponse.data[i].id + ")'>" + recordsResponse.data[i].view + "</div>"
						+ "<div id=record" + recordsResponse.data[i].id +".data style='width:45%;float:left;text-align:center;line-height:30px;' onclick='javascript:editRecord(" + recordsResponse.data[i].id + ")'>" + recordsResponse.data[i].data + "</div>"
						+ "<div id=record" + recordsResponse.data[i].id +".ttl style='width:10%;float:left;text-align:center;line-height:30px;' onclick='javascript:editRecord(" + recordsResponse.data[i].id + ")'>" + recordsResponse.data[i].ttl + "</div>"
						+ "<div style='width:15%;float:left;text-align:center;line-height:30px;'><a href='javascript:delRecord(\"" + recordsResponse.data[i].zone + "\"," + recordsResponse.data[i].id + ",\"" + recordsResponse.data[i].host + "\")'; style='color:black; text-decoration:none'>删除</a></div>"
						+ "</div>";
				}
			}
			document.getElementById('recordslist').innerHTML=recordsHTML;
			
		}else{		
			alert("got records failed");
		}
	}
}

function showAddRecord(){
	document.getElementById("add").style.display = "block";
}
function hideAddRecord(){
	document.getElementById("add").style.display = "none";
}

var xmlhttpAddRecord;
function addRecord(){
	var zone = document.getElementById("record.zone").value;
	var host = document.getElementById("newrecord.host").value;
	var type = document.getElementById("newrecord.type").value;
	var view = document.getElementById("newrecord.view").value;
	var data = document.getElementById("newrecord.data").value;
	var ttl = document.getElementById("newrecord.ttl").value;
	
	xmlhttpAddRecord = new XMLHttpRequest();
	xmlhttpAddRecord.onreadystatechange=cbAddRecord;
	xmlhttpAddRecord.open("POST","/devops/records",true);
	xmlhttpAddRecord.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	xmlhttpAddRecord.send(
		"action=add" +
		"&zone=" + zone +
		"&host=" + host +
		"&type=" + type +
		"&view=" + view +
		"&data=" + data +
		"&ttl=" + ttl
		);	
}

function cbAddRecord(){
	if(xmlhttpAddRecord.readyState==4){
		if(xmlhttpAddRecord.status==200){
			eval("var recordsResponse =" + xmlhttpAddRecord.responseText);
			if(recordsResponse="ok"){
				alert("Add Seccessflly");
				hideAddRecord();
				document.getElementById("newrecord.host").value = "";
				document.getElementById("newrecord.data").value = "";
			}
			getRecords(document.getElementById("record.zone").value);
		}else{		
			alert("add records failed");
		}
	}
}

var xmlhttpDelRecord;
function delRecord(zone,id,host){
	if(!confirm("请确认是否要删除 " + host + "." + zone)){
		//getRecords(document.getElementById("record.zone").value);
		return;
	} 
		 
	xmlhttpDelRecord = new XMLHttpRequest();
	xmlhttpDelRecord.onreadystatechange=cbDelRecord;
	xmlhttpDelRecord.open("POST","/devops/records",true);
	xmlhttpDelRecord.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	xmlhttpDelRecord.send(
		"action=del" +
		"&zone=" + zone +
		"&id=" + id +
		"&host=" + host
		);	
}

function cbDelRecord(){
	if(xmlhttpDelRecord.readyState==4){
		if(xmlhttpDelRecord.status==200){
			eval("var recordsResponse =" + xmlhttpDelRecord.responseText);
			if(recordsResponse.status=="ok"){
				alert("Delete Seccessflly");
				getRecords(document.getElementById("record.zone").value);
			}
		}else{		
			alert("del records failed");
		}
	}
}

function cancleEditRecord(id){
	var zone = document.getElementById("record.zone").value;
	getRecords(zone);
}

function editRecord(id){
	var zone = document.getElementById("record.zone").value;
	var host = document.getElementById("record" +id + ".host").innerText;
	var type = document.getElementById("record" +id + ".type").innerText;
	var view = document.getElementById("record" +id + ".view").innerText;
	var data = document.getElementById("record" +id + ".data").innerText;
	var ttl = document.getElementById("record" +id + ".ttl").innerText;
	var editHTML=
		"<div id='record"+ id + " class='main_right_detail_add' style='background-color:#f8f8f8'>"
			+ "<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id='record" + id + ".host' size='6' style='text-align:center;'' value='"+host+"'/></div>"
			+ "<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>"
			+ 	"<select id='record" + id + ".type' name='type' value='"+type+"'>"
			+		"<option value='A'>A</option> "
			+		"<option value='CNAME'>CNAME</option>"
			+		"<option value='MX'>MX</option>"
			+		"<option value='NS'>NS</option>"
			+		"<option value='AAAA'>AAAA</option>"
			+	"</select>"
			+ "</div>"
			+ "<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>"
			+	"<select id='record" + id + ".view'  value='"+view+"'>"
			+		"<option value='any'>默认</option>"
			+		"<option value='telcom'>电信</option>"
			+		"<option value='unicom'>联通</option>"
			+		"<option value='cmcc'>移动</option>"
			+		"<option value='cernet'>教育网</option>"
			+	"</select>"
			+ "</div>"
			+ "<div style='width:45%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id='record" + id + ".data' size='40' style='text-align:center;' value='"+data+"'></div>"
			+ "<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id='record" + id + ".ttl' size='5' style='text-align:center;' value='"+ttl+"'></div>"
			+ "<div style='width:15%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href='javascript:updateRecord("+id+");' style='color:black; text-decoration:none'>提交</a> | <a href='javascript:cancleEditRecord("+id+");' style='color:black; text-decoration:none'>取消</a></div>"
			+ "</div>";
	document.getElementById("record"+id).innerHTML=editHTML;
	document.getElementById("record"+id).onclick=null;
	document.getElementById("record"+id+".type").value=type;
	document.getElementById("record"+id+".view").value=view;
}


var xmlhttpUpdateRecord;
function updateRecord(id){
	var zone = document.getElementById("record.zone").value;
	var host = document.getElementById("record" +id + ".host").value;
	var type = document.getElementById("record" +id + ".type").value;
	var view = document.getElementById("record" +id + ".view").value;
	var data = document.getElementById("record" +id + ".data").value;
	var ttl = document.getElementById("record" +id + ".ttl").value;
	
	xmlhttpUpdateRecord = new XMLHttpRequest();
	xmlhttpUpdateRecord.onreadystatechange=cbUpdateRecord;
	xmlhttpUpdateRecord.open("POST","/devops/records",true);
	xmlhttpUpdateRecord.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
	xmlhttpUpdateRecord.send(
		"action=update" +
		"&zone=" + zone +
		"&id=" + id +
		"&host=" + host +
		"&type=" + type +
		"&view=" + view +
		"&data=" + data +
		"&ttl=" + ttl
		);	
}

function cbUpdateRecord(){
	if(xmlhttpUpdateRecord.readyState==4){
		if(xmlhttpUpdateRecord.status==200){
			eval("var recordsResponse =" + xmlhttpUpdateRecord.responseText);
			if(recordsResponse.status=="ok"){
				alert("Update Seccessflly");
				getRecords(document.getElementById("record.zone").value);
			}
		}else{		
			alert("update records failed");
		}
	}
}

</script> 
<%  HttpSession s = request.getSession(); %>
<div class="nav">
	<div id="logo" class="logo"><h1>DevOps</h1></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'"><h2>DNS</h2></div>
	<div id="dns" class="menu" onmouseover="this.className='menu_hover'" onmouseout="this.className='menu'" onclick="this.className='menu_click'"><h2>AutoMation</h2></div>
	<div id="user" class="user"><%=s.getAttribute("commonname")%> | <a href="/devops/logout">logout</a></div>
</div>
<div id="main" class="main">
	<div id="main_left"  class="main_left">
		<div id="title" class="main_left_title"> <a href="javascript:getZoneList()" style="color:black; text-decoration:none; font-size:13;">ZoneList</a>  |  <a href="javascript:showNewZone()" style="color:black; text-decoration:none; font-size:13;">Add Zone</a></div>
		<div id="zonelist" class="main_left_list">
			<div id="list">
				<li></li>
			</div>		
		</div>
		<div class="main_left_add" style="margin:0px">
			<div id="newzone"  name="newzone" float="left" width="100%" height="100%" class="main_left_newzone"  style="line-height:30px; margin:0px; display:none">
				<div width="100%" class="main_left_newzone_item">
					<div style="float:left;width:40%;text-align:right;">Zone:</div>
					<div style="float:left;width:55%;"><input id="newzone.zone" name="newzone.zone" style="padding:0px;text-align:center; height:22px; width:90%" value="" ></div>
				</div>
				<div width="100%" class="main_left_newzone_item">
					<div style="float:left;width:40%;text-align:right;">TTL:</div>
					<div style="float:left;width:55%;"><input id="newzone.ttl" name="newzone.ttl" style="padding:0px;text-align:center; height:22px; width:90%" value="3600" ></div>
				</div>
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Refresh:</div>
					<div style="float:left;width:55%"><input id="newzone.refresh" name="newzone.refresh" style="padding:0px;text-align:center; height:22px; width:90%" value="3600" ></div>
				</div>
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Retry:</div>
					<div style="float:left;width:55%"><input id="newzone.retry" name="newzone.retry" style="padding:0px;text-align:center; height:22px; width:90%"  value="3600"></div>
				</div>
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Expire:</div>
					<div style="float:left;width:55%"><input id="newzone.expire" name="zone" style="padding:0px;text-align:center; height:22px; width:90%"  value="86400"></div>
				</div>	
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Minimum:</div>
					<div style="float:left;width:55%"><input id="newzone.minimum" name="newzone.minimum" style="padding:0px;text-align:center; height:22px; width:90%"  value="10"></div>
				</div>					
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Serial:</div>
					<div style="float:left;width:55%"><input id="newzone.serial" name="newzone.serial" style="padding:0px;text-align:center; height:22px; width:90%"  value="2008082700"></div>
				</div>
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Resp_person:</div>
					<div style="float:left;width:55%"><input id="newzone.resp_person" name="newzone.resp_person" style="padding:0px;text-align:center; height:22px; width:90%" value="root"></div>
				</div>	
				<div width="100%">
					<div style="float:left;width:40%;text-align:right;">Primary_ns:</div>
					<div style="float:left;width:55%"><input id="newzone.primary_ns" name="newzone.primary_ns" style="padding:0px;text-align:center; height:22px; width:90%"  value="ns1.mgogo.com."></div>
				</div>	
				<div width="100%">
					<div style="float:left;width:100%;text-align:center;margin-top:10px"><a href="javascript:addZone()">Submit</a></div>
				</div>						
			</div>
		</div>
	</div>
	<div id="main_right" class="main_right">
		<div class="main_right_detail">
  			<div id="count" class='main_right_detail_head'>
    			<div style='width:35%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><span id="recordscount"></span></div>
    			<div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'></div>
    			<div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAddRecord();" style="color:grey">添加记录</a></div>
   				<div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">暂停</a></div>
    			<div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">启用</a></div>
    			<div style='width:12%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:showAdd();" style="color:grey">删除</a></div>
  		</div>

  		<div class='main_right_detail_item'>
  			<input name="zone" id="zone" type="hidden" value="">
  			<input name="id" id="id" type="hidden" value="0">
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>主机记录</div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>记录类型</div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>线路类型</div>
			<div style='width:45%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>记录值</div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>TTL</div>
			<div style='width:15%;float:left;text-align:center;vertical-align: middle; height:100%; font-weight:bold;line-height:30px;'>操作</div>
  		</div>
  
  		<div id="add" class='main_right_detail_add' style="display:none;background-color:#f8f8f8">
  			<input type="hidden" id="record.zone" value="">
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="newrecord.host" name="host" size="6" style="text-align:center;" /></div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>
	  			<select id="newrecord.type" name="type"> 
					<option value="A">A</option> 
					<option value="CNAME">CNAME</option>
					<option value="MX">MX</option>
					<option value="NS">NS</option>
					<option value="AAAA">AAAA</option> 
	  			</select>
			</div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'>
	  			<select id="newrecord.view" name="view"> 
					<option value="any">默认</option> 
					<option value="telcom">电信</option>
					<option value="unicom">联通</option>
					<option value="cmcc">移动</option>
					<option value="cernet">教育网</option> 
	  			</select>
			</div>
			<div style='width:45%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="newrecord.data" name="data" size="40" style="text-align:center;" /></div>
			<div style='width:10%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><input id="newrecord.ttl" name="ttl" size="5" value="600" style="text-align:center;" /></div>
			<div style='width:15%;float:left;text-align:center;vertical-align: middle; height:100%; line-height:30px;'><a href="javascript:addRecord();" style="color:black; text-decoration:none">提交</a> | <a href="javascript:hideAddRecord();" style="color:black; text-decoration:none">取消</a></div>
  		</div>
  		<span id="recordslist"></span>
	</div>	
</div>
</body>
</html>

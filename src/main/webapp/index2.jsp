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

var xmlhttp;

function getZoneRecords(){
	xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET","http://127.0.0.1:8080/devops/records?zone=mdn2.net",true);
	xmlhttp.send();
	
}

function onResponse(){
	if(xmlhttp.readyState == 4){
		if    (xmlhttp.status == 200)    {
			alert("200");
			document.getElementById("main_right").innerHTML=xmlhttp.getAllResponseHeaders();        
		}else{
			alert(xmlhttp.status);
			document.getElementById("main_right").innerHTML=xmlhttp.statusText;
		}
	}    
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
		  
		</div>
		<div class="main_left_add" style="margin:0px">
			<div float="left" width="100%" height="30px"  class="main_left_add_item" style="border-top:none; line-height:30px; color:grey" ><a href="javascript:void(0);" onclick="getZoneRecords()">add</a></div>
			<div float="left" width="100%" height="30px"  class="main_left_add_item" style="border-top:none; line-height:30px; color:grey" ><a href="javascript:void(0);" onclick="onResponse()">show</a></div>
			<!--  
			<div float="left" width="100%" height="50px" class="main_left_add_item"  style="line-height:30px; margin:0px;"><input id="zone" name="zone" size="45" style="padding:0px;text-align:center; height:22px" ></div>
			<div display="none" float="left" class="main_left_add_item"><a href="javascript:add();" style="line-height:30px;color:black; text-decoration:none">submit</a> | <a href="javascript:hideAdd();" style="color:black; text-decoration:none">cancle</a></div>
			-->
		</div>
	</div>
	<div id="main_right" class="main_right"></div>
	
</div>

</body>
</html>

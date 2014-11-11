var xmlhttpLogin;
function login() {
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if(username==""){
		document.getElementById('message').innerHTML="Please enter your account"; 
		document.getElementById('message').style.display="block"; 
	}else if(password==""){
		document.getElementById('message').innerHTML="Please enter your password"; 
		document.getElementById('message').style.display="block"; 
	}else{
		xmlhttpLogin = new XMLHttpRequest();
		xmlhttpLogin.open("POST","/devops/login",true);
		xmlhttpLogin.onreadystatechange=cbLogin;
		xmlhttpLogin.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
		xmlhttpLogin.send("username=" + username
			+ "&password=" + password);
	}
}

function cbLogin(){
	if(xmlhttpLogin.readyState==4){
		if(xmlhttpLogin.status==200){
			eval("var recordsResponse =" + xmlhttpLogin.responseText);
			if(recordsResponse.status=="ok"){
				var commonname = recordsResponse.data.commonname;
				var locationhref = recordsResponse.data.locationhref;
				document.getElementById('message').innerHTML="Welcome, " + commonname + " Please wait­"; 
				document.getElementById('message').style.display="block";
				window.location.href = locationhref;
							
			}else{
				document.getElementById('message').innerHTML="Login Failed, Please try again"; 
				document.getElementById('message').style.display="block";			
			}
		}else{		
			document.getElementById('message').innerHTML="Server Error, Please try again";
			document.getElementById('message').style.display="block";
		}
	}
}

function clearMessage(){
	document.getElementById('message').innerHTML=""; 
	document.getElementById('message').style.display="none";	
}

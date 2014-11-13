package devops;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws JSchException, IOException {
		// TODO Auto-generated method stub

		String username = "root";
		String password = "";
		String host = "211.136.107.44";
		int port = 60886;
		
		Session session;
		ChannelExec openChannel =null;
		String command = "yum install -y dmidecode  ;  dmidecode ";
		
		String result = "";
		
		
		JSch jsch=new JSch();
	    session = jsch.getSession(username, host, port);
	    java.util.Properties config = new java.util.Properties();
	    config.put("StrictHostKeyChecking", "no");
	    session.setConfig(config);
	    session.setPassword(password);
	    session.connect();
	    openChannel = (ChannelExec) session.openChannel("exec");
	    openChannel.setCommand(command);
	    int exitStatus = openChannel.getExitStatus();
	    
	    //System.out.println(exitStatus);
	    
	    openChannel.connect();  
	    InputStream in = openChannel.getInputStream();  
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
	    String buf = null;
	    while ((buf = reader.readLine()) != null) {
	    	result+= new String(buf.getBytes("gbk"),"UTF-8")  + "\r\n";  
	    }
	    
	    
	    openChannel.disconnect();
	    session.disconnect();
	    System.out.print(result);
	    
	    String reg = "(Product Name:)(.*)\r\n";
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(result);
	    
	    if(matcher.find()){
	    	System.out.println("ok");
	    	System.out.println(matcher.group(2).trim());
	    }else{
	    	System.out.println("No");
	    }
	    
	    
	}

}

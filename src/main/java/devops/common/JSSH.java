/**   
* @Project: devops
* @Title: JSSH.java
* @Package devops.common
* @Description: TODO
* @author sunyu
* @date Nov 14, 2014 2:51:36 PM
* @version V1.0   
*/
package devops.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author sunyu
 *
 */
public class JSSH {
	private String host;
	private int port;
	private String username;
	private String password;
	
	private Session session;
	private ChannelExec openChannel = null;
 	
	/**
	 * 
	 */
	public JSSH() {
		super();
	}
	
	public JSSH(String host, int port, String username, String password){
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public Session getSession(){
		JSch jsch=new JSch();
    	try {
			session = jsch.getSession(username, host, port);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	java.util.Properties config = new java.util.Properties();
    	config.put("StrictHostKeyChecking", "no");
    	session.setConfig(config);
    	session.setPassword(password);
    	try {
			session.connect(30000);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return session;
	}
	
	public String execute(String command){
		JSch jsch=new JSch();
    	try {
			session = jsch.getSession(username, host, port);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	java.util.Properties config = new java.util.Properties();
    	config.put("StrictHostKeyChecking", "no");
    	session.setConfig(config);
    	session.setPassword(password);
    	try {
			session.connect(30000);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	try {
			openChannel = (ChannelExec) session.openChannel("exec");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    openChannel.setCommand(command);
	    int exitStatus = openChannel.getExitStatus();
	    
	    //System.out.println(exitStatus);
	    
	    try {
			openChannel.connect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    InputStream in;
	    String result = "";
		try {
			in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
			String buf = null;
		    while ((buf = reader.readLine()) != null) {
		    	result+= new String(buf.getBytes("gbk"),"UTF-8")  + "\r\n";  
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
		openChannel.disconnect();
		session.disconnect();
		
	    return result;
	}
	
	public String productName(){
		String result = execute("dmidecode");
		String reg = "(Product Name:)(.*)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(result);
		
		if(matcher.find()){
			return matcher.group(2).trim();
		}else{
			return "unKnown";
		}
	
	}
	
	public String osName(){
		String result = execute("[ -f /etc/issue ]; ");
		/*
		String reg = "(Product Name:)(.*)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(result);
		
		if(matcher.find()){
			return matcher.group(2).trim();
		}else{
			return "unKnown";
		}
		*/
		return result;
	}
	
	
	public boolean installPackage(String packagename){
		execute("yum install -y" + packagename);
		return true;
	}
	
	public boolean addService(String servicename){
		execute("chkconfig --add " + servicename);
		execute("chkconfig " + servicename + " on");
		execute("service " + servicename + " start");
		return true;
	}
}

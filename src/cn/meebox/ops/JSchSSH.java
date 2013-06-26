package cn.meebox.ops;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class JSchSSH {

	/**
	 * @param args
	 * @throws IOException 
	 */
	/**
	 * @param args
	 * @throws IOException
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws JSchException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, JSchException, InterruptedException {
		// TODO Auto-generated method stub
		String username = "sunyu";
		String password = "sunny@mad";
		String host     = "220.189.221.38";
		int port		= 60886;
		
		String[] idcs  = new String[256];
		String[] hosts = new String[1024];
		String[] jails = new String[8192];
		
		idcs[0] = "huamu";
		idcs[1] = "ningbo";
		
		hosts[0] = "10.10.9.1";
		hosts[1] = "10.10.9.4";
		hosts[2] = "10.10.9.4";
		hosts[3] = "10.10.9.4";
		hosts[4] = "10.10.9.105";
		hosts[5] = "10.10.9.106";
		hosts[6] = "10.10.9.107";
		hosts[7] = "10.10.9.108";
		hosts[8] = "10.10.9.109";
		hosts[9] = "10.10.9.110";
		hosts[10] = "10.10.9.111";
		hosts[11] = "10.10.9.112";
		hosts[12] = "10.10.9.113";
		hosts[13] = "10.10.9.114";
		
		
		for(int n=5; n<=13; n++)
        {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, port);
			session.setPassword(password);
			Properties sshConfig = new Properties();
	        sshConfig.put("StrictHostKeyChecking", "no");
	        session.setConfig(sshConfig);
	        session.connect();
	        Channel channel = session.openChannel("shell");
	        OutputStream os = channel.getOutputStream();
	        PrintStream commander = new PrintStream(os,true);
        
        
        
        	channel.connect();
	        //channel.setOutputStream(System.out, true);
	        commander.println("ssh " + hosts[n] + " -l sunyu");
	        Thread.sleep(1000);
	        
	        commander.println("sunny@mad");
	        Thread.sleep(1000);
	        
	        InputStream outputtream_from_channel = channel.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(outputtream_from_channel));
	        String line;
	        
	        commander.println("jls | awk '{print $1\"|\"$2\"|\"$3\"|\"$4}'");
	        
	        
	        String[] jls = new String[1024];
	        Thread.sleep(2000);
	        System.out.println("---------------" + hosts[n] + "-----------------");
	        int i=0;
	        
	        
	        commander.close();
	        
	        Thread.sleep(1000);
	        
	        channel.disconnect();
	        
	        while((line=br.readLine())!= null){
	        	jls[i] = line;
	        	i++;
	        	//System.out.print(i);
	        	//System.out.print(line+"\n");
	        	
	        }
	        //System.out.print("Max:" + i);
	        for(int j=2; j<i; j++){
	        	jails = jls[j].split("\\|");
	        	for(int m=0; m<jails.length; m++){
	        		System.out.print(jails[m] + " ");
	        		channel.connect();
	        		
	        	}
	        	System.out.println();
	        }
	        
        }
        
	}

}

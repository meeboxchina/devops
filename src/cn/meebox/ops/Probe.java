package cn.meebox.ops;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Probe {

	/**
	 * @param args
	 * @throws JSchException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws JSchException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		IDC office = new IDC("office", "Yingchun Road, Shanghai");
		Entrance eoffice = new Entrance("office","27.115.15.14",22,"madhouse","madhouse");
		
		office.addEntrance(eoffice);
		
		
		JSch jsch = new JSch();
		Session session = jsch.getSession(eoffice.getUsernmae(), eoffice.getIpaddr(), eoffice.getPort());
		session.setPassword(eoffice.getPassword());
		Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect();
        Channel channel = session.openChannel("shell");
        OutputStream os = channel.getOutputStream();
        PrintStream commander = new PrintStream(os,true);
    
    	channel.connect();
        channel.setOutputStream(System.out, true);
        commander.println("cat /etc/passwd ");
        Thread.sleep(1000);
        
        
        
        
	}

}

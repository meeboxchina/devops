package devops;

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

import devops.common.JSSH;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws JSchException, Exception {
		// TODO Auto-generated method stub

		String username = "root";
		String password = "madhouse";
		String host = "172.16.28.201";
		int port = 22;
		
		JSSH ssh = new JSSH(host, port, username, password);
		/*
		System.out.print(ssh.execute("rpm -qa | grep libvirt"));
		System.out.print(ssh.execute("date"));
		System.out.print(ssh.execute("dmidecode"));
		
		System.out.print(ssh.installPackage("libvirt"));
		System.out.print(ssh.addService("libvirtd"));
		*/
		System.out.println(ssh.productName());
		System.out.println(ssh.osName());
		
	}
}

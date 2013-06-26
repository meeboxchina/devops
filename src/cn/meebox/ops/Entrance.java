package cn.meebox.ops;

public class Entrance {
	private String hostname;
	private String ipaddr;
	private int port;
	private String usernmae;
	private String password;
	
	public Entrance(String hostname, String ipaddr, int port, String usernmae,
			String password) {
		super();
		this.hostname = hostname;
		this.ipaddr = ipaddr;
		this.port = port;
		this.usernmae = usernmae;
		this.password = password;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsernmae() {
		return usernmae;
	}

	public void setUsernmae(String usernmae) {
		this.usernmae = usernmae;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

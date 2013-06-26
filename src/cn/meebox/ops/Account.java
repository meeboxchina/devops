package cn.meebox.ops;

public class Account {
	private String username;
	private String fullname;
	private int uid;
	private String group;
	private String loginclass;
	private String shell;
	private String homedir;
	private String homedirpermissions;
	private String password;
	
	public Account() {
		super();
	}

	public Account(String username) {
		super();
		this.username = username;
	}

	public Account(String username, String group) {
		super();
		this.username = username;
		this.group = group;
	}

	public Account(String username, String group, String password) {
		super();
		this.username = username;
		this.group = group;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLoginclass() {
		return loginclass;
	}

	public void setLoginclass(String loginclass) {
		this.loginclass = loginclass;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}

	public String getHomedir() {
		return homedir;
	}

	public void setHomedir(String homedir) {
		this.homedir = homedir;
	}

	public String getHomedirpermissions() {
		return homedirpermissions;
	}

	public void setHomedirpermissions(String homedirpermissions) {
		this.homedirpermissions = homedirpermissions;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

package devops.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private String driver;
	private String host;
	private String username;
	private String password;
	private String database;
	
	private String url = null;
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	
	public DBUtil() throws IOException {
		// TODO Auto-generated constructor stub
		String path = DBUtil.class.getResource("/").getPath();
		String websiteURL = (path.replace("/classes", "") + "jdbc.properties");
		System.out.print(websiteURL);
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(websiteURL);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.load(fis);
		this.driver = properties.getProperty("jdbc.driver");
		this.host = properties.getProperty("jdbc.host");
		this.username = properties.getProperty("jdbc.username");
		this.password = properties.getProperty("jdbc.password");
		this.database = properties.getProperty("jdbc.database");
		
		System.out.print(host);
	}
	
	public void BuildConn() {
		try {
			Class.forName(driver).getInterfaces();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url ="jdbc:mysql://" + host + "/"+ database + "?user=" + username + "&password=" + password + "&useUnicode=true&characterEncoding=utf8";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean insert(String sql){
		try {
			return stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void CloseConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Connection getConn() {
		BuildConn();
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDatabase() {
		return database;
	}


	public void setDatabase(String database) {
		this.database = database;
	}


	public static void main(String[] args) throws IOException{
		DBUtil db = new DBUtil();
	}

}

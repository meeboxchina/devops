package devops.dns;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import devops.common.DBUtil;

public class Record {
	private int id;
	private String zone;
	private String host;
	private String type;
	private String data;
	private int ttl;
	private String view;
	private int mx_priority;
	private int refresh;
	private int retry;
	private int expire;
	private int minimum;
	private int seiral;
	private String resp_person;
	private String primary_ns;
	private int data_count;
	
	public Record() {
		// TODO Auto-generated constructor stub
	}
	

	public boolean addRecord(String type, String host, String zone, String data, String view, int ttl) throws IOException{
		DBUtil db = new DBUtil();
		String sql = "insert into records (zone,host,data,type,view,ttl,primary_ns,resp_person) values ('" 
				+ zone + "','" 
				+ host + "','" 
				+ data + "','" 
				+ type + "','" 
				+ view + "'," 
				+ ttl + ",'ns1.mgogo.com.','root." 
				+ zone + ".')" ; 
		
		return db.insert(sql);
	}
	
	public List<HashMap> getZoneRecords(String zone) throws IOException{
		DBUtil db = new DBUtil();
		String query = "select * from records where zone='" + zone + "'";
		List<HashMap> result = db.query(query);
		return result;
	}
	/*
	public boolean addRecord(String zone) throws IOException{
		DBUtil db = new DBUtil();
		String sql = "select * from records where zone='" + zone + "'"; 
		
		return db.query(sql);
	}
	*/

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the ttl
	 */
	public int getTtl() {
		return ttl;
	}

	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	/**
	 * @return the view
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return the mx_priority
	 */
	public int getMx_priority() {
		return mx_priority;
	}

	/**
	 * @param mx_priority the mx_priority to set
	 */
	public void setMx_priority(int mx_priority) {
		this.mx_priority = mx_priority;
	}

	/**
	 * @return the refresh
	 */
	public int getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh the refresh to set
	 */
	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	/**
	 * @return the retry
	 */
	public int getRetry() {
		return retry;
	}

	/**
	 * @param retry the retry to set
	 */
	public void setRetry(int retry) {
		this.retry = retry;
	}

	/**
	 * @return the expire
	 */
	public int getExpire() {
		return expire;
	}

	/**
	 * @param expire the expire to set
	 */
	public void setExpire(int expire) {
		this.expire = expire;
	}

	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the seiral
	 */
	public int getSeiral() {
		return seiral;
	}

	/**
	 * @param seiral the seiral to set
	 */
	public void setSeiral(int seiral) {
		this.seiral = seiral;
	}

	/**
	 * @return the resp_person
	 */
	public String getResp_person() {
		return resp_person;
	}

	/**
	 * @param resp_person the resp_person to set
	 */
	public void setResp_person(String resp_person) {
		this.resp_person = resp_person;
	}

	/**
	 * @return the primary_ns
	 */
	public String getPrimary_ns() {
		return primary_ns;
	}

	/**
	 * @param primary_ns the primary_ns to set
	 */
	public void setPrimary_ns(String primary_ns) {
		this.primary_ns = primary_ns;
	}

	/**
	 * @return the data_count
	 */
	public int getData_count() {
		return data_count;
	}

	/**
	 * @param data_count the data_count to set
	 */
	public void setData_count(int data_count) {
		this.data_count = data_count;
	}

}

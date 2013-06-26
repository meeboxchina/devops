package cn.meebox.ops;

import java.util.HashSet;

public class IDC {
	private String name;
	private String address;
	private HashSet<Entrance> entrance = new HashSet<Entrance>();
	private HashSet<Host> host = new HashSet<Host>();
	
	public IDC(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public void addEntrance(Entrance entrance) {
		this.entrance.add(entrance);
	}
	
	public HashSet<Entrance> getEntrance() {
		return entrance;
	}

	public void addHost(Host host){
		this.host.add(host);
	}
	
	public HashSet<Host> getHost() {
		return host;
	}

	
	
	
}

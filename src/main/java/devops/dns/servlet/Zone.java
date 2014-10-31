package devops.dns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Zone
 */
@WebServlet({ "/Zone", "/zone" })
public class Zone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String zone = request.getParameter("zone");
		if(action==null){
			out.print("Parameters Error");
		}else if(action.equals("add") && zone!=""){
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.addZone(zone)>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "add zone");
				json.put("data", zone);
				out.println(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("status", "error");
				json.put("message", "add zone");
				json.put("data", zone);
				out.println(json.toString());
			}
		}else if(action.equals("del") && zone!=""){
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.delZone(zone)>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "delete zone");
				json.put("data", "");
				out.println(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("status", "error");
				json.put("message", "delete zone");
				json.put("data", "");
				out.println(json.toString());
			}
		}else if(action.equals("getlist")){
			devops.dns.Zone newZone = new devops.dns.Zone();
			List<HashMap> zonelist = newZone.getList();
			Iterator it = zonelist.iterator();
			JSONObject json = new JSONObject();
			JSONArray jsonarray = new JSONArray();
			while(it.hasNext()){
				String zoneName = ((HashMap)it.next()).get("zone").toString();
				jsonarray.put(zoneName);
			}
			json.put("status", "ok");
			json.put("message", "get list ok");
			json.put("data", jsonarray);
			out.println(json.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String zone = request.getParameter("zone");
		if(action==null){
			out.print("Parameters Error");
		}else if(action.equals("add") && zone!=""){
			int ttl = Integer.parseInt(request.getParameter("ttl"));
			int refresh = Integer.parseInt(request.getParameter("refresh"));
			int retry = Integer.parseInt(request.getParameter("retry"));
			int expire = Integer.parseInt(request.getParameter("expire"));
			int minimum = Integer.parseInt(request.getParameter("minimum"));
			int serial = Integer.parseInt(request.getParameter("serial"));
			String resp_person = request.getParameter("resp_person");
			String primary_ns = request.getParameter("primary_ns");
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.addZone(zone,ttl,refresh,retry,expire,minimum,serial,resp_person,primary_ns)>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "add zone");
				json.put("data", zone);
				out.println(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("status", "error");
				json.put("message", "add zone");
				json.put("data", zone);
				out.println(json.toString());
			}
		}else if(action.equals("del") && zone!=""){
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.delZone(zone)>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "delete zone");
				json.put("data", "");
				out.println(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("status", "error");
				json.put("message", "delete zone");
				json.put("data", "");
				out.println(json.toString());
			}
		}else if(action.equals("getlist")){
			devops.dns.Zone newZone = new devops.dns.Zone();
			List<HashMap> zonelist = newZone.getList();
			Iterator it = zonelist.iterator();
			JSONObject json = new JSONObject();
			JSONArray jsonarray = new JSONArray();
			while(it.hasNext()){
				String zoneName = ((HashMap)it.next()).get("zone").toString();
				jsonarray.put(zoneName);
			}
			json.put("status", "ok");
			json.put("message", "get list");
			json.put("data", jsonarray);
			out.println(json.toString());
		}
	}

}

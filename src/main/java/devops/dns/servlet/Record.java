package devops.dns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
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
 * Servlet implementation class Record
 */
@WebServlet({"/devops/zonerecords","/records"})
public class Record extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Record() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String zone = request.getParameter("zone");
		if(zone==null || zone==""){
			return;
		}
		devops.dns.Record record = new devops.dns.Record();
		List<HashMap> list = record.getZoneRecords(zone);

		Iterator itZone = list.iterator();
		
		while(itZone.hasNext()){
			HashMap recordHM = (HashMap) itZone.next();
			out.println(recordHM.get("host") + ":" + recordHM.get("data"));

		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String zone = request.getParameter("zone");
		String action = request.getParameter("action");
		if(zone==null || zone==""){
			return;
		}
		if(action.equals("get"))
		{
			devops.dns.Record record = new devops.dns.Record();
			List<HashMap> list = record.getZoneRecords(zone);

			Iterator itZone = list.iterator();
		
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "get records list");
			JSONArray jsonarray = new JSONArray();

			if(itZone.hasNext())
			{
				while(itZone.hasNext()){
					HashMap recordHM = (HashMap) itZone.next();
				
					JSONObject jsonrecord = new JSONObject();
					jsonrecord.put("id", recordHM.get("id"));
					jsonrecord.put("zone", recordHM.get("zone"));
					jsonrecord.put("host", recordHM.get("host"));
					jsonrecord.put("view", recordHM.get("view"));
					jsonrecord.put("data", recordHM.get("data"));
					jsonrecord.put("ttl", recordHM.get("ttl"));
					jsonrecord.put("type", recordHM.get("type"));
					jsonrecord.put("mx_priority", recordHM.get("mx_priority"));
					jsonrecord.put("priority", recordHM.get("priority"));
				
					jsonarray.put(jsonrecord);
				}
				json.put("data", jsonarray);
				out.println(json.toString());
			}else{
				json.put("data", "");
				out.println(json.toString());
			}
		}else if(action.equals("add")){
			String type = request.getParameter("type");
			String host = request.getParameter("host");
			String data = request.getParameter("data");
			String view = request.getParameter("view");
			int ttl = Integer.parseInt(request.getParameter("ttl"));
			devops.dns.Record record = new devops.dns.Record();
			int result = record.addRecord(type, host, zone, data, view, ttl);

			if(result>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "add record " + host + "." + zone);
				json.put("data", "");
				
				out.println(json.toString());
				
			}
		}else if(action.equals("del")){
			String host = request.getParameter("host");
			int id = Integer.parseInt(request.getParameter("id"));
			devops.dns.Record record = new devops.dns.Record();
			int result = record.delRecord(zone,host,id);

			if(result>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "delete record " + host + "." + zone);
				json.put("data", "");
				
				out.println(json.toString());
				
			}
		}else if(action.equals("update")){
			String type = request.getParameter("type");
			String host = request.getParameter("host");
			String data = request.getParameter("data");
			String view = request.getParameter("view");
			int id = Integer.parseInt(request.getParameter("id"));
			int ttl = Integer.parseInt(request.getParameter("ttl"));
			devops.dns.Record record = new devops.dns.Record();
			int result = record.updateRecord(zone,host,type,id,data,view,ttl);

			if(result>0){
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("message", "update record " + host + "." + zone);
				json.put("data", data);
				
				out.println(json.toString());
				
			}else{
				JSONObject json = new JSONObject();
				json.put("status", "error");
				json.put("message", "update record " + host + "." + zone + " failed");
				json.put("data", data);
				
				out.println(json.toString());
			}
		}
	}

}

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
		if(zone==null || zone==""){
			return;
		}
		devops.dns.Record record = new devops.dns.Record();
		List<HashMap> list = record.getZoneRecords(zone);

		Iterator itZone = list.iterator();
		
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		json.put("message", "get records list");
		JSONArray jsonarray = new JSONArray();
		while(itZone.hasNext()){
			HashMap recordHM = (HashMap) itZone.next();
			
			JSONObject jsonrecord = new JSONObject();
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
	}

}

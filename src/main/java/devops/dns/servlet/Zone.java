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
		if(action==null || zone==null){
			out.print("Parameters Error");
		}else if(action.equals("add") && zone!=""){
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.addZone(zone)>0){
				out.print("Add Successfully!");
			}else{
				out.print("Add Failed");
			}
		}else if(action.equals("del") && zone!=""){
			devops.dns.Zone newZone = new devops.dns.Zone();
			if(newZone.delZone(zone)>0){
				out.print("Delete Successfully!");
			}else{
				out.print("Delete Failed");
			}
		}else if(action.equals("getlist")){
			devops.dns.Zone zoneList = new devops.dns.Zone();
			List<HashMap> list = zoneList.getList();
			Iterator it = list.iterator();
			JSONObject json = new JSONObject();
			while(it.hasnext()){
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

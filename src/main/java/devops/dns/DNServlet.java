package devops.dns;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DNServlet
 */
@WebServlet("/record")
public class DNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DNServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String type =  request.getParameter("type");
		String host =  request.getParameter("host");
		String zone =  request.getParameter("zone");
		String data =  request.getParameter("data");
		String view =  request.getParameter("view");
		String ttl  =  request.getParameter("ttl");
		String mx_priority =  request.getParameter("mx_priority");
		
		if(type==null || host==null || zone==null || data==null || view==null || ttl==null
				|| type.equals("") || host.equals("") || zone.equals("") || data.equals("") || view.equals("") || ttl.equals("")){
			out.print("input error");
			out.close();
		}else{
			Record record = new Record();
			if(type.equals("A") || type.equals("NS") || type.equals("CNAME") || type.equals("AAAA")){
				out.print(record.addRecord(type, host, zone, data, view, Integer.parseInt(ttl)));
				out.close();
			}else if(type.equals("MX")){
				
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

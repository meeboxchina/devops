package devops.dns.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import devops.common.LDAPUser;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Login", "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out = response.getWriter();
		
		LDAPUser ldapuser = new LDAPUser();
		HashMap map = new HashMap();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			map.put(name, request.getParameterValues(name));
		}
		JSONObject json = new JSONObject();
				
		try {
			BeanUtils.populate(ldapuser, map);
			if(ldapuser.authenticate()){
				json.put("status", "ok");
				json.put("message", "login successfully");
				JSONObject jsondata = new JSONObject();
				
				jsondata.put("commonname", ldapuser.getCommonname());
				jsondata.put("mail", ldapuser.getMail());
				jsondata.put("location", "/devops/index2.html");
				json.put("data", jsondata);
				
				out.print(json.toString());
			}else{
				json.put("status", "error");
				json.put("message", "login failed");
				JSONObject jsondata = new JSONObject();
				
				jsondata.put("location", "/devops/login.jsp");
				json.put("data", jsondata);
				
				out.print(json.toString());
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}

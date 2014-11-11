package devops.dns.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="LoginFilter",urlPatterns={"/*"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response;  
        HttpSession session = req.getSession(true);  
        System.out.print("--------------------------------------------------\n");
        // ���session������������������������  
        String username = (String) session.getAttribute("username");  
        String   uri   =   ((HttpServletRequest)   request).getRequestURI(); 

        // ������������������������������������,������������������������  
        //if (username != null || !("".equals(username)) || uri.endsWith("login.html") ||  uri.startsWith("/devops/logout") || uri.startsWith("/devops/login") || uri.endsWith(".js") || uri.endsWith(".css")) {  
        if (username != null ){
        	chain.doFilter(request, response);  
        }else if( uri.endsWith(".css") ||  uri.endsWith(".js") || uri.endsWith("login") || uri.endsWith("logout") || uri.endsWith("login.html")|| uri.endsWith("login.jsp")){
        	chain.doFilter(request, response);  
        }else{  
            // ���������������������  
            //RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");    
            //dispatcher.forward(request, response);    
        	res.sendRedirect("/devops/login.jsp");
        }  
		
		// pass the request along the filter chain
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

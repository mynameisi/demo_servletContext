

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServerSnoop
 */
@WebServlet("/DemoServerSnoop")
public class DemoServerSnoop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServerSnoop() {
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
	}
	
	public void service(ServletRequest req , ServletResponse res)
            throws ServletException,IOException{
        
        res.setContentType("text/plain");
        PrintWriter out= res.getWriter();
        out.println("req.getServerName()" + req.getServerName());
        out.println("req.getServerPort()" + req.getServerPort());
        
        out.println("ServletContext().getServerInfo()" + 
                getServletContext().getServerInfo());
        
        out.println("getServerInfo() name:" + 
                getServerInfoName(getServletContext().getServerInfo()));
        
        out.println("getServerInfo() version:" + 
                getServerInfoVersion(getServletContext().getServerInfo()));
        
        out.println("getServerContext().getAttribute(\"attribute\")" + 
                getServletContext().getAttribute("attribute"));
    }
    private String getServerInfoName(String serverInfo){
        
        int slash = serverInfo.indexOf('/');
        if(slash==-1)
            return serverInfo;
        else
            return (String) serverInfo.subSequence(0,slash);
    }
    private String getServerInfoVersion(String serverInfo){
        
        int slash = serverInfo.indexOf('/');
        if(slash==-1)
            return null;
        else
            return serverInfo.substring(slash + 1);
    }

}

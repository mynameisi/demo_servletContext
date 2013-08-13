import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServerSnoop
 */
public class ServerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServerInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * init-->service-->doGet or doPost
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException {
		// 获得基于网站的上下文ServletContext类的对象 context
		ServletContext context = this.getServletContext();

		Integer siteCount = (Integer) context.getAttribute("total");// 获得网站的访问数目

		if (siteCount == null) {
			siteCount = new Integer(1);
		} else {
			siteCount = new Integer(siteCount + 1);
		}
		context.setAttribute("total", siteCount);// 更新网站的访问数目

		//获得服务器地址
		String serverName = request.getServerName();
		
		//获得服务器端口号
		int serverPort = request.getServerPort();
		
		//获得服务器/容器相关信息，比如：Apache Tomcat/7.0.42
		String serverInfo = context.getServerInfo();
		
		//获得Servlet的版本号，比如 3.2 [3就是Major, 2就是Minor]
		int major = context.getMajorVersion();
		int minor = context.getMinorVersion();
		String servletVersion = Integer.toString(major) + '.' + Integer.toString(minor);
		
		//获得所有的ServletContext Attributes，转化成ArrayList
		ArrayList<String> aList = Collections.list(context.getAttributeNames());
		
		//获得所有的ServletContext 初始变量init-param，转化成ArrayList, 参看Web.xml <context-param>
		ArrayList<String> ctxPList = Collections.list(context.getInitParameterNames());
		
		//获得所有的该Servlet的 初始变量init-param，转化成ArrayList, 参看Web.xml <servlet><init-param>
		ArrayList<String> servletPList = Collections.list(getInitParameterNames());

		PrintWriter out = getHTMLWritter(response);
		out.println("<hr> <strong>服务器基本信息:</strong>");
		out.print("<ul>");
		out.println("<li>服务器地址: " + serverName);
		out.println("<li>服务器端口: " + serverPort);
		out.println("<li>服务器综合信息: " + serverInfo);
		out.println("<li>服务器Servlet版本: " + servletVersion);
		out.println("<li>该网站总共被访问次数: " + siteCount);
		out.print("</ul>");

		out.println("<hr> <strong>Servlet初始化参数servlet init-param:</strong><br>");
		out.println("<em>这些是基于Servlet的初始化参数, 详见Web.xml &lt;servlet&gt;&lt;init-param&gt; </em><ul>");
		for (String name : servletPList) {
			out.println("<li> 键:[" + name + "] : 值[" + this.getInitParameter(name) + "]");
		}
		out.println("</ul>");

		out.println("<hr> <strong>上下文(我的翻译:网站大环境)ServletContext初始化参数context init-param:</strong><br>");
		out.println("<em>这些是基于整个网站的初始化参数, 详见Web.xml &lt;context-param&gt; </em><ul>");
		for (String name : ctxPList) {
			out.println("<li> 键:[" + name + "] : 值[" + context.getInitParameter(name) + "]");
		}
		out.println("</ul>");

		out.println("<hr> <strong>上下文(我的翻译:网站大环境)ServletContext属性(Attribute)名称:</strong><br><ul>");
		for (String name : aList) {
			out.println("<li>" + name);
		}
		out.println("</ul>");

	}

	/**
	 * 获取HTML形式的输出流
	 * 
	 * @param response
	 * @return
	 */
	public PrintWriter getHTMLWritter(ServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

}

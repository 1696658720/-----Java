package yunnet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String downloadurl;//这里是一个下载链接的地址，通过客户端定义的name来找
	//两种方式一样的服务器获取是一样的
	//如果不是服务器形式，可能要通过response来设置获取连接方式
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		downloadurl=request.getParameter("downloadFile");//根据name来找对应的属性值
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		downloadurl=request.getParameter("downloadFile");//根据name来找对应的属性值
	}
}


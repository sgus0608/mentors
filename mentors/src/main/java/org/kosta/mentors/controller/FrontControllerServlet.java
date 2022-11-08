package org.kosta.mentors.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String servletPath = request.getServletPath();
			String controllerName = servletPath.substring(1,servletPath.lastIndexOf("."));
			
			boolean checkList=CheckLoginInterceptor.getInstance().checkLogin(request, controllerName);
			if(checkList==false) {
				response.sendRedirect("interceptor.jsp");
				return;
			}
			
			Controller controller = HandlerMapping.getInstance().create(controllerName);
			String viewPath = controller.handleRequest(request, response);
			if(viewPath.startsWith("redirect:")) {
				response.sendRedirect(viewPath.substring(9));
			}else {
				request.getRequestDispatcher(viewPath).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doDispatch(request, response);
	}

}

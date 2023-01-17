package com.edu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.entity.User;
import com.edu.service.UserService;
import com.edu.service.Impl.UserServiceImpl;

/**
 * 
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.login(email, password);

		if (user == null) {
			String msg = "用户名或密码错误！";
			
			request.setAttribute("msg", msg);
			
			request.getRequestDispatcher("/login_v1.jsp").forward(request, response);
		} else {
			response.sendRedirect("BookListServlet");
		}
	}

}

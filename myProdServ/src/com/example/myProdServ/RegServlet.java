package com.example.myProdServ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int INSERT_SUCCESS = 0; //
	private static final int INSERT_ERROR = 1; //

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ret;
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		// name,password,birthday,sex

		String name = req.getParameter("NAME");
		// out.print(name);
		String password = req.getParameter("CODE");
		// out.print(password);
		String birthday = req.getParameter("BIRTHDAY");
		// out.print(birthday);
		String sex = req.getParameter("SEX");
		// out.print(sex);

		Boolean falg = DBUtil.insertUser(name, password, birthday, sex);
		if (falg)
			ret = 0;
		else
			ret = 1;
		out.print(ret);
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

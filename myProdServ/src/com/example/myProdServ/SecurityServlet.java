package com.example.myProdServ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ret;//成功返回0
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String type = req.getParameter("TYPE");
		if ("update".equals(type)) {
			String name = req.getParameter("NAME");
			String password = req.getParameter("CODE");
			Boolean falg = DBUtil.updateUser(name, password);
			if (falg)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}else if("select".equals(type)){
			String name = req.getParameter("NAME");
			Boolean falg = DBUtil.findUser(name);
			if (falg)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

/*package com.example.myProdServ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*//**
 * Servlet implementation class ProdDBServlet
 *//*
public class ProdDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ret;
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		// name,password,birthday,sex

		String action = req.getParameter("ACTION");
		if (action.equals("0"))// insert
		{
			String sId = req.getParameter("SID");
			// out.print(password);
			String sName = req.getParameter("SNAME");
			// out.print(birthday);
			String sBa = req.getParameter("SBA");
			String sCo = req.getParameter("SCO");
			Boolean falg = DBUtil.insertProdDB(sId, sName, sBa, sCo);
			if (falg)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}
		if (action.equals("1"))// delete
		{
			String sId = req.getParameter("SID");
			// out.print(password);
			Boolean falg = DBUtil.deleteProdDB(sId);
			if (falg)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}
		if (action.equals("2"))// update
		{
			String sId = req.getParameter("SID");
			// out.print(password);
			String sName = req.getParameter("SNAME");
			// out.print(birthday);
			String sBa = req.getParameter("SBA");
			Boolean falg = DBUtil.updateProdDB(sId, sName, sBa);
			if (falg)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}
		if (action.equals("3"))// select *
		{
			String result = DBUtil.queryAllProdDB();
			out.print(result);
			out.flush();
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
*/
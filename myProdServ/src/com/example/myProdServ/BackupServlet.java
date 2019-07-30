/*package com.example.myProdServ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BackupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ret;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("TYPE");
		System.out.println(type);
		if ("backup".equals(type)) {
			String jsonData = request.getParameter("JSONDATA");
			System.out.println(jsonData);
			Boolean flag = DBUtil.backupContacts(jsonData);
			if (flag)
				ret = 0;
			else
				ret = 1;
			out.print(ret);
			out.flush();
			out.close();
		}else if("getJson".equals(type)) {
			String jsonData=DBUtil.getContactsJson();
			out.print(jsonData);
			out.flush();
			out.close();
		}
	}

}
*/
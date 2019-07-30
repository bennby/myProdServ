package com.example.myProdServ;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestTomcat
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    private static final int NAME_CODE_RIGHT = 0; //  
    private static final int CODE_WRONG = 1;     //  
    private static final int NAME_WRONG = 2;     //  
  
    public LoginServlet(){  
  
    }  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        // TODO Auto-generated method stub  
        
       
		  

        
        resp.setContentType("text/html;charset=utf-8");  
        req.setCharacterEncoding("utf-8");  
        resp.setCharacterEncoding("utf-8");  
        PrintWriter out = resp.getWriter();  
        String name = req.getParameter("NAME");  
        String code = req.getParameter("CODE");  
  
       //浏览器访问，没传递任何参数。用HTML格式给浏览器返回数据 
       // out.println("<html>"); 
       // out.println("<head>"); 
        //out.println("<title>Tomcat Servlet测试</title>"); 
        //out.println("</head>"); 
       // out.println("<body>"); 
        //out.println("Hello,哥知道你是浏览器访问的.");
        try {

		     Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   

		     //Class.forName("org.gjt.mm.mysql.Driver");

		     //out.println("Success loadingMysql Driver!");

		    }

		   catch (Exception e) {

		     //out.print("Error loading Mysql Driver!");

		     e.printStackTrace();

		    }
        String result=null;
		   try {

		     //Connection connect = DriverManager.getConnection(

		      //   "jdbc:mysql://localhost:3306/test","root","123456");

		           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/androidb","root","123456");

		     //连接URL为  jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是你数据库的登录用户名和密码

		 //test为你之前建的数据库的名字，root替换成你的数据库的登录用户名（默认是root），yourpassword换成你的密码

		     //out.println("Success connect Mysql server!");
		     Statement st=con.createStatement();
		     String sql="select U_pswd from user where U_name='"+name+"'";
				ResultSet rs=st.executeQuery(sql);
				if(rs.next())
				{
					result=rs.getString(1);
					//out.print(result);
				}

		     //Statement stmt = connect.createStatement();

		     //ResultSet rs = stmt.executeQuery("select * from student");

		 

		     //while (rs.next()) {

		      // out.println(rs.getString("S_Name"));

		     //}
		     rs.close();
		     st.close();
		     con.close();
		    }

		   catch (Exception e) {

		     out.print("get data error!");

		     e.printStackTrace();

		    }
		   
        //out.println("NAME="+name+",CODE="+code);
        //out.println("</body>"); 
        //out.println("</html>"); 
        //out.println("Hello,第一个Tomcat!!!"); 
        //out.close();   
     //*/
        //手机客户端访问  
        int ret = checkSubmit(name, code);  
        //String ret = checkSubmit(name, code); 
        //out.print("密码是："+ret); 
        //out.println("密码是："+ret); 
        out.print(ret);
        out.flush();  
        out.close();  
  
     }    
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        // TODO Auto-generated method stub  
        if(req == null){  
            return;  
        }  
  
        resp.setContentType("text/html;charset=utf-8");  
        req.setCharacterEncoding("utf-8");  
        resp.setCharacterEncoding("utf-8");  
  
        PrintWriter out = resp.getWriter();  
        String name = req.getParameter("NAME");  
        String code = req.getParameter("CODE");  
  
     //   int ret = checkSubmit(name, code);  
        //out.print(ret);  
        out.flush();  
        out.close();  
    }  
  
    /** 
     * 判断登录名和密码 
     * @param name 
     * @param code 
     * @return 
     */  
    private int checkSubmit(String name, String code){  
        int ret = -2;  
        //String ret="";
        
        String ppwd=DBUtil.selectUPwd(name);
        //out.print(ppwd); 
        if(code.equals(ppwd))
        ret = NAME_CODE_RIGHT;
        else ret = CODE_WRONG;
        /*
        if(name.equals("admin")){  
            if(code.equals("123")){  
                ret = NAME_CODE_RIGHT;  
            }else{  
                ret = CODE_WRONG;  
            }  
        }else{  
            ret = NAME_WRONG;  
        }  
        */
        return ret;  
        //return  ppwd;
    }  
}

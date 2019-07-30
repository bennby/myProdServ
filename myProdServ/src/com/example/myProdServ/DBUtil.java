package com.example.myProdServ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DBUtil {
	// 与 MySql数据库建立连接
	
	
	  public static Connection getConnection()
		{
	    	Connection con=null;
			try
			{			
				Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
				String url="jdbc:mysql://localhost:3306/androidb?user=root&password=123456";//链接数据库语句
	            con= (Connection) DriverManager.getConnection(url); //链接数据库
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}

	public static void delete(String sql) {
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 登录密码验证
	public static String selectUPwd(String uname) {
		String result = null;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select U_pswd from user where U_name='" + uname + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getString(1);
				// out.print(result);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	// 添加用户信息到MySql数据库
	public static Boolean insertUser(String name, String password, String birthday, String sex) {
		Boolean falg = false;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "insert into user(U_name,U_pswd,U_bird,U_sex) value('" + name + "','" + password + "','"
					+ birthday + "','" + sex + "')";
			// st.execute("set char set 'gbk'");
			st.executeUpdate(sql);
			st.close();
			con.close();
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return falg;

	}
//修改密码
	public static Boolean updateUser(String name, String password) {
		Boolean falg = false;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "update user set U_pswd='" + password + "' where U_name='" + name + "'";
			// st.execute("set char set 'gbk'");
			st.executeUpdate(sql);
			st.close();
			con.close();
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}
//根据用户名查找用户
	public static Boolean findUser(String name) {
		Boolean falg = false;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select * from user where U_name='" + name + "'";
			// st.execute("set char set 'gbk'");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				falg = true;
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	// 添加用户信息到MySql数据库
	public static Boolean insertProdDB(String sId, String sName, String sBa,String sCo) {
		Boolean falg = false;

		// String sId=String.valueOf(id);
		// String sBa=String.valueOf(account.getBalance());
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "insert into account(_id,name,balance,count) value('" + sId + "','" + sName + "','" + sBa +"','" +sCo+ "')";
			//st.execute("set char set 'gbk'");
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			con.close();
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}
/*//删除商品信息
	public static Boolean deleteProdDB(String sId) {
		Boolean falg = false;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "delete from account where _id='" + sId + "'";
			st.execute(sql);
			st.close();
			con.close();
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return falg;

	}
//更新商品信息
	public static Boolean updateProdDB(String sId, String sName, String sBa) {
		Boolean falg = false;

		// String sId=String.valueOf(id);
		// String sBa=String.valueOf(account.getBalance());
		try {

			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "update account set name='" + sName + "',balance='" + sBa + "'where _id='" + sId + "'";
			st.executeUpdate(sql);
			st.close();
			con.close();
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}
	//查找所有商品
	public static String queryAllProdDB() {
		//Boolean falg = false;
		String result=null;
		JSONArray accounts=new JSONArray();
		try {

			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select * from account";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// 通过字段检索
				long id = rs.getLong("_id");
				String name = rs.getString("name");
				Integer balance = rs.getInt("balance");
				Integer count = rs.getInt("count");
				JSONObject a=new JSONObject();
				a.put("_id", id);
				a.put("name", name);
				a.put("balance", balance);
				a.put("count", count);
				accounts.add(a);
			}
			result=accounts.toString();
			System.out.println(result);
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//备份联系人
	public static Boolean backupContacts(String jsonData) {
		Boolean flag=false;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "insert into contacts (jsonData) VALUES ('"+jsonData+"')";
			st.executeUpdate(sql);
			st.close();
			con.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//恢复联系人
	public static String getContactsJson() {
		String result=null;
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String sql = "select jsonData from contacts where id= (select max(id) from contacts)";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getString(1);
				// out.print(result);
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
}

package mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;


public class conn {
	java.sql.Connection conn;
	public void ConnectMySQL(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://123.207.39.128/Student?"+"useUnicode=true&characterEncoding=utf8";
			String user = "root";
			String password="XIEhanyang033~!";
			conn = DriverManager.getConnection(url,user,password);
			//System.out.print("连接成功！\n");
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}

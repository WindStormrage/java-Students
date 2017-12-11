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
	java.sql.Statement stmt;
	ResultSet rs;
	ResultSet rs1;
	public void ConnectMySQL(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://123.207.39.128/Student?"+"useUnicode=true&characterEncoding=utf8";
			String user = "root";
			String password="XIEhanyang033~!";
			conn = DriverManager.getConnection(url,user,password);
			System.out.print("连接成功！\n");
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	//展示
	public void showData(String table){
		try{
			stmt = conn.createStatement();
			String sql = "select * from " + table;
			rs = stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//删除
	public void delete(String table, String key, String value){
		try{
			//创建会话
			stmt = conn.createStatement();
			//删除语句
			String sql = "delete from "+ table +" where "+ key +" ="+ value +"";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("删除成功!\n");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	

	//插入
	public void insert(int stuNo,String stuName,int stuScore){
		try{
			//创建会话
			stmt = conn.createStatement();
			//插入语句
			String sql = "select * from student where stuNo ="+stuNo+"";
			rs = stmt.executeQuery(sql);
			sql = "select * from student where stuNo ="+stuName+"";
			rs1 = stmt.executeQuery(sql);
			if(rs != null || rs1 != null){
				System.out.print("该学号或者姓名已经存在，请重新输入!\n");
			}else{
				sql = "insert into student(stuNo,stuName,stuScore)"+"values('"+stuNo+"',"+"'"+stuName+"',"+stuScore+")";
				int count = stmt.executeUpdate(sql);
				stmt.close();
				System.out.print("插入成功!\n");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}

package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class student {
	conn db = new conn();
	java.sql.Statement stmt;
	ResultSet rs;
	
	public int count () {
		int count = 0;
		db.ConnectMySQL();
		try {
			stmt = db.conn.createStatement();
			//先要找出数据库里面的数据数量，根据数量来确定数组大小
			String sql = "select count(Stu_Num) from student";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}	
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//查
	public Object[][] showData(){
		int count = 0;
		try{
			db.ConnectMySQL();
			stmt = db.conn.createStatement();
			//先要找出数据库里面的数据数量，根据数量来确定数组大小
			String sql = "select count(Stu_Num) from student";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}		
			
			sql = "select * from student";
			rs = stmt.executeQuery(sql);
			int i = 0;
			Object[][] data = new Object[count][5];
			while(rs.next()){
				data[i][0] = rs.getString("Stu_Num");
				data[i][1] = rs.getString("Stu_Name");
				data[i][2] = rs.getString("Stu_Sex");
				data[i][3] = rs.getString("Stu_Age");
				i++;
//				System.out.print(rs.getString("Stu_Num")+"\t");
//				System.out.print(rs.getString("Stu_Name")+"\t");
//				System.out.print(rs.getInt("Stu_Sex") == 1 ? "女"+"\t" : "男"+"\t");
//				System.out.print(rs.getInt("Stu_Age")+"\t");
//				System.out.print("\n");
			}
			rs.close();
			stmt.close();
			return data;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//删
	public void delete(String key, String value){
		try{
			db.ConnectMySQL();
			//创建会话
			stmt = db.conn.createStatement();
			//删除语句
			String sql = "delete from student where "+ key +" ='"+ value +"'";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("删除成功!"+count);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//增
	public int insert(String Stu_Num,String Stu_Name,int Stu_Sex, int Stu_Age){
		try{
			db.ConnectMySQL();
			//创建会话
			stmt = db.conn.createStatement();
			//插入语句
			String sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("该学号已经存在，请重新输入!");
				return 1;
			}else{
				sql = "insert into student(Stu_Num,Stu_Name,Stu_Sex,Stu_Age,Stu_Status)"+"values('"+Stu_Num+"',"+"'"+Stu_Name+"',"+Stu_Sex+","+Stu_Age+",2)";
				stmt.executeUpdate(sql);
				stmt.close();
				System.out.print("插入成功!\n");
				return 2;
			}			
		}catch(SQLException e){
			e.printStackTrace();
			return 3;
		}
	}
	
	public int update(String Stu_Num0, String Stu_Num, String Stu_Name, int Stu_Sex, int Stu_Age) {
		db.ConnectMySQL();
		try {
			//查询是否已经存在这个学号
			stmt = db.conn.createStatement();
			String sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next() && Stu_Num0 != Stu_Num){
				System.out.println("该学号已经存在，请重新修改!");
				return 1;
			}else{			
				sql = "update student set Stu_Num = '"+Stu_Num+"',Stu_Name = '"+Stu_Name+"',Stu_Sex = "+Stu_Sex+",Stu_Age = "+Stu_Age+",Stu_Status = 2 where Stu_Num = '"+Stu_Num0+"'";
				stmt.executeUpdate(sql);
				stmt.close();
				System.out.print("修改成功!\n");
				return 2;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;	
		}			
	}
		
}

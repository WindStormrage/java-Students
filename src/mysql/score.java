package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class score {
	conn db = new conn();
	java.sql.Statement stmt;
	ResultSet rs;

	//查//true升序//false降序
	public Object[][] showData(boolean type){
		Object[][] data = new Object[100][100];
		try{
			db.ConnectMySQL();
			stmt = db.conn.createStatement();
			String sql = "select * from Score order by Score " + (type ? "": "desc");
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				data[i][0] = rs.getString("Stu_Num");
				data[i][1] = rs.getString("Course_Num");
				data[i][2] = rs.getInt("Score");
				i++;
//				System.out.print(rs.getString("Stu_Num")+"\t");
//				System.out.print(rs.getString("Course_Num")+"\t");
//				System.out.print(rs.getInt("Score")+"\t");
//				System.out.print("\n");
			}
			rs.close();
			stmt.close();
			return data;
		}catch(SQLException e){
			e.printStackTrace();
			return data;
		}
	}
	
	//删
	public void delete(String key1, String value1, String key2, String value2){
		try{
			db.ConnectMySQL();
			//创建会话
			stmt = db.conn.createStatement();
			//删除语句
			String sql = "delete from Score where "+ key1 +" ='"+ value1 +"' and "+ key2 +" = '"+value2+"'";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("删除成功!"+count);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//增
	public int insert(String Stu_Num,String Course_Num,int Score){
		try{
			db.ConnectMySQL();
			//创建会话
			stmt = db.conn.createStatement();

			//判断是否已经有了学号和课程号
			String sql = "select * from Score where Stu_Num ='"+Stu_Num+"' and  Course_Num='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("该课程号和学号已经存在，请重新输入!");
				return 1;
			}

			//判断是否存在这个学号
			sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(!rs.next()) {
				System.out.println("不存在该学号，请重新输入!");
				return 2;
			}

			//判断是否存在这个课程号
			sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(!rs.next()) {
				System.out.println("不存在该课程号，请重新输入!");
				return 3;
			}

			sql = "insert into Score(Stu_Num,Course_Num,Score)"+"values('"+Stu_Num+"',"+"'"+Course_Num+"',"+Score+")";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("插入成功!\n");
			return 4;
		
		}catch(SQLException e){
			e.printStackTrace();
			return 5;
		}
	}
	
	//改
	public int update(String Stu_Num0, String Course_Num0, String Stu_Num,String Course_Num,int Score) {
		db.ConnectMySQL();
		try {
			stmt = db.conn.createStatement();
			

			//查询是否已经存在这个学号和课程号
			String sql = "select * from Score where Stu_Num ='"+Stu_Num+"' and  Course_Num='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);			
			//如果更改后的主键在数据库中存在，而且不与之前的主键相同
			if(rs.next() && (Course_Num0 != Course_Num || Course_Num0 != Course_Num)){
				System.out.println("该学号和课程号已经存在，请重新修改!");
				return 1;
			}

			//判断是否存在这个学号
			sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.next()){
				System.out.println("不存在该学号，请重新输入!");
				return 2;
			}

			//判断是否存在这个课程号
			sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.next()){
				System.out.println("不存在该课程号，请重新输入!");
				return 3;
			}
			
			sql = "update Score set Stu_Num = '"+Stu_Num+"',Course_Num = '"+Course_Num+"',Score = "+Score+" where  Stu_Num ='"+Stu_Num0+"' and  Course_Num='"+Course_Num0+"'";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("修改成功!\n");
			return 4;	

		} catch (SQLException e) {
			e.printStackTrace();
			return 5;	
		}			
	}	

}

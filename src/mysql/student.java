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
			//��Ҫ�ҳ����ݿ��������������������������ȷ�������С
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
	
	//��
	public Object[][] showData(){
		int count = 0;
		try{
			db.ConnectMySQL();
			stmt = db.conn.createStatement();
			//��Ҫ�ҳ����ݿ��������������������������ȷ�������С
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
//				System.out.print(rs.getInt("Stu_Sex") == 1 ? "Ů"+"\t" : "��"+"\t");
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
	
	//ɾ
	public void delete(String key, String value){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();
			//ɾ�����
			String sql = "delete from student where "+ key +" ='"+ value +"'";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("ɾ���ɹ�!"+count);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//��
	public int insert(String Stu_Num,String Stu_Name,int Stu_Sex, int Stu_Age){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();
			//�������
			String sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("��ѧ���Ѿ����ڣ�����������!");
				return 1;
			}else{
				sql = "insert into student(Stu_Num,Stu_Name,Stu_Sex,Stu_Age,Stu_Status)"+"values('"+Stu_Num+"',"+"'"+Stu_Name+"',"+Stu_Sex+","+Stu_Age+",2)";
				stmt.executeUpdate(sql);
				stmt.close();
				System.out.print("����ɹ�!\n");
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
			//��ѯ�Ƿ��Ѿ��������ѧ��
			stmt = db.conn.createStatement();
			String sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next() && Stu_Num0 != Stu_Num){
				System.out.println("��ѧ���Ѿ����ڣ��������޸�!");
				return 1;
			}else{			
				sql = "update student set Stu_Num = '"+Stu_Num+"',Stu_Name = '"+Stu_Name+"',Stu_Sex = "+Stu_Sex+",Stu_Age = "+Stu_Age+",Stu_Status = 2 where Stu_Num = '"+Stu_Num0+"'";
				stmt.executeUpdate(sql);
				stmt.close();
				System.out.print("�޸ĳɹ�!\n");
				return 2;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 3;	
		}			
	}
		
}

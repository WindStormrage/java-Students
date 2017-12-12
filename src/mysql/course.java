package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class course {
	conn db = new conn();
	java.sql.Statement stmt;
	ResultSet rs;
	
	//��
	public Object[][] showData(){
		Object[][] data = new Object[100][100];
		try{
			db.ConnectMySQL();
			stmt = db.conn.createStatement();
			String sql = "select * from Course";
			rs = stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				data[i][0] = rs.getString("Course_Num");
				data[i][1] = rs.getString("Course_Name");
				data[i][2] = rs.getInt("Course_Leasons");
				data[i][3] = rs.getInt("Course_Nature");
				data[i][4] = rs.getInt("Course_Credit");
				data[i][5] = rs.getString("Course_Teacher");
				i++;
//				System.out.print(rs.getString("Course_Num")+"\t");
//				System.out.print(rs.getString("Course_Name")+"\t");
//				System.out.print(rs.getInt("Course_Leasons")+"\t");
//				System.out.print(rs.getInt("Course_Nature") == 1 ? "ѡ��"+"\t" : "����"+"\t");
//				System.out.print(rs.getInt("Course_Credit")+"\t");
//				System.out.print(rs.getString("Course_Teacher")+"\t");
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
	
	//ɾ
	public void delete(String key, String value){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();
			//ɾ�����
			String sql = "delete from Course where "+ key +" ='"+ value +"'";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("ɾ���ɹ�!"+count);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//��
	public int insert(String Course_Num,String Course_Name,int Course_Leasons, int Course_Nature, int Course_Credit, String Course_Teacher){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();
			//�������
			String sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("�ÿγ̺��Ѿ����ڣ�����������!");
				return 1;
			}else{
				sql = "insert into Course(Course_Num,Course_Name,Course_Leasons,Course_Nature,Course_Credit,Course_Teacher)"+"values('"+Course_Num+"',"+"'"+Course_Name+"',"+Course_Leasons+","+Course_Nature+","+Course_Credit+",'"+Course_Teacher+"')";
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
	
	public int update(String Course_Num0, String Course_Num,String Course_Name,int Course_Leasons, int Course_Nature, int Course_Credit, String Course_Teacher) {
		db.ConnectMySQL();
		try {
			//��ѯ�Ƿ��Ѿ���������γ̺�
			stmt = db.conn.createStatement();
			String sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next() && Course_Num0 != Course_Num){
				System.out.println("��ѧ���Ѿ����ڣ��������޸�!");
				return 1;
			}else{			
				sql = "update Course set Course_Num = '"+Course_Num+"',Course_Name = '"+Course_Name+"',Course_Leasons = "+Course_Leasons+",Course_Nature = "+Course_Nature+",Course_Credit = "+Course_Credit+",Course_Teacher = '"+Course_Teacher+"' where Course_Num = '"+Course_Num0+"'";
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

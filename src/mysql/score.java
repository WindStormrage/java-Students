package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class score {
	conn db = new conn();
	java.sql.Statement stmt;
	ResultSet rs;
	
	public int count () {
		int count = 0;
		db.ConnectMySQL();
		try {
			stmt = db.conn.createStatement();
			//��Ҫ�ҳ����ݿ��������������������������ȷ�������С
			String sql = "select count(Stu_Num) from Score";
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
	
	//��//true����//false����
	public Object[][] showData(boolean type){
		int count = 0;
		try{
			db.ConnectMySQL();
			stmt = db.conn.createStatement();
			//��Ҫ�ҳ����ݿ��������������������������ȷ�������С
			String sql = "select count(Stu_Num) from Score";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}				
			//System.out.print(count);
			sql = "select * from Score order by Score " + (type ? "": "desc");
			rs = stmt.executeQuery(sql);
			int i = 0;
			Object[][] data = new Object[count][3];
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
			return null;
		}
	}
	
	//ɾ
	public void delete(String key1, String value1, String key2, String value2){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();
			//ɾ�����
			String sql = "delete from Score where "+ key1 +" ='"+ value1 +"' and "+ key2 +" = '"+value2+"'";
			int count = stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("ɾ���ɹ�!"+count);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	//��
	public int insert(String Stu_Num,String Course_Num,int Score){
		try{
			db.ConnectMySQL();
			//�����Ự
			stmt = db.conn.createStatement();

			//�ж��Ƿ��Ѿ�����ѧ�źͿγ̺�
			String sql = "select * from Score where Stu_Num ='"+Stu_Num+"' and  Course_Num='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				System.out.println("�ÿγ̺ź�ѧ���Ѿ����ڣ�����������!");
				return 1;
			}

			//�ж��Ƿ�������ѧ��
			sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(!rs.next()) {
				System.out.println("�����ڸ�ѧ�ţ�����������!");
				return 2;
			}

			//�ж��Ƿ��������γ̺�
			sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			
			if(!rs.next()) {
				System.out.println("�����ڸÿγ̺ţ�����������!");
				return 3;
			}

			sql = "insert into Score(Stu_Num,Course_Num,Score)"+"values('"+Stu_Num+"',"+"'"+Course_Num+"',"+Score+")";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("����ɹ�!\n");
			return 4;
		
		}catch(SQLException e){
			e.printStackTrace();
			return 5;
		}
	}
	
	//��
	public int update(String Stu_Num0, String Course_Num0, String Stu_Num,String Course_Num,int Score) {
		db.ConnectMySQL();
		try {
			stmt = db.conn.createStatement();
			

			//��ѯ�Ƿ��Ѿ��������ѧ�źͿγ̺�
			String sql = "select * from Score where Stu_Num ='"+Stu_Num+"' and  Course_Num='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);			
			//������ĺ�����������ݿ��д��ڣ����Ҳ���֮ǰ��������ͬ
			if(rs.next() && (Course_Num0 != Course_Num || Course_Num0 != Course_Num)){
				System.out.println("��ѧ�źͿγ̺��Ѿ����ڣ��������޸�!");
				return 1;
			}

			//�ж��Ƿ�������ѧ��
			sql = "select * from student where Stu_Num ='"+Stu_Num+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.next()){
				System.out.println("�����ڸ�ѧ�ţ�����������!");
				return 2;
			}

			//�ж��Ƿ��������γ̺�
			sql = "select * from Course where Course_Num ='"+Course_Num+"'";
			rs = stmt.executeQuery(sql);
			if(!rs.next()){
				System.out.println("�����ڸÿγ̺ţ�����������!");
				return 3;
			}
			
			sql = "update Score set Stu_Num = '"+Stu_Num+"',Course_Num = '"+Course_Num+"',Score = "+Score+" where  Stu_Num ='"+Stu_Num0+"' and  Course_Num='"+Course_Num0+"'";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.print("�޸ĳɹ�!\n");
			return 4;	

		} catch (SQLException e) {
			e.printStackTrace();
			return 5;	
		}			
	}	

}

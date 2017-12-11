package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class student {
	conn db = new conn();
	ResultSet rs;
	
	
	public void find() {
		db.ConnectMySQL();
		db.showData("student");
		rs = db.rs;
		try {
			while(rs.next()){
				System.out.print(rs.getString("Stu_Num")+"\t");
				System.out.print(rs.getString("Stu_Name")+"\t");
				System.out.print(rs.getInt("Stu_Sex") == 1 ? "Å®" : "ÄÐ" +"\t");
				System.out.print(rs.getInt("Stu_Age")+"\t");
				System.out.print("\n");
				
			}
			db.rs.close();
			db.stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

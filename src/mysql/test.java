package mysql;

import java.sql.ResultSet;

import tool.*;
import trigger.*;

public class test {

	
	public static void main(String []args){
		student a = new student();

			
		showTable aa = new showTable();
		
		Object data[][] =  new Object[100][100];
		
		data = aa.highestScore();
		//data = aa.showData();

			
		
		//System.out.println(data[0][3]+"***********");
		//System.out.println(Integer.parseInt((String)data[0][3])>10? "0":"1"+"***********");
		//String str= data[0][3];
		//Integer integer=new Integer(str)	

			
		
		for(int i = 0; i<2; i++) {
			for(int j = 0; j<6 ; j++){
				System.out.print(data[i][j]+"\t");	
			}
			System.out.println();	
		}	
		
		
//		a.showData();
//		a.insert("A123", "HAHA", 1, 20);
//		a.showData();
//		a.delete("Stu_Num", "A123");
//		a.showData();
//		a.update("A11","A1243", "666", 1, 20);
		
		
		course b = new course();
//		b.showData();
//		b.insert("123", "456", 789, 0, 100, "111");
//		b.showData();
//		b.delete("Course_Num", "123");
//		b.showData();
//		b.insert("123", "456", 789, 0, 100, "111");
//		b.showData();
//		b.update("456", "1234", "456", 789, 0, 100, "111");
//		b.showData();
		

		
		score c = new score();
		
		
		
		//c.showData(false);
		//c.insert("A123111", "1234", 100);
		//c.delete("Stu_Num", "A123", "Course_Num", "1234");
		//c.update("A123", "1234", "A1243", "123", 20);
		//c.showData();
		//在改的那还要判断是否有这个名字和是否有这个课程
	}

}

package trigger;
import mysql.*;

//数据库转数组类
public class showTable {
	student student;
	course course;
	score score;
	Object[][] rs1 = new Object[100][100];
	Object[][] rs2 = new Object[100][100];
	Object[][] rs3 = new Object[100][100];
	
	public showTable(){
		student = new student();
		course = new course();
		score = new score();
	}
	//最高成绩表
	public Object[][] highestScore() {
		int max;	
		Object[][] data = new Object[100][100];
		int i = 0;
		int j = 0, k = 0, l = 0;
		rs2 = course.showData();
		//循环科目
		while(rs2[j][0] != null) {	
			max = -1;
			rs3 = score.showData(false);
			//循环成绩，找当前科目最高成绩
			k = 0;
			while(rs3[k][0] != null){
				//只有科目相同才处理
				if(rs2[j][0].equals(rs3[k][1])) {
					System.out.println(Integer.parseInt(rs3[k][2].toString()));
					if(Integer.parseInt(rs3[k][2].toString()) >= max) {
						//第一个人是最大的，肯定比-1大，如果第二个人等于第一个人的话继续记录，这样子就可以记录下分数相同的最高分了
						//这个人是最高分//记下这个人
						max = Integer.parseInt(rs3[k][2].toString());
						rs1 = student.showData();
						//循环学生表，找到这个人的信息
						l = 0;
						while(rs1[l][0] != null) {
							if(rs3[k][0].equals(rs1[l][0])) {
								data[i][0] = rs1[l][0];
								data[i][1] = rs1[l][1];
								data[i][2] = rs1[l][2];
								data[i][3] = rs1[l][3];
								data[i][4] = rs3[k][2];
								i++;
								break;
							}
							l++;
						}								
					}else {
						//如果有一个比他小的，那么后面都会比他小了，然后就可以结束了
						break;
					}
				}
				k++;
			}
			j++;
		}
		return data;		
	}


	
}

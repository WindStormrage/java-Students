package trigger;
import mysql.*;

//数据库转数组类
public class showTable {
	student student;
	course course;
	score score;
	Object[][] rs1;
	Object[][] rs2;
	Object[][] rs3;
	
	public showTable(){
		student = new student();
		course = new course();
		score = new score();
	}
	//最高成绩表
	public Object[][] highestScore() {
		
		int max;	
		Object[][] data = new Object[100][6];
		int i = 0;
		int j = 0, k = 0, l = 0;
		rs1 = student.showData();
		rs2 = course.showData();
		rs3 = score.showData(false);
		//循环科目
		while(rs2.length > j) {	
			//System.out.println(rs2[j][1].toString());
			max = -1;
			//System.out.println(rs2[j][1].toString());
			//循环成绩，找当前科目最高成绩
			k = 0;
			while(rs3.length > k){
				//只有科目相同才处理
				if(rs2[j][0].equals(rs3[k][1])) {
					if(Integer.parseInt(rs3[k][2].toString()) >= max) {
						//System.out.println(Integer.parseInt(rs3[k][2].toString()));
						//System.out.println(max);
						//第一个人是最大的，肯定比-1大，如果第二个人等于第一个人的话继续记录，这样子就可以记录下分数相同的最高分了
						//这个人是最高分//记下这个人
						max = Integer.parseInt(rs3[k][2].toString());
						//循环学生表，找到这个人的信息
						l = 0;
						while(rs1.length > l) {
							if(rs3[k][0].equals(rs1[l][0])) {
								data[i][0] = rs2[j][1];
								data[i][1] = rs1[l][0];
								data[i][2] = rs1[l][1];
								data[i][3] = rs1[l][2];
								data[i][4] = rs1[l][3];
								data[i][5] = rs3[k][2];
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
	
	//通过学号给名字
	public String getName(String id) {
		int i = 0;
		rs1 = student.showData();
		while(rs1.length > i) {
			if(rs1[i][0].equals(id)) {
				return (String) rs1[i][1];
			}
			i++;
		}
		return null;
		
	}

	//学生成绩表
	public Object[][] studentScore(String id, boolean type){
		int j = 0,k = 0;
		Object[][] data = new Object[course.count()][3];
		int m = 0;
		//先找出是哪个同学
		rs2 = course.showData();
		rs3 = score.showData(type);
		//用这个学号，去成绩表里面，把这个学生的成绩和课程号找到
		while(rs3.length > k) {
			if(rs3[k][0].equals(id)) {
				//找到一条记录是这个学号的,记下他的成绩，然后通过课程号去课程表找
				data[m][0] = rs3[k][1];
				data[m][2] = rs3[k][2];
				j = 0;
				while(rs2.length > j) {
					if(rs2[j][0].equals(rs3[k][1])) {
						//通过课程号找到这个课程名，然后记下
						data[m][1] = rs2[j][1];
						m++;
						break;
					}
					j++;
				}
			}
			k++;
		}
		return data;
	}
	
	//给所有课程名课程号
	public Object[][] getAllCourse(){
		int j = 0;
		Object[][] data = new Object[course.count()][2];
		rs2 = course.showData();
		while(rs2.length > j) {
			data[j][0] = rs2[j][0];
			data[j][1] = rs2[j][1];
			j++;
		}
		return data;	
	}
	
	//课程号给课程名
	public String getCourse(String id) {
		int j = 0;
		rs2 = course.showData();
		while(rs2.length > j) {
			if(rs2[j][0].equals(id)) {
				return (String) rs2[j][1];
			}
			j++;
		}
		return null;		
	}
	
	//课程成绩表
	public Object[][] courseScore(String id, boolean type){
		int i = 0,k = 0;
		Object[][] data = new Object[student.count()][3];
		int m = 0;
		rs1 = student.showData();
		rs3 = score.showData(type);
		//用这个课程号，去成绩表里面，把这个课程的成绩和学生号找到
		while(rs3.length > k) {
			if(rs3[k][1].equals(id)) {
				//找到一条记录是这个课程的,记下他的成绩，然后通过学生号去学生表找
				data[m][0] = rs3[k][0];
				data[m][2] = rs3[k][2];
				i = 0;
				while(rs1.length > i) {
					if(rs1[i][0].equals(rs3[k][0])) {
						//通过学生号找到这个学生名，然后记下
						data[m][1] = rs1[i][1];
						m++;
						break;
					}
					i++;
				}
			}
			k++;
		}
		return data;
	}
	
	//课程平均成绩表
	public Object[][] average(){
		Object[][] data = new Object[course.count()][5];
		rs2 = course.showData();
		rs3 = score.showData(false);
		int j = 0,k = 0;
		int m = 0;
		int sum = 0, number = 0, average = 0;
		while(rs2.length > j) {
			data[m][0] = rs2[j][0];
			data[m][1] = rs2[j][1];
			sum = 0; number = 0;
			//开始根据课程编号，去成绩表统计总数，算平均数了
			k = 0;
			//循环加然后计算平均数
			while(rs3.length > k) {
				//如果是这个课程就加上
				if(rs3[k][1].equals(rs2[j][0])) {
					sum += Integer.parseInt(rs3[k][2].toString());
					number++;					
				}
				k++;
			}
			data[m][2] = number;
			average = number == 0 ? 0 : sum/number;
			data[m][3] = average;
			//循环找，超过平均数的人数
			number = 0;
			k = 0;
			while(rs3.length > k) {
				//如果是这个课程就加上
				if(rs3[k][1].equals(rs2[j][0])) {
					//如果大于平均数就加上
					if(Integer.parseInt(rs3[k][2].toString()) > average) {
						number++;		
					}			
				}
				k++;
			}
			data[m][4] = number;			
			m++;
			j++;
		}
		return data;		
	}
	
}

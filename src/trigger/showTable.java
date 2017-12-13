package trigger;
import mysql.*;

//���ݿ�ת������
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
	//��߳ɼ���
	public Object[][] highestScore() {
		
		int max;	
		Object[][] data = new Object[100][6];
		int i = 0;
		int j = 0, k = 0, l = 0;
		rs1 = student.showData();
		rs2 = course.showData();
		rs3 = score.showData(false);
		//ѭ����Ŀ
		while(rs2.length > j) {	
			//System.out.println(rs2[j][1].toString());
			max = -1;
			//System.out.println(rs2[j][1].toString());
			//ѭ���ɼ����ҵ�ǰ��Ŀ��߳ɼ�
			k = 0;
			while(rs3.length > k){
				//ֻ�п�Ŀ��ͬ�Ŵ���
				if(rs2[j][0].equals(rs3[k][1])) {
					if(Integer.parseInt(rs3[k][2].toString()) >= max) {
						//System.out.println(Integer.parseInt(rs3[k][2].toString()));
						//System.out.println(max);
						//��һ���������ģ��϶���-1������ڶ����˵��ڵ�һ���˵Ļ�������¼�������ӾͿ��Լ�¼�·�����ͬ����߷���
						//���������߷�//���������
						max = Integer.parseInt(rs3[k][2].toString());
						//ѭ��ѧ�����ҵ�����˵���Ϣ
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
						//�����һ������С�ģ���ô���涼�����С�ˣ�Ȼ��Ϳ��Խ�����
						break;
					}
				}
				k++;
			}
			j++;
		}
		return data;		
	}
	
	//ͨ��ѧ�Ÿ�����
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

	//ѧ���ɼ���
	public Object[][] studentScore(String id, boolean type){
		int j = 0,k = 0;
		Object[][] data = new Object[course.count()][3];
		int m = 0;
		//���ҳ����ĸ�ͬѧ
		rs2 = course.showData();
		rs3 = score.showData(type);
		//�����ѧ�ţ�ȥ�ɼ������棬�����ѧ���ĳɼ��Ϳγ̺��ҵ�
		while(rs3.length > k) {
			if(rs3[k][0].equals(id)) {
				//�ҵ�һ����¼�����ѧ�ŵ�,�������ĳɼ���Ȼ��ͨ���γ̺�ȥ�γ̱���
				data[m][0] = rs3[k][1];
				data[m][2] = rs3[k][2];
				j = 0;
				while(rs2.length > j) {
					if(rs2[j][0].equals(rs3[k][1])) {
						//ͨ���γ̺��ҵ�����γ�����Ȼ�����
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
	
	//�����пγ����γ̺�
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
	
	//�γ̺Ÿ��γ���
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
	
	//�γ̳ɼ���
	public Object[][] courseScore(String id, boolean type){
		int i = 0,k = 0;
		Object[][] data = new Object[student.count()][3];
		int m = 0;
		rs1 = student.showData();
		rs3 = score.showData(type);
		//������γ̺ţ�ȥ�ɼ������棬������γ̵ĳɼ���ѧ�����ҵ�
		while(rs3.length > k) {
			if(rs3[k][1].equals(id)) {
				//�ҵ�һ����¼������γ̵�,�������ĳɼ���Ȼ��ͨ��ѧ����ȥѧ������
				data[m][0] = rs3[k][0];
				data[m][2] = rs3[k][2];
				i = 0;
				while(rs1.length > i) {
					if(rs1[i][0].equals(rs3[k][0])) {
						//ͨ��ѧ�����ҵ����ѧ������Ȼ�����
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
	
	//�γ�ƽ���ɼ���
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
			//��ʼ���ݿγ̱�ţ�ȥ�ɼ���ͳ����������ƽ������
			k = 0;
			//ѭ����Ȼ�����ƽ����
			while(rs3.length > k) {
				//���������γ̾ͼ���
				if(rs3[k][1].equals(rs2[j][0])) {
					sum += Integer.parseInt(rs3[k][2].toString());
					number++;					
				}
				k++;
			}
			data[m][2] = number;
			average = number == 0 ? 0 : sum/number;
			data[m][3] = average;
			//ѭ���ң�����ƽ����������
			number = 0;
			k = 0;
			while(rs3.length > k) {
				//���������γ̾ͼ���
				if(rs3[k][1].equals(rs2[j][0])) {
					//�������ƽ�����ͼ���
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

package trigger;
import mysql.*;

//���ݿ�ת������
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
	//��߳ɼ���
	public Object[][] highestScore() {
		int max;	
		Object[][] data = new Object[100][100];
		int i = 0;
		int j = 0, k = 0, l = 0;
		rs2 = course.showData();
		//ѭ����Ŀ
		while(rs2[j][0] != null) {	
			max = -1;
			rs3 = score.showData(false);
			//ѭ���ɼ����ҵ�ǰ��Ŀ��߳ɼ�
			k = 0;
			while(rs3[k][0] != null){
				//ֻ�п�Ŀ��ͬ�Ŵ���
				if(rs2[j][0].equals(rs3[k][1])) {
					System.out.println(Integer.parseInt(rs3[k][2].toString()));
					if(Integer.parseInt(rs3[k][2].toString()) >= max) {
						//��һ���������ģ��϶���-1������ڶ����˵��ڵ�һ���˵Ļ�������¼�������ӾͿ��Լ�¼�·�����ͬ����߷���
						//���������߷�//���������
						max = Integer.parseInt(rs3[k][2].toString());
						rs1 = student.showData();
						//ѭ��ѧ�����ҵ�����˵���Ϣ
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


	
}

package chapter06;

public class Avg {
	public static void main(String[] args) {
		int[] scores = new int[]{88,76,90,89,70,95,99,85,66,89};
		int scoresCount = 0;
		for(int i=0;i < scores.length;i++){
			scoresCount += scores[i];
		}
		int avgResult = scoresCount/scores.length;
		System.out.println("班内平均成绩是："+avgResult);
	}
}

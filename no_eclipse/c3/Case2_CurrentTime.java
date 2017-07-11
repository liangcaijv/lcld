
public class Case2_CurrentTime{
	public static void main(String[] args){
		long currentTimeMillis = System.currentTimeMillis();  // GMT 

		// 总秒数
		long totalSeconds = currentTimeMillis/1000;
		// 当前秒数
		long seconds = totalSeconds%60;

		// 总分钟数
		long totalMinutes = totalSeconds/60;
		// 当前分钟数
		long minutes = totalMinutes%60;

		// 总小时数
		long totalHours = totalMinutes/60;
		// 当前小时数
		long hours = totalHours%24;

		System.out.println("系统当前时间是："+(hours+8)+":"+minutes+":"+seconds);

	}

}
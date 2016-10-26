//包声明
package chapter07;

//引入所需要用的窗体控件包
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowsTest {
	
	public static void main(String[] args) {
		//利用JFrame类构建一个窗体对象,对象引用标识符frame
		JFrame frame = new JFrame();
		//利用窗体对象引用的setSize()方法调整frame的大小
		frame.setSize(400, 200);
		//利用JLable类构建一个Label对象，对象引用标识符lable
		JLabel label = new JLabel("Hello Java窗体");
		//利用frame的add方法把label添加到frame
		frame.add(label);
		//设置现实窗体
		frame.setVisible(true);
	}
}

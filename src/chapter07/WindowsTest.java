//������
package chapter07;

//��������Ҫ�õĴ���ؼ���
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowsTest {
	
	public static void main(String[] args) {
		//����JFrame�๹��һ���������,�������ñ�ʶ��frame
		JFrame frame = new JFrame();
		//���ô���������õ�setSize()��������frame�Ĵ�С
		frame.setSize(400, 200);
		//����JLable�๹��һ��Label���󣬶������ñ�ʶ��lable
		JLabel label = new JLabel("Hello Java����");
		//����frame��add������label��ӵ�frame
		frame.add(label);
		//������ʵ����
		frame.setVisible(true);
	}
}

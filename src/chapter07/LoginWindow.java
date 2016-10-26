package chapter07;

import javax.swing.*;

import java.awt.*;   //�����Ҫ�İ�

public class LoginWindow {
	
	int width;
	
	int height;
	
	//���췽��
	public LoginWindow(int width,int height){
		this.width = width;
		this.height = height;
	}
	
    public void show(){
    	//�������ڶ�������
    	JFrame mainFrame = new JFrame("��ӭ��½");
    	//�����������ÿ�͸�
        mainFrame.setSize(width, height);
        //�ı������,12���ַ����
    	JTextField loginNameTextField = new JTextField(12);
    	//���������,12���ַ����
    	JPasswordField passwordTextField = new JPasswordField(12);
    	JLabel loginNameLabel = new JLabel("�û���");
    	JLabel passwordLabel = new JLabel("�ܡ���");//������ǩ
    	JButton submit = new JButton("ȷ��");
    	JButton cancel = new JButton("ȡ��");//��ť����
    	
    	JPanel row1 = new JPanel();
    	JPanel row2 = new JPanel();
    	JPanel row3 = new JPanel();//����������
        
        //���ò���
    	mainFrame.setLayout(new GridLayout(3,1));
        
        row1.add(loginNameLabel); 
        row1.add(loginNameTextField);//��һ���������û������ı��� 
        
        row2.add(passwordLabel);
        row2.add(passwordTextField);//�ڶ�����������������������
        
        row3.add(submit);
        row3.add(cancel); //������������ȷ�Ϻ�ȡ��
        
        mainFrame.add(row1);
        mainFrame.add(row2);
        mainFrame.add(row3);  //�����������ӵ���½������
        //����Ĭ�ϵĴ��ڹرհ�ť����,������Ĭ�ϵĹرհ�ť�����ز����˳�����
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
         
    }
    
    //������Ҳ����д��������������У�ֻҪ����LoginWindow���ڰ�·������
    public static void main(String[] args){
        LoginWindow loginWindow = new LoginWindow(300,200);//����LoginWindow����
        loginWindow.show();//��ʾLoginwindow����
    }
}
package chapter07;

import javax.swing.*;

import java.awt.*;   //导入必要的包

public class LoginWindow {
	
	int width;
	
	int height;
	
	//构造方法
	public LoginWindow(int width,int height){
		this.width = width;
		this.height = height;
	}
	
    public void show(){
    	//构建窗口顶级容器
    	JFrame mainFrame = new JFrame("欢迎登陆");
    	//根据属性设置宽和高
        mainFrame.setSize(width, height);
        //文本输入框,12个字符宽度
    	JTextField loginNameTextField = new JTextField(12);
    	//密码输入框,12个字符宽度
    	JPasswordField passwordTextField = new JPasswordField(12);
    	JLabel loginNameLabel = new JLabel("用户名");
    	JLabel passwordLabel = new JLabel("密　码");//输入框标签
    	JButton submit = new JButton("确认");
    	JButton cancel = new JButton("取消");//按钮声明
    	
    	JPanel row1 = new JPanel();
    	JPanel row2 = new JPanel();
    	JPanel row3 = new JPanel();//三行子容器
        
        //设置布局
    	mainFrame.setLayout(new GridLayout(3,1));
        
        row1.add(loginNameLabel); 
        row1.add(loginNameTextField);//第一块面板添加用户名和文本框 
        
        row2.add(passwordLabel);
        row2.add(passwordTextField);//第二块面板添加密码和密码输入框
        
        row3.add(submit);
        row3.add(cancel); //第三块面板添加确认和取消
        
        mainFrame.add(row1);
        mainFrame.add(row2);
        mainFrame.add(row3);  //将三块面板添加到登陆框上面
        //设置默认的窗口关闭按钮动作,不设置默认的关闭按钮是隐藏并不退出程序
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
         
    }
    
    //主方法也可以写在任意的其他类中，只要导入LoginWindow所在包路径即可
    public static void main(String[] args){
        LoginWindow loginWindow = new LoginWindow(300,200);//构建LoginWindow对象
        loginWindow.show();//显示Loginwindow对象
    }
}
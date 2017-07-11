/**
* 全月应纳税所得额        税率         速算扣除数(元) 
* 全月应纳税额不超过1500元            3%             0 
* 全月应纳税额超过1500元至4500元     10%          105 
* 全月应纳税额超过4500元至9000元     20%          555 
* 全月应纳税额超过9000元至35000元    25%          1005 
* 全月应纳税额超过35000元至55000元   30%          2755 
* 全月应纳税额超过55000元至80000元   35%          5505 
* 全月应纳税额超过80000元              45%          13505
*
*/
import java.util.Scanner;
import java.text.NumberFormat;
public class Case14_PersonalIncomeTaxWithWhile{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    NumberFormat nFormat=NumberFormat.getNumberInstance();
    nFormat.setMaximumFractionDigits(2);//设置小数点后面尾数为2
    while(true){
      System.out.println("请输入您的税前工资：");
      double salary  = in.nextDouble();
      if (salary<0) {
        System.out.println("你是在逗我吗？上班还给公司钱？");
        System.exit(0);// 当前程序退出
      }
      // 应纳税所得额 
      double taxableIncome = salary-salary*0.1-3500;
      // double realSalary = 0;
      double taxRate = 0; // 税率
      double deduct = 0; // 速算扣除数
      double tax = 0; // 税额
      if (taxableIncome <= 0) {
        System.out.println("少年，你的工资太低了，不用缴税！");
        System.exit(0);// 当前程序退出
      }

      if(taxableIncome <= 1500){
        taxRate = 0.03;
        deduct = 0;
      }else if(taxableIncome <= 4500){
        taxRate = 0.1;
        deduct = 105;
      }else if(taxableIncome <= 9000){
        taxRate = 0.2;
        deduct = 555;
      }else if(taxableIncome <= 35000){
        taxRate = 0.25;
        deduct = 1005;
      }else if(taxableIncome <= 55000){
        taxRate = 0.3;
        deduct = 2755;
      }else if(taxableIncome <= 80000){
        taxRate = 0.35;
        deduct = 5505;
      }else{
        taxRate = 0.45;
        deduct = 13505;
      }

      System.out.println("应纳税所得额 "+ nFormat.format(taxableIncome) +" 税率 "+taxRate
        +" 速算扣除数 "+deduct);
      tax = taxableIncome*taxRate-deduct;
      System.out.println("应缴税款："+ nFormat.format(tax));
      System.out.println("实发工资："+ nFormat.format(salary*0.9-tax));
    }
  }

}
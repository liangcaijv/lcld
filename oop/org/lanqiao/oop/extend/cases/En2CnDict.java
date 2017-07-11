package org.lanqiao.oop.extend.cases;

public class En2CnDict extends AbstractEn2CnDict{
	public String enToCn(String cnString) {
		String errorMsg = "�Ҳ�����Ӧ�ķ���!!";
		if (cnString==null) {
			return errorMsg;
		} else if(cnString.equalsIgnoreCase("hello")){
			return "���";
		}else if(cnString.equals("bye")){
			return "�ټ�";
		}
		return errorMsg;
	}
}

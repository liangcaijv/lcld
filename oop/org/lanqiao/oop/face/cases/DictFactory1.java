package org.lanqiao.oop.face.cases;

import org.lanqiao.oop.extend.cases.IDict;
/**
 * class���Զ�����������������
 * @author �ڹ�
 *
 */
public class DictFactory1 {
	public static IDict getDict(String flag){
		if(flag.equals("en")){
			class EnDict implements IDict{
				@Override
				public String cn2En(String en) {
					return "�ù���δʵ��";
				}

				@Override
				public String enToCn(String cnString) {
					if(cnString.equals("���")) return "hello";
					if(cnString.equals("�ټ�")) return "bye";
					return null;
				}
				
			}
			return new EnDict();
		}else{
			class CnDict implements IDict{
				@Override
				public String cn2En(String en) {
					if(en.equals("hello")) return "���";
					if(en.equals("bye")) return "�ټ�";
					return null;
				}

				@Override
				public String enToCn(String cnString) {
					
					return "�ù���δʵ��";
				}
				
			}
			return new CnDict();
		}
	}
}

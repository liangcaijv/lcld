package org.lanqiao.oop.face.cases;

import org.lanqiao.oop.extend.cases.IDict;

public class DictFactory {
	public static IDict getDict(){
		//��Ҫ��������ʱ,������������
		return new IDict() {
			
			public String enToCn(String cnString) {
				return null;
			}
			
			public String cn2En(String en) {
				return null;
			}
		};
	}
}

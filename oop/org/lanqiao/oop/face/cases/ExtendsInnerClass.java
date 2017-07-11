package org.lanqiao.oop.face.cases;
/**
 * ����̳��ڲ���
 * @author heigong
 *
 */
public class ExtendsInnerClass {
	class Inner{}
}

class DerivedFromInnerClass extends ExtendsInnerClass.Inner{
	//1.�̳��ڲ���ʱ�����ڹ������д�����Χ����
	public DerivedFromInnerClass(ExtendsInnerClass sp) {
		//2.���ȵ�����Χ���super����
		sp.super();
	}
}

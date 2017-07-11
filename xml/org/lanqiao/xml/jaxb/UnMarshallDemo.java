package org.lanqiao.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.lanqiao.xml.jaxb.example.person.Persons;
/**
 * 
 * <p>Title:jaxb����-��ϵ-ʵ�� </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * @author ֣δ(zhengweic@si-tech.com.cn)
 * @version 1.0
 */
public class UnMarshallDemo {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("org.example.person");
		Unmarshaller um = jc.createUnmarshaller();
		Persons persons = (Persons) um.unmarshal(ClassLoader.getSystemResourceAsStream("app\\persons.xml"));
		System.out.println(persons.getPerson());
		
		//��person�б�����޸�,�����ٽ��б���
		//......
	}
}

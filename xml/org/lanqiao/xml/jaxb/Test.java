package org.lanqiao.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.lanqiao.xml.jaxb.example.person.ObjectFactory;
import org.lanqiao.xml.jaxb.example.person.Person;
import org.lanqiao.xml.jaxb.example.person.Persons;


public class Test {
	public static void main(String[] args) throws Exception {
		 JAXBContext jc = JAXBContext.newInstance( "org.example.person");//package ��Ϣ-->jaxb������
		 ObjectFactory factory = new ObjectFactory();
		 Persons persons= factory.createPersons();
		 
		 Person p = new Person();
		 p.setId(1);
		 p.setName("zhangsan");
		 p.setAge(20);
		 p.setSalary(2000);
		 persons.getPerson().add(p);//����һ��person
		 
		 p = new Person();
		 p.setId(2);
		 p.setName("lisi");
		 p.setAge(30);
		 p.setSalary(3000);
		 persons.getPerson().add(p);//����һ��person
		 
		 Marshaller marshaller =  jc.createMarshaller();//����:java object-->xml
		 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 marshaller.marshal(persons, System.out);
	}
}

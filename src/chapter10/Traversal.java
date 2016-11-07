package chapter10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chapter09.StudentInfo;

public class Traversal {

	public static void main(String[] args) {
		List<StudentInfo> list = new ArrayList<StudentInfo>();
		for (int i = 0; i < list.size(); i++) {
			StudentInfo info = list.get(i);
			System.out.println(info);
		}
		
		Set<StudentInfo> set = new HashSet<StudentInfo>();
		for (StudentInfo studentInfo : set) {
			System.out.println(studentInfo);
		}
		
		Iterator<StudentInfo> iterator = set.iterator();
		while(iterator.hasNext()){
			StudentInfo studentInfo = (StudentInfo) iterator.next();
			System.out.println(studentInfo);
		}
		
//		for (Iterator<StudentInfo> iterator = set.iterator(); iterator.hasNext();) {
//			StudentInfo studentInfo = (StudentInfo) iterator.next();
//			System.out.println(studentInfo);
//		}
		
		//创建Map对象，键为Integer,值为String
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		//map赋值
		map.put(1,"Jack");
		map.put(2,"Rose");
		map.put(3,"Lucy");
		map.put(4,"Coco");

		//遍历map
		Set<Integer> keys = map.keySet();//获取所有key的集合
		
		for(Integer key:keys){
			System.out.println("key为 " + key +",value为 " + map.get(key));
		}
	}
}

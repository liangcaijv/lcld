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
		
		//����Map���󣬼�ΪInteger,ֵΪString
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		//map��ֵ
		map.put(1,"Jack");
		map.put(2,"Rose");
		map.put(3,"Lucy");
		map.put(4,"Coco");

		//����map
		Set<Integer> keys = map.keySet();//��ȡ����key�ļ���
		
		for(Integer key:keys){
			System.out.println("keyΪ " + key +",valueΪ " + map.get(key));
		}
	}
}

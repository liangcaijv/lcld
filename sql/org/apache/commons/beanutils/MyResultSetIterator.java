package org.apache.commons.beanutils;


public class MyResultSetIterator extends ResultSetIterator {

	
	public MyResultSetIterator(ResultSetDynaClass dynaClass) {
		super(dynaClass);
	}

	/*@Override
	public Object get(String name) {
		String javaName = WordDealUtil.dBColumn2Java(name);
		return super.get(javaName);
	}*/
}

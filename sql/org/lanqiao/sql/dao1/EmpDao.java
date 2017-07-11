package org.lanqiao.sql.dao1;

import java.util.List;


public interface EmpDao {
	void save(Employee emp);
	void delete(Employee emp);
	void update(Employee emp);
	Employee findById(int id);
	List<Employee> findAll();
}

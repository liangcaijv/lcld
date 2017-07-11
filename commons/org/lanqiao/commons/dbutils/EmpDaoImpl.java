package org.lanqiao.commons.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.sql.util1.DbUtil;

public class EmpDaoImpl implements EmpDao{

	@Override
	public void save(Employee emp) {
		Connection conn = null;
		try {
			String sql = "insert into employees (employee_id,first_name,last_name,email,phone_number,hire_date,salary) values(empSeq.nextval,?,?,?,?,?,?)";
			conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getFirst_name());
			pstmt.setString(2, emp.getLast_name());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getPhone_number());
			pstmt.setDate(5, emp.getHire_date());
			pstmt.setDouble(6, emp.getSalary());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			DbUtil.freeConnection(conn);
		}
	}

	@Override
	public void delete(Employee emp) {
			DbUtil.update("delete from employees where employee_id="+emp.getEmployee_id());
	}

	@Override
	public void update(Employee emp) {
		Connection conn = null;
		try {
			String sql = "update employees set first_name=?,last_name=?,email=?,phone_number=?,hire_date=?,salary=? where employee_id=?";
			conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getFirst_name());
			pstmt.setString(2, emp.getLast_name());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getPhone_number());
			pstmt.setDate(5, emp.getHire_date());
			pstmt.setDouble(6, emp.getSalary());
			pstmt.setInt(7, emp.getEmployee_id());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			DbUtil.freeConnection(conn);
		}
	}

	@Override
	public Employee findById(int id) {
		String sql = "select * from employees where employee_id=?";
		QueryRunner runner = new QueryRunner(DbUtil.getDataSource());
		ResultSetHandler<Employee> rsh = new BeanHandler(Employee.class);
		try {
			Employee emp = runner.query(sql, rsh,new Object[]{id});
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Employee> findAll(){
		String sql = "select * from employees ";
		QueryRunner runner = new QueryRunner(DbUtil.getDataSource());
		ResultSetHandler h = new BeanListHandler(Employee.class);
		try {
			List<Employee> list = (List<Employee>) runner.query(sql, h);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

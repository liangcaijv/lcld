package org.lanqiao.sql.dao1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "select * from employees where employee_id="+id;
		ResultSet rs;
		Employee emp = new Employee();
		try {
			rs = DbUtil.exeQuery(sql);
			while(rs.next()){
				emp.setEmployee_id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setPhone_number(rs.getString(5));
				emp.setHire_date(rs.getDate(6));
				emp.setSalary(rs.getDouble(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public List<Employee> findAll(){
		String sql = "select * from employees ";
		ResultSet rs;
		List<Employee> list = new ArrayList<Employee>();
		try {
			rs = DbUtil.exeQuery(sql);
			while(rs.next()){
				Employee emp = new Employee();
				emp.setEmployee_id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));
				emp.setEmail(rs.getString(4));
				emp.setPhone_number(rs.getString(5));
				emp.setHire_date(rs.getDate(6));
				emp.setSalary(rs.getDouble(7));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

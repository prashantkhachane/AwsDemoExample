package com.javatpoint.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatpoint.bean.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	@Query(value="from Employee e where e.name like %:search% or e.email like %:search% or e.phoneNo like %:search% or e.state like %:search% or e.city like %:search% or e.country like %:search%" )
	Page<Employee> findAll(Pageable pageable, String search);
}

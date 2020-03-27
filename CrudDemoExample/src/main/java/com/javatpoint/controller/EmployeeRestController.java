package com.javatpoint.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.javatpoint.bean.DataTableDemo;
import com.javatpoint.bean.Employee;
import com.javatpoint.services.EmployeeServices;

@RestController
public class EmployeeRestController {

	@Autowired
	EmployeeServices employeeServices;

	@GetMapping(path = "/display")
	public List<Employee> display() {
		List<Employee> list = employeeServices.getAllList();
		return list;
	}
	@GetMapping(path = "/getAllEmployee")
	public DataTableDemo getAllEmployee(HttpServletRequest request) {
		int pageid = 0;
		
		int pageNumber = 0;
		if (null != request.getParameter("iDisplayStart")) {
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10)+1;
		}
		Integer total = Integer.valueOf(request.getParameter("iDisplayLength"));
		String search = request.getParameter("sSearch");
		System.out.println("search="+search);
		Pageable pageable = PageRequest.of(pageNumber-1,total);
		Page<Employee> pageList = employeeServices.getAllListWithPagination(pageable,search);
		   
		List<Employee> list=pageList.getContent();
		DataTableDemo dataTableJson = new DataTableDemo();

		Long totalNumberOfRecord=employeeServices.getTotalEmployee();
		
		
		
		dataTableJson.setiTotalDisplayRecords(totalNumberOfRecord);
		// Set Total record
		dataTableJson.setiTotalRecords(totalNumberOfRecord);
		dataTableJson.setAaData(list);
		return dataTableJson;
	}

	@RequestMapping(path = "/edit")
	public Employee edit(@RequestParam("id") String id) {
		Employee employee;
		if (id.contains("'")) {
			String s[] = id.split("'");
			Integer empId = Integer.parseInt(s[1]);
			employee = employeeServices.editEmployee(empId);
		} else {
			employee = employeeServices.editEmployee(Integer.parseInt(id));
		}
		return employee;
	}

	@PutMapping(value = "/update")
	public String update(Employee employee) {
		Employee employee2 = employeeServices.updateEmployee(employee);
		if (employee2 != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@RequestMapping(value = "/pdf")
	public void update(@PathVariable("id") String id) {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://Demo.pdf"));
			System.out.println("writer=" + writer);
			document.open();
			document.add(new Paragraph("Some content here"));

		
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

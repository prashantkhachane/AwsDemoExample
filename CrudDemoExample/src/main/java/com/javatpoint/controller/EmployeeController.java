package com.javatpoint.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javatpoint.bean.DataTableDemo;
import com.javatpoint.bean.Employee;
import com.javatpoint.services.EmployeeServices;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
		
		
	}

	@PostMapping(value = "/save")
	public String register(@ModelAttribute("employee") Employee employee) {
		employeeServices.addEmployee(employee);
		return "redirect:/view";
	}

	@RequestMapping(value = "/view")
	public String display() {
		return "display";
	}
	

}

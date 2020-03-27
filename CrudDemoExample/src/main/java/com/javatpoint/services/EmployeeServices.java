package com.javatpoint.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.javatpoint.bean.Employee;
import com.javatpoint.dao.EmployeeDao;

@Service
public class EmployeeServices {
	@Autowired
	EmployeeDao dao;
	@Autowired
	private JavaMailSender sender;

	public void addEmployee(Employee employee) {

		String str1 = sendEmail(employee.getEmail());
		System.out.println(str1);
		String str = sendSms();
		System.out.println(str);
		dao.save(employee);
	}

	public String sendSms() {
		try {
			// Construct data
			Random rand = new Random();
			int otp = rand.nextInt(999999);
			String apiKey = "apikey=" + "a9qfXOz5Z0E-q1hIpgHOFodQO21eJjAl0FHuKPHEQM";
			String message = "&message=" + "Your OTP is=" + otp;
			String sender = "&sender=" + "Jims Autos";

			String numbers = "&numbers=" + "8805184633";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	private String sendEmail(String to) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(to);
			String s = "<html><body><h1>Take Care For The Carona Virous in Pune Thanks For Registring </h1><body></html>";
			helper.setText(s, true);
			helper.setSubject("Take Care Wash Your Hand Prashant");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";

	}

	public List<Employee> getAllList() {
		return dao.findAll();
	}

	public Long getTotalEmployee() {
		return dao.count();
	}

	public Employee editEmployee(Integer empId) {
		Employee employee = dao.getOne(empId);
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		Employee updateEmp = dao.saveAndFlush(employee);

		return updateEmp;

	}

	public Page<Employee> getAllListWithPagination(Pageable pageable, String search) {
		return dao.findAll(pageable, search);
	}

	public Employee getFindById(Integer empId) {

		return dao.getOne(empId);
	}

}

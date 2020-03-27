package com.javatpoint.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.bean.Employee;
import com.javatpoint.services.EmployeeServices;

@Controller
public class PdfController {

	@Autowired
	PdfCreator pdfCreator;

	@Autowired
	EmployeeServices services;

	@PostMapping("/download")
	public void downloadPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("id Param="+request.getParameter("id"));
		int empId=Integer.parseInt(request.getParameter("id"));
		System.out.println("empId Param="+empId);


		final ServletContext servletContext = request.getSession().getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String tempFilePath = tempDirectory.getAbsolutePath();

		String fileName ="record.pdf";
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		pdfCreator.setData(services.getFindById(empId));
		pdfCreator.createPdf(tempFilePath + "\\" + fileName);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byteArrayOutputStream = convertPdfToByteArayOutputStream(tempFilePath + "\\" + fileName);
		OutputStream outputStream = response.getOutputStream();
		byteArrayOutputStream.writeTo(outputStream);
		outputStream.flush();

	}

	private ByteArrayOutputStream convertPdfToByteArayOutputStream(String string) {

		InputStream inputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			inputStream = new FileInputStream(string);
			byte[] buffer = new byte[1024];
			byteArrayOutputStream = new ByteArrayOutputStream();
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

}

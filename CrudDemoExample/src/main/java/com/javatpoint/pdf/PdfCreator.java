package com.javatpoint.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javatpoint.bean.Employee;

@Service
public class PdfCreator {

	Employee employee = new Employee();

	public void setData(Employee employee) {
		if (employee != null) {
			this.employee = employee;
		}
	}

	public void createPdf(String string) {
		Document document = null;
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(string));
			document.open();
			addMetaData(document);
			addTittle(document);
			Image image = Image.getInstance("src/main/resources/static/Ganesh.jpg");
			image.scaleToFit(90f, 90f);

     
		    document.add(image);   
			createTable(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addTittle(Document document) throws DocumentException {
		
		Paragraph paragraph=new Paragraph();
		createEmptyLine(paragraph, 5);
		paragraph.add(new Paragraph("Employee Record"));
	
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		paragraph.add("Date:"+dateFormat.format(new Date()));
		createEmptyLine(paragraph, 2);
		document.add(paragraph);
	}

	private void createTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		createEmptyLine(paragraph, 5);
		paragraph.setSpacingBefore(50);
		paragraph.setSpacingAfter(50);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(6);

		PdfPCell cell1 = new PdfPCell(new Phrase("Name"));
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell1);

		cell1 = new PdfPCell(new Phrase("Email"));
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell1);

		cell1 = new PdfPCell(new Phrase("PhoneNo"));
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell1);
		
		cell1 = new PdfPCell(new Phrase("City"));
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell1);
		
		cell1 = new PdfPCell(new Phrase("State"));
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell1);
		
		cell1 = new PdfPCell(new Phrase("Country"));
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell1);
		
		
		
		table.setHeaderRows(1);

		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(employee.getName());
		table.addCell(employee.getEmail());
		table.addCell(employee.getPhoneNo().toString());
		table.addCell(employee.getCity());
		table.addCell(employee.getState());
		table.addCell(employee.getCountry());

		document.add(table);

	}

	private void createEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph());
		}

	}

	private void addMetaData(Document document) {
		document.addTitle("Muktai Computers");
		document.addSubject("Employee Record");
		document.addCreator("Mr.Prashanat Khachane");
	}

}

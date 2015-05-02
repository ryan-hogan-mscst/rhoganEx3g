package ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	private String fileName2 = "ex3g.txt";
	
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		
		 // Open the file.
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists(); 
									
			if (fileOpened) {
				// Open the file.
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		
		return fileOpened;
	}
	
	public boolean openOutputFile() {
		boolean fileOpened = false;
		try {
			this.outputFile = new PrintWriter(fileName2);
			fileOpened = true;

		}
		catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeInputFile() {
		if (this.inputFile != null) this.inputFile.close();
	}
	
	public void closeOutputFile() {

	}
	
	public Payroll getNextPayroll() {
		Payroll p = null;
		
		String textLine = inputFile.nextLine();
		int id = Integer.parseInt(textLine);
		String name = inputFile.nextLine();
		textLine = inputFile.nextLine();
		double payRate = Double.parseDouble(textLine);
		textLine = inputFile.nextLine();
		double hours = Double.parseDouble(textLine);
		
		p = new Payroll(id, name, payRate, hours);	
		
		return p;
		
	}
	
	public void writePayroll(Payroll Payroll) {
		//if (this.outputFile != null & Payroll != null) {

		outputFile.println(Payroll.getEmpId());
		outputFile.println(Payroll.getName());
		outputFile.println(Payroll.getPay());
		outputFile.println(Payroll.getHours());
		//}
	}
	
	public DefaultListModel getAllPayroll() {
		DefaultListModel payrollCollection = new DefaultListModel();
		if (this.openInputFile()) {
			while(this.inputFile.hasNext()) {
				payrollCollection.addElement(this.getNextPayroll());
			}
		}
		this.closeInputFile();
		return payrollCollection;
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection) {
		if (this.openOutputFile()) {
			Payroll p = (Payroll) payrollCollection.get(0);
			this.writePayroll(p);
		}
	}
}

package ex3g;

	import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

	public class Payroll {
		
		int EmpId;
		String Name;
		double Pay;
		double Hours;
		double GrossPay;
		double OverTime;
		private PrintWriter outputFile;
		private String fileName;
		private File file;
		
		public Payroll(int empId, String name, double pay) {
			super();
			this.EmpId = empId;
			Name = name;
			Pay = pay;
		}
		
		public Payroll(int empId, String name, double pay, double hours) {
			super();
			this.EmpId = empId;
			Name = name;
			Pay = pay;
			Hours = hours;
		}
		
		@Override
		public String toString() {
			return "Payroll empId=" + EmpId + ", Name=" + Name + ", Pay=" + Pay;
		}

		public boolean setHours (double hour) {
			if (hour > 1) {
				if (hour < 100) {
					this.Hours = hour;
					return true;
				}
				else
					return false;					
			}
			else
				return false;
		}
		
		public double getHours () {
			return Hours;
		}
		
		public double grossPay() {
			if (Hours > 40)
			{
				//Calculate Regular time
				GrossPay = 40 * Pay;
				
				//Calculate Overtime
				OverTime = (Hours - 40) * (Pay * 1.5);
				
				GrossPay += OverTime;
			}
			else
				GrossPay = Pay * Hours;
			
			return GrossPay;
		}

		public int getEmpId() {
			return EmpId;
		}

		public boolean setId(int id) {
			if (id > 100) {
				this.EmpId = id;
				return true;
			}
			else {
				return false;
			}
		}

		public String getName() {
			return Name;
		}

		public boolean setName(String name) {
			if (name.isEmpty()) {
				return false;
			}
			else {
				this.Name = name;
				return true;
			}
		}

		public double getPay() {
			return Pay;
		}

		public void clearHours(double clear) {
			this.Hours = clear;
		}

		public boolean setPay(double pay) { 
			if (pay > 7.25 && pay < 100) {
				this.Pay = pay;
				return true;
			}
			else 
				return false;
		}
		
		
		public boolean openOutputFile() {
			boolean fileOpened = false;
			try {
				this.outputFile = new PrintWriter(fileName);
				fileOpened = true;
			}
			catch (IOException e) {}
			
			return fileOpened;
		}
	}	
	
/*
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
	
	/2740ex2f/src/Payroll.java
 */



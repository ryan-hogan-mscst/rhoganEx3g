package ex3g;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultListModel;

import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;


public class PayrollForm extends JFrame {
	private DefaultListModel PayrollListModel;
	private JPanel contentPane;
	private JList list;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;	
	private JTextField EmployeeId;
	private JTextField EmployeeName;
	private JTextField PayRate;	
	private JTextField EnterHours;
	private JLabel TotalHours;	
	private JLabel GrossPay;	
	private JButton ClearButton;
	DecimalFormat CurrencyFmt = new DecimalFormat("$#,###.00");
	DecimalFormat CurrencyFmt2 = new DecimalFormat("#,###.00");
	DecimalFormat HrFmt = new DecimalFormat("##0.00");
	private JButton PlusButton;
	private JButton btnUpdate;
	private JButton btnClearForm;
	private PayrollObjMapper payrollObjMapper;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 415, 90);
		contentPane.add(scrollPane);


		payrollObjMapper = new PayrollObjMapper("ex3g.txt");
		PayrollListModel = payrollObjMapper.getAllPayroll();
		list = new JList(PayrollListModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_list_mouseClicked(e);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setBounds(20, 110, 95, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name");
		lblNewLabel_1.setBounds(20, 135, 95, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Pay Rate");
		lblNewLabel_2.setBounds(20, 160, 81, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Enter Hours");
		lblNewLabel_3.setBounds(20, 185, 81, 14);
		contentPane.add(lblNewLabel_3);
		
		EnterHours = new JTextField();
		EnterHours.setBounds(123, 182, 30, 20);
		contentPane.add(EnterHours);
		EnterHours.setColumns(10);
		
		PlusButton = new JButton("+");
		PlusButton.setEnabled(false);
		PlusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_PlusButton_actionPerformed(arg0);
			}
		});
		PlusButton.setBounds(163, 181, 57, 23);
		contentPane.add(PlusButton);
		
		ClearButton = new JButton("Clear");
		ClearButton.setEnabled(false);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClear_actionPerformed(arg0);
			}
		});
		ClearButton.setBounds(224, 181, 113, 23);
		contentPane.add(ClearButton);
		
		lblNewLabel_4 = new JLabel("Total Hours");
		lblNewLabel_4.setBounds(20, 210, 81, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Gross Pay");
		lblNewLabel_5.setBounds(20, 235, 81, 14);
		contentPane.add(lblNewLabel_5);
		
		TotalHours = new JLabel("0.00");
		TotalHours.setHorizontalAlignment(SwingConstants.RIGHT);
		TotalHours.setBounds(125, 210, 46, 14);
		contentPane.add(TotalHours);
		
		GrossPay = new JLabel("$0.00");
		GrossPay.setHorizontalAlignment(SwingConstants.RIGHT);
		GrossPay.setBounds(125, 235, 46, 14);
		contentPane.add(GrossPay);
		
		EmployeeId = new JTextField();
		EmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
		EmployeeId.setText("0");
		EmployeeId.setBounds(125, 106, 86, 20);
		contentPane.add(EmployeeId);
		EmployeeId.setColumns(10);
		
		EmployeeName = new JTextField();
		EmployeeName.setHorizontalAlignment(SwingConstants.RIGHT);
		EmployeeName.setBounds(125, 131, 86, 20);
		contentPane.add(EmployeeName);
		EmployeeName.setColumns(10);
		
		PayRate = new JTextField();
		PayRate.setText("7.25");
		PayRate.setHorizontalAlignment(SwingConstants.RIGHT);
		PayRate.setBounds(125, 156, 86, 20);
		contentPane.add(PayRate);
		PayRate.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnUpdate_actionPerformed(arg0);
			}
		});
		btnUpdate.setBounds(77, 279, 89, 23);
		contentPane.add(btnUpdate);
		
		btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClearForm_actionPerformed(arg0);
			}
		});
		btnClearForm.setBounds(182, 279, 89, 23);
		contentPane.add(btnClearForm);
	}

	protected void do_list_mouseClicked(MouseEvent e) {
		Payroll item = (Payroll) list.getSelectedValue();		
		this.EmployeeId.setText(Integer.toString(item.getEmpId()));
		this.EmployeeName.setText(item.getName());
		this.PayRate.setText(CurrencyFmt2.format(item.getPay()));
		this.TotalHours.setText(HrFmt.format(item.getHours()));
		this.GrossPay.setText(CurrencyFmt.format(item.grossPay()));
		this.PlusButton.setEnabled(true);
		this.ClearButton.setEnabled(true);
		this.btnUpdate.setEnabled(true);
	}
	
	protected void do_PlusButton_actionPerformed(ActionEvent arg0) {
		Payroll item = (Payroll) list.getSelectedValue();
		double Hour = Double.parseDouble(EnterHours.getText());
		if(!item.setHours(Hour)) {
			JOptionPane.showMessageDialog(null, "Please Enter A Number Greater Than 0");
			this.EnterHours.requestFocus();
		}	
		else {
			item.setHours(Double.parseDouble(EnterHours.getText())); 
			this.EnterHours.setText("");
			this.EnterHours.requestFocus();
			this.TotalHours.setText(HrFmt.format(item.getHours()));	
			this.GrossPay.setText(CurrencyFmt.format(item.grossPay()));
		}
	}	
	
	protected void do_btnClear_actionPerformed(ActionEvent arg0) {
		Payroll item = (Payroll) list.getSelectedValue();
		EnterHours.setText("");
	}
	
	protected void do_btnUpdate_actionPerformed(ActionEvent arg0) {
		Payroll item = (Payroll) list.getSelectedValue();
		int Id = Integer.parseInt(EmployeeId.getText());
		double CurrentPay = Double.parseDouble(PayRate.getText());
		String CurrentName = EmployeeName.getText();
		if(!item.setId(Id)) {
			JOptionPane.showMessageDialog(null, "Please Enter A Number Greater Than 100");
			EmployeeId.setText(Integer.toString(item.getEmpId()));
			EmployeeId.requestFocus();
		}
		else {
			if(!item.setPay(CurrentPay)) {
			JOptionPane.showMessageDialog(null, "Please Enter A Number Between 7.25 and 100");
			PayRate.setText(Double.toString(item.getPay()));
			PayRate.requestFocus();
		}
			else {
				if(!item.setName(CurrentName)) {
				JOptionPane.showMessageDialog(null, "Please Enter A Name.");
				EmployeeName.setText(item.getName());
				EmployeeName.requestFocus();
				}
			}
		}
		
	}
	
	protected void do_btnClearForm_actionPerformed(ActionEvent arg0) {
		list.clearSelection();
		EmployeeId.setText("");
		EmployeeName.setText("");
		PayRate.setText("");
		EnterHours.setText("");
		TotalHours.setText("");
		GrossPay.setText("");
		this.PlusButton.setEnabled(false);
		this.ClearButton.setEnabled(false);
		this.btnUpdate.setEnabled(false);	
	}
	
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null) {
			payrollObjMapper.writeAllPayroll(PayrollListModel);
		}
		


	}
	
	}

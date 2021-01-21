package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doktor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoktorGUI extends JFrame {

	private JPanel contentPane;
	private static Doktor doktor= new Doktor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doktor);
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
	public DoktorGUI(Doktor doktor) {
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0]= "ID";
		colWhour[1]="TARÝH";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		try {
			for(int i=0 ; i<doktor.getWhourList(doktor.getId()).size(); i++) {
				whourData[0]= doktor.getWhourList(doktor.getId()).get(i).getId();
				whourData[1]= doktor.getWhourList(doktor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 584);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n "+ doktor.getName() );
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 453, 59);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btnNewButton.setBounds(473, 19, 156, 41);
		contentPane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 150, 609, 396);
		contentPane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 10, 124, 32);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.30", "14.00", "14.30", "15.00", "15.30"}));
		select_time.setBounds(144, 10, 74, 32);
		w_whour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date= " ";
				try {
					date = sdf.format(select_date.getDate());
				}catch(Exception e2) {
					
				}
				if(date.length()==0) {
					
					Helper.showMsg("Lütfen geçerli bir tarih giriniz");
				}else {
					String time = " "+ select_time.getSelectedItem().toString()+ ":00";
					String selectDate = date+time;
					try {
						boolean kontrol = doktor.addWhour(doktor.getId(), doktor.getName(), selectDate);
						if(kontrol ) {
							Helper.showMsg("success"); 
							updateWhourModel(doktor);
							
						}else {   
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		btn_addWhour.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btn_addWhour.setBounds(228, 10, 101, 32);
		w_whour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 52, 584, 288);
		w_whour.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow>=0) {
					String selectRow= table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID= Integer.parseInt(selectRow);
					boolean kontrol;
					try {
						kontrol = doktor.deleteWhour(selID);
						if(kontrol) {
							Helper.showMsg("success");
							updateWhourModel(doktor);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
					
						e1.printStackTrace();
					}
					
				}else {
					Helper.showMsg("Lütfen bir tarih seçiniz");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btn_deleteWhour.setBounds(493, 10, 101, 32);
		w_whour.add(btn_deleteWhour);
		
	}
		public void updateWhourModel(Doktor doktor) throws SQLException {
			DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
			clearModel.setRowCount(0);
			for(int i=0 ; i<doktor.getWhourList(doktor.getId()).size(); i++) {
				whourData[0]= doktor.getWhourList(doktor.getId()).get(i).getId();
				whourData[1]= doktor.getWhourList(doktor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);
			
		}
	}
}

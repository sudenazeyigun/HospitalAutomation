package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.*;

import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {

	static Bashekim bashekim = new Bashekim();
	Klinik klinik = new Klinik();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTc;
	private JTextField fld_dPass;
	private JTextField fld_doktorID;
	private JTable table_doktor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTable table_klinik;
	private JTextField fld_clinicName;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;
	private JPopupMenu klinikMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}});
			}
		
	
	public BashekimGUI(Bashekim bashekim) throws SQLException {

		// Doktor model

		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "TC No";
		colDoktorName[3] = "Þifre";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doktorData[0] = bashekim.getDoctorList().get(i).getId();
			doktorData[1] = bashekim.getDoctorList().get(i).getName();
			doktorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doktorModel.addRow(doktorData);

		}
		// klinik model

		klinikModel = new DefaultTableModel();

		Object[] colKlinik = new Object[2];
		colKlinik[0] = "ID";
		colKlinik[1] = "Klinik Adý";

		klinikModel.setColumnIdentifiers(colKlinik);
		klinikData = new Object[2];
		for (int i = 0; i < klinik.getList().size(); i++) {
			klinikData[0] = klinik.getList().get(i).getId();
			klinikData[1] = klinik.getList().get(i).getName();

			klinikModel.addRow(klinikData);

		}
		
		//worker model
		
		DefaultTableModel workerModel= new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		

		setTitle("HASTANE Y\u00D6NET\u0130M S\u0130STEM\u0130");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 584);

		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + bashekim.getName());
		lblNewLabel.setBounds(10, 26, 453, 59);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(473, 35, 156, 41);
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 135, 609, 396);
		w_pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1.setBounds(371, 5, 77, 27);
		panel.add(lblNewLabel_1);

		fld_dName = new JTextField();
		fld_dName.setBounds(371, 29, 104, 27);
		panel.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. Kimlik Numaras\u0131");
		lblNewLabel_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(373, 71, 169, 27);
		panel.add(lblNewLabel_1_1);

		fld_dTc = new JTextField();
		fld_dTc.setColumns(10);
		fld_dTc.setBounds(375, 105, 104, 27);
		panel.add(fld_dTc);

		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre");
		lblNewLabel_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(373, 144, 77, 27);
		panel.add(lblNewLabel_1_2);

		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(375, 175, 104, 27);
		panel.add(fld_dPass);

		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTc.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {

					try {
						boolean control = bashekim.addDoctor(fld_dTc.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTc.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();

						}

					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton_1.setBounds(373, 218, 145, 27);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1_3 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_3.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(373, 251, 118, 27);
		panel.add(lblNewLabel_1_3);

		fld_doktorID = new JTextField();
		fld_doktorID.setColumns(10);
		fld_doktorID.setBounds(375, 277, 104, 27);
		panel.add(fld_doktorID);

		JButton btnNewButton_2 = new JButton("Sil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doktorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doktorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {

							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton_2.setBounds(373, 314, 145, 27);
		panel.add(btnNewButton_2);

		JScrollPane w_scrolldoktor = new JScrollPane();
		w_scrolldoktor.setBounds(10, 9, 341, 349);
		panel.add(w_scrolldoktor);

		table_doktor = new JTable(doktorModel);
		w_scrolldoktor.setViewportView(table_doktor);

		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					fld_doktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		table_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectName = table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selecTcno = table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectPass = table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selecTcno, selectPass, selectName);

					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_klinik = new JPanel();
		tabbedPane.addTab("Poliklinikler", null, w_klinik, null);
		w_klinik.setBackground(Color.WHITE);
		w_klinik.setLayout(null);

		JScrollPane w_scroll_klinik = new JScrollPane();
		w_scroll_klinik.setBounds(10, 10, 224, 349);
		w_klinik.add(w_scroll_klinik);
		w_klinik.setLayout(null);

		klinikMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		klinikMenu.add(updateMenu);
		klinikMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selID = Integer.parseInt(table_klinik.getValueAt(table_klinik.getSelectedRow(), 0).toString());
				Klinik selectKlinik = klinik.getFetch(selID);
				UpdateKlinikGUI updateGUI = new UpdateKlinikGUI(selectKlinik);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateKlinikModel();
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
					}
				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_klinik.getValueAt(table_klinik.getSelectedRow(), 0).toString());
					try {
						if (klinik.deleteKlinik(selID)) {
							Helper.showMsg("success");
							updateKlinikModel();
						} 
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}

			}
		});

		table_klinik = new JTable(klinikModel);
		table_klinik.setComponentPopupMenu(klinikMenu);
		table_klinik.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_klinik.rowAtPoint(point);
				table_klinik.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		w_scroll_klinik.setViewportView(table_klinik);

		JLabel lblNewLabel_1_4 = new JLabel("Klinik Ad\u0131");
		lblNewLabel_1_4.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(253, 6, 77, 27);
		w_klinik.add(lblNewLabel_1_4);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(244, 26, 130, 27);
		w_klinik.add(fld_clinicName);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(384, 10, 210, 349);
		w_klinik.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JButton btn_addKlinik = new JButton("Ekle");
		btn_addKlinik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (klinik.addKlinik(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateKlinikModel();

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_addKlinik.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btn_addKlinik.setBounds(244, 63, 130, 34);
		w_klinik.add(btn_addKlinik);
		
		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(244, 274, 130, 41);
		for(int i = 0; i<bashekim.getDoctorList().size(); i++) {
			
			select_doktor.addItem(new Item( bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
			
		}
		select_doktor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey()+ " : "+ item.getValue());
		});
		w_klinik.add(select_doktor);
		
		JButton btn_addworker = new JButton("Ekle");
		btn_addworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= table_klinik.getSelectedRow();
				if(selRow>=0) {
					String selKlinik =table_klinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikID= Integer.parseInt(selKlinik);
					Item doctorItem = (Item) select_doktor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selKlinikID );
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0 ; i< bashekim.getKlinikDoktorList(selKlinikID).size(); i++){
								workerData[0]=bashekim.getKlinikDoktorList(selKlinikID).get(i).getId();
								workerData[1]=bashekim.getKlinikDoktorList(selKlinikID).get(i).getName();
								workerModel.addRow(workerData);
								
						}
							table_worker.setModel(workerModel);
						}
							else {
							Helper.showMsg(" error ");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				else {
					Helper.showMsg("Lütfen bir klinik seçiniz !");
				}
				
			}	
		});
		btn_addworker.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btn_addworker.setBounds(244, 325, 130, 34);
		w_klinik.add(btn_addworker);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Klinik Ad\u0131");
		lblNewLabel_1_4_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1_4_1.setBounds(253, 161, 77, 27);
		w_klinik.add(lblNewLabel_1_4_1);
		
		JButton btn_workerSelect = new JButton("Se\u00E7");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_klinik.getSelectedRow();
				if(selRow>=0) {
					String selKlinik =table_klinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikID= Integer.parseInt(selKlinik);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0 ; i< bashekim.getKlinikDoktorList(selKlinikID).size(); i++){
							workerData[0]=bashekim.getKlinikDoktorList(selKlinikID).get(i).getId();
							workerData[1]=bashekim.getKlinikDoktorList(selKlinikID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}else {
					Helper.showMsg("Lütfen bir klinik seçiniz");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btn_workerSelect.setBounds(244, 198, 130, 34);
		w_klinik.add(btn_workerSelect);

	}
		
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doktorData[0] = bashekim.getDoctorList().get(i).getId();
			doktorData[1] = bashekim.getDoctorList().get(i).getName();
			doktorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doktorModel.addRow(doktorData);

		}
	}

	public void updateKlinikModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_klinik.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < klinik.getList().size(); i++) {
			klinikData[0] = klinik.getList().get(i).getId();
			klinikData[1] = klinik.getList().get(i).getName();

			klinikModel.addRow(klinikData);

		}

	}	
}



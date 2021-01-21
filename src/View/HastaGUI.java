package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;

import Model.Hasta;
import Model.Klinik;
import Model.Randevu;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Klinik klinik = new Klinik();
	private JTable table_doktor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData = null;
	private JTable tablewhour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoktorID = 0;
	private String selectDoktorName = null;
	private JTable table_rand;
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private Randevu randevu = new Randevu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		doktorModel = new DefaultTableModel();
		Object[] colDoktor = new Object[2];
		colDoktor[0] = "ID";
		colDoktor[1] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktor);
		doktorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		randevuModel = new DefaultTableModel();
		Object[] colRandevu = new Object[3];
		colRandevu[0] = "ID";
		colRandevu[1] = "Doktor";
		colRandevu[2] = "Tarih";
		randevuModel.setColumnIdentifiers(colRandevu);
		randevuData = new Object[3];
		for (int i = 0; i < randevu.getHastaList(hasta.getId()).size(); i++) {

			randevuData[0] = randevu.getHastaList(hasta.getId()).get(i).getId();
			randevuData[1] = randevu.getHastaList(hasta.getId()).get(i).getDoktorName();
			randevuData[2] = randevu.getHastaList(hasta.getId()).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}

		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("HASTANE Y\u00D6NET\u0130M S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 584);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + hasta.getName());
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 0, 421, 59);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btnNewButton.setBounds(10, 49, 156, 41);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 100, 619, 446);
		w_pane.add(w_tab);

		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_randevu, null);
		w_randevu.setLayout(null);

		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 31, 264, 105);
		w_randevu.add(w_scrollDoktor);

		table_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(table_doktor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 10, 134, 23);
		w_randevu.add(lblNewLabel_1);

		JLabel lblNewLabel_1_4 = new JLabel("Klinik Ad\u0131");
		lblNewLabel_1_4.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(10, 186, 134, 27);
		w_randevu.add(lblNewLabel_1_4);

		JComboBox select_klinik = new JComboBox();
		select_klinik.setBounds(10, 211, 163, 34);
		select_klinik.addItem("Poliklinik Seçiniz");
		for (int i = 0; i < klinik.getList().size(); i++) {
			select_klinik.addItem(new Item(klinik.getList().get(i).getId(), klinik.getList().get(i).getName()));
		}
		select_klinik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_klinik.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < klinik.getKlinikDoktorList(item.getKey()).size(); i++) {
							doktorData[0] = klinik.getKlinikDoktorList(item.getKey()).get(i).getId();
							doktorData[1] = klinik.getKlinikDoktorList(item.getKey()).get(i).getName();
							doktorModel.addRow(doktorData);
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
					clearModel.setRowCount(0);
				}

			}

		});

		w_randevu.add(select_klinik);

		JLabel lblNewLabel_2 = new JLabel("Doktor Se\u00E7iniz");
		lblNewLabel_2.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 283, 163, 23);
		w_randevu.add(lblNewLabel_2);

		JButton btn_seldoktor = new JButton("Se\u00E7");
		btn_seldoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doktor.getSelectedRow();
				if (row >= 0) {
					String value = table_doktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tablewhour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					tablewhour.setModel(whourModel);
					selectDoktorID = id;
					selectDoktorName = table_doktor.getModel().getValueAt(row, 1).toString();
					System.out.println(selectDoktorID + "-" + selectDoktorName);
				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz");
				}

			}
		});
		btn_seldoktor.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		btn_seldoktor.setBounds(10, 316, 163, 34);
		w_randevu.add(btn_seldoktor);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(318, 31, 286, 105);
		w_randevu.add(w_scrollWhour);

		tablewhour = new JTable(whourModel);
		w_scrollWhour.setViewportView(tablewhour);
		tablewhour.getColumnModel().getColumn(0).setPreferredWidth(5);

		JButton btn_addRandevu = new JButton("Randevu Al");
		btn_addRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = tablewhour.getSelectedRow();
				if (selRow >= 0) {
					String date = tablewhour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean kontrol = hasta.addRandevu(selectDoktorID, hasta.getId(), selectDoktorName,
								hasta.getName(), date);
						if (kontrol) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoktorID, date);
							updateWhourModel(selectDoktorID);
							updateRandevuModel(hasta.getId());

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz");
				}

			}
		});
		btn_addRandevu.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btn_addRandevu.setBounds(365, 295, 216, 55);
		w_randevu.add(btn_addRandevu);

		JPanel w_rand = new JPanel();
		w_rand.setBackground(Color.WHITE);
		w_tab.addTab("Randevularým", null, w_rand, null);
		w_rand.setLayout(null);

		JScrollPane w_scrollrand = new JScrollPane();
		w_scrollrand.setBounds(10, 10, 594, 351);
		w_rand.add(w_scrollrand);

		table_rand = new JTable(randevuModel);
		w_scrollrand.setViewportView(table_rand);
	}

	public void updateWhourModel(int doktor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tablewhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doktor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doktor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doktor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}

	}

	public void updateRandevuModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_rand.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < randevu.getHastaList(hasta_id).size(); i++) {
			randevuData[0] = randevu.getHastaList(hasta_id).get(i).getId();
			randevuData[1] = randevu.getHastaList(hasta_id).get(i).getDoktorName();
			randevuData[2] = randevu.getHastaList(hasta_id).get(i).getRandevuDate();
			randevuModel.addRow(randevuData);
		}

	}
}

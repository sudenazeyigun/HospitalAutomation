package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doktor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_doktortc;
	private JPasswordField fld_doktorpass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastapass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.login();
		
	}

	/**
	 * Create the frame.
	 * 
	 * 
	 * 
	 */
	
	
	
	public LoginGUI() {
		setResizable(false);
		setTitle("HASTANE Y\u00D6NET\u0130M S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 535);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("unnamed.png")));
		lbl_logo.setBounds(10, 10, 575, 200);
		w_pane.add(lbl_logo);

		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(20, 245, 575, 236);
		w_pane.add(w_tabPane);

		JPanel w_hastalogin = new JPanel();
		w_hastalogin.setBackground(Color.WHITE);
		w_tabPane.addTab("Hasta Giriþi ", null, w_hastalogin, null);
		w_hastalogin.setLayout(null);

		JLabel lbl_hastatc = new JLabel("T.C. Kimlik Numaran\u0131z:");
		lbl_hastatc.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lbl_hastatc.setBounds(10, 35, 257, 40);
		w_hastalogin.add(lbl_hastatc);

		JLabel lbl_hastapass = new JLabel("\u015Eifreniz:");
		lbl_hastapass.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lbl_hastapass.setBounds(305, 35, 108, 40);
		w_hastalogin.add(lbl_hastapass);

		fld_hastatc = new JTextField();
		fld_hastatc.setBounds(20, 85, 231, 29);
		w_hastalogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);

		JButton btn_login = new JButton("Giri\u015F");
		btn_login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (fld_hastatc.getText().length() == 0 || fld_hastapass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
					
						
						while (rs.next()) {
							if (fld_hastatc.getText().equals(rs.getString("tcno"))	&& fld_hastapass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Hasta")) {
									Hasta hasta = new Hasta();								
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key=false;
								}
								
								}

							}

						}
					 catch (SQLException e1) {

						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadý.Lüyfen kayýt olunuz.");
					}
				}

			}
		});
		btn_login.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btn_login.setBounds(128, 157, 113, 35);
		w_hastalogin.add(btn_login);

		JButton btn_hastaregister = new JButton("Kay\u0131t Ol");
		btn_hastaregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_hastaregister.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btn_hastaregister.setBounds(305, 156, 108, 37);
		w_hastalogin.add(btn_hastaregister);

		fld_hastapass = new JPasswordField();
		fld_hastapass.setBounds(305, 85, 231, 29);
		w_hastalogin.add(fld_hastapass);

		JPanel w_doktorlogin = new JPanel();
		w_doktorlogin.setBackground(Color.WHITE);
		w_tabPane.addTab("Doktor Giriþi", null, w_doktorlogin, null);
		w_doktorlogin.setLayout(null);

		JLabel lbl_hastatc_1 = new JLabel("T.C. Kimlik Numaran\u0131z:");
		lbl_hastatc_1.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lbl_hastatc_1.setBounds(10, 35, 257, 40);
		w_doktorlogin.add(lbl_hastatc_1);

		JLabel lbl_hastapass_1 = new JLabel("\u015Eifreniz:");
		lbl_hastapass_1.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lbl_hastapass_1.setBounds(305, 35, 108, 40);
		w_doktorlogin.add(lbl_hastapass_1);

		fld_doktortc = new JTextField();
		fld_doktortc.setColumns(10);
		fld_doktortc.setBounds(20, 85, 231, 29);
		w_doktorlogin.add(fld_doktortc);

		JButton btn_doktorlogin = new JButton("Giri\u015F");
		btn_doktorlogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (fld_doktortc.getText().length() == 0 || fld_doktorpass.getText().length() == 0) {

					Helper.showMsg("fill");

				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
						while (rs.next()) {
							if (fld_doktortc.getText().equals(rs.getString("tcno"))&& fld_doktorpass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("Doktor")) {
									Doktor doktor = new Doktor();
									doktor.setId(rs.getInt("id"));
									doktor.setPassword("password");
									doktor.setTcno(rs.getString("tcno"));
									doktor.setName(rs.getString("name"));
									doktor.setType(rs.getString("type"));
									DoktorGUI dGUI = new DoktorGUI(doktor);
									dGUI.setVisible(true);
									dispose();
								}

							}

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorlogin.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btn_doktorlogin.setBounds(210, 152, 113, 35);
		w_doktorlogin.add(btn_doktorlogin);

		fld_doktorpass = new JPasswordField();
		fld_doktorpass.setBounds(305, 85, 231, 29);
		w_doktorlogin.add(fld_doktorpass);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("Ekran görüntüsü 2020-12-29 010220.png")));
		lblNewLabel.setBounds(335, 198, 242, 40);
		w_pane.add(lblNewLabel);
	}
}

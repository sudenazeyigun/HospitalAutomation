package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Klinik;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class UpdateKlinikGUI extends JFrame {

	
	private JPanel contentPane;
	private JTextField fld_klinikName;
	private static Klinik klinik = new Klinik();

	
	public static void main(String[] args) {
		 
				try {
					UpdateKlinikGUI frame = new UpdateKlinikGUI(klinik);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	


	public UpdateKlinikGUI(Klinik klinik) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 153);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Klinik Ad\u0131");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		lblNewLabel.setBounds(36, 10, 161, 28);
		contentPane.add(lblNewLabel);
		
		fld_klinikName = new JTextField();
		
		fld_klinikName.setColumns(10);
		fld_klinikName.setBounds(36, 33, 155, 33);
        fld_klinikName.setText(klinik.getName());

		contentPane.add(fld_klinikName);
		
		JButton btn_updateKlinik = new JButton("D\u00FCzenle");
		btn_updateKlinik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						klinik.updateKlinik(klinik.getId(), fld_klinikName.getText() );
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
		
			}
				
				
				
				
			});
		
		
		btn_updateKlinik.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		btn_updateKlinik.setBounds(36, 76, 155, 30);
		contentPane.add(btn_updateKlinik);
	}
}

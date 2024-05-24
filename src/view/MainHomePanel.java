package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.Constants;

@SuppressWarnings("serial")
public class MainHomePanel extends JPanel {
	public MainHomePanel(MainFrame mainFrame) {
		setBackground(Color.white);
		setPreferredSize(Constants.MAIN_SIZE);
		setLayout(null);

		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblBemVindo.setBounds(21, 11, 1045, 41);
		add(lblBemVindo);
	}
}
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginController;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;

	public LoginFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 544, 401);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(150, 82, 46, 14);
		panel.add(lblNewLabel);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(150, 107, 244, 23);
		panel.add(textFieldUser);
		textFieldUser.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(150, 139, 46, 14);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Entrar");

		// Acão do botão
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (textFieldUser.getText() == null || textFieldUser.getText().isEmpty()
						|| passwordField.getText() == null || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique o Usuário e Senha", "Avisos",
							JOptionPane.WARNING_MESSAGE);

				} else {

					ArrayList<String> validationResult = LoginController.validateLogin(textFieldUser.getText(),
							passwordField.getText());
					if (validationResult.get(0) == "Error") {
						JOptionPane.showMessageDialog(btnNewButton, validationResult.get(1), "Avisos",
								JOptionPane.WARNING_MESSAGE);
					} else {
						System.out.println(validationResult.get(1));
						MainFrame mainScreen = new MainFrame();
						dispose();
						mainScreen.setLocationRelativeTo(mainScreen);
						mainScreen.setVisible(true);
					}

				}
			}
		});
		btnNewButton.setBounds(150, 229, 244, 34);
		panel.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Bem Vindo");
		lblNewLabel_2.setBounds(225, 35, 73, 23);
		panel.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 164, 244, 20);
		panel.add(passwordField);
	}
}

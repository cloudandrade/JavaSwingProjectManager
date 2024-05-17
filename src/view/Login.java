package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Crypto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		//Acão do botão 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crypto crypto = new Crypto(passwordField.getText(), Crypto.MD5);
				System.out.println(crypto.toString());
				if(textFieldUser.getText() != null && textFieldUser.getText().isEmpty() && passwordField.getText() != null && passwordField.getText().isEmpty()) {
					
					User userScreen = new User();
					dispose();
					userScreen.setLocationRelativeTo(userScreen);
					userScreen.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique o Usuário e Senha", "Avisos", JOptionPane.WARNING_MESSAGE);
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

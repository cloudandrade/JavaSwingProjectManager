package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {
	
	 private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JTextField textFieldUser;
	    private JPasswordField passwordField;

	    public LoginFrame() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 560, 440);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(135, 206, 250));
	        panel.setBounds(0, 0, 544, 401);
	        contentPane.add(panel);
	        panel.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("Usuário:");
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
	        lblNewLabel.setBounds(276, 210, 66, 14);
	        panel.add(lblNewLabel);
	        
	        textFieldUser = new JTextField();
	        textFieldUser.setBounds(276, 227, 244, 25);
	        panel.add(textFieldUser);
	        textFieldUser.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("Senha:");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	        lblNewLabel_1.setBounds(276, 255, 66, 14);
	        panel.add(lblNewLabel_1);
	        
	        JButton btnNewButton = new JButton("Entrar");
	        btnNewButton.setForeground(new Color(240, 255, 255));
	        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
	        btnNewButton.setBackground(new Color(153, 50, 204));
	        btnNewButton.setBounds(276, 316, 244, 45);
	        panel.add(btnNewButton);
	        
	        JLabel lblNewLabel_2 = new JLabel("LOGIN");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 32));
	        lblNewLabel_2.setBounds(338, 72, 117, 34);
	        panel.add(lblNewLabel_2);
	        
	        passwordField = new JPasswordField();
	        passwordField.setBounds(276, 273, 244, 25);
	        panel.add(passwordField);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBounds(260, 0, -260, 400);
	        panel.add(panel_1);
	        
	        JLabel lblImageGirl = new JLabel("");
	        lblImageGirl.setBounds(20, 93, 215, 215);
	        panel.add(lblImageGirl);
	        
	        JLabel lblImageFluid = new JLabel("");
            lblImageFluid.setBounds(295, 23, 200, 150);
            panel.add(lblImageFluid);

	        // Carregar a imagem a partir dos recursos
	        try {
	            Image originalImage = ImageIO.read(new File("src/resources/minimalistic_girl.png"));
	            // Redimensionar a imagem para caber no JLabel
	            Image scaledImage = originalImage.getScaledInstance(lblImageGirl.getWidth(), lblImageGirl.getHeight(), Image.SCALE_SMOOTH);
	            ImageIcon scaledIcon = new ImageIcon(scaledImage);
	            // Definir a imagem redimensionada como ícone do JLabel
	            lblImageGirl.setIcon(scaledIcon);
	            
	            Image originalImage2 = ImageIO.read(new File("src/resources/fluid.png"));
	            // Redimensionar a imagem para caber no JLabel
	            Image scaledImage2 = originalImage2.getScaledInstance(lblImageFluid.getWidth(), lblImageFluid.getHeight(), Image.SCALE_SMOOTH);
	            ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
	            // Definir a imagem redimensionada como ícone do JLabel
	            lblImageFluid.setIcon(scaledIcon2);
	        } catch (IOException e) {
	            e.printStackTrace();
	            lblImageGirl.setText("Imagens não encontradas");
	            lblImageFluid.setText("Imagens não encontradas");
	        }

	        // Adiciona um listener de teclado ao campo de senha
	        passwordField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    // Aciona a ação do botão
	                    btnNewButton.doClick();
	                }
	            }
	        });

	        // Ação do botão
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if(textFieldUser.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
	                    JOptionPane.showMessageDialog(btnNewButton, "Verifique o Usuário e Senha", "Avisos", JOptionPane.WARNING_MESSAGE);
	                } else {
	                    ArrayList<String> validationResult = LoginController.validateLogin(textFieldUser.getText(), new String(passwordField.getPassword()));
	                    if(validationResult.get(0).equals("Error")) {
	                        JOptionPane.showMessageDialog(btnNewButton, validationResult.get(1), "Avisos", JOptionPane.WARNING_MESSAGE);
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
	    }

	    public static void main(String[] args) {
	        LoginFrame frame = new LoginFrame();
	        frame.setVisible(true);
	    }
}
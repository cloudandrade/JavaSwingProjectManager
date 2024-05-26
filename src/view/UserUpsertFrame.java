package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import application.Constants;
import controller.UserController;
import model.UserModel;
import java.awt.Dimension;

public class UserUpsertFrame extends JFrame {
    private JTextField nameField;
    private JTextField userField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JButton saveButton;

    private UserModel user;
    private JLabel label;
    private MainUserPanel mainUserPanel;

    public UserUpsertFrame(UserModel user, MainUserPanel mainUserPanel) {
        this.user = user;
        this.mainUserPanel = mainUserPanel;
        initialize();
        if (user != null) {
            populateFields();
            setTitle("Editar Usuário");

        } else {
            setTitle("Cadastrar Usuário");

        }
    }

    private void initialize() {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.white);
		setPreferredSize(new Dimension(500, 600));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel titleLabel = new JLabel(user == null ? "Cadastrar Usuário" : "Editar Usuário", SwingConstants.CENTER);
        titleLabel.setBounds(0, 23, 484, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);
        
        label = new JLabel("");
        label.setBounds(0, 23, 484, 18);
        panel.add(label);
        
        
        JLabel label_13 = new JLabel("Nome:");
        label_13.setBounds(22, 70, 440, 18);
        panel.add(label_13);
        nameField = new JTextField();
        nameField.setBounds(22, 95, 440, 18);
        panel.add(nameField);

        JLabel label_14 = new JLabel("Usuário:");
        label_14.setBounds(22, 124, 440, 18);
        panel.add(label_14);
        userField = new JTextField();
        userField.setBounds(22, 142, 440, 18);
        panel.add(userField);

        JLabel label_15 = new JLabel("Email:");
        label_15.setBounds(22, 167, 440, 18);
        panel.add(label_15);
        emailField = new JTextField();
        emailField.setBounds(22, 185, 440, 18);
        panel.add(emailField);

        JLabel label_16 = new JLabel("Telefone:");
        label_16.setBounds(22, 214, 440, 18);
        panel.add(label_16);
        phoneField = new JTextField();
        phoneField.setBounds(22, 232, 440, 18);
        panel.add(phoneField);

        JLabel label_17 = new JLabel("Senha:");
        label_17.setBounds(22, 268, 440, 18);
        panel.add(label_17);
        passwordField = new JPasswordField();
        passwordField.setBounds(22, 286, 440, 18);
        panel.add(passwordField);

        saveButton = new JButton("Salvar");
        saveButton.setBackground(new Color(135, 206, 250));
        saveButton.setBounds(22, 335, 440, 36);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        panel.add(saveButton);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void populateFields() {
        nameField.setText(user.getName());
        userField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        passwordField.setText(user.getPassword());
    }

    private void saveUser() {
        String nameValue = nameField.getText();
        String usernameValue = userField.getText();
        String emailValue = emailField.getText();
        String phoneValue = phoneField.getText();
        String passwordValue = new String(passwordField.getText());
        
        
        if(nameField.getText() == null || nameField.getText().isBlank()) {
        	JOptionPane.showMessageDialog(this, "O campo nome é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
        }
        
        if(userField.getText() == null || userField.getText().isBlank()) {
        	JOptionPane.showMessageDialog(this, "O campo nome de usuário é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
        }
        
        if(emailField.getText() == null || emailField.getText().isBlank()) {
        	JOptionPane.showMessageDialog(this, "O campo email é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
        }

        if(phoneField.getText() == null || phoneField.getText().isBlank()) {
        	JOptionPane.showMessageDialog(this, "O campo telefone é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
        }
        
        if(passwordField.getText() == null || passwordField.getText().isBlank()) {
        	JOptionPane.showMessageDialog(this, "O campo senha é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
        }

        if (user == null) {
            user = new UserModel( nameValue, usernameValue, emailValue, phoneValue, passwordValue);
            ArrayList<String> result = UserController.addUser(user);
            if(result.get(0) == "Error") {
            	JOptionPane.showMessageDialog(this, result.get(1));
            } else {
            	JOptionPane.showMessageDialog(this, result.get(1));
            	dispose();
            	mainUserPanel.refreshTable(null);
            }

        } else {
            user.setName(nameValue);
            user.setUsername(usernameValue);
            user.setEmail(emailValue);
            user.setPhone(phoneValue);
            user.setPassword(passwordValue);
            
            ArrayList<String> result = UserController.updateUser(user);
            if(result.get(0) == "Error") {
            	JOptionPane.showMessageDialog(this, result.get(1));
            } else {
            	JOptionPane.showMessageDialog(this, result.get(1));
            	dispose();
            	mainUserPanel.refreshTable(null);
            }
        }
    }

}
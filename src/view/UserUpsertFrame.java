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

public class UserUpsertFrame extends JFrame {
    private JTextField nameField;
    private JTextField userField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JButton saveButton;

    private UserModel user;
    private JLabel label;

    public UserUpsertFrame(UserModel user) {
        this.user = user;
        initialize();
        if (user != null) {
            populateFields();
            setTitle("Editar Usuário");
          
        } else {
            setTitle("Cadastrar Usuário");

        }
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);
		setPreferredSize(Constants.SMALL_FRAME_SIZE);
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
        userField.setText(user.getUser());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        passwordField.setText(user.getPassword());
    }

    private void saveUser() {
        String nameValue = nameField.getText();
        String userValue = userField.getText();
        String emailValue = emailField.getText();
        String phoneValue = phoneField.getText();
        String passwordValue = new String(passwordField.getPassword());

        if (user == null) {
            user = new UserModel(userValue, userValue, emailValue, phoneValue, passwordValue);
            ArrayList<String> result = UserController.addUser(user);
            if(result.get(0) == "Error") {
            	JOptionPane.showMessageDialog(this, result.get(1));
            } else {
            	JOptionPane.showMessageDialog(this, result.get(1));
            	dispose();
            }

        } else {
            user.setName(nameValue);
            user.setUser(userValue);
            user.setEmail(emailValue);
            user.setPhone(phoneValue);
            user.setPassword(passwordValue);
            
            ArrayList<String> result = UserController.updateUser(user);
            if(result.get(0) == "Error") {
            	JOptionPane.showMessageDialog(this, result.get(1));
            } else {
            	JOptionPane.showMessageDialog(this, result.get(1));
            	dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserUpsertFrame(null).setVisible(true);
            }
        });
    }
}
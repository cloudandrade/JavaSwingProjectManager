package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import application.Constants;
import controller.ProjectController;
import controller.UserController;
import model.ProjectModel;

public class ProjectUpsertFrame extends JFrame {
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField ownerIdField;
    private JTextField ownerNameField;
    private JTextField ownerPhoneField;
    private JTextField odsIdField;
    private JTextField odsNameField;
    private JTextField createdAtField;
    private JTextField statusField;
    private JButton saveButton;

    private ProjectModel project;

public ProjectUpsertFrame(ProjectModel project) {
    this.project = project;
    initialize();
    if (project != null) {
        populateFields();
        setTitle("Editar Projeto");
    } else {
        setTitle("Cadastrar Projeto");
    }
}

private void initialize() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.white);
    setPreferredSize(Constants.SMALL_FRAME__EXTENDED_SIZE);
    setLocationRelativeTo(null);
    getContentPane().setLayout(new BorderLayout());

    JPanel panel = new JPanel();
    panel.setLayout(null);

    JLabel titleLabel = new JLabel(project == null ? "Cadastrar Projeto" : "Editar Projeto", SwingConstants.CENTER);
    titleLabel.setBounds(0, 23, 484, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    panel.add(titleLabel);

    JLabel titleFieldLabel = new JLabel("Título:");
    titleFieldLabel.setBounds(22, 70, 440, 18);
    panel.add(titleFieldLabel);
    titleField = new JTextField();
    titleField.setBounds(22, 95, 440, 18);
    panel.add(titleField);

    JLabel descriptionFieldLabel = new JLabel("Descrição:");
    descriptionFieldLabel.setBounds(22, 124, 440, 18);
    panel.add(descriptionFieldLabel);
    descriptionField = new JTextField();
    descriptionField.setBounds(22, 142, 440, 18);
    panel.add(descriptionField);

    JLabel ownerIdFieldLabel = new JLabel("ID do Proprietário:");
    ownerIdFieldLabel.setBounds(22, 167, 440, 18);
    panel.add(ownerIdFieldLabel);
    ownerIdField = new JTextField();
    ownerIdField.setBounds(22, 185, 440, 18);
    panel.add(ownerIdField);

    JLabel ownerNameFieldLabel = new JLabel("Nome do Proprietário:");
    ownerNameFieldLabel.setBounds(22, 214, 440, 18);
    panel.add(ownerNameFieldLabel);
    ownerNameField = new JTextField();
    ownerNameField.setBounds(22, 232, 440, 18);
    panel.add(ownerNameField);

    JLabel ownerPhoneFieldLabel = new JLabel("Telefone do Proprietário:");
    ownerPhoneFieldLabel.setBounds(22, 268, 440, 18);
    panel.add(ownerPhoneFieldLabel);
    ownerPhoneField = new JTextField();
    ownerPhoneField.setBounds(22, 286, 440, 18);
    panel.add(ownerPhoneField);

    JLabel odsIdFieldLabel = new JLabel("ID da ODS:");
    odsIdFieldLabel.setBounds(22, 314, 440, 18);
    panel.add(odsIdFieldLabel);
    odsIdField = new JTextField();
    odsIdField.setBounds(22, 332, 440, 18);
    panel.add(odsIdField);

    JLabel odsNameFieldLabel = new JLabel("Nome da ODS:");
    odsNameFieldLabel.setBounds(22, 360, 440, 18);
    panel.add(odsNameFieldLabel);
    odsNameField = new JTextField();
    odsNameField.setBounds(22, 378, 440, 18);
    panel.add(odsNameField);

    JLabel createdAtFieldLabel = new JLabel("Data de Criação:");
    createdAtFieldLabel.setBounds(22, 406, 440, 18);
    panel.add(createdAtFieldLabel);
    createdAtField = new JTextField();
    createdAtField.setBounds(22, 424, 440, 18);
    panel.add(createdAtField);

    JLabel statusFieldLabel = new JLabel("Status:");
    statusFieldLabel.setBounds(22, 452, 440, 18);
    panel.add(statusFieldLabel);
    statusField = new JTextField();
    statusField.setBounds(22, 470, 440, 18);
    panel.add(statusField);

    saveButton = new JButton("Salvar");
    saveButton.setBounds(22, 510, 440, 36);
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveProject();
        }
    });
    panel.add(saveButton);

    getContentPane().add(panel, BorderLayout.CENTER);
    pack();
}

private void populateFields() {
    titleField.setText(project.getTitle());
    descriptionField.setText(project.getDescription());
    ownerIdField.setText(String.valueOf(project.getOwnerId()));
    ownerNameField.setText(project.getOwnerName());
    ownerPhoneField.setText(project.getOwnerPhone());
    odsIdField.setText(String.valueOf(project.getOdsId()));
    odsNameField.setText(project.getOdsName());
    createdAtField.setText(project.getCreatedAt().toString());
    statusField.setText(project.getStatus());
}

private void saveProject() {
    String titleValue = titleField.getText();
    String descriptionValue = descriptionField.getText();
    int ownerIdValue = Integer.parseInt(ownerIdField.getText());
    String ownerNameValue = ownerNameField.getText();
    String ownerPhoneValue = ownerPhoneField.getText();
    int odsIdValue = Integer.parseInt(odsIdField.getText());
    String odsNameValue = odsNameField.getText();
    Timestamp createdAtValue = Timestamp.valueOf(createdAtField.getText());
    String statusValue = statusField.getText();

    if (project == null) {
        project = new ProjectModel(0, titleValue, descriptionValue, ownerIdValue, ownerNameValue, ownerPhoneValue, odsIdValue, odsNameValue, createdAtValue, statusValue);
        ArrayList<String> result = ProjectController.addProject(project);
        if(result.get(0) == "Error") {
        	JOptionPane.showMessageDialog(this, result.get(1));
        } else {
        	JOptionPane.showMessageDialog(this, result.get(1));
        	dispose();
        }

    } else {
        project.setTitle(titleValue);
        project.setDescription(descriptionValue);
        project.setOwnerId(ownerIdValue);
        project.setOwnerName(ownerNameValue);
        project.setOwnerPhone(ownerPhoneValue);
        project.setOdsId(odsIdValue);
        project.setOdsName(odsNameValue);
        project.setCreatedAt(createdAtValue);
        project.setStatus(statusValue);
        
        ArrayList<String> result = ProjectController.updateProject(project);
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
            new ProjectUpsertFrame(null).setVisible(true);
        }
    });
}
}

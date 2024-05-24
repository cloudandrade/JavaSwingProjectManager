package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
    private JButton saveButton;

    private ProjectModel project;
    private MainProjectPanel mainProjectPanel;

    public ProjectUpsertFrame(ProjectModel project, MainProjectPanel mainProjectPanel) {
        this.project = project;
        this.mainProjectPanel = mainProjectPanel;
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
}

private void saveProject() {
    String titleValue = titleField.getText();
    String descriptionValue = descriptionField.getText();
    int ownerIdValue = Integer.parseInt(ownerIdField.getText());
    String ownerNameValue = ownerNameField.getText();
    String ownerPhoneValue = ownerPhoneField.getText();
    int odsIdValue = Integer.parseInt(odsIdField.getText());
    
    Date date = new Date();
    Timestamp createdAtValue = new Timestamp(date.getTime());
    
    String statusValue = Constants.PROJECT_STATUS_CREATED;

    if (project == null) {
        project = new ProjectModel(titleValue, descriptionValue, ownerIdValue, ownerNameValue, ownerPhoneValue, odsIdValue,  createdAtValue, statusValue);
        ArrayList<String> result = ProjectController.addProject(project);
        if(result.get(0) == "Error") {
        	JOptionPane.showMessageDialog(this, result.get(1));
        } else {
        	JOptionPane.showMessageDialog(this, result.get(1));
        	dispose();
        	mainProjectPanel.refreshTable();
        }

    } else {
        project.setTitle(titleValue);
        project.setDescription(descriptionValue);
        project.setOwnerId(ownerIdValue);
        project.setOwnerName(ownerNameValue);
        project.setOwnerPhone(ownerPhoneValue);
        project.setOdsId(odsIdValue);
        
        ArrayList<String> result = ProjectController.updateProject(project);
        if(result.get(0) == "Error") {
        	JOptionPane.showMessageDialog(this, result.get(1));
        } else {
        	JOptionPane.showMessageDialog(this, result.get(1));
        	dispose();
        	mainProjectPanel.refreshTable();
        }
    }
}

}

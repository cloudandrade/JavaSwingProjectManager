package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import application.Constants;
import controller.ProjectController;
import dao.ODSDAO;
import model.ODSModel;
import model.ProjectModel;
import model.UserModel;
import util.CacheUtils;


public class ProjectUpsertFrame extends JFrame {
    private JTextField titleField;
    private JButton saveButton;
    private JComboBox odsComboBox;
    private JTextArea descriptionField;

    private ProjectModel project;
    private MainProjectPanel mainProjectPanel;
    ArrayList<ODSModel> odsList;

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
	ODSDAO odsDao = new ODSDAO();
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

    JLabel odsIdFieldLabel = new JLabel("ODS:");
    odsIdFieldLabel.setBounds(22, 377, 440, 18);
    panel.add(odsIdFieldLabel);

    saveButton = new JButton("Salvar");
    saveButton.setBackground(new Color(135, 206, 250));
    saveButton.setBounds(22, 510, 440, 36);
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveProject();
        }
    });
    panel.add(saveButton);

    getContentPane().add(panel, BorderLayout.CENTER);
    
    odsList = odsDao.list();
    odsComboBox = new JComboBox();
    odsComboBox.setBounds(22, 406, 440, 22);
    for (ODSModel ods : odsList) {
        odsComboBox.addItem(ods);
    }
    
    odsComboBox.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof ODSModel) {
                setText(((ODSModel) value).getTitle());
            }
            return this;
        }
    });
    
    panel.add(odsComboBox);
    
    descriptionField = new JTextArea();
    descriptionField.setBounds(22, 148, 440, 218);
    panel.add(descriptionField);
    
    
    pack();
}

private void populateFields() {
    titleField.setText(project.getTitle());
    descriptionField.setText(project.getDescription());
    
    setSelectedODS(odsList, project.getOdsId());
}

private void saveProject() {
    String titleValue = titleField.getText();
    String descriptionValue = descriptionField.getText();
    
    if( titleField.getText() == null || titleField.getText().isBlank()) {
    	JOptionPane.showMessageDialog(this, "O campo titulo é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    if(descriptionField.getText() == null || descriptionField.getText().isBlank()) {
    	JOptionPane.showMessageDialog(this, "O campo descrição é obrigatório!","Aviso", JOptionPane.WARNING_MESSAGE);
    }
    

    
    
    UserModel loggedUser = CacheUtils.recoverUserOnLocalStorage();
    
    ODSModel selectedODS = (ODSModel) odsComboBox.getSelectedItem();
    
    Date date = new Date();
    Timestamp createdAtValue = new Timestamp(date.getTime());
    
    String statusValue = Constants.PROJECT_STATUS_CREATED;

    if (project == null) {
        project = new ProjectModel(titleValue, descriptionValue, loggedUser.getId(), loggedUser.getName(), loggedUser.getPhone(), selectedODS.getId(),  createdAtValue, statusValue);
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
        project.setOdsId(selectedODS.getId());
        
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

// Método para definir o ODS selecionado com base no ID
private void setSelectedODS(ArrayList<ODSModel> odsList, int odsId) {
    for (ODSModel ods : odsList) {
        if (ods.getId() == odsId) {
            odsComboBox.setSelectedItem(ods);
            break;
        }
    }
}
}

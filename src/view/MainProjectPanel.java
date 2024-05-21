package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.Constants;
import dao.ProjectDAO;
import dao.UserDAO;
import model.UserTableModel;
import model.ProjectModel;
import model.ProjectTableModel;
import model.UserModel;
import javax.swing.SwingConstants;

public class MainProjectPanel extends JPanel {
	
private static final long serialVersionUID = 1L;
private JTextField textField;
private JTable table;
private ArrayList<ProjectModel> projects;
private static ProjectDAO projectDao;


public MainProjectPanel(MainFrame mainFrame) {
	projectDao = new ProjectDAO();
	projects = new ArrayList<>();

	projects =	projectDao.list();
	System.out.println("projects: "+ projects);
	ProjectTableModel tableModel = new ProjectTableModel(projects);
	
	setBackground(Color.white);
	setPreferredSize(Constants.MAIN_SIZE);
	
	setBorder(new EmptyBorder(5, 5, 5, 5));
	setLayout(null);
	
	JButton btnNewButton = new JButton("Cadastrar");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnNewButton.setBounds(21, 161, 101, 23);
	add(btnNewButton);
	
	textField = new JTextField();
	textField.setBounds(458, 161, 479, 22);
	add(textField);
	
	textField.setColumns(10);
	JButton btnNewButton_1 = new JButton("Pesquisar");
	btnNewButton_1.setBounds(947, 161, 119, 23);
	
	add(btnNewButton_1);
	
	JLabel lblNewLabel = new JLabel("Pesquisar:");
	lblNewLabel.setBounds(367, 165, 81, 14);
	add(lblNewLabel);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(21, 215, 1045, 368);
	add(scrollPane);
	
	
	table = new JTable();
	table.setModel(tableModel);
	scrollPane.setViewportView(table);
	
	JLabel lblProjects = new JLabel("Projetos");
	lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
	lblProjects.setFont(new Font("Tahoma", Font.BOLD, 28));
	lblProjects.setBounds(21, 11, 1045, 41);
	add(lblProjects);

}
}
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
import javax.swing.JOptionPane;
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
        projects = projectDao.list();
        ProjectTableModel tableModel = new ProjectTableModel(projects);

        setBackground(Color.white);
        setPreferredSize(Constants.MAIN_SIZE);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBackground(Color.GREEN);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProjectUpsertFrame(null); // Abrir ProjectUpsertFrame para adicionar novo projeto
            }
        });
        btnNewButton.setBounds(21, 161, 101, 23);
        add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(458, 161, 479, 22);
        add(textField);
        textField.setColumns(10);

        JButton btnNewButton_1 = new JButton("Pesquisar");
        btnNewButton_1.setBackground(Color.CYAN);
        btnNewButton_1.setBounds(947, 161, 119, 23);
        add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("Pesquisar:");
        lblNewLabel.setBounds(367, 165, 81, 14);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 249, 1045, 368);
        add(scrollPane);

        table = new JTable();
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        JLabel lblProjects = new JLabel("Projetos");
        lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjects.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblProjects.setBounds(21, 11, 1045, 41);
        add(lblProjects);

        JButton btnDeleteProject = new JButton("Excluir");
        btnDeleteProject.setBackground(new Color(128, 0, 0));
        btnDeleteProject.setBounds(947, 215, 119, 23);
        add(btnDeleteProject);
        btnDeleteProject.setEnabled(false); // Desabilita o botão Excluir inicialmente

        JButton btnEditProject = new JButton("Editar");
        btnEditProject.setBackground(Color.YELLOW);
        btnEditProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    ProjectModel selectedProject = projects.get(selectedRow);
                    openProjectUpsertFrame(selectedProject); // Abrir ProjectUpsertFrame para editar projeto selecionado
                } else {
                    JOptionPane.showMessageDialog(btnEditProject, "Por favor, selecione um projeto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnEditProject.setBounds(818, 215, 119, 23);
        add(btnEditProject);
        btnEditProject.setEnabled(false); // Desabilita o botão Editar inicialmente
    }

    private void openProjectUpsertFrame(ProjectModel project) {
        ProjectUpsertFrame upsertFrame = new ProjectUpsertFrame(project, this);
        upsertFrame.pack();
        upsertFrame.setLocationRelativeTo(null);
        upsertFrame.setVisible(true);
    }

    public void refreshTable() {
        projects = projectDao.list();
        ProjectTableModel tableModel = new ProjectTableModel(projects);
        table.setModel(tableModel);
    }
}
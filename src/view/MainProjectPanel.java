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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    private JTextField textFieldSearch;
    private JTable table;
    private ArrayList<ProjectModel> projects;
    private static ProjectDAO projectDao;
    private ProjectModel selectedProject;
    private ProjectTableModel tableModel;

    public MainProjectPanel(MainFrame mainFrame) {
        projectDao = new ProjectDAO();
        projects = new ArrayList<>();
        projects = projectDao.list();
        tableModel = new ProjectTableModel(projects);

        setBackground(new Color(255, 255, 240));
        setPreferredSize(Constants.MAIN_SIZE);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBackground(new Color(135, 206, 250));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProjectUpsertFrame(null); // Abrir ProjectUpsertFrame para adicionar novo projeto
            }
        });
        btnNewButton.setBounds(21, 161, 101, 23);
        add(btnNewButton);

        textFieldSearch = new JTextField();
        textFieldSearch.setBounds(458, 161, 479, 22);
        add(textFieldSearch);
        textFieldSearch.setColumns(10);

        JButton btnSearch = new JButton("Pesquisar");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String search = textFieldSearch.getText();
        		projects = projectDao.findByTitle(search);
        		refreshTable(projects);
        	}
        });
        btnSearch.setBackground(Color.LIGHT_GRAY);
        btnSearch.setBounds(947, 161, 119, 23);
        add(btnSearch);

        JLabel lblNewLabel = new JLabel("Pesquisar:");
        lblNewLabel.setBounds(367, 165, 81, 14);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 249, 1045, 368);
        add(scrollPane);
        
        table = new JTable();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        JLabel lblProjects = new JLabel("Projetos");
        lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjects.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblProjects.setBounds(21, 11, 1045, 41);
        add(lblProjects);

        JButton btnDeleteProject = new JButton("Excluir");
        btnDeleteProject.setForeground(Color.BLACK);
        btnDeleteProject.setEnabled(false);
        

        btnDeleteProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	ProjectModel selectedProject = projects.get(selectedRow);
                	projectDao.delete(selectedProject.getId());
                    refreshTable(null);
                } else {
                    JOptionPane.showMessageDialog(btnDeleteProject, "Por favor, selecione um projeto para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnDeleteProject.setBounds(947, 215, 119, 23);
        add(btnDeleteProject);

        JButton btnEditProject = new JButton("Editar");
        btnEditProject.setForeground(Color.BLACK);
        btnEditProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                     selectedProject = projects.get(selectedRow);
                    openProjectUpsertFrame(selectedProject); // Abrir ProjectUpsertFrame para editar projeto selecionado
                } else {
                    JOptionPane.showMessageDialog(btnEditProject, "Por favor, selecione um projeto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnEditProject.setBounds(818, 215, 119, 23);
        add(btnEditProject);
        btnEditProject.setEnabled(false); // Desabilita o botão Editar inicialmente
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (table.getSelectedRow() != -1) {
                        int selectedRowIndex = table.getSelectedRow();
                        selectedProject = projects.get(selectedRowIndex);
                        btnEditProject.setEnabled(true);
                        btnEditProject.setBackground(Color.YELLOW);
                        btnDeleteProject.setEnabled(true);
                        btnDeleteProject.setBackground(new Color(128, 0, 0));
                    } else {
                    	btnEditProject.setEnabled(false);
                    	btnDeleteProject.setEnabled(false);
                    }
                }
            }
        });
    }

    private void openProjectUpsertFrame(ProjectModel project) {
        ProjectUpsertFrame upsertFrame = new ProjectUpsertFrame(project, this);
        upsertFrame.pack();
        upsertFrame.setLocationRelativeTo(null);
        upsertFrame.setVisible(true);
    }

    public void refreshTable(ArrayList<ProjectModel> projectsList) {
    	if(projectsList != null) {
            tableModel.setProjects(projectsList);
        } else {
            tableModel.setProjects(projectDao.list());
        }
    	tableModel.fireTableDataChanged();
    }
}
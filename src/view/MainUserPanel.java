package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Constants;
import dao.UserDAO;
import model.UserModel;
import model.UserTableModel;
import javax.swing.SwingConstants;

public class MainUserPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldSearch;
    private JTable table;
    private ArrayList<UserModel> users;
    private static UserDAO userDao;
    private JButton btnEditUser;
    private JButton btnDeleteUser;
    private UserModel selectedUser;
    private UserTableModel tableModel;

    public MainUserPanel(MainFrame mainFrame) {
        userDao = new UserDAO();
        users = new ArrayList<>();

        users = userDao.list();
        tableModel = new UserTableModel(users);

        setBackground(new Color(255, 255, 240));
        setPreferredSize(Constants.MAIN_SIZE);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBackground(new Color(135, 206, 250));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openUserUpsertFrame(null);
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
        		users = userDao.findByName(search);
        		System.out.println("searched: " + search);
        		System.out.println("returned: " + users.size() + " length");
                refreshTable(users);
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

        JLabel lblUsers = new JLabel("Usuários");
        lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsers.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblUsers.setBounds(21, 11, 1045, 41);
        add(lblUsers);

        btnEditUser = new JButton("Editar");
        btnEditUser.setForeground(Color.BLACK);
        btnEditUser.setEnabled(false);
        
        btnEditUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUser != null) {
                	openUserUpsertFrame(selectedUser);
                }
            }
        });
        btnEditUser.setBounds(818, 215, 119, 23);
        add(btnEditUser);

        btnDeleteUser = new JButton("Excluir");
        btnDeleteUser.setForeground(Color.BLACK);
        btnDeleteUser.setEnabled(false);
        
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUser != null) {
                    userDao.delete(selectedUser.getId());
                    refreshTable(null);
                }
            }
        });
        btnDeleteUser.setBounds(947, 215, 119, 23);
        add(btnDeleteUser);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (table.getSelectedRow() != -1) {
                        int selectedRowIndex = table.getSelectedRow();
                        selectedUser = users.get(selectedRowIndex);
                        btnEditUser.setEnabled(true);
                        btnEditUser.setBackground(Color.YELLOW);
                        btnDeleteUser.setEnabled(true);
                        btnDeleteUser.setBackground(new Color(128, 0, 0));
                    } else {
                        btnEditUser.setEnabled(false);
                        btnDeleteUser.setEnabled(false);
                    }
                }
            }
        });
    }

    void refreshTable(ArrayList<UserModel> usersList) {
        if(usersList != null) {
            tableModel.setUsers(usersList);
        } else {
            tableModel.setUsers(userDao.list());
        }
        tableModel.fireTableDataChanged();
    }
    
    public void openUserUpsertFrame(UserModel user) {
        UserUpsertFrame upsertFrame = new UserUpsertFrame(user, this);
        upsertFrame.pack();
        upsertFrame.setLocationRelativeTo(null);
        upsertFrame.setVisible(true);
    }
}

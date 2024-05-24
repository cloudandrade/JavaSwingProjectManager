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

public class MainUserPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;
    private ArrayList<UserModel> users;
    private static UserDAO userDao;
    private JButton btnEditUser;
    private JButton btnDeleteUser;
    private UserModel selectedUser;

    public MainUserPanel(MainFrame mainFrame) {
        userDao = new UserDAO();
        users = new ArrayList<>();

        users = userDao.list();
        UserTableModel tableModel = new UserTableModel(users);

        setBackground(Color.white);
        setPreferredSize(Constants.MAIN_SIZE);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openUserUpsertFrame(null);
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
        scrollPane.setBounds(21, 249, 1045, 368);
        add(scrollPane);

        table = new JTable();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        JLabel lblUsers = new JLabel("Usuários");
        lblUsers.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblUsers.setBounds(21, 11, 1045, 41);
        add(lblUsers);

        btnEditUser = new JButton("Editar");
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
        btnDeleteUser.setEnabled(false);
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedUser != null) {
                    userDao.delete(selectedUser.getId());
                    refreshTable();
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
                        btnDeleteUser.setEnabled(true);
                    } else {
                        btnEditUser.setEnabled(false);
                        btnDeleteUser.setEnabled(false);
                    }
                }
            }
        });
    }

    void refreshTable() {
        users.clear();
        users.addAll(userDao.list());
        ((UserTableModel) table.getModel()).fireTableDataChanged();
    }
    
    public void openUserUpsertFrame(UserModel user) {
        UserUpsertFrame upsertFrame = new UserUpsertFrame(user, this);
        upsertFrame.pack();
        upsertFrame.setLocationRelativeTo(null);
        upsertFrame.setVisible(true);
    }
}
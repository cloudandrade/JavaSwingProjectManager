package view;

import java.awt.Color;
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
import dao.UserDAO;
import model.UserTableModel;
import model.UserModel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainUserPanel extends JPanel {
		
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private ArrayList<UserModel> users;
	private static UserDAO userDao;
	
	
	public MainUserPanel(MainFrame mainFrame) {
		userDao = new UserDAO();
		users = new ArrayList<>();

		users =	userDao.list();
		System.out.println("users: "+ users);
		UserTableModel tableModel = new UserTableModel(users);
		
		setBackground(Color.white);
		setPreferredSize(Constants.MAIN_SIZE);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpsertFrame addUserFrame = new UserUpsertFrame(null);
				addUserFrame.setLocationRelativeTo(null);
				addUserFrame.setVisible(true);
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
		scrollPane.setViewportView(table);
		
		JLabel lblUsers = new JLabel("Usuários");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblUsers.setBounds(21, 11, 1045, 41);
		add(lblUsers);
		
		JButton btnEditUser = new JButton("Editar");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditUser.setBounds(818, 215, 119, 23);
		add(btnEditUser);
		
		JButton btnDeleteUser = new JButton("Excluir");
		btnDeleteUser.setBounds(947, 215, 119, 23);
		add(btnDeleteUser);

	}
}

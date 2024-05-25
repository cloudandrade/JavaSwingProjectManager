package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.FormatUtils;

public class UserTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;

	private static final String[] collumns = {"Id", "Usuario","Nome","Email", "Senha", "Telefone"};
	
	private ArrayList<UserModel> users;

	public UserTableModel(ArrayList<UserModel> users) {
		super();
		this.users = users;
	}

	@Override
	public int getRowCount() {
		
		return users.size();
	}

	@Override
	public int getColumnCount() {

		return collumns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		UserModel user = users.get(rowIndex);
		switch (columnIndex) {
		case 0: {
			return user.getId();
		}
		case 1: {
			return user.getUsername();
		}
		case 2: {
			return user.getName();
		}
		case 3: {
			return user.getEmail();
		}
		case 4: {
			return user.getPassword();
		}
		case 5: {
			return FormatUtils.formatPhoneNumber(user.getPhone());
		}
		default:
			return null;
		}
	
	}
	
	@Override
	public String getColumnName(int column) {
		return collumns[column];
	}

}

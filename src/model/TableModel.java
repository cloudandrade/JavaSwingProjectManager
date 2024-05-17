package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	
	private static final String[] collumns = {"Id", "Usuario","Nome","Email", "Senha", "Telefone"};
	
	private ArrayList<UserModel> users;

	public TableModel(ArrayList<UserModel> users) {
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
			return user.getUser();
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
			return user.getPhone();
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

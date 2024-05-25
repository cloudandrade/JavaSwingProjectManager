package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import util.FormatUtils;

public class ProjectTableModel extends AbstractTableModel{
	
	private static final String[] collumns = {"Id", "Título","Descrição","Responsável","Telefone", "Categoria", "Data de Criação", "Status"};
	
	private ArrayList<ProjectModel> projects;

	public ProjectTableModel(ArrayList<ProjectModel> projects) {
		super();
		this.projects = projects;
	}

	@Override
	public int getRowCount() {
		
		return projects.size();
	}

	@Override
	public int getColumnCount() {

		return collumns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProjectModel project = projects.get(rowIndex);
		switch (columnIndex) {
		case 0: {
			return project.getId();
		}
		case 1: {
			return project.getTitle();
		}
		case 2: {
			return project.getDescription();
		}
		case 3: {
			return project.getOwnerName();
		}
		case 4: {
			return FormatUtils.formatPhoneNumber(project.getOwnerPhone());
		}
		case 5: {
			return project.getOdsName();
		}
		case 6: {
			return FormatUtils.formatTimestamp(project.getCreatedAt());
		}
		case 7: {
			return project.getStatus();
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
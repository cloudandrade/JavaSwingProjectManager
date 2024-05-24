package controller;

import java.util.ArrayList;

import dao.ProjectDAO;
import model.ProjectModel;
import util.Messages;


public class ProjectController {
	
private static ProjectDAO projectDao;
	
    public static  ArrayList<String> addProject(ProjectModel project) {
    	projectDao = new ProjectDAO();
    	ProjectModel newProject = new ProjectModel();
    	newProject = project;
    	
    	try {
    		projectDao.addProject(newProject); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_PROJECT_ADDED;
    }
    
    public static  ArrayList<String> updateProject(ProjectModel project) {
    	projectDao = new ProjectDAO();
    	ProjectModel changedProject = new ProjectModel();
    	changedProject = project;
    	
    	try {
    		projectDao.update(changedProject); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_PROJECT_UPDATED;
    }
    
    public static  ArrayList<String> deleteProject(int projectId) {
    	projectDao = new ProjectDAO();
    	
    	try {
    		projectDao.delete(projectId); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_PROJECT_DELETED;
    }

}

package controller;

import java.util.ArrayList;

import application.Messages;
import dao.UserDAO;
import model.UserModel;

public class UserController {
	
	private static UserDAO userDao;
	
    public static  ArrayList<String> addUser(UserModel user) {
    	userDao = new UserDAO();
    	UserModel newUser = new UserModel();
    	newUser = user;
    	
    	try {
    		userDao.addUser(newUser); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_USER_ADDED;
    }
    
    public static  ArrayList<String> updateUser(UserModel user) {
    	userDao = new UserDAO();
    	UserModel newUser = new UserModel();
    	newUser = user;
    	
    	try {
    		userDao.update(newUser); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_USER_UPDATED;
    }
    
    public static  ArrayList<String> deleteUser(int userId) {
    	userDao = new UserDAO();
    	
    	try {
    		userDao.delete(userId); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_USER_DELETED;
    }


}

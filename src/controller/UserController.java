package controller;

import java.util.ArrayList;

import dao.UserDAO;
import model.UserModel;
import util.EncryptUtils;
import util.Messages;

public class UserController {
	
	private static UserDAO userDao = new UserDAO();
	
	public static ArrayList<String> addUser(UserModel user) {
		
        try {
            String encryptedPassword = EncryptUtils.encryptPassword(user.getPassword());
            System.out.println("encrypted password: " + encryptedPassword);
            user.setPassword(encryptedPassword);
            userDao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Messages.GENERAL_ERROR;
        }

        return Messages.SUCCESS_USER_ADDED;
    }
    
    public static  ArrayList<String> updateUser(UserModel user) {
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
    	
    	try {
    		userDao.delete(userId); 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_USER_DELETED;
    }

}

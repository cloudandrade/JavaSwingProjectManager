package controller;

import java.util.ArrayList;

import application.Messages;
import dao.UserDAO;
import model.UserModel;

public class LoginController {
	
	private static UserDAO userDao;
	
    public static  ArrayList<String> validateLogin(String email, String password) {
    	userDao = new UserDAO();
    	UserModel user = new UserModel();
    	
    	try {
			user =	userDao.findByEmail(email);
			 if(user == null) {
	        	 return Messages.USER_EMAIL_NOT_FOUND;
	         } else if(user != null && user.getPassword() != password){
	        	 return Messages.PASSWORD_WRONG;
	         } 
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}
    	
    	return Messages.SUCCESS_AUTH;
    }

    // Outro método estático para um exemplo adicional
    public static void logout() {
        // Lógica de logout (exemplo)
        System.out.println("Usuário deslogado com sucesso.");
    }
}

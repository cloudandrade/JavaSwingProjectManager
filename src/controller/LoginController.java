package controller;

import java.util.ArrayList;

import dao.UserDAO;
import model.UserModel;
import util.CacheUtils;
import util.EncryptUtils;
import util.Messages;

public class LoginController {

    private static UserDAO userDao = new UserDAO();

    public static ArrayList<String> validateLogin(String email, String password) {

        try {
            UserModel user = userDao.findByEmail(email);
            
            if (user == null) {
                System.out.println("LoginController :: validateLogin :: Error: User not found on database");
                return Messages.USER_EMAIL_NOT_FOUND;
            } else if (user.getPassword() != null && !EncryptUtils.checkPassword(password, user.getPassword())) {
                System.out.println("LoginController :: validateLogin :: password is incorrect");
                return Messages.PASSWORD_WRONG;
            }
            // armazenando em cache local o usuario autenticado
            CacheUtils.putUserOnLocalStorage(user); 
            System.out.println("LoginController :: validateLogin :: User found: " + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return Messages.GENERAL_ERROR;
        }
        
        return Messages.SUCCESS_AUTH;
    }

    public static void onLogout() {
        CacheUtils.deleteUserOnLocalStorage();
        System.out.println("LoginController :: logout :: Usuário deslogado com sucesso.");
    }
}


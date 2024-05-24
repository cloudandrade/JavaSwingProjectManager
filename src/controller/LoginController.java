package controller;

import java.util.ArrayList;

import dao.UserDAO;
import model.UserModel;
import util.EncryptUtils;
import util.Messages;

public class LoginController {

    private static UserDAO userDao = new UserDAO();

    public static ArrayList<String> validateLogin(String email, String password) {
        UserModel user = null;

        try {
            user = userDao.findByEmail(email);

            if (user == null) {
                System.out.println("LoginController :: validateLogin :: Error: User not found on database");
                return Messages.USER_EMAIL_NOT_FOUND;
            } else if (user.getPassword() != null && !EncryptUtils.checkPassword(password, user.getPassword())) {
                System.out.println("LoginController :: validateLogin :: password is incorrect");
                return Messages.PASSWORD_WRONG;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Messages.GENERAL_ERROR;
        }

        System.out.println("LoginController :: validateLogin :: User found: " + user.toString());
        return Messages.SUCCESS_AUTH;
    }

    // Outro método estático para um exemplo adicional
    public static void logout() {
        // Lógica de logout (exemplo)
        System.out.println("Usuário deslogado com sucesso.");
    }
}


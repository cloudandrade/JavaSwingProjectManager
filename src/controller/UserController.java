package controller;

import java.util.ArrayList;

import dao.UserDAO;
import model.UserModel;
import util.EncryptUtils;
import util.Messages;

public class UserController {

	private static UserDAO userDao;

	public static ArrayList<String> addUser(UserModel user) {
		System.out.println("UserController :: addUser " + user.toString());
		userDao = new UserDAO();
		UserModel newUser = new UserModel();
		newUser = user;

		try {
			String encryptedPassword = EncryptUtils.encryptPassword(user.getPassword());
			System.out.println("encrypted password: " + encryptedPassword);
			newUser.setPassword(encryptedPassword);
			userDao.addUser(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.GENERAL_ERROR;
		}

		return Messages.SUCCESS_USER_ADDED;
	}

	public static ArrayList<String> updateUser(UserModel user) {
		System.out.println("UserController :: updateUser " + user.toString());
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

	public static ArrayList<String> deleteUser(int userId) {
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

package application;

import java.util.ArrayList;
import java.util.Arrays;

public class Messages {
	//error messages
	public static final ArrayList<String> USER_EMAIL_NOT_FOUND = new ArrayList<>(Arrays.asList("Error", "Usuário não encontrado com esse email, verifique os campos e tente novamente"));
	public static final ArrayList<String> PASSWORD_WRONG = new ArrayList<>(Arrays.asList("Error", "Senha Incorreta, verifique os campos e tente novamente"));
	public static final ArrayList<String> GENERAL_ERROR = new ArrayList<>(Arrays.asList("Error", "OPS, Ocorreu um erro no servidor, entre em contato com o suporte"));
	//success messages
	
	public static final ArrayList<String> SUCCESS_AUTH = new ArrayList<>(Arrays.asList("Success", "Usuário autenticado com sucesso."));
	public static final ArrayList<String> SUCCESS_USER_ADDED = new ArrayList<>(Arrays.asList("Success", "Usuário cadastrado com sucesso."));
	public static final ArrayList<String> SUCCESS_USER_UPDATED = new ArrayList<>(Arrays.asList("Success", "Usuário atualizado com sucesso."));
	public static final ArrayList<String> SUCCESS_USER_DELETED = new ArrayList<>(Arrays.asList("Success", "Usuário excluido com sucesso."));
	
	public static final ArrayList<String> SUCCESS_PROJECT_ADDED = new ArrayList<>(Arrays.asList("Success", "Projeto criado com sucesso."));
	public static final ArrayList<String> SUCCESS_PROJECT_UPDATED = new ArrayList<>(Arrays.asList("Success", "Projeto atualizado com sucesso."));
	public static final ArrayList<String> SUCCESS_PROJECT_DELETED = new ArrayList<>(Arrays.asList("Success", "Projeto excluido com sucesso."));
}

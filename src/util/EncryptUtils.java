package util;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtils {

	// Gera um hash para a senha
	public static String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	// Verifica se a senha fornecida corresponde ao hash armazenado
	public static boolean checkPassword(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}

	public static void main(String[] args) {
		// Teste de criptografia e verificação
		String password = "mysecretpassword";
		String hashed = encryptPassword(password);

		System.out.println("Senha original: " + password);
		System.out.println("Senha criptografada: " + hashed);

		boolean isPasswordMatch = checkPassword(password, hashed);
		System.out.println("A senha corresponde? " + isPasswordMatch);
	}
}

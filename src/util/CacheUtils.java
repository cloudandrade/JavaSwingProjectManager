package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.UserModel;

public class CacheUtils {

    static UserModel loggedUser;

    public static void putUserOnLocalStorage(UserModel user) {
        loggedUser = user;
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Verifica se o diretório "local" existe, se não, cria
            File directory = new File("local");
            if (!directory.exists()) {
                directory.mkdir();
            }
            // Serializa o objeto UserModel para um arquivo JSON
            mapper.writeValue(new File("local/loggedUser.json"), loggedUser);
            System.out.println("On System User saved to loggedUser.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserModel recoverUserOnLocalStorage() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Verifica se o arquivo "local/loggedUser.json" existe antes de tentar lê-lo
            File file = new File("local/loggedUser.json");
            if (file.exists()) {
                // Desserializa o objeto UserModel de um arquivo JSON
                loggedUser = mapper.readValue(file, new TypeReference<UserModel>() {});
            } else {
                System.out.println("No user is currently saved in loggedUser.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loggedUser;
    }
    
    
    public static void deleteUserOnLocalStorage() {
        File file = new File("local/loggedUser.json");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("User file deleted successfully.");
            } else {
                System.out.println("Failed to delete user file.");
            }
        } else {
            System.out.println("User file does not exist.");
        }
    }
}
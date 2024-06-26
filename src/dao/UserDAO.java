package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ConnectionFactory;
import model.UserModel;

public class UserDAO {
     
     private Connection connection = ConnectionFactory.getConnectionMySQL();
 	
     private static String ADD_USER = "INSERT INTO USER " 
    		 + "(name, username, email, phone, password) " 
    		 + "VALUES (?,?,?,?,?)";
     
     
     private static String LIST_USERS = "SELECT * FROM USER";
     
     private static String FIND_USER = "SELECT * FROM USER " 
    		 + "WHERE id = ? ";
     
     private static String FIND_USER_BY_EMAIL = "SELECT * FROM USER " 
    		 + "WHERE email = ? ";
     
     private static String UPDATE_USER = "UPDATE USER SET " 
    		 + "name = ?, username = ?, email = ?, phone = ?, password = ? " 
    		 + "WHERE id = ?";
     
     private static String DELETE_USER = "DELETE FROM USER " 
    		 + "WHERE id = ? ";
     
     @SuppressWarnings("static-access")
	public UserDAO() {
    	 connection = new ConnectionFactory().getConnectionMySQL();
     }
     
     public void addUser(UserModel user) {
    	 try {
             PreparedStatement statement = connection.prepareCall(ADD_USER);
             statement.setString(1, user.getName());
             statement.setString(2, user.getUsername());
             statement.setString(3, user.getEmail());
             statement.setString(4, user.getPhone());
             statement.setString(5, user.getPassword());
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         } 
    	 
     }
     
     public void update(UserModel user){
   
         try {
             PreparedStatement statement = connection.prepareCall(UPDATE_USER);
             statement.setString(1, user.getName());
             statement.setString(2, user.getUsername());
             statement.setString(3, user.getEmail());
             statement.setString(4, user.getPhone());
             statement.setString(5, user.getPassword());
             statement.setInt(6, user.getId());
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
     } 
     
     public void delete(Integer userId){
         
         try {
             PreparedStatement statement = connection.prepareCall(DELETE_USER);
             statement.setInt(1, userId);
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
     }
     
     public ArrayList<UserModel> list(){
    	 ArrayList<UserModel> usersList = new ArrayList<>();
         
         try {
             PreparedStatement statement = connection.prepareCall(LIST_USERS);
             
             ResultSet resultSet = statement.executeQuery();
             
             while(resultSet.next()){
               UserModel user = new UserModel();
               
               user.setId(resultSet.getInt("id"));
               user.setName(resultSet.getString("name"));
               user.setUsername(resultSet.getString("username"));
               user.setEmail(resultSet.getString("email"));
               user.setPhone(resultSet.getString("phone"));
               user.setPassword(resultSet.getString("password"));
               usersList.add(user);
             }
             
             resultSet.close();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
           
         return usersList;
     }
     
     public UserModel findById(int userId) {
         UserModel user = null;
         try (PreparedStatement statement = connection.prepareCall(FIND_USER)) {
             statement.setInt(1, userId);
             try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                     user = new UserModel();
                     user.setId(resultSet.getInt("id"));
                     user.setName(resultSet.getString("name"));
                     user.setUsername(resultSet.getString("username"));
                     user.setEmail(resultSet.getString("email"));
                     user.setPhone(resultSet.getString("phone"));
                     user.setPassword(resultSet.getString("password"));
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException(e);
         }
         return user;
     }
     
     public UserModel findByEmail(String email) {
         UserModel user = null;
         try (PreparedStatement statement = connection.prepareCall(FIND_USER_BY_EMAIL)) {
             statement.setString(1, email);
             try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                     user = new UserModel();
                     user.setId(resultSet.getInt("id"));
                     user.setName(resultSet.getString("name"));
                     user.setUsername(resultSet.getString("username"));
                     user.setEmail(resultSet.getString("email"));
                     user.setPhone(resultSet.getString("phone"));
                     user.setPassword(resultSet.getString("password"));
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException(e);
         }
         return user;
     }
     
     public ArrayList<UserModel> findByName(String search){
    	 ArrayList<UserModel> usersList = new ArrayList<>();
    	 
    	 String FIND_USER_BY_NAME = "SELECT * FROM USER WHERE name LIKE '%" + search + "%'";
         try {
        	 
        	 PreparedStatement statement = connection.prepareCall(FIND_USER_BY_NAME);
             
             ResultSet resultSet = statement.executeQuery();
    	 while(resultSet.next()){
             UserModel user = new UserModel();
             
             user.setId(resultSet.getInt("id"));
             user.setName(resultSet.getString("name"));
             user.setUsername(resultSet.getString("username"));
             user.setEmail(resultSet.getString("email"));
             user.setPhone(resultSet.getString("phone"));
             user.setPassword(resultSet.getString("password"));
             usersList.add(user);
           }
           
           resultSet.close();
           statement.close();
       } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
       }
         
       return usersList;
     }
     
}

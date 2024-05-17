package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.ConnectionFactory;
import model.UserModel;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	 String serverName = "localhost:3306"; 
     String mydatabase = "dev";       
     String BD = "jdbc:mysql://localhost:3306/dev";
     
     private Connection connection;
 	
     private static String ADD_USER = "INSERT INTO USER " 
    		 + "(name, user, email, phone, password) " 
    		 + "VALUES (?,?,?,?,?)";
     
     
     private static String LIST_USERS = "SELECT * FROM USER";
     
     private static String FIND_USER = "SELECT * FROM USER " 
    		 + " WHERE ID = ? ";
     
     private static String UPDATE_USER = "UPDATE USER " 
    		 + " name = ?, user = ?, email = ?, phone = ?, password = ? " 
    		 + " WHERE id = ?";
     
     private static String DELETE_USER = "DELETE FROM USER " 
    		 + " WHERE ID = ? ";
     
     public DAO() {
    	 connection = new ConnectionFactory().getConnectionMySQL();
     }
     
     public void addUser(UserModel user) {
    	 try {
             PreparedStatement statement = connection.prepareCall(ADD_USER);
             statement.setString(1, user.getName());
             statement.setString(2, user.getUser());
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
             statement.setString(2, user.getUser());
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
     
     public List<UserModel> list(){
    	 List<UserModel> usersList = new ArrayList<>();
         
         try {
             PreparedStatement statement = connection.prepareCall(LIST_USERS);
             
             ResultSet resultSet = statement.executeQuery();
             
             while(resultSet.next()){
               UserModel user = new UserModel();
               
               user.setId(resultSet.getInt("id"));
               user.setName(resultSet.getString("name"));
               user.setUser(resultSet.getString("user"));
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

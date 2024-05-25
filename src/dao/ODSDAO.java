package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectionFactory;
import model.ODSModel;

public class ODSDAO {
    
    private Connection connection = ConnectionFactory.getConnectionMySQL();  
    
    private static String LIST_USERS = "SELECT * FROM ODS";
    

    
    @SuppressWarnings("static-access")
	public ODSDAO() {
   	 connection = new ConnectionFactory().getConnectionMySQL();
    }
    
 
    
    public ArrayList<ODSModel> list(){
   	 ArrayList<ODSModel> odsList = new ArrayList<>();
        
        try {
            PreparedStatement statement = connection.prepareCall(LIST_USERS);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
            	ODSModel ods = new ODSModel();
              
              ods.setId(resultSet.getInt("id"));
              ods.setTitle(resultSet.getString("title"));
    
              odsList.add(ods);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
        }
          
        return odsList;
    }
    
  
    
 
    

    
}

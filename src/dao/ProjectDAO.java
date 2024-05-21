package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ConnectionFactory;
import model.ProjectModel;

public class ProjectDAO {

	 private Connection connection = ConnectionFactory.getConnectionMySQL();;
	 	
     private static String ADD_PROJECT = "INSERT INTO PROJECT " 
    		 + "(title, description, owner_id, ods_id, created_at, status) " 
    		 + "VALUES (?,?,?,?,?,?)";
     
     
     private static String LIST_PROJECTS_OWNERS_ODS = "SELECT " 
+ "P.id, P.title, P.description, P.owner_id, P.ods_id, P.created_at, P.status, " 
+ "U.name as owner_name, U.phone as owner_phone, " 
+ "O.title as ods_name "  
+ " FROM PROJECT P " 
+ "JOIN USER U ON P.owner_id = U.id "
+ "JOIN ODS O ON P.ods_id = O.id ";
     
     private static String FIND_PROJECT = "SELECT " 
    		 + "P.id, P.title, P.description, P.owner_id, P.ods_id, P.created_at, P.status, " 
    		 + "U.name as owner_name, U.phone as owner_phone, " 
    		 + "O.title as ods_name "  
    		 + " FROM PROJECT P " 
    		 + "JOIN USER U ON P.owner_id = U.id "
    		 + "JOIN ODS O ON P.ods_id = O.id "
    		 + " AND P.ID = ? ";
     
     private static String UPDATE_PROJECT = "UPDATE PROJECT SET " 
    		 + "title = ?, description = ?, ods_id = ?, status = ? " 
    		 + "WHERE id = ?";
     
     private static String DELETE_PROJECT = "DELETE FROM PROJECT " 
    		 + " WHERE ID = ? ";
     
     @SuppressWarnings("static-access")
	public ProjectDAO() {
    	 connection = new ConnectionFactory().getConnectionMySQL();
     }
     
     public void addProject(ProjectModel project) {
    	 try {
             PreparedStatement statement = connection.prepareCall(ADD_PROJECT);
             statement.setString(1, project.getTitle());
             statement.setString(2, project.getDescription());
             statement.setInt(3, project.getOwnerId());
             statement.setInt(4, project.getOdsId());
             statement.setTimestamp(5, project.getCreatedAt());
             statement.setString(5, project.getStatus());
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         } 
    	 
     }
     
     public void update(ProjectModel project){
   
         try {
             PreparedStatement statement = connection.prepareCall(UPDATE_PROJECT);

             statement.setString(1, project.getTitle());
             statement.setString(2, project.getDescription());
             statement.setInt(3, project.getOdsId());
             statement.setString(4, project.getStatus());
             statement.setInt(5, project.getId());
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
     } 
     
     public void delete(Integer projectId){
         
         try {
             PreparedStatement statement = connection.prepareCall(DELETE_PROJECT);
             statement.setInt(1, projectId);
             
             statement.execute();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
     }
     
     public ArrayList<ProjectModel> list(){
    	 ArrayList<ProjectModel> projectsList = new ArrayList<>();
         
         try {
             PreparedStatement statement = connection.prepareCall(LIST_PROJECTS_OWNERS_ODS);
             
             ResultSet resultSet = statement.executeQuery();
             
             while(resultSet.next()){
               ProjectModel project = new ProjectModel();
               //attributos de project
               project.setId(resultSet.getInt("id"));
               project.setTitle(resultSet.getString("title"));
               project.setDescription(resultSet.getString("description"));
               project.setOwnerId(resultSet.getInt("owner_id"));
               project.setOdsId(resultSet.getInt("ods_id"));
               project.setCreatedAt(resultSet.getTimestamp("created_at"));
               project.setStatus(resultSet.getString("status"));
               //attributos de user
               project.setOwnerName(resultSet.getString("owner_name"));
               project.setOwnerPhone(resultSet.getString("owner_phone"));
               //attributos de ods
               project.setOdsName(resultSet.getString("ods_name"));

               projectsList.add(project);
             }
             
             resultSet.close();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
           
         return projectsList;
     }
     
     public ProjectModel findById(int userId){
    	 
    	 ProjectModel project = new ProjectModel();
         
         try {
             PreparedStatement statement = connection.prepareCall(FIND_PROJECT);
             statement.setInt(1, userId);
             
             ResultSet resultSet = statement.executeQuery();
             
             //attributos de project
             project.setId(resultSet.getInt("id"));
             project.setTitle(resultSet.getString("title"));
             project.setDescription(resultSet.getString("description"));
             project.setOwnerId(resultSet.getInt("owner_id"));
             project.setOdsId(resultSet.getInt("ods_id"));
             project.setCreatedAt(resultSet.getTimestamp("created_at"));
             project.setStatus(resultSet.getString("status"));
             //attributos de user
             project.setOwnerName(resultSet.getString("owner_name"));
             project.setOwnerPhone(resultSet.getString("owner_phone"));
             //attributos de ods
             project.setOdsName(resultSet.getString("ods_name"));
             
             resultSet.close();
             statement.close();
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
           
         return project;
     }
     
     public ArrayList<ProjectModel> findByTitle(String search){
    	 ArrayList<ProjectModel> projectsList = new ArrayList<>();
    	 
    	 String FIND_PROJECT_BY_TITLE = "SELECT "
    			 + "P.id, P.title, P.description, P.owner_id, P.ods_id, P.created_at, P.status, " 
        		 + "U.name as owner_name, U.phone as owner_phone, " 
        		 + "O.title as ods_name "  
        		 + " FROM PROJECT P " 
        		 + "JOIN USER U ON P.owner_id = U.id "
        		 + "JOIN ODS O ON P.ods_id = O.id "
    	 		+ "AND P.title LIKE '%" + search + "%'";
         try {
        	 
        	 PreparedStatement statement = connection.prepareCall(FIND_PROJECT_BY_TITLE);
             
             ResultSet resultSet = statement.executeQuery();
    	 while(resultSet.next()){
    		 ProjectModel project = new ProjectModel();
             
    		 //attributos de project
             project.setId(resultSet.getInt("id"));
             project.setTitle(resultSet.getString("title"));
             project.setDescription(resultSet.getString("description"));
             project.setOwnerId(resultSet.getInt("owner_id"));
             project.setOdsId(resultSet.getInt("ods_id"));
             project.setCreatedAt(resultSet.getTimestamp("created_at"));
             project.setStatus(resultSet.getString("status"));
             //attributos de user
             project.setOwnerName(resultSet.getString("owner_name"));
             project.setOwnerPhone(resultSet.getString("owner_phone"));
             //attributos de ods
             project.setOdsName(resultSet.getString("ods_name"));
             projectsList.add(project);
           }
           
           resultSet.close();
           statement.close();
       } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
       }
         
       return projectsList;
     }
	
}

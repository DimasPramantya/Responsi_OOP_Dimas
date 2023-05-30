/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConect;
import java.sql.*;
import model.Account;
/**
 *
 * @author Lenovo
 */
public class AccTable {
    private Connection connection;
    public AccTable(){
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/responsioop", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String loginHandler(Account loggedPerson){
        Account person = new Account("", "", "");
        try {
            String query = "SELECT * FROM accounts WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loggedPerson.getUsername());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("inside if");
                person.setPassword(resultSet.getString("password"));
                person.setRole(resultSet.getString("role"));
                return person.getRole();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

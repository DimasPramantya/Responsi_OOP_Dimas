/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Renter;
/**
 *
 * @author Lenovo
 */
public class RenterTable {
    private Connection connection;
    public RenterTable(){
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/responsioop", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Renter> getRenterList(){ 
        List <Renter>renterList = new ArrayList<>();
        try {
            String query = "SELECT * FROM renter";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                 String id = resultSet.getString("id");
                 String name = resultSet.getString("name");;
                 String contact = resultSet.getString("contact");;
                 int duration = Integer.parseInt(resultSet.getString("duration"));
                 int bill = Integer.parseInt(resultSet.getString("bill"));
                 String status = resultSet.getString("status");
                 String room = resultSet.getString("room");
                 Renter renter = new Renter(id, name, contact, duration, bill, status, room);
                 renterList.add(renter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return renterList;
    }
} 

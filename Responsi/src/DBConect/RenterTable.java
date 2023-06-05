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
public class RenterTable implements RenterTableAct {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renterList;
    }

    @Override
    public void updateData(Renter renter) {
        try {
            String query = "UPDATE renter SET name = ?, contact = ?, duration = ?, bill = ?, status = ?, room = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, renter.getName());
            statement.setString(2, renter.getContact());
            statement.setInt(3, renter.getDuration());
            statement.setInt(4, renter.getBill());
            statement.setString(5, renter.getStatus());
            statement.setString(6, renter.getRoom());
            statement.setString(7, renter.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(Renter renter) {
        try {
            String query = "INSERT INTO renter (name, id, contact, duration, bill, status, room) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, renter.getName());
            System.out.println(renter.getName());
            statement.setString(2, renter.getId());
            statement.setString(3, renter.getContact());
            statement.setInt(4, renter.getDuration());
            statement.setInt(5, renter.getBill());
            statement.setString(6, renter.getStatus());
            statement.setString(7, renter.getRoom());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteData(String id) {
        try {
            System.out.println(id);
            String query = "DELETE FROM renter WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(String id) {
        try {
             String query = "UPDATE renter SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Paid");
            statement.setString(2, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
} 

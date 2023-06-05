/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Room;
/**
 *
 * @author Lenovo
 */
public class RoomTable {
    private Connection connection;
    public RoomTable(){
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/responsioop", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Room> getRoomList(){
        List<Room> roomList = new ArrayList<>();   
        try {
            String query = "SELECT * FROM rooms";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                int price = resultSet.getInt("price");
                String status = resultSet.getString("status");
                Room room = new Room(name, size, price, status);
                roomList.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
    
    public void updateRoom(String roomName){
        try {
            String query = "UPDATE rooms SET status = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Post Malone");
            statement.setString(2, roomName);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

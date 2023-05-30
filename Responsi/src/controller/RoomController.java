/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.Room;
import DBConect.RoomTable;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class RoomController {
    private RoomTable connection;
    
    public RoomController(){
        connection = new RoomTable();
    }
    
    public List<Room> fetchRoomData(){
        return connection.getRoomList();
    }
}

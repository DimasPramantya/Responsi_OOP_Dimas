/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.Renter;
import DBConect.RenterTable;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class RenterController {
    private RenterTable connection;
    
    public RenterController(){
        connection = new RenterTable();
    }
    
    public List<Renter> fetchRenterData(){
        return connection.getRenterList();
    }
    
    public void addData(String name, String id, String contact, int duration, int bill, String status, String room){
        System.out.println(name);
        Renter renter = new Renter(id, name,contact, duration, bill, status, room);
        connection.addData(renter);
    }
    
    public void updateData(String name, String id, String contact, int duration, int bill, String status, String room){
        Renter renter = new Renter(id,name,contact, duration, bill, status, room);
        connection.updateData(renter);
    }
    
    public void deleteData(String id){
        System.out.println(id);
        connection.deleteData(id);
    }
    
    public void updateStatus(String id){
        connection.updateStatus(id);
    }
}

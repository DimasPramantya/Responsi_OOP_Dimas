/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.Account;
import DBConect.AccTable;
/**
 *
 * @author Lenovo
 */
public class AccController {
    private AccTable connection;
    
    public AccController(){
        connection = new AccTable();
    }
    
    public String loginController(String username, String password){
        Account loggedUser = new Account(username, password, null);
        return connection.loginHandler(loggedUser);
    }
}

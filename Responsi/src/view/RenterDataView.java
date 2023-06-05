/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Lenovo
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import model.Renter;
import controller.RenterController;
import controller.RoomController;
public class RenterDataView implements ActionListener, ItemListener{
    JFrame window = new JFrame("Renter Data");
   
    JLabel lName = new JLabel("Name ");
    JTextField tfName = new JTextField();
    JLabel lid = new JLabel("id ");
    JTextField tfid = new JTextField();
    JLabel lContact= new JLabel("Contact ");
    JTextField tfContact = new JTextField();
    JLabel lRentTime = new JLabel("RentTime ");
    JTextField tfRentTime = new JTextField();

    JButton btnAddPanel = new JButton("Submit");
    JButton btnLogout = new JButton("Logout");
    
    public String room = "";

    public RenterDataView(Renter renter) {
        window.setLayout(null);
        window.setSize(550,200);
        // window.setDefaultCloseOperation(3);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        window.add(lName);
        window.add(tfName);
        window.add(lid);
        window.add(tfid);
        window.add(lContact);
        window.add(tfContact);
        window.add(lRentTime);
        window.add(tfRentTime);
        window.add(btnAddPanel);
        window.add(btnLogout);
        
        //LABEL
        lName.setBounds(5, 30, 120, 20);
        lid.setBounds(5, 55, 120, 20);
        lContact.setBounds(5,80,120,20);
        lRentTime.setBounds(5,110,120,20);

//TEXTFIELD
        tfName.setBounds(110, 35, 120, 20);
        tfid.setBounds(110, 60, 120, 20);
        tfContact.setBounds(110, 85, 120, 20);
        tfRentTime.setBounds(110, 115, 120, 20);
        tfName.setText(renter.getName());
        tfid.setText(renter.getId());
        tfContact.setText(renter.getContact());
        tfRentTime.setText(String.valueOf(renter.getDuration()));
        this.room = renter.getRoom();

//BUTTON PANEL
        btnAddPanel.setBounds(250, 35, 90, 20);
        btnLogout.setBounds(250, 65, 90, 20);
        
        btnAddPanel.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if(button.getSource()==btnAddPanel){
            String name = tfName.getText();
            String id = tfid.getText();
            String contact = tfContact.getText();
            int duration = Integer.parseInt(tfRentTime.getText());
            String status = "notPaid";
            String room = this.room;
            int bill = 0;
            if(this.room.equals("A1") || this.room.equals("A2")){
                bill = duration * 200000;
            }else if(this.room.equals("B1") || this.room.equals("B2")){
                bill = duration * 300000;
            }else if(this.room.equals("C1") || this.room.equals("C2")){
                bill = duration * 400000;
            }
            RenterController controller = new RenterController();
            RoomController roomController = new RoomController();
            if(name.equals("") || id.equals("") || contact.equals("") || duration == 0){
                System.out.println("empty data");
                JOptionPane.showMessageDialog(window, "Each data can not be EMPTY!");
            }else{
                System.out.println(name);
                controller.addData(name, id, contact, duration, bill, status, room);
                roomController.updateStatus(room);
                window.dispose();
                RoomListView roomListView = new RoomListView();
                roomListView.window.setVisible(true);
            }
        }else if(button.getSource()==btnLogout){
            LoginPageView loginPageView = new LoginPageView();
            loginPageView.setVisible(true);
            window.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



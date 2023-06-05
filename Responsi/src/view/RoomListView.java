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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Room;
import model.Renter;
import controller.RoomController;
import java.util.List;

public class RoomListView implements ActionListener, ItemListener{ 
    JFrame window = new JFrame("Renter Data");
    Object columnName [];
    
    String data[][] = new String [100][4];
    DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
    JTable tabel = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(tabel);
    
    JButton bcancel = new JButton("Logout");

    public RoomListView(){
        window.setLayout(null);
        window.setSize(550,600);
       
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        window.add(scrollPane);
        window.add(bcancel);
        scrollPane.setBounds(20, 35, 500, 300);
        bcancel.setBounds(20, 350, 100,50);
        bcancel.addActionListener(this);
        
        showData();
        tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    String status = (String) tabel.getValueAt(row, 3);
                    if(status.equals("Post Malone")){
                        JOptionPane.showMessageDialog(window, "The room is already RENTED!");
                    }else{
                        Renter renter = new Renter("", "", "", 0, 0, "", "");
                        String room = (String) target.getValueAt(row, 0);
                        renter.setRoom(room);
                        RenterDataView renterDataView = new RenterDataView(renter);
                        renterDataView.window.setVisible(true);
                        window.dispose();
                    }
                }
            }
        });
    }
    private void showData(){
        RoomController controller = new RoomController();
        List<Room> roomList = controller.fetchRoomData();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("size");
        model.addColumn("price");
        model.addColumn("status");
        for (Room room : roomList) {
            Object[] row = {
                room.getName(),
                room.getSize(),
                room.getPrice(),
                room.getStatus()
            };
            model.addRow(row);
        }
        tabel.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if(button.getSource()==bcancel){
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

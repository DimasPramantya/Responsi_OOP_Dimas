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
        
        showData();
        tabel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row = tabel.rowAtPoint(e.getPoint());
                Object name =  tabel.getValueAt(row, 0);
                System.out.println(name.toString());
                RenterDataView renterDataView = new RenterDataView();
                renterDataView.window.setVisible(true);
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
    public void actionPerformed(ActionEvent arg0) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

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
import controller.RenterController;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Renter;
import controller.RenterController;

public class AdminPageView implements ActionListener{ 
    JFrame window = new JFrame("Renter Data");
    Object columnName [];
    
    String data[][] = new String [100][4];
    DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
    JTable tabel = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(tabel);
    
    JButton blogout = new JButton("Logout");

    public AdminPageView() {
        window.setLayout(null);
        window.setSize(550,600);
       
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        window.add(scrollPane);
        window.add(blogout);
        scrollPane.setBounds(20, 35, 500, 300);
        blogout.setBounds(20, 350, 100,50);
        
        blogout.addActionListener(this);
        
        showData();
        
        tabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    showPopup(row);
                }
            }
        });
    }
    
    private void showData(){
        RenterController controller = new RenterController();
        List<Renter> renterList = controller.fetchRenterData();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("id");
        model.addColumn("contact");
        model.addColumn("duration");
        model.addColumn("bill");
        model.addColumn("status");
        model.addColumn("room");
        for (Renter renter : renterList) {
            Object[] row = {
                renter.getName(),
                renter.getId(),
                renter.getContact(),
                renter.getDuration(),
                renter.getBill(),
                renter.getStatus(),
                renter.getRoom()
            };
            model.addRow(row);
        }
        tabel.setModel(model);
    }
    
    private void showPopup(int row) {
        String status = (String) tabel.getValueAt(row, 5);
        String optionList[] = new String[2];
        String optionMessage = "";
        if(status.equals("Paid")){
            optionList[0] = "Edit";
            optionList[1] = "Delete";
            optionMessage = "Choose an action";
        }else{
            optionList[0] = "Yes";
            optionList[1] = "No";
            optionMessage = "Change status to paid";
        }
        int option = JOptionPane.showOptionDialog(window, optionMessage, "Option Dialog",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                optionList, null);
        if (option == 0) {
            window.dispose();
            if(status.equals("Paid")){
                Renter renter = new Renter("", "", "", 0, 0, "", "");
                renter.setId((String) tabel.getValueAt(row, 1));
                renter.setName((String) tabel.getValueAt(row, 0));
                renter.setContact((String) tabel.getValueAt(row, 2));
                renter.setDuration((Integer) tabel.getValueAt(row, 3));
                renter.setBill((Integer) tabel.getValueAt(row, 4));
                renter.setStatus((String) tabel.getValueAt(row, 5));
                renter.setRoom((String) tabel.getValueAt(row, 6));
                RenterDataView renterDataView = new RenterDataView(renter);
                renterDataView.window.setVisible(true); 
            }else{
                String id = (String) tabel.getValueAt(row, 1);
                RenterController controller = new RenterController();
                controller.updateStatus(id);
                window.setVisible(true);
                showData();
            }
        } else if (option == 1) {
            window.dispose();
            if(status.equals("Paid")){
                String id = (String) tabel.getValueAt(row, 1);
                RenterController controller = new RenterController();
                controller.deleteData(id);
                window.setVisible(true);
                showData();
            }else{
                window.setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if(button.getSource()==blogout){
            LoginPageView loginPageView = new LoginPageView();
            loginPageView.setVisible(true);
            window.dispose();
        }
    }
}


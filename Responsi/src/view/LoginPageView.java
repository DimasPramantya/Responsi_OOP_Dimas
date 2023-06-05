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
import controller.AccController;
public class LoginPageView extends JFrame implements ActionListener, ItemListener{
 
    JLabel ltitle = new JLabel("Login");
    
    JLabel lusername = new JLabel("Username");
    JTextField fusername = new JTextField();
    
    JLabel lpassword = new JLabel("Password");
    JPasswordField fpassword = new JPasswordField();
    
    JButton blogin = new JButton("Login");
    
    public LoginPageView(){
        setSize(800, 600);
        setTitle("Login Page");
        setVisible(true);
        setLayout(null);
        
        setLocationRelativeTo(null);
        
        add(ltitle);
        add(lusername);
        add(lpassword);
        add(fusername);
        add(fpassword);
        add(blogin);
        
        ltitle.setBounds(365, 50, 75, 50);
        
        lusername.setBounds(275, 120, 100, 30);
        fusername.setBounds(275, 150, 250, 30);
        
        lpassword.setBounds(275, 180, 100, 30);
        fpassword.setBounds(275, 210, 250, 30);
        
        blogin.setBounds(350, 260, 100, 30);
        
        blogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        String username = fusername.getText();
        String password = fusername.getText();
        System.out.println("test");
        AccController controller = new AccController();
        if(button.getSource() == blogin){
            String result = controller.loginController(username, password);
            if(result != null){
                if(result.equals("renter")){
                    RoomListView roomListView = new RoomListView();
                    roomListView.window.setVisible(true);           
                    this.dispose();
                }else if(result.equals("admin")){
                    System.out.println(result);
                    AdminPageView adminPageView = new AdminPageView();
                    adminPageView.window.setVisible(true);
                    this.dispose();
                }
            }else{
                JOptionPane.showMessageDialog(this, "Wrong Email or Password");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

import pojo.SinhVien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Writer;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel rootPanel;

    public Login(){
        Login login=this;
        JFrame frame = new JFrame("Login");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String puname = login.textField1.getText();
                String ppaswd = login.passwordField1.getText();
                SinhVien sv=null;
                try{
                    sv=SinhVienDAO.layThongTinhSinhVien(puname);
                }
                catch (NumberFormatException ex){}

                boolean laGiaoVu = puname.compareTo("giaovu") == 0 && ppaswd.compareTo("giaovu") == 0;
                if(sv!=null|| laGiaoVu) {
                    //MainMenu menu =new MainMenu();
                    if(laGiaoVu)Account.isGiaoVu=true;
                    else {
                        Account.sv=sv;
                    }
                    MainLayout mainLayout=new MainLayout();
                    frame.dispose();
                }
                else if(puname.equals("") && ppaswd.equals("")){
                    JOptionPane.showMessageDialog(null,"Please insert Username and Password");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong Username / Password");
                    login.textField1.setText("");
                    login.passwordField1.setText("");
                    login.textField1.requestFocus();
                }
            }
        });
    }
}

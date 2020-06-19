import pojo.GiaoVu;
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
        JFrame frame = new JFrame("Đăng nhập");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String puname = login.textField1.getText();
                String ppaswd = login.passwordField1.getText();
                SinhVien sv=null;
                try{
                    sv=SinhVienDAO.layThongTinhSinhVien(puname);
                }
                catch (NumberFormatException ex){}
                GiaoVu gv=SinhVienDAO.getGiaoVu();
                boolean laGiaoVu = puname.compareTo("giaovu") == 0 && ppaswd.compareTo(gv.getMatKhau()) == 0;
                if(sv!=null|| laGiaoVu) {
                    //MainMenu menu =new MainMenu();
                    if(laGiaoVu)Account.isGiaoVu=true;
                    else {
                        if(ppaswd.compareTo(sv.getMatKhau())!=0){
                            JOptionPane.showMessageDialog(null,"Tên đăng nhập/Mật khẩu sai!");
                            login.passwordField1.setText("");
                            return;
                        }
                        Account.sv=sv;
                    }
                    MainLayout mainLayout=new MainLayout();
                    frame.dispose();
                }
                else if(puname.equals("") && ppaswd.equals("")){
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Tên đăng nhập/Mật khẩu sai!");
                    login.textField1.setText("");
                    login.passwordField1.setText("");
                    login.textField1.requestFocus();
                }
            }
        });
    }
}

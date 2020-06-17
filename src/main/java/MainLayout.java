import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLayout {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JList listDiem;
    private JTextField textXemLop;
    private JList listDSHocPhan;
    private JButton nhapHocSinhButton;
    private JButton nhapMonHocButton;
    private JButton nhapDiemButton;
    private JTextField textDKHocPhan;
    private JList listHocPhan;
    private JButton dangKyButton;
    private JButton xoaButton;
    private JPanel panelXemDiemHS;
    private JPanel panelDSLop;
    private JPanel panelGiaoVu;
    private JPanel panelDKHP;
    private JButton buttonDangXuat;
    private JLabel userLabel;
    private JFrame frame;

    public MainLayout(){
        frame = new JFrame("Quan ly sinh vien");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        setComponentIndex();
    }
    public void setComponentIndex(){
        if(Account.isGiaoVu){
            panelDKHP.setVisible(false);
            panelXemDiemHS.setVisible(false);
            userLabel.setText("Giao vu");
        }
        else{
            panelGiaoVu.setVisible(false);
            userLabel.setText(Account.sv.getHoTen() +" - "+Account.sv.getMaSinhVien());
        }
        buttonDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account.sv=null;
                Account.isGiaoVu=false;
                Login login=new Login();
                frame.dispose();
            }
        });
    }
}

import pojo.HocLop;
import pojo.HocPhan;
import pojo.SinhVien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainLayout {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JList<HocLop> listDiem;
    private JTextField textXemLop;
    private JList<HocLop> listDSHocPhan;
    private JButton nhapHocSinhButton;
    private JButton nhapHocPhanButton;
    private JButton nhapDiemButton;
    private JTextField textDKHocPhan;
    private JList<HocPhan> listHocPhan;
    private JPanel panelXemDiemHS;
    private JPanel panelDSLop;
    private JPanel panelGiaoVu;
    private JPanel panelDKHP;
    private JButton buttonDangXuat;
    private JLabel userLabel;
    private JButton xemDSLopButton;
    private JButton timHPButton;
    private JButton dangKyButton;
    private JButton xoaDKHPButton;
    private JLabel TenHP;
    private JButton nhapDanhSachLopButton;
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
            tabbedPane1.setEnabledAt(0,false);
            tabbedPane1.setEnabledAt(3,false);
            userLabel.setText("Giao vu");
        }
        else{
            tabbedPane1.setEnabledAt(2,false);
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

        nhapHocSinhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<SinhVien> list=CSVFileUtil.docFileThemSinhVien(panelGiaoVu);
                if(list==null)return;
                int count=0;
                for(SinhVien sv:list){
                    if(SinhVienDAO.themSinhVien(sv))count++;
                }
                JOptionPane.showMessageDialog(panelGiaoVu,count+" sinh vien da duoc load",
                        "Success",JOptionPane.WARNING_MESSAGE);
            }
        });
        nhapHocPhanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<HocPhan> list=CSVFileUtil.docFileThemHocPhan(panelGiaoVu);
                if(list==null)return;
                int count=0;
                for(HocPhan hocPhan:list){
                    if(HocPhanDAO.themHocPhan(hocPhan))count++;
                }
                JOptionPane.showMessageDialog(panelGiaoVu,count+" hoc phan da duoc load",
                        "Success",JOptionPane.WARNING_MESSAGE);
                //SetListXemHocPhan();
            }
        });
        nhapDanhSachLopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<HocLop> list=CSVFileUtil.docFileThemHocLop(panelGiaoVu);
                if (list==null)return;
                int count=0;
                for (HocLop hocLop : list) {
                    if(HocLopDAO.themHocLop(hocLop))count++;
                }
                JOptionPane.showMessageDialog(panelGiaoVu,count+" hoc lop da duoc load",
                        "Success",JOptionPane.WARNING_MESSAGE);
                //SetListDSLop();
            }
        });
        nhapDiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<HocLop> list=CSVFileUtil.docFileThemDiem(panelGiaoVu);
                if (list==null)return;
                int count=0;
                for (HocLop hocLop : list) {
                    if(HocLopDAO.capNhatThongTinHocLop(hocLop))count++;
                }
                JOptionPane.showMessageDialog(panelGiaoVu,count+" set diem da duoc load",
                        "Success",JOptionPane.WARNING_MESSAGE);
                //SetListXemDiemMotSV();
            }
        });
        //SetListXemDiemMotSV();
        //SetListXemHocPhan();
        //SetListDSLop();
    }
    public void SetListXemDiemMotSV(){
        if(Account.isGiaoVu)return;
        ArrayList<HocLop> list=new ArrayList<>(SinhVienDAO.layThongTinhSinhVien(String.valueOf(Account.sv.getMaSinhVien())).
                getHocLop());
        listDiem.setListData((HocLop[]) list.toArray());
        listDiem.setCellRenderer(new XemDiemRenderer(1));
    }

    public void SetListDSLop(){
        ArrayList<HocLop> list=new ArrayList<>(HocLopDAO.layDanhSachHocLop());

        listDSHocPhan.setListData((HocLop[]) list.toArray());
        if(Account.isGiaoVu)listDSHocPhan.setCellRenderer(new XemDiemRenderer(2));
        else listDSHocPhan.setCellRenderer(new XemDiemRenderer(3));
    }

    public void SetListXemHocPhan(){
        ArrayList<HocPhan> list=new ArrayList<>(HocPhanDAO.layDanhSachHocPhan());

        listHocPhan.setListData((HocPhan[]) list.toArray());
        listHocPhan.setCellRenderer(new HocPhanRenderer());
    }
}

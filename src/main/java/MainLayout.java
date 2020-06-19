import pojo.*;

import javax.swing.*;
import java.awt.*;
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
    private JTextField textXemTKB;
    private JList<HocPhan> listHocPhan;
    private JPanel panelXemDiemHS;
    private JPanel panelDSLop;
    private JPanel panelGiaoVu;
    private JPanel panelXemTKB;
    private JButton buttonDangXuat;
    private JLabel userLabel;
    private JButton xemDSLopButton;
    private JButton timHPButton;
    private JLabel TenHP;
    private JButton nhapDanhSachLopButton;
    private JPasswordField matKhauCuTxt;
    private JPasswordField matKhauMoiTxt;
    private JButton doiMKButton;
    private JPanel panelDoiMK;
    private JTextField textMaHP;
    private JPanel xemDSLopTablePanel;
    private JLabel xemDSLopDiemGK;
    private JLabel xemDSLopDiemCK;
    private JLabel xemDSLopDiemKhac;
    private JLabel xemDSLopDiemTong;
    private JLabel xemDSLopKetQua;
    private JLabel xemDSLopCMND;
    private JPanel panelCapNhatDiem;
    private JTextField chinhDiemGK;
    private JTextField chinhDiemCK;
    private JTextField chinhDiemKhac;
    private JTextField chinhDiemTong;
    private JButton chinhSuaDiemOKBtn;
    private JButton chinhSuaDiemCancelBtn;
    private JLabel chinhSuaDiemTarget;
    private JButton suaDiemBtn;
    private JFrame frame;
    private int times=0;
    public static int soLuongDau=0;
    private static int type=3;
    private HocLop selectedHocLop;
    private String maHPDangXem="";

    public MainLayout(){
        frame = new JFrame("Quản Lý Sinh Viên");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(dim);
        frame.setMinimumSize(dim);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setComponentIndex();
    }
    public void setComponentIndex(){
        tabbedPane1.setEnabledAt(3,false);
        if(Account.isGiaoVu){
            tabbedPane1.setSelectedIndex(2);
            tabbedPane1.setEnabledAt(0,false);
            userLabel.setText("Giáo vụ");
        }
        else{
            tabbedPane1.setSelectedIndex(0);
            tabbedPane1.setEnabledAt(2,false);
            tabbedPane1.setEnabledAt(4,false);
            tabbedPane1.setEnabledAt(5,false);
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
                JOptionPane.showMessageDialog(panelGiaoVu,count+" sinh viên đã được load lên database",
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
                JOptionPane.showMessageDialog(panelGiaoVu,count+" học phần đã được load lên database",
                        "Success",JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(panelGiaoVu,count+" set tham gia học phần đã đuợc load lên database",
                        "Success",JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(panelGiaoVu,count+" set điểm đã đuợc load lên database",
                        "Success",JOptionPane.WARNING_MESSAGE);
                SetListXemDiemMotSV();
            }
        });

        doiMKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(matKhauMoiTxt.getText().toString().isEmpty()){
                    JOptionPane.showMessageDialog(panelDoiMK,"Vui lòng nhập mật khẩu mới!",
                            "Failure",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                GiaoVu gv=SinhVienDAO.getGiaoVu();
                if(Account.isGiaoVu){
                    gv=SinhVienDAO.getGiaoVu();
                    if(matKhauCuTxt.getText().compareTo(gv.getMatKhau())!=0){
                        JOptionPane.showMessageDialog(panelDoiMK,"Vui lòng nhập lại mật khẩu cũ!",
                                "Failure",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                else{
                    if(matKhauCuTxt.getText().compareTo(Account.sv.getMatKhau())!=0){
                        JOptionPane.showMessageDialog(panelDoiMK,"Vui lòng nhập lại mật khẩu cũ!",
                                "Failure",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if(Account.isGiaoVu){
                    gv.setMatKhau(matKhauMoiTxt.getText());
                    if(SinhVienDAO.capNhatMatKhauGiaoVu(gv)){
                        matKhauCuTxt.setText("");
                        matKhauMoiTxt.setText("");
                        JOptionPane.showMessageDialog(panelDoiMK,"Đổi mật khẩu thành công!",
                                "Success",JOptionPane.WARNING_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(panelDoiMK,"Đổi mật khẩu không thành công!",
                                "Failure",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else{
                    Account.sv.setMatKhau(matKhauMoiTxt.getText());
                    if(SinhVienDAO.capNhatThongTinSinhVien(Account.sv)){
                        matKhauCuTxt.setText("");
                        matKhauMoiTxt.setText("");
                        JOptionPane.showMessageDialog(panelDoiMK,"Đổi mật khẩu thành công!",
                                "Success",JOptionPane.WARNING_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(panelDoiMK,"Đổi mật khẩu không thành công!",
                                "Failure",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        xemDSLopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textXemLop.getText().isEmpty())return;
                if(textMaHP.getText().isEmpty()){
                    if(SetListDSLop(textXemLop.getText())){
                        TenHP.setText("");
                        type=3;
                    }
                }
                else{
                    String maHP=textXemLop.getText().toString() + "–"+textMaHP.getText().toString();
                    maHPDangXem=maHP;
                    if(SetListDSLopHP(maHP)){
                        StringBuilder builder=new StringBuilder();
                        builder.append(MainLayout.soLuongDau);
                        builder.append(" đậu");
                        builder.append(System.lineSeparator());
                        builder.append("Tỉ lệ: ");
                        double tile=(double)MainLayout.soLuongDau/(double)(listDSHocPhan.getModel().getSize());
                        tile=tile*100;
                        builder.append(tile);
                        builder.append("%");
                        TenHP.setText(builder.toString());
                        type=2;
                    }
                }
            }
        });
        timHPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textXemTKB.getText().isEmpty())return;
                SetListXemHocPhan(textXemTKB.getText());
            }
        });
        suaDiemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type!=2||listDSHocPhan.getSelectedIndex()==-1)return;
                HocLop hl=listDSHocPhan.getSelectedValue();
                selectedHocLop=hl;
                tabbedPane1.setEnabledAt(3,true);
                tabbedPane1.setSelectedComponent(panelCapNhatDiem);
                tabbedPane1.setEnabledAt(2,false);
                StringBuilder builder=new StringBuilder();
                builder.append("MSSV: ");
                builder.append(String.valueOf(hl.getSinhVien().getMaSinhVien()));
                builder.append(";Ten: ");
                builder.append(hl.getSinhVien().getHoTen());
                builder.append("; Ma hoc phan: ");
                builder.append(hl.getHocPhan().getMaHocPhan());
                chinhSuaDiemTarget.setText(builder.toString());
                chinhDiemGK.setText(String.valueOf(hl.getDiemGK()));
                chinhDiemCK.setText(String.valueOf(hl.getDiemCK()));
                chinhDiemKhac.setText(String.valueOf(hl.getDiemKhac()));
                chinhDiemTong.setText(String.valueOf(hl.getDiemTong()));
            }
        });
        chinhSuaDiemOKBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    selectedHocLop.setDiemGK(Float.parseFloat(chinhDiemGK.getText()));
                    selectedHocLop.setDiemCK(Float.parseFloat(chinhDiemCK.getText()));
                    selectedHocLop.setDiemKhac(Float.parseFloat(chinhDiemKhac.getText()));
                    selectedHocLop.setDiemTong(Float.parseFloat(chinhDiemTong.getText()));
                    if(!HocLopDAO.capNhatThongTinHocLop(selectedHocLop))throw new Exception();
                    if(selectedHocLop.getDiemGK()<0||selectedHocLop.getDiemGK()>10)throw new Exception();
                    if(selectedHocLop.getDiemCK()<0||selectedHocLop.getDiemCK()>10)throw new Exception();
                    if(selectedHocLop.getDiemKhac()<0||selectedHocLop.getDiemKhac()>10)throw new Exception();
                    if(selectedHocLop.getDiemTong()<0||selectedHocLop.getDiemTong()>10)throw new Exception();
                    selectedHocLop=null;
                    SetListDSLopHP(maHPDangXem);
                    tabbedPane1.setEnabledAt(2,true);
                    tabbedPane1.setSelectedComponent(panelDSLop);
                    tabbedPane1.setEnabledAt(3,false);
                }catch(Exception ex){
                    tabbedPane1.setEnabledAt(2,true);
                    tabbedPane1.setSelectedComponent(panelDSLop);
                    tabbedPane1.setEnabledAt(3,false);
                    JOptionPane.showMessageDialog(panelDSLop,"Gặp lỗi!",
                            "Failure",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        chinhSuaDiemCancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDSLop.setVisible(true);
                tabbedPane1.setSelectedComponent(panelDSLop);
                panelCapNhatDiem.setVisible(false);
            }
        });
        SetListXemDiemMotSV();
    }
    public void SetListXemDiemMotSV(){
        if(Account.isGiaoVu)return;
        ArrayList<HocLop> list=new ArrayList<>(SinhVienDAO.layThongTinhSinhVien(String.valueOf(Account.sv.getMaSinhVien())).
                getHocLop());
        DefaultListModel<HocLop> model=new DefaultListModel<>();
        for(int i=0;i<list.size();i++){
            model.add(i,list.get(i));
        }
        listDiem.setModel(model);
        listDiem.setCellRenderer(new XemDiemRenderer(1));
    }

    public boolean SetListDSLopHP(String maHocPhan){
        HocPhan hp=HocPhanDAO.layThongTinhHocPhan(maHocPhan);
        if(hp==null){
            JOptionPane.showMessageDialog(panelDoiMK,"Không tồn tại học phần này!",
                    "Failure",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        MainLayout.soLuongDau=0;
        ArrayList<HocLop> list=new ArrayList<>(hp.getHocLop());
        DefaultListModel<HocLop> model=new DefaultListModel<>();
        for(int i=0;i<list.size();i++){
            model.add(i,list.get(i));
        }
        listDSHocPhan.setModel(model);
        listDSHocPhan.setCellRenderer(new XemDiemRenderer(2));
        if(times>0){
            xemDSLopDiemGK.setVisible(true);
            xemDSLopDiemCK.setVisible(true);
            xemDSLopDiemKhac.setVisible(true);
            xemDSLopDiemTong.setVisible(true);
            xemDSLopKetQua.setVisible(true);
        }
        xemDSLopCMND.setVisible(false);
        times++;
        return true;
    }
    public boolean SetListDSLop(String maLop){
        Lop lop=LopDAO.layThongTinLop(maLop);
        if(lop==null){
            JOptionPane.showMessageDialog(panelDoiMK,"Không tồn tại lớp này!",
                    "Failure",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        ArrayList<SinhVien> sv = new ArrayList<>(lop.getSv());
        DefaultListModel<HocLop> model=new DefaultListModel<>();
        for(int i=0;i<sv.size();i++){
            HocLop temp=new HocLop();
            temp.setSinhVien(sv.get(i));
            model.add(i,temp);
        }
        listDSHocPhan.setModel(model);
        listDSHocPhan.setCellRenderer(new XemDiemRenderer(3));
        if(times>0){
            xemDSLopCMND.setVisible(true);
        }
        xemDSLopDiemGK.setVisible(false);
        xemDSLopDiemCK.setVisible(false);
        xemDSLopDiemKhac.setVisible(false);
        xemDSLopDiemTong.setVisible(false);
        xemDSLopKetQua.setVisible(false);
        times++;
        return true;
    }

    public void SetListXemHocPhan(String maLop){
        ArrayList<HocPhan> list=new ArrayList<>();
        try{
            list.addAll(HocPhanDAO.layThongTinHocPhanTheoLop(maLop));
        }catch (Exception e){
            JOptionPane.showMessageDialog(panelDoiMK,"Không tồn tại lớp này!",
                    "Failure",JOptionPane.WARNING_MESSAGE);
        }
        DefaultListModel<HocPhan> model=new DefaultListModel<>();
        for(int i=0;i<list.size();i++){
            model.add(i,list.get(i));
        }
        listHocPhan.setModel(model);
        listHocPhan.setCellRenderer(new HocPhanRenderer());

    }
}

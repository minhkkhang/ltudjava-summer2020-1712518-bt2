import pojo.HocLop;

import javax.swing.*;
import java.awt.*;

public class XemDiemRenderer implements ListCellRenderer<HocLop>{
    public int type=1;
    public XemDiemRenderer(int type){
        this.type=type;
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends HocLop> list, HocLop value, int index, boolean isSelected, boolean cellHasFocus) {
        //if(type==1)setText(value.xemDiemCuaMotSV());
        //if(type==2)setText(value.xemDiemCuaHocPhan());
        //if(type==3)setText(value.xemDSSVThamGiaHP());
        JPanel panel;
        if(type==1){
            panel=new JPanel(new GridLayout(1,9));
            panel.setPreferredSize(new Dimension(800,30));

            JLabel stt=new JLabel(String.valueOf(index+1));
            JLabel maHocPhan=new JLabel(value.getHocPhan().getMaHocPhan());
            JLabel tenMon=new JLabel(value.getHocPhan().getMonHoc().getTenMon());
            JLabel phongHoc=new JLabel(value.getHocPhan().getPhongHoc());
            JLabel diemGK=new JLabel(value.getDiemGK()==-1?"-":String.valueOf(value.getDiemGK()));
            JLabel diemCK=new JLabel(value.getDiemCK()==-1?"-":String.valueOf(value.getDiemCK()));
            JLabel diemKhac=new JLabel(value.getDiemKhac()==-1?"-":String.valueOf(value.getDiemKhac()));
            JLabel diemTong=new JLabel(value.getDiemTong()==-1?"-":String.valueOf(value.getDiemTong()));
            JLabel ketQua=new JLabel(value.getDiemTong()==-1?"Chưa có":value.getDiemTong()>=5?"Đậu":"Rớt");
            panel.add(stt);
            panel.add(maHocPhan);
            panel.add(tenMon);
            panel.add(phongHoc);
            panel.add(diemGK);
            panel.add(diemCK);
            panel.add(diemKhac);
            panel.add(diemTong);
            panel.add(ketQua);
        }
        else{
            if(type==2)panel=new JPanel(new GridLayout(1,9));
            else panel=new JPanel(new GridLayout(1,5));
            JLabel stt=new JLabel(String.valueOf(index+1));
            JLabel maSV=new JLabel(String.valueOf(value.getSinhVien().getMaSinhVien()));
            JLabel tenSV=new JLabel(value.getSinhVien().getHoTen());
            JLabel gioiTinhSV=new JLabel(value.getSinhVien().getGioiTinh()==1?"Nam":"Nữ");
            panel.add(stt);
            panel.add(maSV);
            panel.add(tenSV);
            panel.add(gioiTinhSV);
            if(type==2){
                if(value.getDiemTong()!=-1){
                    if(value.getDiemTong()>=5)MainLayout.soLuongDau++;
                }
                JLabel diemGK=new JLabel(value.getDiemGK()==-1?"-":String.valueOf(value.getDiemGK()));
                JLabel diemCK=new JLabel(value.getDiemCK()==-1?"-":String.valueOf(value.getDiemCK()));
                JLabel diemKhac=new JLabel(value.getDiemKhac()==-1?"-":String.valueOf(value.getDiemKhac()));
                JLabel diemTong=new JLabel(value.getDiemTong()==-1?"-":String.valueOf(value.getDiemTong()));
                JLabel ketQua=new JLabel(value.getDiemTong()==-1?"Chưa có":value.getDiemTong()>=5?"Đậu":"Rớt");
                panel.add(diemGK);
                panel.add(diemCK);
                panel.add(diemKhac);
                panel.add(diemTong);
                panel.add(ketQua);
            }else{
                JLabel CMND=new JLabel(String.valueOf(value.getSinhVien().getCMND()));
                panel.add(CMND);
            }

        }
        if (isSelected) {
            panel.setBackground(list.getSelectionBackground());
            panel.setForeground(list.getSelectionForeground());
        } else {
            panel.setBackground(list.getBackground());
            panel.setForeground(list.getForeground());
        }

        return panel;
    }
}
import pojo.PhucKhao;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PhucKhaoRenderer implements ListCellRenderer<PhucKhao> {
    public PhucKhaoRenderer(){

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends PhucKhao> list, PhucKhao value, int index, boolean isSelected, boolean cellHasFocus) {
        JTextPane data=new JTextPane();
        StringBuilder builder=new StringBuilder();
        builder.append("STT");
        builder.append(value.getStt());
        builder.append(" -- Trang Thai: ");
        switch (value.getTrangThai()){
            case 1:
                builder.append("Chua xem");
                break;
            case 2:
                builder.append("Khong cap nhat diem");
                break;
            default:
                builder.append("Da cap nhat diem");
                break;
        }
        builder.append(System.lineSeparator());
        builder.append("Sinh vien:          ");
        builder.append(String.valueOf(value.getSinhVien().getMaSinhVien())+" - "+value.getSinhVien().getHoTen());
        builder.append(System.lineSeparator());
        builder.append("Mon hoc:            ");
        builder.append(value.getMonHoc().getMaMon()+" - "+value.getMonHoc().getTenMon());
        builder.append(System.lineSeparator());
        builder.append("Cot diem:           ");
        String cot=value.getCotDiem()==1?"Diem giua ky":value.getCotDiem()==2?"Diem cuoi ky":value.getCotDiem()==3?
                "Diem khac":"Diem tong";
        builder.append(cot);
        builder.append(System.lineSeparator());
        builder.append("Diem mong muon:     ");
        builder.append(String.valueOf(value.getDiemMongMuon()));
        builder.append(System.lineSeparator());
        builder.append("Ly do:              ");
        builder.append(value.getLyDo());
        data.setText(builder.toString());
        if (isSelected) {
            data.setBackground(list.getSelectionBackground());
            data.setForeground(list.getSelectionForeground());
        } else {
            data.setBackground(list.getBackground());
            data.setForeground(list.getForeground());
        }
        return data;
    }
}

import pojo.HocPhan;

import javax.swing.*;
import java.awt.*;

public class HocPhanRenderer implements ListCellRenderer<HocPhan>{
    public HocPhanRenderer() {
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends HocPhan> list, HocPhan value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel=new JPanel(new GridLayout(1,5));
        JLabel stt=new JLabel(String.valueOf(index+1));
        JLabel maLop=new JLabel(value.getLop().getMaLop());
        JLabel maMon=new JLabel(value.getMonHoc().getMaMon());
        JLabel tenMon=new JLabel(value.getMonHoc().getTenMon());
        JLabel phongHoc=new JLabel(value.getPhongHoc());
        panel.add(stt);
        panel.add(maLop);
        panel.add(maMon);
        panel.add(tenMon);
        panel.add(phongHoc);
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

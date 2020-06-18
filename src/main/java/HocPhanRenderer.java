import pojo.HocPhan;

import javax.swing.*;
import java.awt.*;

public class HocPhanRenderer extends JLabel implements ListCellRenderer<HocPhan>{
    public HocPhanRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends HocPhan> list, HocPhan value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}

import pojo.HocLop;

import javax.swing.*;
import java.awt.*;

public class XemDiemRenderer extends JLabel implements ListCellRenderer<HocLop>{

    public int type=1;
    public XemDiemRenderer(int type){
        setOpaque(true);
        this.type=type;
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends HocLop> list, HocLop value, int index, boolean isSelected, boolean cellHasFocus) {
        if(type==1)setText(value.xemDiemCuaMotSV());
        if(type==2)setText(value.xemDiemCuaHocPhan());
        if(type==3)setText(value.xemDSSVThamGiaHP());
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
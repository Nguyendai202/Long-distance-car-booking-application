package cab.controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TooltipCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JComponent) {
            JComponent jComponent = (JComponent) component;
            if (table.getColumnName(column).equals("Route")) {
//                jComponent.setToolTipText(value.toString().replace('>','\n'));
//            } else {
//                jComponent.setToolTipText(null);
//            }
                String htmlValue = "<html><body>" + value.toString().replace(">", "<br>") + "</body></html>";
                jComponent.setToolTipText(htmlValue);
            } else {
                jComponent.setToolTipText(null);
            }


        }
        return component;
    }
}
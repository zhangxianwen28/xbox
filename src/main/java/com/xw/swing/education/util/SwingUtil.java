package com.xw.swing.education.util;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;

public class SwingUtil {
    public static void cleanTextField(Container contentPane) {
        Component[] components = contentPane.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    public static void fitTableColumns(JTable myTable, int columnNum) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            if (col == columnNum) {
                int width = (int) myTable.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(myTable, column.getIdentifier()
                                , false, false, -1, col).getPreferredSize().getWidth();
                for (int row = 0; row < rowCount; row++) {
                    int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                            myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                    width = Math.max(width, preferedWidth);
                }
                header.setResizingColumn(column); // 此行很重要
                column.setWidth(width + myTable.getIntercellSpacing().width);
            }

        }
    }
}

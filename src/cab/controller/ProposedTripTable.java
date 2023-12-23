
package cab.controller;

import cab.model.ConnectDatabase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class ProposedTripTable {
    private JTable table;
    private Object[] rowData = new Object[0];
    private CountDownLatch selectionLatch;
    //    private Object[] rowData;
    JLabel selectionLabel;

    public static void main(String[] args) {
    }

    public void createUI(String[][] data) {
        JFrame frame = new JFrame("Chuyến xe đề xuất");
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Source", "Destination", "license plates", "Price", "Phone", "Route"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Ngăn người dùng chỉnh sửa dữ liệu trên bảng
                return false;
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(5).setCellRenderer(new TooltipCellRenderer()); // Truyền table vào TooltipCellRenderer
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public String loadUsernameFromFile(String filePath) {
                String username = "";
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    username = bufferedReader.readLine();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return username;
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        rowData = new Object[table.getColumnCount()];
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            rowData[i] = table.getValueAt(selectedRow, i);
                        }
                        selectionLabel.setText("Bạn đã chọn chuyến số " + (selectedRow + 1));
                        System.out.println("Dòng đã chọn: " + Arrays.toString(rowData));
//                        processSelectedRow(rowData);
                        String license_plate = rowData[2].toString();
                        String price = rowData[3].toString();
                        String driver = rowData[4].toString();
                        String username = loadUsernameFromFile("username.txt");
                        ConnectDatabase c = new ConnectDatabase();
                        String str2 = "UPDATE intercityBOOK SET price = '" + price + "',BSX ='" + license_plate + "',driver ='" + driver + "' WHERE username ='" + username + "'";
                        try {
                            c.s.executeUpdate(str2);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
            }
        });
        selectionLabel = new JLabel("Vui lòng chọn chuyến xe mong muốn!");
        selectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(selectionLabel, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi đóng JFrame
        frame.setVisible(true);
    }

    private void processSelectedRow(Object[] rowData) {
        System.out.println("Dữ liệu đã chọn: " + Arrays.toString(rowData));
    }

    public Object[] getSelectedRowData() {
        return rowData;
    }


}
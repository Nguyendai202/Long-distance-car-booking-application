package cab.controller;


import cab.model.ConnectDatabase;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewCustomer extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private JLabel username;
    private JLabel infomation_text;
    private JLabel  number_info;
    private JLabel name;
    private JLabel lblRoomNumber;
    private JLabel gender,country,gmail,phone,address;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewCustomer frame = new ViewCustomer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ViewCustomer() throws SQLException {
        //conn = Javaconnect.getDBConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ấn x thì thoát khỏi chương trình
        setBounds(500, 220, 900, 680);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);//add vào cửa sổ
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/viewall.jpg"));
        Image i3 = i1.getImage().getScaledInstance(626, 201, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(0, 450, 626, 201);
        add(l1);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/viewall.jpg"));
        Image i5 = i4.getImage().getScaledInstance(626, 201, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(615, 450, 626, 201);
        add(l2);

        // Trước khi chèn dữ liệu, hãy tạo các biến JLabel để hiển thị thông tin từ cơ sở dữ liệu
        JLabel[] customerLabels = new JLabel[8]; // Số lượng thông tin khách hàng (8 là ví dụ, hãy thay đổi tùy theo số trường dữ liệu thực tế)

// Vị trí ban đầu của các label thông tin khách hàng
        int labelX = 10;
        int labelY = 40;
        int labelWidth = 90;
        int labelHeight = 14;
        int labelMargin = 100; // Khoảng cách giữa các label

        try {
            ConnectDatabase c = new ConnectDatabase();
            String displayCustomersql = "SELECT * FROM dbo.customer";
            ResultSet rs = c.s.executeQuery(displayCustomersql);

            int index = 0;
            while (rs.next()) {
                // Lấy thông tin từ ResultSet
                String usernameValue = rs.getString("username");
                String infomationTextValue = rs.getString("id_type");
                String numberInfoValue = rs.getString("number");
                String nameValue = rs.getString("name");
                String genderValue = rs.getString("gender");
                String countryValue = rs.getString("country");
                String addressValue = rs.getString("address");
                String phoneValue = rs.getString("phone");
                String gmailValue = rs.getString("email");

                // Tạo JLabel để hiển thị thông tin khách hàng và đặt vị trí

                //tên người dùng
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(usernameValue);
                labelX += labelMargin;
                //tên loại giấy tờ
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(infomationTextValue);
                labelX += labelMargin;

                // số giấy tờ
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(numberInfoValue);
                labelX += labelMargin;
                // tên
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(nameValue);
                labelX += labelMargin;
                // giới tính
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(genderValue);
                labelX += labelMargin;
                // quốc tịch
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(countryValue);
                labelX += labelMargin;
                // địa chỉ thường trú
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(addressValue);
                labelX += labelMargin;
                // số điện thoại
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX-10, labelY, labelWidth, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(phoneValue);
                labelX += labelMargin;
                //email
                customerLabels[index] = new JLabel();
                customerLabels[index].setBounds(labelX-20, labelY, labelWidth+100, labelHeight);
                contentPane.add(customerLabels[index]);
                customerLabels[index].setText(gmailValue);
                labelX += labelMargin;
                // Điều chỉnh vị trí y cho label của khách hàng tiếp theo
                labelY += 30;
                labelX = 10;

                index++;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JButton btnNewButton = new JButton("Quay lại");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnNewButton.setBounds(390, 400, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);

        username = new JLabel("Tên người dùng");
        username.setBounds(10, 15, 130, 14);
        contentPane.add(username);

        infomation_text = new JLabel("Loại giấy tờ");
        infomation_text.setBounds(110, 15, 76, 14);
        contentPane.add(infomation_text);

        number_info = new JLabel("Số giấy tờ");
        number_info.setBounds(210, 15, 70, 14);
        contentPane.add( number_info);

        name = new JLabel("Tên");
        name.setBounds(310, 15, 76, 14);
        contentPane.add(name);


        gender = new JLabel("Giới tính");
        gender.setBounds(400, 15, 90, 14);
        contentPane.add(gender);

        country = new JLabel("Quốc tịch");
        country.setBounds(510, 15, 90, 14);
        contentPane.add(country);

        address = new JLabel("Địa chỉ");
        address.setBounds(610, 15, 90, 14);
        contentPane.add(address);

        phone = new JLabel("SDT");
        phone.setBounds(710, 15, 90, 14);
        contentPane.add(phone);

        gmail = new JLabel("Email");
        gmail.setBounds(810, 15, 90, 14);
        contentPane.add(gmail);
        getContentPane().setBackground(Color.WHITE);
    }

}
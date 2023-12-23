package cab.controller;

import cab.model.ConnectDatabase;

import java.awt.*;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTruckingDetails extends JFrame {
    private JPanel contentPane;
    Choice c1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewTruckingDetails frame = new ViewTruckingDetails("");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewTruckingDetails(String username) {
        setBounds(450, 220, 1050, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/viewtrucking.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 370, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(450, 150, 550, 350);
        add(la1);

        JLabel lblName = new JLabel("DỊCH VỤ ĐÃ ĐẶT");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(88, 1, 350, 53);
        contentPane.add(lblName);

        JLabel lb3 = new JLabel("Tên người dùng:");
        lb3.setBounds(35, 70, 200, 14);
        contentPane.add(lb3);

        JLabel l1 = new JLabel();
        l1.setBounds(271, 70, 200, 14);
        contentPane.add(l1);

        JLabel lblId = new JLabel("Người lái:");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        JLabel l2 = new JLabel();
        l2.setBounds(271, 110, 200, 14);
        contentPane.add(l2);

        JLabel lb2 = new JLabel("Điểm đi:");
        lb2.setBounds(35, 150, 200, 14);
        contentPane.add(lb2);

        JLabel l3 = new JLabel();
        l3.setBounds(271, 150, 200, 14);
        contentPane.add(l3);

        JLabel lblName_1 = new JLabel("Điểm đến:");
        lblName_1.setBounds(35, 190, 200, 14);
        contentPane.add(lblName_1);

        JLabel l4 = new JLabel();
        l4.setBounds(271, 190, 200, 14);
        contentPane.add(l4);


        JLabel lblGender = new JLabel("Loại xe:");
        lblGender.setBounds(35, 230, 200, 14);
        contentPane.add(lblGender);

        JLabel l5 = new JLabel();
        l5.setBounds(271, 230, 200, 14);
        contentPane.add(l5);

        JLabel lblCountry = new JLabel("Khối lượng:");
        lblCountry.setBounds(35, 270, 200, 14);
        contentPane.add(lblCountry);

        JLabel l6 = new JLabel();
        l6.setBounds(271, 270, 200, 14);
        contentPane.add(l6);

        JLabel lblReserveRoomNumber = new JLabel("Khoảng cách:");
        lblReserveRoomNumber.setBounds(35, 310, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        JLabel l7 = new JLabel();
        l7.setBounds(271, 310, 200, 14);
        contentPane.add(l7);

        JLabel lblCheckInStatus = new JLabel("Giá:");
        lblCheckInStatus.setBounds(35, 350, 200, 14);
        contentPane.add(lblCheckInStatus);

        JLabel l8 = new JLabel();
        l8.setBounds(271, 350, 200, 14);
        contentPane.add(l8);

        ConnectDatabase c = new ConnectDatabase();
        try {
            ResultSet rs = c.s.executeQuery("select * from customer where username = '" + username + "'");
            String name = "";
            while (rs.next()) {
                l1.setText(rs.getString("name"));
                name = rs.getString("name");
            }
            rs = c.s.executeQuery("select * from intercityBOOK where name = '" + name + "'");
            while (rs.next()) {
                l2.setText(rs.getString("dname"));
                l3.setText(rs.getString("source"));
                l4.setText(rs.getString("destination"));
                l5.setText(rs.getString("truck"));
                l6.setText(rs.getString("weight"));
                l7.setText(rs.getString("distance"));
                l8.setText(rs.getString("price"));

            }


            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        JButton btnExit = new JButton("Quay lại");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnExit.setBounds(120, 490, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
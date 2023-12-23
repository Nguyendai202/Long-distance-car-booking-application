package cab.controller;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Font;
import javax.swing.JFrame;

public class About extends JFrame implements ActionListener {

    JButton exitButton;
    TextArea textArea;
    String string;

    public About() {

        setLayout(null);
        exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.setBounds(180, 430, 120, 20);
        exitButton.addActionListener(this);

        Font f = new Font("RALEWAY", Font.BOLD, 180);
        setFont(f);

        string = "                                    About Projects          \n  "
                + "\nProject này được thực hiện bởi các thành viên sau:\n\n"
                + "Nguyễn Văn Đài \n"
                + "Nguyễn Quốc Hiệu \n"
                + "Trần Anh Minh \n"
                + "Nguyễn Hoàng Thái\n\n"
                + "Với mục tiêu ứng dụng các cấu trúc dữ liệu đã học vào xây dựng một hệ thống " +
                "giúp người dân dễ dàng đặt xe để lại giữa các tỉnh từ khoảng cách địa lí vừa đến xa\n\n"
                + "Dự án này được kết thúc vào ngày 16/12/2023\n\n"
                + "Thời gian thực hiện : < 30 ngày"
        ;

        textArea = new TextArea(string, 10, 40, Scrollbar.VERTICAL);
        textArea.setEditable(false);
        textArea.setBounds(20, 100, 450, 300);

        add(textArea);

        Font f1 = new Font("RALEWAY", Font.BOLD, 16);
        textArea.setFont(f1);

        Container contentPane = this.getContentPane();
        textArea = new TextArea();

        JLabel l1 = new JLabel("About Project");
        add(l1);
        l1.setBounds(170, 10, 180, 80);
        l1.setForeground(Color.red);

        Font f2 = new Font("RALEWAY", Font.BOLD, 20);
        l1.setFont(f2);

        setBounds(700, 220, 500, 550);

        setLayout(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String args[]) {
        new About().setVisible(true);
    }

}

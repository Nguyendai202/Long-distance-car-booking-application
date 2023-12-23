package cab.controller;


import cab.model.ConnectDatabase;
import cab.view.Login;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {

    /*
jpanel: khung(container) chứa các thanh phần giao diện khác trong 1 khung(frame)  như JLabel, JTextField và JButton
     */
    private JPanel contentPane;
    /*
jtext: nhập và hiện thị data dưới dạng văn bản ngắn
     */
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_pasword;
    private JTextField textField_3;
    /*
    jbutton : Tạo các node bấm
     */
    private JButton b1, b2;
    private JComboBox comboBox;// tạo danh sách thả xuống cho người dùng chọn


    public static void main(String[] args) {
        new Signup().setVisible(true);
    }

    public Signup() {
        // hiển thị giao diện
        setBounds(800, 250, 700, 406);// set kích thước và cửa sổ trên màn hình
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// đặt đường viền rỗng để tạo khoảng trong xung quanh cửa sổ hiện ra
        setContentPane(contentPane);// đặt contenpane làm nội dung chínnh của cửa sổ
        contentPane.setBackground(Color.WHITE);// màu  nền là màu trắng
        contentPane.setLayout(null);// ko sử dụng bố cục tự động

        JLabel lblUsername = new JLabel("Tên người dùng :");
        lblUsername.setForeground(Color.DARK_GRAY);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setBounds(99, 86, 130, 26);
        contentPane.add(lblUsername);

        JLabel lblName = new JLabel("Tên :");
        lblName.setForeground(Color.DARK_GRAY);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(99, 123, 92, 26);// toạ độ khu vực nhập chữ
        contentPane.add(lblName);

        JLabel lblPassword = new JLabel("Mật khẩu :");
        lblPassword.setForeground(Color.DARK_GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPassword.setBounds(99, 160, 92, 26);
        contentPane.add(lblPassword);

        JLabel lblAnswer = new JLabel("Câu trả lời :");
        lblAnswer.setForeground(Color.DARK_GRAY);
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(99, 234, 92, 26);
        contentPane.add(lblAnswer);

        JLabel lblSecurityQuestion = new JLabel("Câu hỏi bảo mật :");
        lblSecurityQuestion.setForeground(Color.DARK_GRAY);
        lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSecurityQuestion.setBounds(99, 197, 140, 26);
        contentPane.add(lblSecurityQuestion);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{
                "Tên đặc biệt?", "Con số may mắn?", "Số lượng người yêu cũ?"}));
        comboBox.setBounds(265, 202, 148, 20);
        contentPane.add(comboBox);

        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/signup.png"));
        Image i1 = c1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);// điều chỉnh kết thước , trả về đối tượng

        ImageIcon i2 = new ImageIcon(i1);
        JLabel l6 = new JLabel(i2);// hiển thị hình ảnh đã điều chỉnh kích thước từ trước
        l6.setBounds(460, 70, 200, 200);
        contentPane.add(l6);
        textField = new JTextField();


        textField.setBounds(265, 91, 148, 20);
        contentPane.add(textField);
        textField.setColumns(10);// độ rộng vùng nhập dữ liệu

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(265, 128, 148, 20);
        contentPane.add(textField_1);

        textField_pasword = new JTextField();
        textField_pasword.setColumns(10);
        textField_pasword.setBounds(265, 165, 148, 20);
        contentPane.add(textField_pasword);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(265, 239, 148, 20);
        contentPane.add(textField_3);

        b1 = new JButton("Tạo");
        b1.addActionListener(this);
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(140, 289, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        b2 = new JButton("Quay lại");
        b2.addActionListener(this);// xử lí sự kiện trên actionPerformed(ActionEvent e).(this)
        b2.setFont(new Font("Tahoma", Font.BOLD, 13));
        b2.setBounds(300, 289, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        contentPane.add(b2);

        JPanel panel = new JPanel();
        panel.setForeground(new Color(20, 23, 20));
        // đường viền và tiêu đề nằm trên đường viền , căn le trasi
        //TitledBorder.LEADING: chữ từ trái sang phải
        panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 0), 2), "Create-Account",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(31, 30, 640, 310);// kích thước đường bao
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        // đc kích hoạt khi có sự kiện xẩy ra, ví dụ như bấm nút create or back
        try {
            ConnectDatabase con = new ConnectDatabase();

            if (ae.getSource() == b1) {

                String username = textField.getText();
                String name = textField_1.getText();
                String password = textField_pasword.getText();
                String question = (String) comboBox.getSelectedItem();
                String answer = textField_3.getText();

                String sql = "INSERT INTO account (username, password, question, answer, name) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = con.c.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, question);
                statement.setString(4, answer);
                statement.setString(5, name);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Đăng kí tài khoản thành công!");

                new Login().setVisible(true);// sang bước đăng nhập
                setVisible(false);
            }
            if (ae.getSource() == b2) {
                this.setVisible(false);
                new Login().setVisible(true);// vào đăng nhập luôn

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
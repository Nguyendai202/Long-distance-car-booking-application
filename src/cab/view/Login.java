package cab.view;


import cab.controller.ForgotPassword;
import cab.controller.Home;
import cab.controller.Signup;
import cab.model.ConnectDatabase;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JPanel panel;
    public JTextField textField;
    private JPasswordField passwordField;//nhập pass word
    private JButton b1, b2, b3;
    public String username;


    public Login() {
        // background cho panel
        setBackground(new Color(204, 255, 220, 255));
        setBounds(550, 250, 700, 500);// thiết lập kích thước cho 1 thành phần giao diện khi show thu gọn
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);//viền ở trên

        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/login_new2.jpg"));
        Image i1 = c1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);


        JLabel l6 = new JLabel(i2);
        l6.setBounds(0, 50, 700, 500);// kích và vị trí hình ảnh đc thêm vào
        add(l6);


        JLabel l1 = new JLabel("Tên người dùng: ");
        l1.setBounds(310, 0, 100, 24);
        l6.add(l1);

        JLabel l2 = new JLabel("Mật Khẩu: ");
        l2.setBounds(310, 40, 95, 24);
        l6.add(l2);

        textField = new JTextField();
        textField.setBounds(420, 0, 157, 20);
        l6.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(420, 40, 157, 20);
        l6.add(passwordField);
        b1 = new JButton("Đăng Nhập");
        b1.addActionListener(this);

        b1.setForeground(new Color(46, 139, 87));
        b1.setBackground(new Color(176, 224, 230));
        b1.setBounds(310, 80, 113, 25);// bao quanh login
        l6.add(b1);

        b2 = new JButton("Đăng Kí");
        b2.addActionListener(this);

        b2.setForeground(new Color(139, 69, 19));
        b2.setBackground(new Color(255, 235, 205));
        b2.setBounds(460, 80, 113, 25);
        l6.add(b2);

        b3 = new JButton("Quên Mật Khẩu");
        b3.addActionListener(this);
        b3.setForeground(new Color(205, 92, 92));
        b3.setBackground(new Color(253, 245, 230));
        b3.setBounds(360, 120, 179, 25);
        l6.add(b3);

//	JLabel l5 = new JLabel("Trouble in Login?");
//	l5.setFont(new Font("Tahoma", Font.PLAIN, 15));
//	l5.setForeground(new Color(255, 0, 0));
//	l5.setBounds(240, 120, 110, 20);
//	l6.add(l5);


    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            Boolean status = false;
            try {
                ConnectDatabase con = new ConnectDatabase();

                String sql = "SELECT * FROM dbo.account where username=? and password=?";
                PreparedStatement st = con.c.prepareStatement(sql);// cho phép điền giá trị vào ? Trước khi truy vấn
                st.setString(1, textField.getText());// Thiết lập giá trị cho tham số đầu tiên
                st.setString(2, passwordField.getText());// Thiết lập giá trị cho tham số thứ 2

                ResultSet rs = st.executeQuery();// thực hiện truy vấn
                if (rs.next()) {
                    //check xem truy vấn có trả về kết quả hay ko ,
                    // sau đó sẽ hiển thị ra cửa sổ Home thông báo
                    this.setVisible(false);
                    username = textField.getText();
                    try {
                        FileWriter writer = new FileWriter("username.txt");
                        writer.write(username);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Home(username).setVisible(true);
                } else
                    JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!");

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ae.getSource() == b2) {
            setVisible(false);// ko hiển thị đối tượng hiện tại
            Signup su = new Signup();
            su.setVisible(true);
        }
        if (ae.getSource() == b3) {
            setVisible(false);
            ForgotPassword forgot = new ForgotPassword();
            forgot.setVisible(true);
        }
    }


}
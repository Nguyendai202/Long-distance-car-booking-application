package cab.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.*;

import cab.model.ConnectDatabase;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import static cab.model.Trip.TestTripManager.book;

public class BookIntercityCoach extends JFrame {
    JLabel ReservationDetails, Source, Destination;
    JButton submit, b1, b2, datePickerButton, selectSource, selectDes;
    private JTextField textFieldPrice, dateTextField;

    public static void main(String[] args) {
        new BookIntercityCoach("");
    }

    public BookIntercityCoach(String username) {
        setTitle("Book Coach");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ReservationDetails = new JLabel("ĐẶT XE LIÊN TỈNH");
        ReservationDetails.setForeground(Color.BLUE);
        ReservationDetails.setFont(new Font("Tahoma", Font.PLAIN, 31));
        ReservationDetails.setBounds(280, 27, 359, 50);
        add(ReservationDetails);
        setLayout(null);

        Source = new JLabel("ĐIỂM ĐI");
        Source.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Source.setBounds(50, 100, 70, 27);
        add(Source);

        selectSource = new JButton("Chọn");
        selectSource.setBackground(Color.BLACK);
        selectSource.setForeground(Color.WHITE);
        selectSource.setBounds(200, 100, 100, 30);
        add(selectSource);

        Destination = new JLabel("ĐIỂM ĐẾN");
        Destination.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Destination.setBounds(370, 100, 150, 27);
        add(Destination);

        selectDes = new JButton("Chọn");
        selectDes.setBackground(Color.BLACK);
        selectDes.setForeground(Color.WHITE);
        selectDes.setBounds(500, 100, 100, 30);
        add(selectDes);

        JLabel l9 = new JLabel("Tên: ");
        l9.setFont(new Font("Tahoma", Font.PLAIN, 19));
        l9.setBounds(50, 150, 250, 27);
        add(l9);

        JLabel showName = new JLabel(""); // phần để show ra name
        showName.setFont(new Font("Tahoma", Font.PLAIN, 19));
        showName.setBounds(200, 150, 150, 27);
        add(showName);

        JLabel l11 = new JLabel("Tên người dùng: ");
        l11.setFont(new Font("Tahoma", Font.PLAIN, 19));
        l11.setBounds(50, 200, 150, 27);
        add(l11);

        JLabel showUsername = new JLabel("");//// phần để show ra username
        showUsername.setFont(new Font("Tahoma", Font.PLAIN, 19));
        showUsername.setBounds(200, 200, 350, 27);
        add(showUsername);

        try {
            ConnectDatabase c = new ConnectDatabase();
            ResultSet rs = c.s.executeQuery("select DISTINCT source, destination from intercity");
//            while (rs.next()) {
//                c1.add(rs.getString("source"));
//                c2.add(rs.getString("destination"));
//            }

            rs = c.s.executeQuery("select * from account where username = '" + username + "'");
            while (rs.next()) {
                showName.setText(rs.getString("name"));
                showUsername.setText(username);
            }

            rs.close();
        } catch (SQLException e) {
        }


        JLabel Type = new JLabel("Loại xe: ");
        Type.setFont(new Font("Tahoma", Font.PLAIN, 19));
        Type.setBounds(50, 245, 150, 27);
        add(Type);

        Choice c3 = new Choice();
        c3.add("xe ghế ngồi");
        c3.add("xe ghế nằm");
        c3.setBounds(200, 250, 100, 67);
        add(c3);

        submit = new JButton("NỘP");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(420, 395, 100, 27);
        add(submit);

        JLabel price = new JLabel("Giá mong muốn: ");
        price.setFont(new Font("Tahoma", Font.PLAIN, 19));
        price.setBounds(50, 300, 150, 27);
        add(price);
        textFieldPrice = new JTextField();
        textFieldPrice.setBounds(200, 300, 100, 27);
        add(textFieldPrice);

        JLabel Date = new JLabel("Ngày về: ");
        Date.setFont(new Font("Tahoma", Font.PLAIN, 19));
        Date.setBounds(50, 350, 250, 27);
        add(Date);
        dateTextField = new JTextField(10);
        dateTextField.setBounds(200, 350, 100, 27);
        add(dateTextField);


        JLabel bsx = new JLabel("Ưu tiên: ");
        bsx.setFont(new Font("Tahoma", Font.PLAIN, 19));
        bsx.setBounds(50, 395, 150, 27);
        add(bsx);

        Choice pritymode = new Choice();
        pritymode.add("Giá rẻ");
        pritymode.add("Khoảng cách gần");
        pritymode.setBounds(200, 400, 210, 67);
        add(pritymode);

        JLabel showBSX = new JLabel("");// phần để show ra bsx từ database
        showBSX.setFont(new Font("Tahoma", Font.PLAIN, 19));
        showBSX.setBounds(200, 400, 450, 27);
        add(showBSX);
//        JLabel driverLabel = new JLabel("Driver : ");
//        driverLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
//        driverLabel.setBounds(50, 450, 150, 27);
//        add(driverLabel);
//        JLabel showDriver = new JLabel("");// phần để hiện thị tên lái xe
//        showDriver.setFont(new Font("Tahoma", Font.PLAIN, 19));
//        showDriver.setBounds(200, 450, 450, 27);
//        add(showDriver);

//        add(mainPanel);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/intercoach.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l16 = new JLabel(i3);
        l16.setBounds(480, 150, 300, 300);
        add(l16);

        JLabel l13 = new JLabel("ID: ");
        l13.setFont(new Font("Tahoma", Font.PLAIN, 19));
        l13.setBounds(650, 450, 50, 27);
        add(l13);

        JLabel showRef = new JLabel();// phần để show text lên
        showRef.setFont(new Font("Tahoma", Font.PLAIN, 19));
        showRef.setBounds(700, 450, 250, 27);
        add(showRef);

        Random r = new Random();
        showRef.setText("" + Math.abs(r.nextInt() % 100000));
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    String pritymodel = pritymode.getSelectedItem();
                    System.out.println(pritymodel);
                    //biến1
                    String price = textFieldPrice.getText();
                    //biến2
                    String date = dateTextField.getText();
                    ConnectDatabase c = new ConnectDatabase();
                    String str = "UPDATE intercity SET price = '" + price + "', date = '" + date + "' WHERE username ='" + username + "'";
                    c.s.executeUpdate(str);
                    String source_s = "SELECT source FROM dbo.intercity WHERE username = ?";
                    PreparedStatement statement = c.c.prepareStatement(source_s);
                    statement.setString(1, username);
                    ResultSet rs = statement.executeQuery();
                    String source = "";
                    if (rs.next()) {
                        //biến3
                        String source_sql = rs.getString("source");
                        source = source_sql;
                        System.out.println(source);
                    } else {
                        System.out.println("Không có dữ liệu trong cột source");
                    }
                    String des_s = "SELECT destination FROM intercity WHERE username = ?";
                    PreparedStatement statement2 = c.c.prepareStatement(des_s);
                    statement2.setString(1, username);
                    ResultSet des = statement2.executeQuery();
                    String destination = "";
                    if (des.next()) {
                        //biến4
                        String destination_sql = des.getString(1);
                        destination = destination_sql;
                        System.out.println(destination);
                    } else {
                        System.out.println("Không có dữ liệu trong cột destination");
                    }

                    String[][] table = book(source, destination, pritymodel);
                    if (table == null) {
                        JOptionPane.showMessageDialog(null, "Chuyến xe bạn đang tìm hiện chưa được cập nhật!");

                    }
                    // thuật toán sẽ nhận 4 biến trên và trả ra thông tin để ghi vào ProposedTripTable
                    SwingUtilities.invokeLater(() -> {
                        String[][] data = table;
                        try {
                            c.s.executeUpdate("delete from intercity");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);

                        }


                        ProposedTripTable example = new ProposedTripTable();
                        example.createUI(data);
                    });
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, " Chuyến xe bạn đang tìm hiện chưa được cập nhật!: " + e.getMessage());
                }
            }
        });
        selectSource.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainSrcAddress frame = new mainSrcAddress();

            }

            ;
        });
        selectDes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mainDesAdress frame = new mainDesAdress();
            }

            ;
        });


        b1 = new JButton("Đặt xe");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(50, 500, 150, 30);
        add(b1);
        datePickerButton = new JButton("Chọn ngày");
        datePickerButton.addActionListener(e -> showDatePicker());
        datePickerButton.setBackground(Color.BLACK);
        datePickerButton.setForeground(Color.WHITE);
        datePickerButton.setBounds(310, 350, 100, 27);
        add(datePickerButton);

        b1.addActionListener(new ActionListener() {
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

            public void actionPerformed(ActionEvent ae) {
                try {
//                    String src = c1.getSelectedItem();
//                    String dst = c2.getSelectedItem();
                    String type = c3.getSelectedItem();
                    String pritymodel = pritymode.getSelectedItem();
                    System.out.println(pritymodel);
                    String price = textFieldPrice.getText();
                    String BSX = showBSX.getText();
//                    String driver = showDriver.getText();
                    String name = showName.getText();
                    String ref = showRef.getText();
                    String date = dateTextField.getText();
                    String username = loadUsernameFromFile("username.txt");
                    ConnectDatabase c = new ConnectDatabase();
//                    c.s.executeUpdate("delete from intercityBOOK");
//                    String str = "UPDATE intercityBOOK SET driver = '" + driver + "',type = '" + type + "',BSX =  '" + BSX + "',price =  '" + price + "',date =  '" + date + "',id ='" + ref + "', " +
//                            "WHERE username = " + username + "'";
//                    c.s.executeUpdate(str);
                    String sql = "UPDATE intercityBOOK SET type = ?, date = ?, id = ? WHERE username = ?";
                    PreparedStatement statement = c.c.prepareStatement(sql);
                    statement.setString(1, type);
                    statement.setString(2, date);
                    statement.setString(3, ref);
                    statement.setString(4, username);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cab Booked Successfully");
                    setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        b2 = new JButton("Thoát");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(250, 500, 150, 30);
        add(b2);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });


        setSize(860, 600);
        setLocation(500, 220);
        setVisible(true);

    }

    private void showDatePicker() {
        JFrame datePickerFrame = new JFrame();
        datePickerFrame.setTitle("Date Picker");
        datePickerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        datePickerFrame.setSize(300, 200);
        datePickerFrame.setLocationRelativeTo(null);
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();
        JDatePicker datePicker = dateComponentFactory.createJDatePicker();
        datePickerFrame.add((JComponent) datePicker);
        datePickerFrame.setVisible(true);
        datePicker.addActionListener(e -> {
            GregorianCalendar selectedDate = (GregorianCalendar) datePicker.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(selectedDate.getTime());
            dateTextField.setText(formattedDate);
        });
    }

}

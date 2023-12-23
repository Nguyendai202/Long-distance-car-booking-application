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
public class AddCustomer extends JFrame {
	Connection consql = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField numberIDTextField,lblName_1_TextField,t3,t4,t5,t6,userNameTextField,t8;
        JComboBox comboBox;// thanh tuỳ chọn
        JRadioButton r1,r2;//chọn theo kiểu tích vào
        Choice c1;// các thành phần tuỳ chọn

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//EventQueue.invokeLater: tránh lỗi liên quan đến đa luồng
			public void run() {
				try {
					AddCustomer frame = new AddCustomer("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddCustomer(String username) throws SQLException {
		System.out.println(username);
                setBounds(500, 220, 850, 550);
		contentPane = new JPanel();// khởi tạo
		setContentPane(contentPane);// add vào jframe
		contentPane.setLayout(null);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/newcustomer.jpg"));
                Image i3 = i1.getImage().getScaledInstance(450, 500,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(450,40,450,420);//vị trí hình ảnh
                add(l1);
		
		JLabel lblName = new JLabel("CẬP NHẬT THÔNG TIN CỦA BẠN ĐỂ ĐẶT XE");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
		lblName.setBounds(100, 11, 400, 30);
		contentPane.add(lblName);
                
                JLabel usernameLabel = new JLabel("Tên người dùng:");
		usernameLabel.setBounds(35, 70, 200, 14);
		contentPane.add( usernameLabel);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(271, 70, 150, 20);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
                
                JLabel lblId = new JLabel("Giấy tờ:");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
                
                comboBox = new JComboBox(new String[] {"CCCD", "Hộ chiếu"});
		comboBox.setBounds(271, 110, 150, 20);
		contentPane.add(comboBox);
                
                JLabel numberID = new JLabel("Số giấy tờ:");
		numberID.setBounds(35, 150, 200, 14);
		contentPane.add(numberID);
                
                numberIDTextField = new JTextField();
		numberIDTextField.setBounds(271, 150, 150, 20);
		contentPane.add(numberIDTextField);
		numberIDTextField.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Tên:");
		lblName_1.setBounds(35, 190, 200, 14);
		contentPane.add(lblName_1);

		lblName_1_TextField = new JTextField();
		lblName_1_TextField.setBounds(271, 190, 150, 20);
		contentPane.add(lblName_1_TextField);
		lblName_1_TextField.setColumns(10);

                
		JLabel lblGender = new JLabel("Giới tính:");
		lblGender.setBounds(35, 230, 200, 14);
		contentPane.add(lblGender);
                
                r1 = new JRadioButton("Nam");
                r1.setFont(new Font("Raleway", Font.BOLD, 14));
                r1.setBackground(Color.WHITE);
                r1.setBounds(271, 230, 80, 12);
                add(r1);
                
                r2 = new JRadioButton("Nữ");
                r2.setFont(new Font("Raleway", Font.BOLD, 14));
                r2.setBackground(Color.WHITE);
                r2.setBounds(350, 230, 100, 12);
		add(r2);
                
		JLabel lblCountry = new JLabel("Quốc tịch:");
		lblCountry.setBounds(35, 270, 200, 14);
		contentPane.add(lblCountry);
                
                t3 = new JTextField();
		t3.setBounds(271, 270, 150, 20);
		contentPane.add(t3);
		t3.setColumns(10);
		
		JLabel lblReserveRoomNumber = new JLabel("Địa chỉ thường trú:");
		lblReserveRoomNumber.setBounds(35, 310, 200, 14);
		contentPane.add(lblReserveRoomNumber);
                
                t5 = new JTextField();
		t5.setBounds(271, 310, 150, 20);
		contentPane.add(t5);
		t5.setColumns(10);
           	
		JLabel lblCheckInStatus = new JLabel("SDT:");
		lblCheckInStatus.setBounds(35, 350, 200, 14);
		contentPane.add(lblCheckInStatus);
                
                t6 = new JTextField();
		t6.setBounds(271, 350, 150, 20);
		contentPane.add(t6);
		t6.setColumns(10);
		
		JLabel lblDeposite = new JLabel("Email:");
		lblDeposite.setBounds(35, 390, 200, 14);
		contentPane.add(lblDeposite);
		
		t8 = new JTextField();
		t8.setBounds(271, 390, 150, 20);
		contentPane.add(t8);
		t8.setColumns(10);
		
		
                try{
                    ConnectDatabase c = new ConnectDatabase();
                    ResultSet rs = c.s.executeQuery("select * from account where username = '"+username+"'");
                    while(rs.next()){
                        userNameTextField.setText(rs.getString("username"));
						lblName_1_TextField.setText(rs.getString("name"));
                    }
                }catch(Exception e){ }
		
		

		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            ConnectDatabase c = new ConnectDatabase();
                            String radio = null;
                            
                            if(r1.isSelected()){ 
                                radio = "Male";
                            }
                            else if(r2.isSelected()){ 
                                radio = "Female";
                            }
                           
                          
                            try{
	    			String s9 = userNameTextField.getText(); //username
								String s1 = (String)comboBox.getSelectedItem();
	    			String s2 = numberIDTextField.getText();
	    			String s3 =  lblName_1_TextField.getText();
					String s4 =  radio;
	    			String s5 =  t3.getText();
	    			String s7 =  t5.getText();  //address
                                String s8 =  t6.getText();
                                String s10 = t8.getText(); //email
                                
                                String q1 = "insert into customer values('"+s9+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s7+"','"+s8+"','"+s10+"')";
                                c.s.executeUpdate(q1);
                                
	    			JOptionPane.showMessageDialog(null, "Thành công!");
                                setVisible(false);
	    		}catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
		    		catch(NumberFormatException s){
		    			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
			}
			}
		});
		btnNewButton.setBounds(100, 430, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Quay lại");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(260, 430, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);
	}
}
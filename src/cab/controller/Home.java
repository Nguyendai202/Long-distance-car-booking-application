package cab.controller;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Home extends JFrame{
    String username;

    public static void main(String[] args) {
        new Home("").setVisible(true);
    }
    
    public Home(String username) {
        super("Cab Booking System");
	this.username = username;
        setForeground(Color.CYAN);
        setLayout(null); 

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/home.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 750,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); 
	JLabel NewLabel = new JLabel(i3);
	NewLabel.setBounds(0, 0, 1550, 750);
        add(NewLabel);
        
        JLabel l1 = new JLabel("HỆ THỐNG ĐẶT XE ĐƯỜNG DÀI VIỆT NAM");
	l1.setForeground(Color.RED);
        l1.setFont(new Font("Tahoma", Font.BOLD, 30));
	l1.setBounds(440, 60, 1000, 100);// vị trí cua l1
	NewLabel.add(l1);
		
		
        JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
		
        JMenu m1 = new JMenu("CÁ NHÂN");
        m1.setForeground(Color.BLUE);
	menuBar.add(m1);
		
        JMenuItem mi1 = new JMenuItem("THÊM THÔNG TIN");
	m1.add(mi1);
        
        JMenuItem mi2 = new JMenuItem("CẬP NHẬT THÔNG TIN");
	m1.add(mi2);
        
        JMenuItem mi3 = new JMenuItem("XEM THÔNG TIN");
	m1.add(mi3);
        
        JMenuItem mi4 = new JMenuItem("XOÁ THÔNG TIN");
	m1.add(mi4);
        
        mi1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new AddCustomer(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new UpdateCustomer(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new ViewCustomer().setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new DeleteCustomer().setVisible(true);
                }catch(Exception e ){}
            }
	});

        
        
        JMenu m3 = new JMenu("ĐẶT XE");
        m3.setForeground(Color.BLUE);
	menuBar.add(m3);
        
        JMenuItem mi8 = new JMenuItem("XEM XE");
	m3.add(mi8);
        
        JMenuItem mi9 = new JMenuItem("ĐẶT CHUYẾN");
	m3.add(mi9);
        
        JMenuItem mi10 = new JMenuItem("XEM LỊCH SỬ ĐẶT");
	m3.add(mi10);
        
        mi8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new ViewCoachs().setVisible(true);
            }
	});
        
        
        
	mi9.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new BookIntercityCoach(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        mi10.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new ViewBookedCoach(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        JMenu m8 = new JMenu("VẬN CHUYỂN");
        m8.setForeground(Color.RED);
	menuBar.add(m8);
        
        JMenuItem mi16 = new JMenuItem("ĐẶT DỊCH VỤ");
	m8.add(mi16);
        
        JMenuItem mi17 = new JMenuItem("DỊCH VỤ ĐÃ ĐẶT");
	m8.add(mi17);
        
        mi16.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Trucking(username).setVisible(true);
            }
	});
        
        
        
	mi17.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new ViewTruckingDetails(username).setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        JMenu m9 = new JMenu("HOÁ ĐƠN");
        m9.setForeground(Color.BLUE);
	menuBar.add(m9);
        
        JMenuItem mi18 = new JMenuItem("KIỂM TRA HOÁ ĐƠN");
	m9.add(mi18);
        
        mi18.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new CheckBill(username).setVisible(true);
                }catch(Exception e){ }
            }
	});
        
        
        JMenu m5 = new JMenu("THANH TOÁN");
        m5.setForeground(Color.RED);
	menuBar.add(m5);
        
        JMenuItem mi12 = new JMenuItem("THANH TOÁN TẠI NGÂN HÀNG");
	m5.add(mi12);
        
        mi12.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Payment().setVisible(true);
            }
	});
        
        JMenu m6 = new JMenu("TÍNH NĂNG");
        m6.setForeground(Color.BLUE);
	menuBar.add(m6);
        
        JMenuItem mi13 = new JMenuItem("GHI CHÚ");
	m6.add(mi13);
        
        JMenuItem mi14 = new JMenuItem("MÁY TÍNH TAY");
	m6.add(mi14);
        
        mi13.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    Runtime.getRuntime().exec("notepad.exe");
                }catch(Exception e){ }
            }
	});
        
        
        mi14.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    Runtime.getRuntime().exec("calc.exe");
                }catch(Exception e){ }
            }
	});
        
        JMenu m7 = new JMenu("PHẢN HỒI");
        m7.setForeground(Color.RED);
	menuBar.add(m7);
        
        JMenuItem mi15 = new JMenuItem("ABOUT");
	m7.add(mi15);
        
        mi15.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new About().setVisible(true);
            }
	});
        
        
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); //mở roojng cửa sổ jframe toàn màn hình
	setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }
    public String getUsername() {
        return username;
    }
}

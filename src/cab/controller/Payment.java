package cab.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Prity
 */
public class Payment extends JFrame{
    
    public Payment(){
        
        setLayout(null);
        setBounds(600, 220, 800, 600);
        
        JLabel label=new JLabel("NGÂN HÀNG THANH TOÁN");
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        label.setBounds(80, 20, 350, 45);
        add(label);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/bank.png"));
        Image i8 = i7.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l4 = new JLabel(i9);
        l4.setBounds(80, 130, 700, 400);
        add(l4);
        
        JButton pay = new JButton("Thanh toán");
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Paid successfully");
            }
        });
        pay.setBounds(420, 20, 100, 40);
        add(pay);
    
        JButton back=new JButton("Quay lại");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        back.setBounds(530, 20, 80, 40);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

    }

    public static void main(String[] args){
        new Payment().setVisible(true);
    }
    
}

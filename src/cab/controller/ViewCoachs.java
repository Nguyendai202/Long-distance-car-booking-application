package cab.controller;


import javax.swing.*;
import java.awt.*;

public class ViewCoachs extends JFrame implements Runnable {

    JLabel  l2, l3;
    JLabel caption;
    Thread th;

    public void run() {
        try {

            l2.setVisible(true);
            caption.setText("Xe khách ghế ngồi");
            l2.add(caption);
            Thread.sleep(3800);
            l2.setVisible(false);// tắt
            l3.setVisible(true);
            caption.setText("Xe khách ghế nằm");
            l3.add(caption);
            Thread.sleep(3800);// thời gian chờ
            l3.setVisible(false);
            Thread.sleep(3800);
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ViewCoachs() {

        setBounds(500, 220, 900, 700);
        getContentPane().setBackground(new Color(220, 250, 250));

        th = new Thread(this);
        // tạo title cho xe
        caption = new JLabel();
        caption.setBounds(275, 550, 1000, 70);
        caption.setForeground(Color.magenta);
        caption.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(caption);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("cab/model/icons/xekhachngoi.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 434, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l2 = new JLabel(i3);
        l2.setBounds(0, 0, 900, 700);
        add(l2);

        setLayout(null);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("cab/icons/xekhachnam.jpg"));
        Image i5 = i4.getImage().getScaledInstance(650, 434, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        l3 = new JLabel(i4);
        l3.setBounds(0, 0, 900, 700);
        add(l3);
        l3.setVisible(false);
        th.start();

    }

    public static void main(String args[]) {
        new ViewCoachs().setVisible(true);

    }

}

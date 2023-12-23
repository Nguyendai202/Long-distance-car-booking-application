package cab.controller;

import cab.controller.DesAdress;

import javax.swing.*;
import java.io.IOException;

public class mainDesAdress extends JFrame {
    public mainDesAdress() {
        setTitle("Address");
//        setBounds(110, 100, 250, 350);
        setSize(210, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            DesAdress address = new DesAdress(this);
            setContentPane(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);

    }

    public static void main(String[] args) {
//        mainDesAdress frame = new mainDesAdress();
        // Sau khi frame hiển thị và người dùng đã chọn địa chỉ
    }
}
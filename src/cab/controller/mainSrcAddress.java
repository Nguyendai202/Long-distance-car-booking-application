package cab.controller;

import javax.swing.*;
import java.io.IOException;

public class mainSrcAddress extends JFrame {
    public mainSrcAddress() {
        setTitle("Address");
//        setBounds(110, 100, 250, 350);
        setSize(210, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            SourceAddress address = new SourceAddress(this);
            setContentPane(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);

    }
    public static void main(String[] args) {
//        mainSrcAddress frame = new mainSrcAddress();
        // Sau khi frame hiển thị và người dùng đã chọn địa chỉ
    }
}


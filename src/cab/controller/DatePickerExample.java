package cab.controller;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerExample extends JFrame {
    private JTextField dateTextField;

    public DatePickerExample() {
        setTitle("Date Picker Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        dateTextField = new JTextField(10);
        JButton datePickerButton = new JButton("Select Date");

        datePickerButton.addActionListener(e -> showDatePicker());

        mainPanel.add(dateTextField);
        mainPanel.add(datePickerButton);

        add(mainPanel);
    }

    private void showDatePicker() {
        JFrame datePickerFrame = new JFrame();
        datePickerFrame.setTitle("Date Picker");
        datePickerFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        datePickerFrame.setSize(300, 200);
        datePickerFrame.setLocationRelativeTo(null);
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();
        JDatePicker datePicker = dateComponentFactory.createJDatePicker();
        datePicker.addActionListener(e -> {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(selectedDate);
            dateTextField.setText(formattedDate);

            datePickerFrame.dispose();
        });

        datePickerFrame.add((JComponent) datePicker);
        datePickerFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatePickerExample().setVisible(true));
    }
}
package cab.controller;

import cab.model.ConnectDatabase;
import cab.model.Map.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DesAdress extends JPanel {
    private JComboBox<String> cities;
    private JComboBox<String> districts;
    private JComboBox<String> wards;
    private JButton button;
    private Country country;
    public static String address;
    public static double[] foundCoordinates;

    private JFrame parentFrame;

    public DesAdress(JFrame parentFrame) throws IOException {
        setLayout(null);
        setBounds(0, 0, 400, 700);
        setBackground(Color.white);

        cities = new JComboBox<>();
        districts = new JComboBox<>();
        wards = new JComboBox<>();
        button = new JButton("Nộp");

        this.parentFrame = parentFrame;
        setupComponents();
        setActions();
    }

    private void setupComponents() throws IOException {
        country = Places.addData();

        JLabel cityLabel = new JLabel("Select a city:");
        JLabel districtLabel = new JLabel("Select a district:");
        JLabel wardLabel = new JLabel("Select a ward:");

        cityLabel.setBounds(0, 15, 250, 20);
        cities.setBounds(0, 40, 200, 25);
        districtLabel.setBounds(0, 85, 250, 20);
        districts.setBounds(0, 110, 200, 25);
        wardLabel.setBounds(0, 165, 200, 25);
        wards.setBounds(0, 190, 200, 25);

        button.setLayout(null);
        button.setBounds(40, 250, 100, 30);
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.setBackground(Color.orange);

        add(cityLabel);
        add(cities);
        add(districtLabel);
        add(districts);
        add(wardLabel);
        add(wards);
        add(button);

        setVisible(true);
    }

    private void setActions() {
        ArrayList<City> citiesList = country.getCities();
        for (City city : citiesList) cities.addItem(city.getName());

        cities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent cityEvent) {
                JComboBox<String> comboBox = (JComboBox<String>) cityEvent.getSource();
                String cityName = (String) comboBox.getSelectedItem();
                if (cityName != null) {
                    districts.removeAllItems();
                    City cityObj = country.search(cityName);
                    ArrayList<District> districtsList = cityObj.getDistricts();
                    for (District district : districtsList) districts.addItem(district.getName());
                    clear();
                }
            }
        });

        districts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent districtEvent) {
                JComboBox<String> districtCbb = (JComboBox<String>) districtEvent.getSource();
                String districtName = (String) districtCbb.getSelectedItem();
                if (districtName != null) {
                    wards.removeAllItems();
                    String cityName = cities.getSelectedItem().toString();
                    City cityObj = country.search(cityName);
                    District districtObj = cityObj.search(districtName);
                    List<Stopover> stopoversList = districtObj.getStopovers();
                    for (Stopover s : stopoversList) wards.addItem(s.getName());
                    clear();
                }
            }
        });

        button.addActionListener(new ActionListener() {
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

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedCity = cities.getModel().getSelectedItem().toString();
                    String selectedDistrict = districts.getModel().getSelectedItem().toString();
                    String selectedWard = wards.getModel().getSelectedItem().toString();
                    address = selectedCity + "," + selectedDistrict + "," + selectedWard;
                    String username = loadUsernameFromFile("username.txt");
                    ConnectDatabase c = new ConnectDatabase();
                    String str = "UPDATE intercity SET destination = N'"+address+"' WHERE username ='"+username+"'";
                    c.s.executeUpdate(str);
                    String str2 = "UPDATE intercityBOOK SET destination = N'"+address+"' WHERE username ='"+username+"'";
                    c.s.executeUpdate(str2);

                    foundCoordinates = getCoordinates(selectedCity, selectedDistrict, selectedWard);
                    revalidate();
                    repaint();
                    parentFrame.dispose();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    private void clear() {
        revalidate();
        repaint();
    }

    private double[] getCoordinates(String city, String district, String ward) {
        City cityObj = country.search(city);
        District districtObj = cityObj.search(district);
        Stopover stopoverObj = districtObj.search(ward);
        double latitude = stopoverObj.getLatitude();
        double longitude = stopoverObj.getLongitude();
        return new double[]{latitude, longitude};
    }

    public String getAddress() {
        return address;
    }

    public double[] getFoundCoordinates() {
        return foundCoordinates;
    }
}
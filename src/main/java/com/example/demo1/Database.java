package com.example.demo1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.*;
import java.util.ArrayList;

class Database {


    public static boolean isConnected = false;
    private static final String HOST = "localhost";
    private static Statement stmt;

    public static ArrayList<Car> cars;

    public static ArrayList<Car> getCarsWith(String make, String model, int year) {
        ArrayList<Car> carsWithList = new ArrayList<>();

        for (Car c :
                cars) {
            if ((c.getMake().equals(make) || make == null) && (c.getModel().equals(model) || model == null) && (year == c.getYear() || year == -1)) {
                carsWithList.add(c);
            }
        }
        return carsWithList;
    }

    public Database() {

    }

    public static double getDistance(String city1, String city2) {
        double answer = -1;
        try {
            Document doc = Jsoup.connect("https://www.google.com/search?q=how+many+miles+ " + city1 + "+to+" + city2 + "&sxsrf=ALiCzsaY_3oNFt_RtgM4XufBckJexHETtw%3A1651612187687&ei=G5pxYvvEKfekptQP6qyDgA4&ved=0ahUKEwi7voPunsT3AhV3kokEHWrWAOAQ4dUDCA8&uact=5&oq=how+many+miles+kansas+city+to+saint+louis&gs_lcp=Cgdnd3Mtd2l6EAMyBwgjELACECc6BwgAEEcQsANKBAhBGABKBAhGGABQgQtYkw9g-A9oAXABeACAAZkBiAGKBZIBAzAuNZgBAKABAcgBCMABAQ&sclient=gws-wiz").get();

            Elements repositories = doc.getAllElements();
            System.out.println(repositories.size());
            System.out.println("Web Scraping " + doc.title());
            int i = 0;

            String mileString = repositories.get(97).text();

            System.out.println(mileString);
            mileString = mileString.replaceAll(",", "");
            answer = Double.parseDouble(mileString.substring(mileString.indexOf("(") + 1, mileString.indexOf("mi)")));

            //  System.out.println(answer);
        } catch (Exception e) {

            return -1;
        }
        return answer;
    }

    public static boolean init() {
        if (isConnected)
            return true;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //        System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://" + HOST + "/final", "root", "root");
            System.out.println("Database connected");
            isConnected = true;
            stmt = connection.createStatement();

            cars = getAllCarsFromDatabase();
        } catch (Exception e) {
            System.err.println("Failed to connect to database");
            e.printStackTrace();
        }

        return isConnected;
    }


    public static ArrayList<Car> getAllCarsFromDatabase() {
        ArrayList<Car> cars = new ArrayList<Car>();

        if (!isConnected) {
            System.out.println("Not connected to " + HOST);
            return cars;
        }
        try {

            String queryString = "SELECT * FROM final.cars";

            ResultSet rset = stmt.executeQuery(queryString);

            while (rset.next()) {
                String id = rset.getString(1);
                String make = rset.getString(3);
                String model = rset.getString(2);
                String year = rset.getString(4);

                String mpg = rset.getString(5);
                String tank = rset.getString(6);


                cars.add(new Car(make, model, Integer.parseInt(year), Integer.parseInt(mpg), Integer.parseInt(tank)));


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    public static Car getCarFromDatabase(String make, String model, int year) {
        if (!isConnected) {
            System.out.println("Not connected to " + HOST);
        }
        Car car = null;
        make = make.toUpperCase();
        model = model.toUpperCase();
        try {

            String queryString = "SELECT * FROM final.cars where make = '" + make + "'and model = '" + model + "'and year = '" + year + "';";
            System.out.println("Running SQL Statement: " + queryString);
            ResultSet rset = stmt.executeQuery(queryString);

            while (rset.next()) {
                String id = rset.getString(1);
                String mpg = rset.getString(5);
                String tank = rset.getString(6);


                car = new Car(make, model, year, Integer.parseInt(mpg), Integer.parseInt(tank));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return car;
    }


}
package com.example.final2;

import java.sql.*;
import java.util.ArrayList;

class Database {


    public static boolean isConnected = false;
    private static final String HOST = "localhost";
    private static Statement stmt;


    public Database() {

    }

    public static boolean init() {
        if(isConnected)
            return true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://" + HOST + "/final", "root", "root");
            System.out.println("Database connected");
            isConnected = true;
            stmt = connection.createStatement();

        } catch (Exception e) {
            System.err.println("Failed to connect to database");
            e.printStackTrace();
        }

        return isConnected;
    }




    public static Car getCarFromDatabase(String make, String model, int year) {
        if (!isConnected) {
            System.out.println("Not connected to " + HOST);
        }
            Car car = null;

        try {

            String queryString = "SELECT * FROM final.cars where make = '"+ make + "'and model = '"+ model + "'and year = '"+ year + "';";
            System.out.println("Running SQL Statement: " + queryString);
            ResultSet rset = stmt.executeQuery(queryString);

            while (rset.next()) {
                String id = rset.getString(1);
                String mpg = rset.getString(4);
                String tank = rset.getString(5);


            car = new Car(make,model,year,Integer.parseInt(mpg),Integer.parseInt(tank));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return car;
    }


}
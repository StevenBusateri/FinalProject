package com.example.final2;

import java.sql.*;

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
                    ("jdbc:mysql://" + HOST + "/cars", "root", "root");
            System.out.println("Database connected");
            isConnected = true;
            stmt = connection.createStatement();

        } catch (Exception e) {
        }

        return isConnected;
    }



    public static Car getCarFromDatabase() {
        if (!isConnected) {
            System.out.println("Not connected to " + HOST);
        }
            Car car = null;

        try {

            String queryString = "SELECT * FROM restaurant.menu";

            ResultSet rset = stmt.executeQuery(queryString);

            while (rset.next()) {
                String id = rset.getString(1);
                String name = rset.getString(2);
                String price = rset.getString(3);


            car = new Car();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return car;
    }


}
package com.example.final2;
//this page can be the home page that sets everything up for home

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet(name = "Home", value = "/home")

public class Homepage extends HttpServlet {
    private String message;
    public void init() {
        message = "Enter your cars information";

    }

// we will have the enter your cars informatoin here **
// enter your starting point
// enter your end location
// need to find out how far the location is
//(easy- prompt the user how many miles are you going to travel)
//medium -*preferd* - use a google map tool and figure it out
    
 public static void main(String[] args) {

        int mpg = 25;//miles per gal    *****from database***
        int tankSize = 10;//tank size   *****from database***
        int maxMillage = mpg * tankSize;// max milage of car
        int tripDistance = 1000; //total trip distance    *****from above***
        int tankNeed = tripDistance/maxMillage;//tanks needed/ stops
        int gasNeed = tankNeed * tankSize;// gas needed
        int gasPrice =3; //gas price
        int totalcost = gasNeed * gasPrice; //total trip cost
        System.out.println(" You need $" + totalcost + " and "+gasNeed +" gallons for the trip");

}


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Database.init();

        Car test = Database.getCarFromDatabase("Ford","Fusion",2006);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + test.toString() + "</h1>");
        out.println("</body></html>");
    }
    public void destroy() {
    }



}

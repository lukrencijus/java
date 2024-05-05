//This project was made by Karolis Zabulis and Lukas Šerelis.
//Vilnius University Mathematics and Informatics faculty.
//Contact: karolis.zabulis@mif.stud.vu.lt or lukas.serelis@mif.stud.vu.lt
//Description of the program: A Java program that allows users to enter activity,
//date, start, end of it, interruptions, comments and represents it.
//Version: V0.1
//Change log:
//Program was tested on Windows 11 64 bit and macOS arm64

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        ActivityFunctions activityFunctions = new ActivityFunctions();
        Scanner inputRead = new Scanner(System.in);
            try {
                while(true){
                    activityFunctions.insertion(inputRead);
                }
            } 
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        inputRead.close();
    }
}

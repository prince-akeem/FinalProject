package FinalProject;


import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

class ServiceRequest {
  private Scanner userInput = new Scanner(System.in);
  private int userServiceChoose[] = new int [20];
  private String serviceInfo[][] = 
      {{"Pants    ", "7.25"},
      {"2pc Suit", "15.50"},
      {"Shorts  ", "6.50"},
      {"3pc Suit", "20.00"} };
  private Double servicePrice = 0.0;

  int showServices(int numberOfItems){
    System.out.println("\t\t Service\tPrice($)\tInput Key");
    for(int i=0; i < serviceInfo.length; i++) {
      System.out.print("\t\t " + serviceInfo[i][0] + "\t");
      System.out.println(serviceInfo[i][1]+ "    \t" + i);

    }
    System.out.print("Please choice a service: ");
    userServiceChoose[numberOfItems] = userInput.nextInt();
    if(userServiceChoose[numberOfItems] < 0 || userServiceChoose[numberOfItems] > serviceInfo.length) {
      System.out.println("Please choose an option from the menu.");
      return showServices(numberOfItems);
    }
    return numberOfItems+1;
  }

  void PrintReceipt(int numberOfItems){
    System.out.println("\t\t\t Order Receipt");
    System.out.print("________________________________________________________________\n");
    System.out.print("\t\t\tItem\t\tPrice\n");
    for(int i=0; i<numberOfItems; i++){
      System.out.print("\t\t\t" + serviceInfo[userServiceChoose[i]][0] + "\t");
      System.out.println(serviceInfo[userServiceChoose[i]][1]);
      servicePrice += Double.parseDouble(serviceInfo[userServiceChoose[i]][1]);
    }
    
    System.out.print("\t\t\t\t\t\tTotal: " + servicePrice);
    saveOrderToFile(numberOfItems);
  }

  void saveOrderToFile(int numberOfItems){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();  

    try{
      FileWriter saveOrder = new FileWriter("FinalProject/Orders.txt", true);

      saveOrder.write("________________________________________________________________\n");
      saveOrder.write("\t\t\tItem\t\tPrice\n");
      for(int i=0; i<numberOfItems; i++){
        saveOrder.write("\t\t\t" + serviceInfo[userServiceChoose[i]][0] + "\t");
        saveOrder.write(serviceInfo[userServiceChoose[i]][1] + "\n");
        servicePrice += Double.parseDouble(serviceInfo[userServiceChoose[i]][1]);
      }
      saveOrder.write(servicePrice + "\n");

      System.out.print("\nPlease enter the name for the order: ");
      userInput.nextLine();
      String temp = userInput.nextLine();

      saveOrder.write(temp + "\n");
      saveOrder.write(dtf.format(now) + "\n\n");  
      saveOrder.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

package FinalProject;


import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

class ServicesMain{
    protected Scanner userInput = new Scanner(System.in);
    protected Double servicePrice = 0.0;

    protected int showServices(int numberOfItems, String serviceInfo[][], int userServiceChoose[]){
        System.out.println("\t\t Service\t\tPrice($)\tInput Key");
        for(int i=0; i < serviceInfo.length; i++) {
          System.out.print("\t\t " + serviceInfo[i][0] + "\t");
          System.out.println(serviceInfo[i][1]+ "    \t" + i);
    
        }
        System.out.print("Please choice a service: ");
        userServiceChoose[numberOfItems] = userInput.nextInt();
        if(userServiceChoose[numberOfItems] < 0 || userServiceChoose[numberOfItems] > serviceInfo.length) {
          System.out.println("Please choose an option from the menu.");
          return this.showServices(numberOfItems, serviceInfo, userServiceChoose);
        }
        return numberOfItems+1;
    }

    void saveOrderToFile(int numberOfItems, String serviceInfo[][], int userServiceChoose[]){
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
    
          userInput = new Scanner(System.in);
          System.out.print("\nPlease enter the name for the order: ");
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

class ServiceRequest extends ServicesMain{
  private int userServiceChoose[] = new int [20];
  private String serviceInfo[][] = {
      {"Pants            ", "7.25"},
      {"2pc Suit         ", "15.50"},
      {"Shorts           ", "6.50"},
      {"3pc Suit         ", "20.00"},
      {"Shirt            ", "7.25"},
      {"Dress            ", "12.95"},
      {"Jacket           ", "12.00"},
      {"Full Coat         ", "20.00"} };
  protected Double servicePrice = 0.0;

  public int showServices(int numberOfItems){
    return super.showServices(numberOfItems, serviceInfo, userServiceChoose);
  }

  private void saveOrderToFile(int numberOfItems){
    super.saveOrderToFile(numberOfItems, serviceInfo, userServiceChoose);
  }

  public void PrintReceipt(int numberOfItems){
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
}

class HomeRunRequest extends ServicesMain{
    private int userServiceChoose[] = new int [20];
    private double priceOfRug = 0.0;
    private String deliveryTime;
    private String homeRunInfo[][] = {
        {"Comforters      ", "50.00"},
        {"Duvet Covers    ", "35.00"},
        {"Table Cloth     ", "25.00"},
        {"Rugs(per sq. ft)", "3.50"},
        {"Wedding Dress   ", "99.00"}
    };

    // overriding the showService so i can add more stuff to the function like delivery time
    @Override
    protected int showServices(int numberOfItems, String[][] serviceInfo, int[] userServiceChoose) {
        super.showServices(numberOfItems, serviceInfo, userServiceChoose);

        if(userServiceChoose[numberOfItems] == 3){
            System.out.print("\nEnter the rug size in sq. ft: ");
            priceOfRug = userInput.nextDouble();
            priceOfRug *= 3.5;
        }

        userInput = new Scanner(System.in);
        System.out.print("Enter date and time for arrival: ");
        deliveryTime = userInput.nextLine();

        return numberOfItems+1;
    }

    // because variables are needed the class to call the overridden function
    public int showHomeRuns(int numberOfItems){
        return this.showServices(numberOfItems, homeRunInfo, userServiceChoose);
    }

    
    @Override void saveOrderToFile(int numberOfItems, String homeRunInfo[][], int userServiceChoose[]){
        super.saveOrderToFile(numberOfItems, homeRunInfo, userServiceChoose);

        try (FileWriter saveOrder = new FileWriter("FinalProject/Orders.txt", true)) {
            saveOrder.write("Final Price: " + (servicePrice + priceOfRug) + "\n");
            saveOrder.write(deliveryTime+ "\n\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void PrintReceipt(int numberOfItems){
        System.out.println("\t\t\t Order Receipt");
        System.out.print("________________________________________________________________\n");
        System.out.print("\t\t\tItem\t\t\tPrice\n");
        for(int i=0; i<numberOfItems; i++){
          System.out.print("\t\t\t" + homeRunInfo[userServiceChoose[i]][0] + "\t");
          System.out.println(homeRunInfo[userServiceChoose[i]][1]);
          servicePrice += Double.parseDouble(homeRunInfo[userServiceChoose[i]][1]);
        }
        
        System.out.print("\t\t\t\t\t\tTotal: " + (servicePrice + priceOfRug));
        saveOrderToFile(numberOfItems, homeRunInfo, userServiceChoose);
    }
}

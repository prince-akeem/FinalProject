package FinalProject;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Main{
  static int getMenuResponse()
  {
    Scanner userInput = new Scanner(System.in);
    int userMenuChoose;

    System.out.println("\t Menu Options\t\t\t Input Key");
    System.out.println("\tUpdate Company Info\t\t  1");
    System.out.println("\tAdd New Purchase\t\t  2");
    System.out.println("\tSchedule Home Run\t\t  3");
    userMenuChoose = userInput.nextInt();
    //userInput.close();
    System.out.println(userMenuChoose);
    if(userMenuChoose == 1) { return 1; }
    else if(userMenuChoose == 2) { return 2; }
    else if(userMenuChoose == 3) { return 3; }
    else {
      System.out.println("Options not on menu, please try again.");
      return getMenuResponse();
    }
  }
 
  public static void main(String args[]){
    Scanner userInput = new Scanner(System.in);
    ServiceRequest userRequest = new ServiceRequest();
    HomeRunRequest userHomeRequest = new HomeRunRequest();
    int menuOption;
    char anotherItem;
    int numberOfItems = 0;
    CompanyInfo companyHandler = new CompanyInfo();


    companyHandler.printCompanyInfo();

    do{
      System.out.print("\033[H\033[2J");
      menuOption = getMenuResponse();

      switch(menuOption){
        case 1:
          if(companyHandler.updateCompanyInfo()) {
            System.out.println("Update Successful!!");
          }
          break;

        case 2:
          numberOfItems = 0;

          do{
            System.out.println("You selected to add a new purchase.");
            userRequest.showServices(numberOfItems);
            numberOfItems++;
            System.out.print("Would you like to add another item?(y|n) ");
            anotherItem = userInput.next().charAt(0);
            
            while(anotherItem != 'n' && anotherItem != 'y'){
              System.out.print("Enter 'n' or 'y': ");
              anotherItem = userInput.next().charAt(0);
            }
          } while(anotherItem == 'y');

          userRequest.PrintReceipt(numberOfItems);
          break;

        case 3:
          numberOfItems = 0;

          do{
            System.out.println("You selected to schedule a home run.");
            userHomeRequest.showHomeRuns(numberOfItems);
            numberOfItems++;
            System.out.print("Would you like to add another item?(y|n) ");
            anotherItem = userInput.next().charAt(0);
            
            while(anotherItem != 'n' && anotherItem != 'y'){
              System.out.print("Enter 'n' or 'y': ");
              anotherItem = userInput.next().charAt(0);
            }
          } while(anotherItem == 'y');

          userHomeRequest.PrintReceipt(numberOfItems);
          break;

        default:
          System.out.println("Unexpected error");
      }

      userInput = new Scanner(System.in);
      System.out.print("Do you want to return to the main menu?(y|n) ");
      anotherItem = userInput.next().charAt(0);
    } while(anotherItem == 'y');

    userInput.close();
  }
}

package FinalProject;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CompanyInfo{
    private String companyName, companyAddress, companyPhone;
    private Scanner userInput = new Scanner(System.in);
    private File companyData = new File("FinalProject/Company Data.txt");
  
    CompanyInfo(){
      try{
        if(companyData.createNewFile()){
          updateCompanyInfo();
        }
        else{
          Scanner readCompanyInfo = new Scanner(companyData);
  
          while(readCompanyInfo.hasNextLine()){
            this.companyName = readCompanyInfo.nextLine();
            this.companyAddress = readCompanyInfo.nextLine();
            this.companyPhone = readCompanyInfo.nextLine();
          }
  
          readCompanyInfo.close();
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    public boolean updateCompanyInfo(){
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter the secret Key: ");
        String userSaid = userInput.nextLine();

        if(!userSaid.equalsIgnoreCase("development")) return false;

      try{
        FileWriter saveCompanyData = new FileWriter("FinalProject/Company Data.txt");
  
        System.out.print("Enter your company name: ");
        companyName = userInput.nextLine();
        saveCompanyData.write(companyName + "\n");
  
        System.out.print("Enter your company Address: ");
        companyAddress = userInput.nextLine();
        saveCompanyData.write(companyAddress + "\n");
  
        System.out.print("Enter your company Phone: ");
        companyPhone = userInput.nextLine();
        saveCompanyData.write(companyPhone + "\n");
  
        saveCompanyData.close();
  
        return true;
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        return false;
      }
    }
  
    public void setCompanyAddress(String companyAddress) {
      this.companyAddress = companyAddress;
    }
  
    public void setCompanyName(String companyName) {
      this.companyName = companyName;
    }
  
    public void setCompanyPhone(String companyPhone) {
      this.companyPhone = companyPhone;
    }
  
    public String getCompanyAddress() {
      return companyAddress;
    }
  
    public String getCompanyName() {
      return companyName;
    }
  
    public String getCompanyPhone() {
      return companyPhone;
    }
  
    public void printCompanyInfo(){
      System.out.println("\n\t\t\t" + companyName + "\n\t\t" + companyAddress + "\n\t\t\t" + companyPhone);
    }
  }

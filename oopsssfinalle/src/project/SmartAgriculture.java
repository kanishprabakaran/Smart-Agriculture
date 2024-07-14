package project;
import java.util.Scanner;

public class SmartAgriculture {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n \n");
        System.out.println("\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \tWELCOME TO SMART AGRICULTURE MANAGEMENT SYSTEM!");
        System.out.println(" \t \t \t\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \tSelect an option (1-5):");
        System.out.println(" \t \t \t\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t1. Farmer Details");
        System.out.println(" \t \t \t \t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t2. Farm Details");
        System.out.println(" \t \t \t\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t3. Farm Automation");
        System.out.println(" \t \t \t\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t4. Sensor Data");
        System.out.println(" \t \t \t\t \t \t \t\t \t\t \t\t \t\t \t\t \t\t \t5. Supply Chain Details");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                AdvancedFarmingApp.main();
                break;
            case 2:
                FarmMonitoringSystem.main();
                break;
            case 3:
                automation.main();
                break;
            case 4:
                sensormain.main();
                break;
            case 5:
                AgriculturalSupplyChainOptimization.main();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 1 and 5.");
        }

        scanner.close();
    }
}
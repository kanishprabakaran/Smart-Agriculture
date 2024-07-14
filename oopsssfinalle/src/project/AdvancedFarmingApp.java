package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ParallelFarmProfitCalculator implements Runnable {
    private Farm farm;
    public ParallelFarmProfitCalculator(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void run() {
        try {
            double farmProfit = farm.calculateProfit();
            System.out.println("Farm " + farm.getFarmId() + " Profit: $" + farmProfit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

enum CropType {
    WHEAT("CLAY", "LOAM", "SILT"),
    CORN("LOAM", "SILT"),
    BARLEY("CLAY", "LOAM", "SILT"),
    SOYBEAN("SAND", "LOAM", "SILT");

    private List<String> compatibleSoilTypes;

    CropType(String... compatibleSoilTypes) {
        this.compatibleSoilTypes = List.of(compatibleSoilTypes);
    }

    public boolean isCompatibleWith(SoilType soilType) {
        return compatibleSoilTypes.contains(soilType.name());
    }
}

public class AdvancedFarmingApp {
    public static void main() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter farmer's name: ");
            String farmerName = scanner.nextLine();
            Farmer1 farmer = new Farmer1(farmerName);

            System.out.print("Enter number of farms: ");
            int numFarms = readIntInput(scanner);

            List<Thread> farmThreads = new ArrayList<>();

            for (int i = 1; i <= numFarms; i++) {
                System.out.println("Farm " + i + ":");
                System.out.print("Enter farm ID: ");
                String farmId = scanner.nextLine();

                System.out.print("Enter soil type (CLAY, LOAM, SAND, SILT): ");
                SoilType soilType = readSoilType(scanner);

                Farm farm = new Farm(farmId, soilType);

                System.out.print("Enter number of crops for this farm: ");
                int numCrops = readIntInput(scanner);

                for (int j = 1; j <= numCrops; j++) {
                    System.out.println("Crop " + j + ":");
                    System.out.print("Enter crop type (WHEAT, CORN, BARLEY, SOYBEAN): ");
                    CropType1 cropType = readCropType(scanner);

                    System.out.print("Enter planting date (yyyy-MM-dd): ");
                    LocalDate plantingDate = readDate(scanner);

                    System.out.print("Enter harvest date (yyyy-MM-dd): ");
                    LocalDate harvestDate = readDate(scanner);

                    System.out.print("Enter profit per unit: ");
                    double profitPerUnit = readDoubleInput(scanner);

                    System.out.print("Enter yield (units per acre): ");
                    double yield = readDoubleInput(scanner);
                    scanner.nextLine(); // Consume the newline character

                    Crop crop = new Crop(cropType, plantingDate, harvestDate, profitPerUnit, yield);
                    farm.addCrop(crop);
                }

                farmer.addFarm(farm);

                // Create a thread for each farm and calculate profits concurrently
                Thread farmThread = new Thread(new ParallelFarmProfitCalculator(farm));
                farmThreads.add(farmThread);
                farmThread.start();
            }

            // Wait for all farm threads to finish
            for (Thread farmThread : farmThreads) {
                try {
                    farmThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Accessing farmer, farms, and crops information
            System.out.println("\nFarmer: " + farmer.getFarmerName());
            for (Farm farm : farmer.getFarms()) {
                System.out.println("Farm ID: " + farm.getFarmId() + ", Soil Type: " + farm.getSoilType());
                for (Crop crop : farm.getCrops()) {
                    System.out.println("Crop Type: " + crop.getType() +
                            ", Planting Date: " + crop.getPlantingDate() +
                            ", Harvest Date: " + crop.getHarvestDate() +
                            ", Profit Per Unit: $" + crop.getProfitPerUnit() +
                            ", Yield: " + crop.getYield() + " units/acre");
                }
            }

            // Advanced analytics
            System.out.println("\nTotal Profit: $" + farmer.calculateProfit());
            System.out.println("Total Yield: " + farmer.calculateTotalYield() + " units");
            System.out.println("Average Profit Per Acre: $" + farmer.calculateAverageProfitPerAcre());
            SmartAgriculture.main(new String[]{});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int readIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double readDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid double.");
            }
        }
    }

    private static SoilType readSoilType(Scanner scanner) {
        while (true) {
            try {
                return SoilType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid soil type. Please enter one of: CLAY, LOAM, SAND, SILT.");
            }
        }
    }

    private static CropType1 readCropType(Scanner scanner) {
        while (true) {
            try {
                return CropType1.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid crop type. Please enter one of: WHEAT, CORN, BARLEY, SOYBEAN.");
            }
        }
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                String dateString = scanner.nextLine();
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a valid date (yyyy-MM-dd).");
            }
        }
    }
}

class Farmer1 {
    private String farmerName;
    private List<Farm> farms;

    public Farmer1(String farmerName) {
        this.farmerName = farmerName;
        this.farms = new ArrayList<>();
    }

    public void addFarm(Farm farm) {
        farms.add(farm);
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public double calculateProfit() {
        double totalProfit = 0.0;
        for (Farm farm : farms) {
            totalProfit += farm.calculateProfit();
        }
        return totalProfit;
    }

    public double calculateTotalYield() {
        double totalYield = 0.0;
        for (Farm farm : farms) {
            for (Crop crop : farm.getCrops()) {
                totalYield += crop.getYield();
            }
        }
        return totalYield;
    }

    public double calculateAverageProfitPerAcre() {
        double totalProfit = calculateProfit();
        double totalAcreage = 0.0;
        for (Farm farm : farms) {
            totalAcreage += farm.calculateAcreage();
        }

        // Avoid division by zero
        if (totalAcreage == 0.0) {
            return 0.0;
        }

        return totalProfit / totalAcreage;

    }
}

class Farm {
    private String farmId;
    private SoilType soilType;
    private List<Crop> crops;

    public Farm(String farmId, SoilType soilType) {
        this.farmId = farmId;
        this.soilType = soilType;
        this.crops = new ArrayList<>();
    }

    public void addCrop(Crop crop) {
        crops.add(crop);
    }

    public String getFarmId() {
        return farmId;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public double calculateProfit() {
        double totalProfit = 0.0;
        for (Crop crop : crops) {
            totalProfit += crop.getProfitPerUnit() * crop.getYield();
        }
        return totalProfit;
    }

    public double calculateAcreage() {
        // Assuming each crop occupies 1 acre, calculate total acreage based on the number of crops
        return crops.size();
    }
}

enum SoilType {
    CLAY, LOAM, SAND, SILT
}

class Crop {
    private CropType1 type;
    private LocalDate plantingDate;
    private LocalDate harvestDate;
    private double profitPerUnit;
    private double yield;

    public Crop(CropType1 type, LocalDate plantingDate, LocalDate harvestDate, double profitPerUnit, double yield) {
        this.type = type;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
        this.profitPerUnit = profitPerUnit;
        this.yield = yield;
    }

    public CropType1 getType() {
        return type;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public double getProfitPerUnit() {
        return profitPerUnit;
    }

    public double getYield() {
        return yield;
    }
}

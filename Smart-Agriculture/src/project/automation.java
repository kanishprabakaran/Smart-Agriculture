package project;

import javax.swing.*;
import java.util.Scanner;


// project.Product class
// Base class
class Resource {
    String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String checkQuality() {
        return "Quality check not available for this resource.";
    }
}

// project.Product class
class Product extends Resource {
    double price;
    String description;

    public Product(String name, double price, String description) {
        super(name);
        this.price = price;
        this.description = description;
    }

    public void evaluateProduct() {
        if (getName().equalsIgnoreCase("tractor") && price <= 3000000) {
            System.out.println(getName() + " is considered a best investment.");
        } else if (getName().equalsIgnoreCase("harvester") && price <= 5000000) {
            System.out.println(getName() + " is a good investment for large-scale farming.");
        } else if (getName().equalsIgnoreCase("plow") && price <= 1000000) {
            System.out.println(getName() + " is worth the investment for soil preparation.");
        } else {
            System.out.println(getName() + " may or may not be worth the investment. It depends on various factors.");
        }
    }
}


// project.Weather class
class Weather extends Resource {
    String condition;
    double temperature;

    public Weather(String name, String condition, double temperature) {
        super(name);
        this.condition = condition;
        this.temperature = temperature;
    }



    public void suggestCropType() {

        if (temperature < 41) {
            System.out.println("Suggest planting: Barley");
        } else if (temperature >= 41 && temperature < 59) {
            System.out.println("Suggest planting: Oats");
        } else if (temperature >= 59 && temperature < 77) {
            System.out.println("Suggest planting: Corn");
        } else if (temperature >= 77 && temperature < 95) {
            System.out.println("Suggest planting: Soybeans");
        } else {
            System.out.println("Suggest planting: Cotton");
        }
    }
}


// project.Soil class
class Soil extends Resource {
    double pHLevel;

    public Soil(String name, double pHLevel) {
        super(name);
        this.pHLevel = pHLevel;
    }

    @Override
    public String checkQuality() {
        if (pHLevel < 6.5) {
            return "project.Soil pH level is low. project.Soil quality looks bad.";
        } else if (pHLevel > 7.5) {
            return "project.Soil pH level is high. project.Soil quality looks bad.";
        } else {
            return "project.Soil pH level is optimal.";
        }
    }

    public void suggestCropsToPlant() {
        if (pHLevel < 5.5) {
            System.out.println("Suggest planting: Blueberries, Potatoes, Rhododendrons");
        } else if (pHLevel >= 5.5 && pHLevel < 6.5) {
            System.out.println("Suggest planting: Raspberries, Apples, Cherries");
        } else if (pHLevel >= 6.5 && pHLevel < 7.5) {
            System.out.println("Suggest planting: Beans, Carrots, Peas");
        } else if (pHLevel >= 7.5 && pHLevel < 8.5) {
            System.out.println("Suggest planting: Broccoli, Cauliflower, Cabbage");
        } else {
            System.out.println("Suggest planting: Spinach, Lettuce, Swiss Chard");
        }
    }
}


// project.Water class
class Water extends Resource {
    double pHLevel;

    public Water(String name, double pHLevel) {
        super(name);
        this.pHLevel = pHLevel;
    }

    @Override
    public String checkQuality() {
        if (pHLevel < 6.5) {
            return "project.Water pH level is low. project.Water quality looks bad.";
        } else if (pHLevel > 7.5) {
            return "project.Water pH level is high. project.Water quality looks bad.";
        } else {
            return "project.Water pH level is optimal.";
        }
    }

    public void suggestCropsToPlant() {
        if (pHLevel < 6.0) {
            System.out.println("Suggest planting: Blueberries, Cranberries, Potatoes");
        } else if (pHLevel >= 6.0 && pHLevel < 7.0) {
            System.out.println("Suggest planting: Beans, Carrots, Peas");
        } else if (pHLevel >= 7.0 && pHLevel < 8.0) {
            System.out.println("Suggest planting: Cucumbers, Squash, Melons");
        } else {
            System.out.println("Suggest planting: Spinach, Lettuce, Kale");
        }
    }
}


// project.Process class
class Process extends Resource {
    String description;

    public Process(String name, String description) {
        super(name);
        this.description = description;
    }
}

public class automation {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        boolean continueCreating = true;

        while (continueCreating) {
            System.out.println("Select an option:");
            System.out.println("1. Is your Product worth it?");
            System.out.println("2. Suggest crops based on current weather?");
            System.out.println("3. Suggest crops based on current soil condition?");
            System.out.println("4. Suggest crops based on current water condition?");
            System.out.println("5. Seed to Sprout Tutorial??");
            System.out.println("6.Access FarmData InsightBot?");
            System.out.println("7. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter product description: ");
                    String productDescription = scanner.nextLine();

                    Product product = new Product(productName, productPrice, productDescription);
                    System.out.println("project.Product: " + product.getName() + ", Price: $" + product.price + ", Description: " + product.description);
                    product.evaluateProduct();
                    break;
                case 2:
                    System.out.print("Enter city name for weather: ");
                    String cityName = scanner.nextLine();
                    System.out.print("Enter weather condition: ");
                    String weatherCondition = scanner.nextLine();
                    System.out.print("Enter temperature: ");
                    double temperature = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    Weather weather = new Weather(cityName, weatherCondition, temperature);
                    System.out.println("project.Weather in " + weather.getName() + ": " + weather.condition + ", Temperature: " + weather.temperature + "F");
                    weather.suggestCropType();
                    break;
                case 3:
                    System.out.print("Enter soil name: ");
                    String soilName = scanner.nextLine();
                    System.out.print("Enter pH level of the soil: ");
                    double pHLevelSoil = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    Soil soil = new Soil(soilName, pHLevelSoil);
                    System.out.println("project.Soil quality: " + soil.checkQuality());
                    soil.suggestCropsToPlant();
                    break;
                case 4:
                    System.out.print("Enter water name: ");
                    String waterName = scanner.nextLine();
                    System.out.print("Enter pH level of the water: ");
                    double pHLevelWater = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    Water water = new Water(waterName, pHLevelWater);
                    System.out.println("project.Water quality: " + water.checkQuality());
                    water.suggestCropsToPlant();
                    break;
                case 5:
                    PlantCarGame game = new PlantCarGame();
                    game.setVisible(true);
                    break;

                case 6:

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new WiderInputDarkModeChatBot();
                        }
                    });
                case 7:

                    continueCreating = false;
                    SmartAgriculture.main(new String[]{});
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}

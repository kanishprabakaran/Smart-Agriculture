package project;

import java.sql.*;
import java.util.Scanner;

public class FarmMonitoringSystem {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/oops";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "Kanish@123";

    public static void main() {
        createFarmTableIfNotExists();

        FarmDatabase farmDatabase = new FarmDatabase();
        FarmMenu farmMenu = new FarmMenu(farmDatabase);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            farmMenu.displayMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            farmMenu.handleMainMenuChoice(choice, scanner);
        }
    }

    private static void createFarmTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS farms (id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT, fields TEXT, irrigation TEXT, irrigation_status TEXT, fertilization TEXT, crops TEXT)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class FarmData {
        protected int id;
        protected String fields;
        protected String irrigation;
        protected String irrigationStatus;
        protected String fertilization;
        protected String crops;
        protected String name;

        public FarmData(int id, String fields, String irrigation, String irrigationStatus, String fertilization, String crops,String name) {
            this.id = id;
            this.fields = fields;
            this.irrigation = irrigation;
            this.irrigationStatus = irrigationStatus;
            this.fertilization = fertilization;
            this.crops = crops;
            this.name=name;
        }

        public void setFields(String fields) {
            this.fields = fields;
        }

        public void setIrrigation(String irrigation) {
            this.irrigation = irrigation;
        }

        public void setIrrigationStatus(String irrigationStatus) {
            this.irrigationStatus = irrigationStatus;
        }

        public void setFertilization(String fertilization) {
            this.fertilization = fertilization;
        }

        public void setCrops(String crops) {
            this.crops = crops;
        }

        public int getId() {
            return id;
        }

        public String getFields() {
            return fields;
        }

        public String getIrrigation() {
            return irrigation;
        }
        public String getname() {
            return name;
        }

        public String getIrrigationStatus() {
            return irrigationStatus;
        }

        public String getFertilization() {
            return fertilization;
        }

        public String getCrops() {
            return crops;
        }
        public void displayDetails() {
            System.out.println("\nFarm Details:");
            System.out.println("Fields: " + fields);
            System.out.println("Irrigation System: " + irrigation);
            System.out.println("Irrigation Status: " + irrigationStatus);
            System.out.println("Fertilization System: " + fertilization);
            System.out.println("Crops: " + crops);
        }

    }

    private static class Farm extends FarmData {
        private String name1;

        public Farm(int id, String name, String fields, String irrigation, String irrigationStatus, String fertilization, String crops) {
            super(id, fields, irrigation, irrigationStatus, fertilization, crops,name);
            this.name1 = name;
        }

        public String getName() {
            return name;
        }
        public void displayDetails() {
            FarmData farmData = new FarmData(getId(), getFields(), getIrrigation(), getIrrigationStatus(), getFertilization(), getCrops(), getname());

            System.out.println("\nFarm Details:");
            System.out.println("Name: " + name);
            farmData.displayDetails();
        }

    }

    private static class FarmDatabase {
        private static final String SELECT_FARM_BY_ID = "SELECT * FROM farms WHERE id = ?";
        private static final String UPDATE_FARM = "UPDATE farms SET name = ?, fields = ?, irrigation = ?, irrigation_status = ?, fertilization = ?, crops = ? WHERE id = ?";

        public FarmData getFarmDetails(int farmId) {
            try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(SELECT_FARM_BY_ID)) {

                statement.setInt(1, farmId);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return new FarmData(
                            resultSet.getInt("id"),
                            resultSet.getString("fields"),
                            resultSet.getString("irrigation"),
                            resultSet.getString("irrigation_status"),
                            resultSet.getString("fertilization"),
                            resultSet.getString("crops"),
                            resultSet.getString("name")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void updateFarm(FarmData farm) {
            try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(UPDATE_FARM)) {

                statement.setString(1, farm.getname());
                statement.setString(2, farm.getFields());
                statement.setString(3, farm.getIrrigation());
                statement.setString(4, farm.getIrrigationStatus());
                statement.setString(5, farm.getFertilization());
                statement.setString(6, farm.getCrops());
                statement.setInt(7, farm.getId());

                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static class FarmMenu {
        private FarmDatabase farmDatabase;

        public FarmMenu(FarmDatabase farmDatabase) {
            this.farmDatabase = farmDatabase;
        }

        public void displayMainMenu() {
            System.out.println("\nFarm Monitoring System");
            System.out.println("1. Display list of farms");
            System.out.println("2. Choose a farm");
            System.out.println("3. Add new farm");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
        }

        public void handleMainMenuChoice(int choice, Scanner scanner) {
            switch (choice) {
                case 1:
                    displayListOfFarms();
                    break;
                case 2:
                    chooseFarm(scanner);
                    break;
                case 3:
                    addNewFarm(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the Farm Monitoring System. Goodbye!");
                    System.exit(0);
                    SmartAgriculture.main(new String[]{});
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        private void displayListOfFarms() {
            try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM farms");
                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("\nList of Farms:");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("name"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void chooseFarm(Scanner scanner) {
            displayListOfFarms();

            System.out.print("\nEnter the farm ID to view details: ");
            int farmId = scanner.nextInt();
            scanner.nextLine();

            FarmActions farmActions = new FarmActions(farmId, farmDatabase, scanner);
            farmActions.handleFarmActions();
        }

        private void addNewFarm(Scanner scanner) {
            System.out.print("\nEnter the farm name: ");
            String name = scanner.nextLine();

            System.out.print("Enter the fields in the farm: ");
            String fields = scanner.nextLine();

            System.out.print("Enter the irrigation system used: ");
            String irrigation = scanner.nextLine();

            System.out.print("Enter the fertilization system used: ");
            String fertilization = scanner.nextLine();

            try (Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO farms (name, fields, irrigation, irrigation_status, fertilization) VALUES (?, ?, ?, ?, ?)")) {

                statement.setString(1, name);
                statement.setString(2, fields);
                statement.setString(3, irrigation);
                statement.setString(4, "Not Currently Irrigating"); // Initial status
                statement.setString(5, fertilization);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("\nFarm added successfully.");
                } else {
                    System.out.println("\nFailed to add farm.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static class FarmActions {
        private int farmId;
        private FarmDatabase farmDatabase;
        private Scanner scanner;

        public FarmActions(int farmId, FarmDatabase farmDatabase, Scanner scanner) {
            this.farmId = farmId;
            this.farmDatabase = farmDatabase;
            this.scanner = scanner;
        }

        public void handleFarmActions() {
            FarmData selectedFarm = farmDatabase.getFarmDetails(farmId);

            if (selectedFarm != null) {
                selectedFarm.displayDetails();

                while (true) {
                    System.out.println("\nFarm Actions:");
                    System.out.println("1. Change Number of Fields");
                    System.out.println("2. Change Irrigation System");
                    System.out.println("3. Change Fertilization System");
                    System.out.println("4. Add Crops");
                    System.out.println("5. Delete Crops");
                    System.out.println("6. Show Details");
                    System.out.println("7. Display Irrigation Status");
                    System.out.println("8. Update Irrigation Status");
                    System.out.println("9. Back to project.Main Menu");
                    System.out.print("Enter your choice: ");

                    int farmActionChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (farmActionChoice) {
                        case 1:
                            changeNumberOfFields(selectedFarm);
                            break;
                        case 2:
                            changeIrrigationSystem(selectedFarm);
                            break;
                        case 3:
                            changeFertilizationSystem(selectedFarm);
                            break;
                        case 4:
                            addCropsToFarm(selectedFarm);
                            break;
                        case 5:
                            deleteCropsFromFarm(selectedFarm);
                            break;
                        case 6:
                            showFarmDetails(selectedFarm);
                            break;
                        case 7:
                            displayIrrigationStatus(selectedFarm);
                            break;
                        case 8:
                            updateIrrigationStatus(selectedFarm);
                            break;
                        case 9:
                            return;
                        default:
                            System.out.println("\nInvalid choice. Please enter a valid option.");
                    }
                }
            } else {
                System.out.println("\nFarm not found.");
            }
        }

        private void changeNumberOfFields(FarmData farm) {
            System.out.print("\nEnter the new set of fields for the farm: ");
            farm.setFields(scanner.nextLine());
            farmDatabase.updateFarm(farm);
            System.out.println("\nNumber of fields changed successfully.");
        }

        private void changeIrrigationSystem(FarmData farm) {
            System.out.print("\nEnter the new irrigation system for the farm: ");
            farm.setIrrigation(scanner.nextLine());
            farmDatabase.updateFarm(farm);
            System.out.println("\nIrrigation system changed successfully.");
        }

        private void changeFertilizationSystem(FarmData farm) {
            System.out.print("\nEnter the new fertilization system for the farm: ");
            farm.setFertilization(scanner.nextLine());
            farmDatabase.updateFarm(farm);
            System.out.println("\nFertilization system changed successfully.");
        }

        private void addCropsToFarm(FarmData farm) {
            System.out.print("\nEnter the crops to add to the farm: ");
            String cropsToAdd = scanner.nextLine();
            String currentCrops = farm.getCrops();
            String newCrops = currentCrops.isEmpty() ? cropsToAdd : currentCrops + ", " + cropsToAdd;
            farm.setCrops(newCrops);
            farmDatabase.updateFarm(farm);
            System.out.println("\nCrops added successfully.");
        }

        private void deleteCropsFromFarm(FarmData farm) {
            System.out.print("\nEnter the crops to delete from the farm: ");
            String cropsToDelete = scanner.nextLine();
            String currentCrops = farm.getCrops();
            String newCrops = currentCrops.replace(cropsToDelete, "").replace(", ,", ", ").trim();
            farm.setCrops(newCrops);
            farmDatabase.updateFarm(farm);
            System.out.println("\nCrops deleted successfully.");
        }

        private void showFarmDetails(FarmData farm) {
            farm.displayDetails();
        }

        private void displayIrrigationStatus(FarmData farm) {
            System.out.println("\nIrrigation Status:");
            System.out.println("Status: " + farm.getIrrigationStatus());
        }

        private void updateIrrigationStatus(FarmData farm) {
            System.out.print("\nDo you want to start irrigating? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("yes")) {
                farm.setIrrigationStatus("Currently Irrigating");
                farmDatabase.updateFarm(farm);
                System.out.println("\nIrrigation started successfully.");
            } else if (choice.equals("no")) {
                farm.setIrrigationStatus("Not Currently Irrigating");
                farmDatabase.updateFarm(farm);
                System.out.println("\nIrrigation stopped successfully.");
            } else {
                System.out.println("\nInvalid choice. Please enter 'yes' or 'no'.");
            }
        }
    }
}

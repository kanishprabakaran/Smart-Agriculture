package project;

import java.util.Scanner;

// Base class representing IoT sensors
abstract class IoTSensor {
    private String sensorType;
    private String manufacturer;
    private String model;

    public IoTSensor(String sensorType, String manufacturer, String model) {
        this.sensorType = sensorType;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    // Abstract method for reading sensor data
    public abstract double readSensorData();

    // Common method to display sensor information
    public void displaySensorInfo() {
        System.out.println("Sensor Type: " + sensorType);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
    }

    // Common method to display sensor reading
    public void displayReading() {
        displaySensorInfo();
        System.out.println("Sensor Reading: " + readSensorData());
    }
}

// Derived class representing IoT sensors for warehouse monitoring
class WarehouseSensor extends IoTSensor {
    private double temperature;
    private double humidity;

    public WarehouseSensor(String manufacturer, String model, double temperature, double humidity) {
        super("Warehouse", manufacturer, model);
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public double readSensorData() {
        // Simulate temperature or humidity reading for warehouse
        return temperature; // Returning temperature for simplicity
    }

    public void displayWarehouseConditions() {
        displaySensorInfo();
        System.out.println("Warehouse Conditions - Temperature: " + temperature + " degrees Celsius, Humidity: " + humidity + "%");
    }

    public void warehouseSpecificFunction() {
        System.out.println("Performing warehouse-specific function.");
    }
}

// Derived class representing IoT devices for transportation tracking
class TransportationTracker extends IoTSensor {
    private double latitude;
    private String cropCondition;

    public TransportationTracker(String manufacturer, String model, double latitude, String cropCondition) {
        super("Transportation", manufacturer, model);
        this.latitude = latitude;
        this.cropCondition = cropCondition;
    }

    @Override
    public double readSensorData() {
        // Returning latitude for simplicity
        return latitude;
    }

    public void displayTransportationDetails() {
        displaySensorInfo();
        System.out.println("Transportation Details - Latitude: " + latitude + ", Crop Condition: " + cropCondition);
    }

    public void transportationSpecificFunction() {
        System.out.println("Performing transportation-specific function.");
    }
}

// Main class for Agricultural Supply Chain Optimization
public class AgriculturalSupplyChainOptimization {
    public static void main() {
        // Simulate IoT devices for agricultural supply chain optimization
        Scanner scanner = new Scanner(System.in);

        // Input for warehouse sensor
        System.out.print("Enter temperature for warehouse: ");
        double warehouseTemperature = scanner.nextDouble();
        System.out.print("Enter humidity for warehouse: ");
        double warehouseHumidity = scanner.nextDouble();

        WarehouseSensor warehouseSensor = new WarehouseSensor("ABC Corp", "Model-X", warehouseTemperature, warehouseHumidity);

        // Input for transportation tracker
        System.out.print("Enter latitude for transportation location: ");
        double transportationLatitude = scanner.nextDouble();
        System.out.print("Enter crop condition (Good/Spoiled) for transportation: ");
        String transportationCropCondition = scanner.next();  // Use next() for non-numeric input

        TransportationTracker transportationTracker = new TransportationTracker("XYZ Inc", "Model-Y", transportationLatitude, transportationCropCondition);

        scanner.close();

        // Display information for warehouse monitoring
        System.out.println("Warehouse Monitoring:");
        warehouseSensor.displayReading();
        warehouseSensor.displayWarehouseConditions();
        warehouseSensor.warehouseSpecificFunction();

        System.out.println(); // Separate warehouse and transportation information

        // Display information for transportation tracking
        System.out.println("Transportation Tracking:");
        transportationTracker.displayReading();
        transportationTracker.displayTransportationDetails();
        transportationTracker.transportationSpecificFunction();
    }
}
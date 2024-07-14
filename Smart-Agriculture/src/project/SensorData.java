package project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// Data structure to hold sensor data
class SensorData {
    private float soilMoisture;
    private float temperature;
    private float humidity;
    private float lightIntensity;
    private boolean hasPest;
    private boolean hasDisease;

    public SensorData(float soilMoisture, float temperature, float humidity, float lightIntensity, boolean hasPest, boolean hasDisease) {
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.hasPest = hasPest;
        this.hasDisease = hasDisease;
    }

    public float getSoilMoisture() {
        return soilMoisture;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getLightIntensity() {
        return lightIntensity;
    }

    public boolean hasPest() {
        return hasPest;
    }

    public boolean hasDisease() {
        return hasDisease;
    }
}

// SensorDevice class (with added functionalities)
class SensorDevice {
    private String deviceId;
    private double batteryLevel;
    private BlockingQueue<SensorData> sensorDataQueue;

    public SensorDevice(String deviceId) {
        this.deviceId = deviceId;
        this.batteryLevel = 100.0;
        this.sensorDataQueue = new LinkedBlockingQueue<>();
    }

    public SensorData collectData() {
        // Collect sensor data with random values
        float randomSoilMoisture = (float) (Math.random() * 100);
        float randomTemperature = (float) (Math.random() * 100);
        float randomHumidity = (float) (Math.random() * 100);
        float randomLightIntensity = (float) (Math.random() * 100);

        SensorData sensorData = new SensorData(
                randomSoilMoisture,    // Soil Moisture
                randomTemperature,     // Temperature
                randomHumidity,        // Humidity
                randomLightIntensity,  // Light Intensity
                Math.random() < 0.5,    // Has Pest (example: randomly true/false)
                Math.random() < 0.5     // Has Disease (example: randomly true/false)
        );

        // Enqueue sensor data
        sensorDataQueue.offer(sensorData);

        // Calibrate sensors
        calibrateSensors();

        // Update battery level
        updateBatteryLevel();

        return sensorData;
    }

    private void calibrateSensors() {
        // Simulate sensor calibration logic
        if (Math.random() < 0.05) {
            System.out.println("Calibrating Sensors...");
            // Simulate calibration process
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sensor Calibration Complete.");
        }
    }

    private void updateBatteryLevel() {
        // Simulate battery usage
        batteryLevel -= 1.0;
        System.out.println("Battery Level: " + batteryLevel + "% \n");
    }

    public String getStatus() {
        // Simulate getting sensor device status
        return "OK";
    }

    public float getBatteryLevel() {
        return (float) batteryLevel;
    }

    // Add methods to check if the device has pest or disease
    public boolean hasPest() {
        // Simulate checking for pest
        return Math.random() < 0.5;
    }

    public boolean hasDisease() {
        // Simulate checking for disease
        return Math.random() < 0.5;
    }
}

// Alert class (with added functionalities)
class Alert {
    private int relatedSensorDeviceId;

    public Alert(int relatedSensorDeviceId) {
        this.relatedSensorDeviceId = relatedSensorDeviceId;
    }

    public void acknowledgeAlert() {
        // Simulate acknowledging the alert
        System.out.println("Alert Acknowledged!");
        System.out.println("Related Sensor Device ID: " + relatedSensorDeviceId);
        System.out.println("Timestamp: " + getTimeStamp());
    }

    private String getTimeStamp() {
        // Get the current timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public int getRelatedSensorDeviceId() {
        return relatedSensorDeviceId;
    }
}

// MaintenanceTeam class
class MaintenanceTeam {
    private BlockingQueue<String> taskQueue = new LinkedBlockingQueue<>();

    public void assignTask(String task) {
        // Assign a maintenance task to the team
        taskQueue.offer(task);

        // Notify the team about the new task
        System.out.println(" Task assigned: " + task);
        notifyFarmers(" Maintenance team assigned task: " + task);
    }

    public void completeTask() {
        // Complete a maintenance task
        String completedTask = taskQueue.poll();

        // Notify the team about the completed task
        if (completedTask != null) {
            System.out.println(" Task completed: " + completedTask);
            notifyFarmers(" Maintenance team completed task: " + completedTask);
        }
    }

    private void notifyFarmers(String issue) {
        // Notify farmers about maintenance issues
        System.out.println(" Notifying farmers: " + issue);
    }
}

// Farmer class (with added functionalities)
class Farmer {
    private SensorDevice sensorDevice;
    private Alert alert;
    private MaintenanceTeam maintenanceTeam;
    private int iterationCounter;
    private int maxIterations;

    public Farmer(SensorDevice sensorDevice, Alert alert, MaintenanceTeam maintenanceTeam, int maxIterations) {
        this.sensorDevice = sensorDevice;
        this.alert = alert;
        this.maintenanceTeam = maintenanceTeam;
        this.iterationCounter = 0;
        this.maxIterations = maxIterations;
    }

    public void initiateSensorMonitoring() {
        Runnable monitoringTask = () -> {
            while (iterationCounter < maxIterations) {
                // Collect sensor data
                SensorData sensorData = sensorDevice.collectData();

                // Interpret sensor data
                interpretSensorData(sensorData);

                // Make decisions based on sensor data
                makeDecision();

                // Increment the counter
                iterationCounter++;

                // Sleep for a monitoring interval
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Start a new thread for sensor monitoring
        new Thread(monitoringTask).start();
    }

    private void interpretSensorData(SensorData data) {
        // Implement logic to interpret sensor data
        System.out.println("Interpreting Sensor Data:  \n" +
                "Soil Moisture: " + data.getSoilMoisture() +
                ", Temperature: " + data.getTemperature() +
                ", Humidity: " + data.getHumidity() +
                ", Light Intensity: " + data.getLightIntensity() +
                ", Has Pest: " + data.hasPest() +
                ", Has Disease: \n" + data.hasDisease());
    }

    private void makeDecision() {
        // Make decisions based on sensor data
        double batteryLevel = sensorDevice.getBatteryLevel();
        if (batteryLevel < 20) {
            // Low battery alert
            alert.acknowledgeAlert();
        }

        // Simulate assigning a maintenance task if there is a pest or disease
        if ((sensorDevice.hasPest()==true && sensorDevice.hasDisease() ==true) || (sensorDevice.hasPest()==true && sensorDevice.hasDisease() ==false)||(sensorDevice.hasPest()==false && sensorDevice.hasDisease() ==true)) {
            maintenanceTeam.assignTask("Inspect and treat crops for pest/disease");
        }
    }
}

// project.Main class
class sensormain {
    public static void main() {
        SensorDevice sensorDevice = new SensorDevice("SensorDevice001");
        //The hashCode() method is used to generate a unique identifier for the sensorDevice, and this ID is passed to the Alert constructor.
        Alert alert = new Alert(sensorDevice.hashCode());
        MaintenanceTeam maintenanceTeam = new MaintenanceTeam();
        // Set the desired number of iterations (e.g., 5)
        int maxIterations = 10;
        Farmer farmer = new Farmer(sensorDevice, alert, maintenanceTeam, maxIterations);

        // Start sensor monitoring
        farmer.initiateSensorMonitoring();
    }
}
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlantCarGame extends JFrame implements KeyListener {
    private int plantX = 50;
    private int plantHeight = 50;
    private Color plantColor = Color.GREEN;
    private int stars = 0;

    private int gameStage = 0; // 0: Water choice, 1: Soil choice, 2: Nutrient choice

    public PlantCarGame() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 400, 800, 200);
        g.setColor(plantColor);
        g.fillRect(plantX, 350 - plantHeight, 50, plantHeight);
        g.setColor(Color.RED);
        g.fillRect(700, 350, 50, 50);
        g.setColor(Color.BLACK);
        g.drawString("Stars: " + stars, 20, 20);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (gameStage == 0) {
                handleWaterChoice();
                movePlantLeft();
            } else if (gameStage == 1) {
                handleSoilChoice();
                movePlantLeft();
            } else if (gameStage == 2) {
                handleNutrientChoice();
                movePlantLeft();
            }
            else if (gameStage == 3) {
                handleharvestChoice();
                movePlantLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            movePlantLeft();
        }
    }

    private void handleWaterChoice() {
        Object[] waterOptions = {"Water", "Polluted Water"};
        int waterChoice = JOptionPane.showOptionDialog(this, "Choose the type of water:",
                "Water Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, waterOptions, waterOptions[0]);

        if (waterChoice == 0) { // Water
            plantHeight += 20;
            if (plantHeight > 100) {
                plantHeight = 100;
            }
            plantColor = Color.GREEN;
            stars++;
        } else {
            plantHeight -= 20;
            if (plantHeight < 0) {
                plantHeight = 0;
            }
            if (plantHeight < 30) {
                plantColor = Color.YELLOW;
            }
        }

        gameStage = 1;
        repaint();
    }

    private void handleSoilChoice() {
        Object[] soilOptions = {"Loam Soil", "Swampy Soil"};
        int soilChoice = JOptionPane.showOptionDialog(this, "Choose the type of soil:",
                "Soil Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, soilOptions, soilOptions[0]);

        if (soilChoice == 0) { // Loam Soil
            plantHeight += 20;
            if (plantHeight > 100) {
                plantHeight = 100;
            }
            plantColor = Color.GREEN;
            stars++;
        } else {
            plantHeight -= 20;
            if (plantHeight < 0) {
                plantHeight = 0;
            }
            if (plantHeight < 30) {
                plantColor = Color.YELLOW;
            }
        }

        gameStage = 2;
        repaint();
    }

    private void handleNutrientChoice() {
        Object[] nutrientOptions = {"Iron", "Boron"};
        int nutrientChoice = JOptionPane.showOptionDialog(this, "Choose the type of micronutrient:",
                "Micronutrient Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, nutrientOptions, nutrientOptions[0]);

        if (nutrientChoice == 0) { // Iron
            plantHeight += 20;
            if (plantHeight > 100) {
                plantHeight = 100;
            }
            plantColor = Color.GREEN;
            stars++;
        } else {
            plantHeight -= 20;
            if (plantHeight < 0) {
                plantHeight = 0;
            }
            if (plantHeight < 30) {
                plantColor = Color.YELLOW;
            }
        }

        gameStage = 3; // Reset to water choice
        repaint();
    }
    private void handleharvestChoice() {
        Object[] nutrientOptions = {"Selective Harvesting", "Rushing Harvest"};
        int nutrientChoice = JOptionPane.showOptionDialog(this, "Choose the type of Harvest:",
                "Micronutrient Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, nutrientOptions, nutrientOptions[0]);

        if (nutrientChoice == 0) { // Iron
            plantHeight += 20;
            if (plantHeight > 100) {
                plantHeight = 100;
            }
            plantColor = Color.GREEN;
            stars++;
        } else {
            plantHeight -= 20;
            if (plantHeight < 0) {
                plantHeight = 0;
            }
            if (plantHeight < 30) {
                plantColor = Color.YELLOW;
            }
        }

        gameStage = 0; // Reset to water choice
        repaint();
    }


    private void movePlantLeft() {
        if (plantX > 0) {
            plantX += 162.5;
            repaint();
        }
        else {
            plantX -= 162.5;
            repaint();

        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {

    }
}
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WiderInputDarkModeChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;

    // Define questions and their corresponding answers
    private String[] questions = {
            "What is your name?",
            "How are you?",
            "What is your favorite color?",
            "Cotton health?",
            "Wheat health?",
            "Sugarcane health?",
            "Today's goal?"
    };

    private String[] answers = {
            "I am a chat bot.",
            "I'm doing well, thank you!",
            "I like all colors.",
            " \n      Date           Growth Stage     Weather          Pest         \n" +
                    "2023-04-01     Emergence           Sunny            None        \n" +
                    "2023-04-10     Tillering           Cloudy           infestation \n" +
                    "2023-04-20     Flowering           Rainy            Healthy     \n" +
                    "2023-05-01     Boll Form           Hot              No issues   \n",
            " \n      Date           Growth Stage     Weather          Pest         \n" +
                    "2023-05-01     Germination           Sunny            None        \n" +
                    "2023-07-10     Heading           Cloudy           infestation \n" +
                    "2023-09-20     Flowering           Rainy            Healthy     \n" +
                    "2023-11-01     Ripening           Hot              No issues   \n" ,
            " \n      Date           Growth Stage     Weather          Pest         \n" +
                    "2023-05-01     Germination           Sunny            None        \n" +
                    "2023-07-10     Tillering           Cloudy           infestation \n" +
                    "2023-09-20     Maturation           Rainy            Healthy     \n" +
                    "2023-11-01     Harvesting           Hot              No issues   \n",
            "\n Morning Tasks:\n" +
                    "\n" +
                    "1.Check Livestock\n" +
                    "2.Inspect Crops\n" +
                    "\n" +
                    "Midday Tasks:\n" +
                    "\n" +
                    "1.Crop Management\n" +
                    "2.Irrigation\n" +
                    "3.Livestock Care\n" +
                    "\n" +
                    "Afternoon Tasks:\n" +
                    "\n" +
                    "1.Equipment Maintenance\n" +
                    "2.Weeding and Cultivation\n" +
                    "3.Fertilization\n" +
                    "\n" +
                    "Evening Tasks:\n" +
                    "\n" +
                    "1.Livestock Feeding\n" +
                    "2.Record Keeping\n" +
                    "3.Planning"

    };

    public WiderInputDarkModeChatBot() {
        // Set up the JFrame
        setTitle("Wider Input Dark Mode Chat Bot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setResizable(false);
        setDarkModeTheme(); // Apply dark mode theme

        // Create components
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setForeground(Color.WHITE);
        chatArea.setBackground(Color.BLACK);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        inputField.setForeground(Color.BLACK); // Set text color
        inputField.setBackground(new Color(128, 128, 128)); // Set grey background color
        inputField.setPreferredSize(new Dimension(400, 40)); // Set preferred size
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });

        // Add components to the JFrame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Display the initial greeting
        chatArea.append("Hello! Ask me something.\n");

        // Make the JFrame visible
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void processInput() {
        String userInput = inputField.getText();
        chatArea.append("You: " + userInput + "\n");

        // Check if the user's input matches any of the predefined questions
        for (int i = 0; i < questions.length; i++) {
            if (userInput.toLowerCase().contains(questions[i].toLowerCase())) {
                // If there is a match, display the corresponding answer
                chatArea.append("Bot: " + answers[i] + "\n");
                inputField.setText(""); // Clear the input field
                return;
            }
        }

        // If no match is found, provide a default response
        chatArea.append("Bot: I'm sorry, I didn't understand that.\n");
        inputField.setText(""); // Clear the input field
    }

    private void setDarkModeTheme() {
        // Set dark mode colors
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusBlueGrey", new Color(18, 30, 49));
        UIManager.put("control", new Color(18, 30, 49));
        UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
        UIManager.put("info", new Color(18, 30, 49));
        UIManager.put("nimbusSelectionBackground", new Color(49, 72, 94));
        UIManager.put("text", new Color(169, 183, 198));
        UIManager.put("nimbusInfoBlue", new Color(160, 160, 160));
        UIManager.put("nimbusFocus", new Color(115, 164, 209));
        UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
    }

    public static void main(String[] args) {

    }
}

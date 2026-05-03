import javax.swing.*;
import java.awt.*;
public class MainGUI {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private UserManager userManager = new UserManager();
    private HealthService healthService = new HealthService();

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;

    private JTextArea outputArea;

    public MainGUI() {
        frame = new JFrame("Health Tracker BMI System");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(loginPanel(), "login");
        mainPanel.add(menuPanel(), "menu");

        frame.add(mainPanel);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Login Panel
    private JPanel loginPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerBtn);
        panel.add(loginBtn);

        registerBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (userManager.register(user, pass)) {
                JOptionPane.showMessageDialog(frame, "Registered Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "User already exists!");
            }
        });

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (userManager.login(user, pass)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                cardLayout.show(mainPanel, "menu");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
            }
        });

        return panel;
    }

    //  Menu Panel
    private JPanel menuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        weightField = new JTextField();
        heightField = new JTextField();
        ageField = new JTextField();

        inputPanel.add(new JLabel("Weight (kg):"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Height (m):"));
        inputPanel.add(heightField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);

        panel.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton addBtn = new JButton("Add Record");
        JButton historyBtn = new JButton("View History");
        JButton tipBtn = new JButton("Daily Tip");
        JButton countBtn = new JButton("Record Count");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(tipBtn);
        buttonPanel.add(countBtn);
        buttonPanel.add(exitBtn);

        panel.add(buttonPanel, BorderLayout.CENTER);

        outputArea = new JTextArea();
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        //  Actions

        addBtn.addActionListener(e -> {
            try {
                double w = Double.parseDouble(weightField.getText());
                double h = Double.parseDouble(heightField.getText());
                int a = Integer.parseInt(ageField.getText());

                HealthRecord r = new HealthRecord(w, h, a);
                healthService.addRecord(r);

                outputArea.setText(r.getDetails());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        historyBtn.addActionListener(e -> {
            outputArea.setText("");
            for (HealthRecord r : healthService.getRecords()) {
                outputArea.append("---------------------\n");
                outputArea.append(r.getDetails());
            }
        });

        tipBtn.addActionListener(e -> {
            outputArea.setText("Health Tip: " + healthService.getDailyTip());
        });

        countBtn.addActionListener(e -> {
            outputArea.setText("Total Records: " + healthService.getRecordCount());
        });

        exitBtn.addActionListener(e -> System.exit(0));

        return panel;
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}


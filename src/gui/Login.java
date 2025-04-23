package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class Login extends JFrame {
    private HashMap<String, String> userCredentials;

    public Login() {
        initialize();
        initializeUserCredentials();
    }

    private void initialize() {
        setTitle("Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 8, 8));
        panel.setBackground(new Color(219, 254, 184));

        JTextField usernameField = new JTextField();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> handleLoginButton(usernameField.getText(), new String(passwordField.getPassword())));
        panel.add(loginButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> System.exit(0));
        panel.add(cancelButton);

        setContentPane(panel);
        setVisible(true);
    }

    private void initializeUserCredentials() {
        userCredentials = new HashMap<>();
        userCredentials.put("Archita", "any1");
        userCredentials.put("Sanjana", "sns1");
        userCredentials.put("Priyal", "ppt1");
    }

    private void handleLoginButton(String username, String password) {
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            setVisible(false);
            new Evaluation(username).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
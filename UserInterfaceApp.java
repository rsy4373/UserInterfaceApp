import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterfaceApp extends JFrame {

    private final JTextField nameField;
    private final JTextField messageField;
    private final JLabel    statusLabel;
    private final JLabel    counterLabel;
    private       int       clickCount = 0;

    public UserInterfaceApp() {
        super("APAN 5170 – GUI Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 380);
        setLocationRelativeTo(null);   
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(root);

        JLabel titleLabel = new JLabel("Interactive GUI Application", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 80, 160));
        root.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("User Input"));

        formPanel.add(new JLabel("Your Name:"));
        nameField = new JTextField(20);
        nameField.setToolTipText("Enter your name here");
        formPanel.add(nameField);

        formPanel.add(new JLabel("Your Message:"));
        messageField = new JTextField(20);
        messageField.setToolTipText("Enter a message here");
        formPanel.add(messageField);

        JButton greetButton = new JButton("Greet Me");
        greetButton.setBackground(new Color(30, 120, 200));
        greetButton.setForeground(Color.WHITE);
        greetButton.setFocusPainted(false);
        formPanel.add(greetButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(200, 60, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        formPanel.add(clearButton);

        formPanel.add(new JLabel("Button Click Counter:"));
        counterLabel = new JLabel("0 clicks");
        counterLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        formPanel.add(counterLabel);

        root.add(formPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Welcome! Enter your name and a message, then click 'Greet Me'.",
                                  SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        statusLabel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(240, 248, 255));
        statusLabel.setPreferredSize(new Dimension(0, 45));
        root.add(statusLabel, BorderLayout.SOUTH);

        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGreet();
            }
        });

        clearButton.addActionListener(e -> handleClear());

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    statusLabel.setText("Typing… hi there, " + name + "!");
                } else {
                    statusLabel.setText("Start typing your name above.");
                }
            }
        });

        setVisible(true);
    }

    private void handleGreet() {
        String name    = nameField.getText().trim();
        String message = messageField.getText().trim();

        clickCount++;
        counterLabel.setText(clickCount + (clickCount == 1 ? " click" : " clicks"));

        if (name.isEmpty()) {
            statusLabel.setForeground(new Color(180, 0, 0));
            statusLabel.setText("Please enter your name before greeting!");
            return;
        }

        String response = message.isEmpty()
                ? "Hello, " + name + "! No message provided."
                : "Hello, " + name + "! You said: \"" + message + "\"";

        statusLabel.setForeground(new Color(0, 110, 0));
        statusLabel.setText(response);
    }

    private void handleClear() {
        nameField.setText("");
        messageField.setText("");
        statusLabel.setForeground(Color.DARK_GRAY);
        statusLabel.setText("Fields cleared. Ready for new input.");

        clickCount++;
        counterLabel.setText(clickCount + (clickCount == 1 ? " click" : " clicks"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInterfaceApp::new);
    }
}
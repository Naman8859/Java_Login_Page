import javax.swing.*; // Importing Swing components for GUI.
import java.awt.*; // Importing AWT classes for layout and graphics.
import java.awt.event.ActionEvent; // Importing ActionEvent for event handling.
import java.awt.event.ActionListener; // Importing ActionListener interface.
import java.awt.event.MouseAdapter; // Importing MouseAdapter for mouse events.
import java.awt.event.MouseEvent; // Importing MouseEvent for mouse-specific actions.

// Class definition extending JFrame and implementing ActionListener interface.
public class Main extends JFrame {
    // Main method toa run the application.
    public static void main(String[] args) {

        new Main(); // Creating an instance of the RegistrationPage class.
    }

    JLabel lb; // JLabel for displaying text on the left panel.
    JButton regist, login; // JButton instances for registration and login actions.
    JPanel leftPanel, rightPane, buttonPanel; // JPanels for organizing the layout.

    // Constructor for RegistrationPage.
    Main() {
        // Defining the "Registration" button with custom painting.
        regist = new JButton("Registration") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g1 = (Graphics2D) g; // Cast Graphics to Graphics2D for advanced rendering.
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing.
                g1.setColor(getBackground()); // Set background color.
                g1.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); // Draw a rounded rectangle.
                super.paintComponent(g); // Call the parent class's paint method.
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g1 = (Graphics2D) g; // Cast Graphics to Graphics2D.
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing for border.
                g1.setColor(Color.GRAY); // Set border color.
                g1.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight()); // Draw rounded border.
            }
        };

        // Adding mouse listener for hover effects.
        regist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // Change color on hover.
                regist.setBackground(Color.decode("#4CAF50")); // Set hover color.
                regist.repaint(); // Repaint the button.
            }

            @Override
            public void mouseExited(MouseEvent e1) { // Revert color on hover exit.
                regist.setBackground(null); // Reset background color.
                regist.repaint(); // Repaint the button.
            }
        });

        // Setting font and appearance for the "Registration" button.
        regist.setFont(new Font("Orbitron", Font.PLAIN, 19)); // Set font style and size.
        regist.setBorderPainted(false); // Remove button border painting.
        regist.setFocusPainted(false); // Remove focus border.
        regist.setContentAreaFilled(false); // Remove default content area filling.
        regist.setOpaque(false); // Make the button transparent.
        regist.setPreferredSize(new Dimension(150, 40)); // Set preferred size.
        regist.setSize(regist.getPreferredSize()); // Apply preferred size.
        regist.addActionListener(new ActionListener() { // Add action listener for button click.
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp s = new SignUp(); // Open the Main class on click.
                dispose(); // Close the current window.
            }
        });

        // Defining the "Login" button with custom painting.
        login = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g1 = (Graphics2D) g; // Cast Graphics to Graphics2D.
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing.
                if (getModel().isRollover()) { // Change color on hover.
                    g1.setColor(Color.BLUE);
                } else {
                    g1.setColor(getBackground());
                }
                g1.setColor(getBackground()); // Set background color.
                g1.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); // Draw a rounded rectangle.
                super.paintComponent(g); // Call the parent class's paint method.
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g1 = (Graphics2D) g; // Cast Graphics to Graphics2D.
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing for border.
                g1.setColor(Color.GRAY); // Set border color.
                g1.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight()); // Draw rounded border.
            }
        };

        // Adding mouse listener for hover effects.
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // Change color on hover.
                login.setBackground(Color.decode("#2196F3")); // Set hover color.
                login.repaint(); // Repaint the button.
            }

            @Override
            public void mouseExited(MouseEvent e) { // Revert color on hover exit.
                login.setBackground(null); // Reset background color.
                login.repaint(); // Repaint the button.
            }
        });

        // Setting font and appearance for the "Login" button.
        login.setFont(new Font("Orbitron", Font.PLAIN, 19)); // Set font style and size.
        login.setBorderPainted(false); // Remove button border painting.
        login.setFocusPainted(false); // Remove focus border.
        login.setContentAreaFilled(false); // Remove default content area filling.
        login.setOpaque(false); // Make the button transparent.
        login.setPreferredSize(new Dimension(150, 40)); // Set preferred size.
        login.setSize(login.getPreferredSize()); // Apply preferred size.
        login.addActionListener(new ActionListener() { // Add action listener for button click.
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage lp = new LoginPage(); // Open the LoginPage class on click.
                dispose(); // Close the current window.
            }
        });

        // Setting up the welcome label.
        lb = new JLabel("WELCOME", SwingConstants.CENTER); // Create a JLabel with centered text.
        lb.setFont(new Font("Orbitron", Font.BOLD, 32)); // Set font style and size.

        // Setting up the left panel.
        leftPanel = new JPanel(new BorderLayout()); // Create a panel with BorderLayout.
        leftPanel.setBackground(Color.GREEN); // Set background color.
        leftPanel.add(lb, BorderLayout.CENTER); // Add label to the center of the panel.

        // Setting up the button panel for layout organization.
        buttonPanel = new JPanel(new BorderLayout()); // Create a panel with BorderLayout.
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(130, 0, 100, 0)); // Add padding around the panel.
        buttonPanel.add(regist, BorderLayout.NORTH); // Add registration button to the top.
        buttonPanel.add(login, BorderLayout.SOUTH); // Add login button to the bottom.

        // Setting up the right panel.
        rightPane = new JPanel(new BorderLayout()); // Create a panel with BorderLayout.
        rightPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel.
        rightPane.add(buttonPanel, BorderLayout.CENTER); // Add button panel to the center.

        // Setting up the main frame layout.
        setLayout(new GridLayout(1, 2)); // Divide the frame into two equal parts.
        add(leftPanel); // Add the left panel.
        add(rightPane); // Add the right panel.

        // Final frame settings.
        setTitle("Registration"); // Set the title of the window.
        setSize(500, 400); // Set the size of the window.
        setVisible(true); // Make the window visible.
        setLocationRelativeTo(null); // Center the window on the screen.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Close the window on exit.
    }

}

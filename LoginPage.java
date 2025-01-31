import javax.swing.*; // Importing Swing components for GUI.
import java.awt.*; // Importing AWT classes for layout and graphics.
import java.awt.event.ActionEvent; // Importing ActionEvent for event handling.
import java.awt.event.ActionListener; // Importing ActionListener interface.
import java.awt.event.MouseAdapter; // Importing MouseAdapter for mouse events.
import java.awt.event.MouseEvent; // Importing MouseEvent for mouse-specific actions.
import java.io.File; // Importing File class to handle file operations.
import java.sql.Connection; // Importing Connection for database connectivity.
import java.sql.DriverManager; // Importing DriverManager to establish database connection.
import java.sql.PreparedStatement; // Importing PreparedStatement for SQL queries.
import java.sql.ResultSet; // Importing ResultSet to store query results.

// Class definition extending JFrame for GUI and implementing the login functionality.
public class LoginPage extends JFrame {

    JLabel userName, pass, result; // Labels for username, password, and result messages.
    JTextField tf1; // TextField for entering the username.
    JPasswordField pw; // PasswordField for entering the password.
    JButton log, canc; // Buttons for login and cancel actions.
    JPanel panel; // Panel to organize components.

    // Database connection details.
    String url = "jdbc:mysql://localhost:3306/Registration_Page"; // Database URL.
    String username = "root"; // Database username.
    String passw = "root"; // Database password.

    // Constructor to initialize the login page.
    LoginPage() {
        // Label for username input.
        userName = new JLabel("Enter your User Name");

        // TextField with custom rendering for rounded corners and transparency.
        tf1 = new JTextField(50) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.white);
                g1.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
                g1.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
                g1.dispose();
            }
        };
        tf1.setOpaque(false);
        tf1.setBackground(new Color(0, 0, 0, 0));
        tf1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Label for password input.
        pass = new JLabel("Enter your password");

        // PasswordField with custom rendering for rounded corners and transparency.
        pw = new JPasswordField(50) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.white);
                g1.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
                g1.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                g1.dispose();
            }
        };
        pw.setOpaque(false);
        pw.setBackground(new Color(0, 0, 0, 0));
        pw.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        result = new JLabel(); // Label to display result messages.

        // Login button with custom rendering.
        log = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g1.setColor(Color.BLUE);
                }
                else {
                    g1.setColor(getBackground());
                }
                g1.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
            }
        };

        // Add hover effects for login button.
        log.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                log.setBackground(Color.decode("#03A9F4"));
                log.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e1) {
                log.setBackground(null);
                log.repaint();
            }
        });
        log.setBorderPainted(false);

        // Add action listener for login functionality.
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver.
                    Connection connection = DriverManager.getConnection(url, username, passw); // Establish connection.
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_data WHERE lower(name) = lower(?) AND password = ?");
                    ps.setString(1, tf1.getText().trim()); // Set username in query.
                    ps.setString(2, new String(pw.getPassword()).trim()); // Set password in query.

                    ResultSet rs = ps.executeQuery(); // Execute query.
                    if (rs.next()) {
                        File htmlFile = new File("/Users/namansinghchauhan/Desktop/Website/new/index.html"); // Open a file on successful login.
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(htmlFile);
                        JOptionPane.showMessageDialog(null, "Login Successful", "Login", JOptionPane.INFORMATION_MESSAGE);
                        tf1.setText(""); // Clear username field.
                        pw.setText(""); // Clear password field.
                        dispose(); // Close login page.
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Either password or the username is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                        tf1.setText("");
                        pw.setText("");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    result.setText("Error reading data");
                }
            }
        });

        // Cancel button with custom rendering.
        canc = new JButton("Cancel") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(Color.RED);
                } else {
                    g2.setColor(getBackground());
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - fm.getDescent();
                g2.drawString(getText(), x, y);
//                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g3 = (Graphics2D) g;
                g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g3.setColor(Color.GRAY);
                g3.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getWidth());
            }
        };

        // Add hover effects for cancel button.
        canc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                canc.setBackground(Color.decode("#E57373"));
                canc.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e1) {
                canc.setBackground(null);
                canc.repaint();
            }
        });

        // Add action listener for cancel functionality.
        canc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main rp = new Main(); // Open main page.
                dispose(); // Close login page.
            }
        });

        // Setting up the panel to organize components.
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 2, 15)); // Grid layout for components.
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Add padding around panel.
        panel.add(userName);
        panel.add(tf1);
        panel.add(pass);
        panel.add(pw);
        panel.add(log);
        panel.add(canc);
        panel.add(result);

        // Adding panel to the frame.
        add(panel);
        setTitle("Login"); // Set window title.
        setVisible(true); // Make window visible.
        setSize(600, 400); // Set window size.
        setLocationRelativeTo(null); // Center the window on the screen.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Dispose window on close.
    }

    public static void main(String[] args) {
        new LoginPage(); // Launch the login page.
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SignUp extends JFrame implements ActionListener  {
    JTextField tf1,tf2, tf3;
    JButton b1,b2, back;
    JLabel name,password, email,con,result, gback;
    JPasswordField pw1;

    String url = "jdbc:mysql://localhost:3306/Registration_Page";
    String username = "root";
    String pass = "root";

    SignUp(){

        name = new JLabel("Enter your name");//Text to be shown on the frame
        tf1 = new JTextField(50);//Text Field where user enters the details
        email = new JLabel("Enter your Email Address");//Text to be shown on the frame
        tf2 = new JTextField(30);//Text Field where user enters the details
        con = new JLabel("Enter your contact number");//Text to be shown on the frame
        tf3=new JTextField(15);//Text Field where user enters the details
        password = new JLabel("Enter your password");//Text to be shown on the frame
        pw1 = new JPasswordField(14);//Password Field where user create their own password
        result = new JLabel();
        gback = new JLabel();
        b1 = new JButton("Sign UP"){
            protected void paintComponent(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(getModel().isRollover()) {
                    g1.setColor(Color.BLUE);
                }else {
                    g1.setColor(getBackground());
                }
                g1.setColor(getBackground());
                g1.fillRoundRect(0,0,getWidth(),getHeight(),50,50);
                super.paintComponent(g);
            }
            protected void paintBorder(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0,0,getWidth()-1, getHeight()-1,getHeight(), getHeight());
            }

        };
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                b1.setBackground(Color.decode("#FFC107"));
                b1.repaint();
            }
            public void mouseExited(MouseEvent e1){
                b1.setBackground(null);
                b1.repaint();
            }
        });
        b1.setBorderPainted(false);;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws Error{
                try{
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    }catch (Exception E){
                        E.printStackTrace();
                    }
                        Connection connection = DriverManager.getConnection(url, username, pass);


                        PreparedStatement ps1 = connection.prepareStatement("Select *from user_data where name = ?");
                        ps1.setString(1, tf1.getText());
                        ResultSet rs = ps1.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "The name has already taken", "Warning", JOptionPane.WARNING_MESSAGE);
                            tf1.setText("");
                            return;
                        }
                    PreparedStatement ps2 = connection.prepareStatement("Select *from user_data where Mail = ?");
                    ps2.setString(1, tf2.getText());
                    ResultSet rs1 = ps2.executeQuery();
                         if (rs1.next()) {
                             JOptionPane.showMessageDialog(null, "The email id has already taken", "Warning", JOptionPane.WARNING_MESSAGE);
                             tf2.setText("");
                             return;
                        } else {
                            PreparedStatement ps = connection.prepareStatement("Insert into user_data value(?,?,?,?)");
                            ps.setString(1, tf1.getText());
                            ps.setString(2, tf2.getText());
                            ps.setString(3, tf3.getText());
                            ps.setString(4, new String(pw1.getPassword()));
                            ps.executeUpdate();
                            ps.close();
                            connection.close();
                            JOptionPane.showMessageDialog(null, "Registration Successful", "User Data", JOptionPane.INFORMATION_MESSAGE);
                            tf1.setText("");
                            tf2.setText("");
                            tf3.setText("");
                            pw1.setText("");
                            result.setText("Press The Back Button To Login Yourself");
                        }
                }catch (SQLException a){
                    a.printStackTrace();
                    result.setText("En error occured");
                }
            }
        });

        b2 = new JButton("Cancel"){
            protected void paintComponent(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(getModel().isRollover()) {
                    g1.setColor(Color.BLUE);
                }else {
                    g1.setColor(getBackground());
                }
                g1.setColor(getBackground());
                g1.fillRoundRect(0,0,getWidth(),getHeight(),50,50);
                super.paintComponent(g);
            }
            protected void paintBorder(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0,0,getWidth()-1, getHeight()-1,getHeight(), getHeight());
            }

        };
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                b2.setBackground(Color.decode("#F44336"));
                b2.repaint();
            }
            public void mouseExited(MouseEvent e1){
                b2.setBackground(null);
                b2.repaint();
            }
        });
        b2.setBorderPainted(false);;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        back = new JButton("Back"){
            protected void paintComponent(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if(getModel().isRollover()) {
                    g1.setColor(Color.BLUE);
                }else {
                    g1.setColor(getBackground());
                }
                g1.setColor(getBackground());
                g1.fillRoundRect(0,0,getWidth(),getHeight(),50,50);
                super.paintComponent(g);
            }
            protected void paintBorder(Graphics g){
                Graphics2D g1 = (Graphics2D) g;
                g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g1.setColor(Color.GRAY);
                g1.drawRoundRect(0,0,getWidth()-1, getHeight()-1,getHeight(), getHeight());
            }

        };
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main rp = new Main();
                dispose();
            }
        });
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(Color.decode("#9E9E9E"));
                back.repaint();
            }
            public void mouseExited(MouseEvent e1){
                back.setBackground(null);
                back.repaint();
            }
        });
        back.setBorderPainted(false);;
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main rp = new Main();
                dispose();
            }
        });
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setLayout(new GridLayout(0,1,2,3));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
        formPanel.add(name);
        formPanel.add(tf1);
        formPanel.add(email);
        formPanel.add(tf2);
        formPanel.add(con);
        formPanel.add(tf3);
        formPanel.add(password);
        formPanel.add(pw1);
        formPanel.add(b1);
        formPanel.add(b2);
        formPanel.add(back);
        formPanel.add(result);
        formPanel.add(gback);

        add(formPanel);
        setTitle("SignUp");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setVisible(true);
        setSize(600,500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
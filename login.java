import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class Login extends JFrame implements ActionListener
{
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1;
    JPasswordField p1;
 
    void login()
    {
        setTitle("Log in");
        setVisible(true);
        setSize(200, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        l1 = new JLabel("Log In:");
        l1.setForeground(Color.red);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
 
        l2 = new JLabel("Enter your email:");
        l3 = new JLabel("Enter password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Submit");
 
        l1.setBounds(25, 7, 100, 7); 
        l2.setBounds(20, 17, 50, 7); 
        l3.setBounds(20, 27, 50, 7); 
        tf1.setBounds(65, 17, 50, 7); 
        p1.setBounds(75, 27, 50, 7);
        btn1.setBounds(75, 27, 50, 7); 
 
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(p1);
        add(btn1);
        btn1.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e)
    {
        showData();
    }
 
    public void showData()
    {
        JFrame f1 = new JFrame();
        JLabel l, l0;
 
        String str1 = tf1.getText();
        char[] p = p1.getPassword();
        String str2 = new String(p);
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe", "?", "Welcome");
            PreparedStatement ps = con.prepareStatement("select name from reg where email=? and pass=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                f1.setVisible(true);
                f1.setSize(150, 150);
                f1.setLayout(null);
                l = new JLabel();
                l0 = new JLabel("Welcome");
                l0.setForeground(Color.yellow);
                l0.setFont(new Font("Serif", Font.BOLD, 15));
                l.setBounds (15, 12, 100, 7); 
                l0.setBounds(15, 25, 100, 10); 
 
                f1.add(l);
                f1.add(l0);
                l.setText("Welcome " + rs.getString(1));
                l.setForeground(Color.yellow);
                l.setFont(new Font("Serif", Font.BOLD, 15));
 
            } else
            {
                JOptionPane.showMessageDialog(null,
                   "Invalid email/password. Try again.");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
 
    public static void main(String arr[])
    {
        new login();
    }
}

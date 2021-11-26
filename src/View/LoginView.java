package src.View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import src.Controller.*;
public class LoginView extends JPanel implements ActionListener {
    private static final long serialVersionUID = 10;
    private ImageIcon picLogo = new ImageIcon("Resources/Images/logo.png");
    private JLabel logoLabel = new JLabel(picLogo);
    private JLabel passwordLabel = new JLabel("Enter the password:");
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");

   public LoginView() {
        super(new GridBagLayout());
        setBackground(new Color(21,72,84));
        logoLabel.setPreferredSize(new Dimension(550, 100));
        passwordLabel.setForeground (Color.white);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        add(logoLabel,c);
        c.gridy=1;
        add(passwordLabel,c);
        c.gridy=2;
        add(passwordField,c);
        c.gridy=3;
        add(loginButton,c);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(input)) {
                System.out.println("Exit");
                super.setVisible(false);
                super.remove(this);
                Controller.MenuV();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid password. Try again.");
            }

        }
        if (e.getSource() == resetButton) {
            passwordField.setText("");
        }
    }
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'r', 'o', 'o', 't'};

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
        Arrays.fill(correctPassword,'0');
        return isCorrect;
    }
}

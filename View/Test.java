import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JPanel implements ActionListener {
    protected JTextField nameField;
    private JLabel nameLabel;
    protected JTextField ageField;
    private JLabel ageLabel;
    protected JTextField addressField;
    private JLabel addressLabel;
    protected JTextField seniorityField;
    private JLabel seniorityLabel;
    protected JTextField lastnameField;
    private JLabel lastnameLabel;
    protected JButton save;
    private final static String newline = "\n";

    public Test() {
        super(new GridBagLayout());

        nameField = new JTextField(20);
        nameLabel = new JLabel("Enter name:");
        ageField = new JTextField(20);
        ageLabel = new JLabel("Enter age:");
        addressField = new JTextField(20);
        addressLabel = new JLabel("Enter address:");
        seniorityField = new JTextField(20);
        seniorityLabel = new JLabel("Enter seniority:");
        lastnameField = new JTextField(20);
        lastnameLabel = new JLabel("Enter lastname:");
        save = new JButton("Save data");
        save.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        add(nameLabel, c);
        add(nameField, c);
        add(lastnameLabel, c);
        add(lastnameField, c);
        add(ageLabel, c);
        add(ageField, c);
        add(addressLabel, c);
        add(addressField, c);
        add(seniorityLabel, c);
        add(seniorityField, c);
        add(save, c);
    }

    public void actionPerformed(ActionEvent evt) {
        boolean flag=true;
        if(nameField.getText().length()==0){
            flag=false;
            nameLabel.setForeground (Color.red);
        }
        else{
            nameField.setEditable(false);
            nameLabel.setForeground (Color.green);
        }
        if(lastnameField.getText().length()==0){
            flag=false;
            lastnameLabel.setForeground (Color.red);
            }
        else{
            lastnameField.setEditable(false);
            lastnameLabel.setForeground (Color.green);
            }
        if(ageField.getText().length()==0){
            flag=false;
            ageLabel.setForeground (Color.red);
            }
        else{
            ageField.setEditable(false);
            ageLabel.setForeground (Color.green);
            }
        if(addressField.getText().length()==0){
            flag=false;
            addressLabel.setForeground (Color.red);
            }
        else{
            addressField.setEditable(false);
            addressLabel.setForeground (Color.green);
            }
        if(seniorityField.getText().length()==0){
            flag=false;
            seniorityLabel.setForeground (Color.red);
            }
        else{
            seniorityField.setEditable(false);
            seniorityLabel.setForeground (Color.green);
            }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Create view");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new Test());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

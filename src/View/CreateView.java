package src.View;

import src.Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Create panel class
 */
public class CreateView extends JPanel implements ActionListener {
    /**
     * Mask for the ageField
     */
    private MaskFormatter dateMask;
    /**
     * Mask for the seniorityField
     */
    private MaskFormatter dateMask2;
    /**
     * Field for the name/s
     */
    protected JTextField nameField;
    /**
     * Label for the nameField
     */
    private JLabel nameLabel;
    /**
     * Formatted field for the birthday
     */
    protected JFormattedTextField ageField = new JFormattedTextField();
    /**
     * Label for the ageField
     */
    private JLabel ageLabel;
    /**
     * Field for the address
     */
    protected JTextField addressField;
    /**
     * Label for the addressField
     */
    private JLabel addressLabel;
    /**
     * Formatted Field for the contract date
     */
    protected JFormattedTextField seniorityField = new JFormattedTextField();
    /**
     * Label for the seniorityField
     */
    private JLabel seniorityLabel;
    /**
     * Field for the lastname/s
     */
    protected JTextField lastnameField;
    /**
     * Label for the lastnameField
     */
    private JLabel lastnameLabel;
    /**
     * Field for the id
     */
    protected JTextField idField;
    /**
     * Label for the idField
     */
    private JLabel idLabel;
    /**
     * Empty label for spacing
     */
    private JLabel emptyLabel;
    /**
     * Save button
     */
    protected JButton save;
    /**
     * Go back button
     */
    protected JButton exit;
    /**
     * Model for the actual projects table
     */
    private final DefaultTableModel amodel = new DefaultTableModel(0,0);
    /**
     * Table for the actual projects
     */
    protected JTable atable = new JTable(amodel);
    /**
     * Model for the past prejects table
     */
    private final DefaultTableModel pmodel = new DefaultTableModel(0,0);
    /**
     * Table for the last projects
     */
    protected JTable ptable = new JTable(pmodel);

    public CreateView(){}

    /**
     * Allocates a CreateView objects with the id sent on the idField
     * @param id Id of the worker
     */
    public CreateView(int id){
        super(new GridBagLayout());
        Dimension dim= new Dimension(120,120);
        String[] acolumnNames = {"Actual projects"};
        amodel.setColumnIdentifiers(acolumnNames);
        Object[] adata = {""};
        amodel.addRow(adata);
        atable.setModel(amodel);
        JScrollPane jsa=new JScrollPane(atable);
        jsa.setPreferredSize(dim);
        jsa.setVisible(true);
        String[] pcolumnNames = {"Past projects"};
        Object[] pdata = {""};
        pmodel.setColumnIdentifiers(pcolumnNames);
        pmodel.addRow(pdata);
        ptable.setModel(pmodel);
        JScrollPane jsp=new JScrollPane(ptable);
        jsp.setPreferredSize(dim);
        jsp.setVisible(true);
        idField = new JTextField(20);
        idLabel = new JLabel("Id:");
        emptyLabel = new JLabel("");
        nameField = new JTextField(20);
        nameLabel = new JLabel("Enter name/s:");
        ageLabel = new JLabel("Enter birthday(yyyy-mm-dd):");
        addressField = new JTextField(20);
        addressLabel = new JLabel("Enter address:");
        seniorityLabel = new JLabel("Enter contract date(yyyy-mm-dd):");
        lastnameField = new JTextField(20);
        lastnameLabel = new JLabel("Enter lastname/s:");
        try{
            dateMask = new MaskFormatter("####-##-##");
            dateMask2 = new MaskFormatter("####-##-##");
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        dateMask.install(ageField);
        dateMask2.install(seniorityField);
        save = new JButton("Save data");
        exit = new JButton("Go back");
        exit.addActionListener(this);
        save.addActionListener(this);
        idField.setText(String.valueOf(id));
        idField.setEditable(false);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        atable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "Solve");
        atable.getActionMap().put("Solve", new atableRow());
        ptable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "Solve");
        ptable.getActionMap().put("Solve", new ptableRow());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        add(idLabel,c);
        add(seniorityLabel,c);
        c.gridy=1;
        add(idField,c);
        add(seniorityField,c);
        c.gridy=2;
        add(nameLabel,c);
        add(lastnameLabel,c);
        c.gridy=3;
        add(nameField,c);
        add(lastnameField,c);
        c.gridy=4;
        add(ageLabel,c);
        add(addressLabel,c);
        c.gridy=5;
        add(ageField,c);
        add(addressField,c);
        c.gridy=6;
        add(emptyLabel,c);
        c.gridx=4;
        c.gridy=7;
        add(save, c);
        c.gridx=3;
        c.gridy=0;
        c.gridheight = 7;
        add(jsa,c);
        c.gridx=4;
        add(jsp,c);
        c.gridx=0;
        c.gridy=7;
        add(exit,c);
    }

    /**
     * Adds a cell to the  actual projects table if all the cells are full
     */
    private class atableRow extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean full=true;
            int column = atable.getSelectedColumn();
            int row = atable.getRowCount();
            for(int x=0;x<row;x++){
                String res = (String)atable.getValueAt(x, column);
                if(res.length()==0)
                    full=false;
            }
            if(full){
            Object[] obj = {""};
                amodel.addRow(obj);
                atable.setModel(amodel);
            }
        }
    }

    /**
     * Adds a cell to the past projects table if all the cells are full
     */
    private class ptableRow extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean full=true;
            int column = ptable.getSelectedColumn();
            int row = ptable.getRowCount();
            for(int x=0;x<row;x++){
                String res = (String)ptable.getValueAt(x, column);
                if(res.length()==0)
                    full=false;
            }
            if(full){
            Object[] obj = {""};
                pmodel.addRow(obj);
                ptable.setModel(pmodel);
            }
        }
    }

    /**
     * If the exit button is pressed, removes the create view and calls the MenuV method of the controller.
     * If the Save date button is pressed, validates if the data is correct, if its correct removes the create view ,calls the Create method of the controller and calls the MenuV method of the controller, Otherwise shows an error message and highlights the not valid data
     * @param evt The press of any button
     */
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == exit) {
            System.out.println("Exit");
            super.setVisible(false);
            super.remove(this);
            Controller.MenuV();
        }
        else {
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
            if(!ageField.isEditValid()||!isDate(ageField.getText())){
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
            if(!seniorityField.isEditValid()||!isDate(seniorityField.getText())){
                flag=false;
                seniorityLabel.setForeground (Color.red);
            }
            else{
                seniorityField.setEditable(false);
                seniorityLabel.setForeground (Color.green);
                }
            if(flag){
                ArrayList<String> ppro = new ArrayList<>();
                ArrayList<String> apro = new ArrayList<>();
                for(int x=0;x<atable.getRowCount();x++){
                        String res = (String)atable.getValueAt(x, 0);
                        if(res.length()!=0)
                            apro.add(res.replace(" ","_"));
                }
                for(int x=0;x<ptable.getRowCount();x++){
                        String res = (String)ptable.getValueAt(x, 0);
                        if(res.length()!=0)
                            ppro.add(res.replace(" ","_"));
                }
                if(ppro.size()==0)
                    ppro.add("_");
                if(apro.size()==0)
                    apro.add("_");
                Controller.Create(nameField.getText().replace(" ","_")+" "+lastnameField.getText().replace(" ","_"),addressField.getText(),Integer.parseInt(idField.getText()),ageField.getText(),seniorityField.getText(),ppro,apro);
                System.out.println("Exit");
                super.setVisible(false);
                super.remove(this);
                Controller.MenuV();
            }
            else
                JOptionPane.showMessageDialog(this, "Please complete all required fields or verify the inputs");
        }
    }

    /**
     * Validates if the date sent is correct
     * @param date date to validate
     * @return true if the date sent is valid and false otherwise
     */
    private boolean isDate(String date){
        try{
            LocalDate.parse(date);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

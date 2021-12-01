package src.View;

import src.Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Read panel class
 */
public class ReadView extends JPanel implements ActionListener {
    /**
     * Field for the name/s
     */
    protected JTextField nameField;
    /**
     * Label for the nameField
     */
    private JLabel nameLabel;
    /**
     * Field for the age
     */
    protected JTextField ageField;
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
     * Field for the seniority
     */
    protected JTextField seniorityField;
    /**
     * Label for the seniority field
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
     * Exit button
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
     * Model for the past projects table
     */
    private final DefaultTableModel pmodel = new DefaultTableModel(0,0);
    /**
     * Table for the past projects
     */
    protected JTable ptable = new JTable(pmodel);

    public ReadView(){}

    /**
     * Allocates a ReadView objects and initializes it with the data sent
     * @param id Id of the worker
     * @param name Name of the worker
     * @param lastname Lastname of the worker
     * @param address Address of the worker
     * @param age Age of the worker
     * @param seniority Seniority of the worker
     * @param actualProyects Actual projects of the worker
     * @param pastProyects Past projects of the worker
     */
    public ReadView(int id,String name,String lastname,String address,int age,int seniority,List<String> actualProyects,List<String> pastProyects) {
        super(new GridBagLayout());
        Dimension dim= new Dimension(120,120);
        ArrayList<String> elements = new ArrayList<>();
        String[] acolumnNames = {"Actual projects"};
        amodel.setColumnIdentifiers(acolumnNames);
        for(String row:actualProyects){
            row=row.replace("_"," ");
            elements.add(row);
            Object[] adata = elements.toArray();
            amodel.addRow(adata);
            elements.clear();
        }
        atable.setModel(amodel);
        JScrollPane jsa=new JScrollPane(atable);
        jsa.setPreferredSize(dim);
        jsa.setVisible(true);
        String[] pcolumnNames = {"Past projects"};
        pmodel.setColumnIdentifiers(pcolumnNames);
        for(String row:pastProyects){
            row=row.replace("_"," ");
            elements.add(row);
            Object[] pdata = elements.toArray();
            pmodel.addRow(pdata);
            elements.clear();
        }
        ptable.setModel(pmodel);
        JScrollPane jsp=new JScrollPane(ptable);
        jsp.setPreferredSize(dim);
        jsp.setVisible(true);
        idField = new JTextField(20);
        idLabel = new JLabel("Id:");
        emptyLabel = new JLabel("");
        nameField = new JTextField(20);
        nameLabel = new JLabel("Name/s:");
        ageField = new JTextField(20);
        ageLabel = new JLabel("Age:");
        addressField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        seniorityField = new JTextField(20);
        seniorityLabel = new JLabel("Seniority:");
        lastnameField = new JTextField(20);
        lastnameLabel = new JLabel("Lastname/s:");
        exit = new JButton("Exit");
        exit.addActionListener(this);
        idField.setText(String.valueOf(id));
        nameField.setText(name.replace("_"," "));
        lastnameField.setText(lastname.replace("_"," "));
        seniorityField.setText(String.valueOf(seniority));
        ageField.setText(String.valueOf(age));
        addressField.setText(address);
        ptable.setDefaultEditor(Object.class, null);
        atable.setDefaultEditor(Object.class, null);
        idField.setEditable(false);
        nameField.setEditable(false);
        lastnameField.setEditable(false);
        seniorityField.setEditable(false);
        ageField.setEditable(false);
        addressField.setEditable(false);
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
        add(exit, c);
        c.gridx=3;
        c.gridy=0;
        c.gridheight = 7;
        add(jsa,c);
        c.gridx=4;
        add(jsp,c);
    }

    /**
     * If the exit button is pressed, removes the read view and calls the MenuV of the controller
     * @param evt The press of the exit button
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Exit");
        super.setVisible(false);
        super.remove(this);
        Controller.MenuV();
    }
}

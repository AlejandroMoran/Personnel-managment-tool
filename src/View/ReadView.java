package src.View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import src.Controller.*;
public class ReadView extends JPanel implements ActionListener {
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
    protected JTextField idField;
    private JLabel idLabel;
    private JLabel emptyLabel;
    protected JButton exit;
    private final DefaultTableModel amodel = new DefaultTableModel(0,0);
    protected JTable atable = new JTable(amodel);
    private final DefaultTableModel pmodel = new DefaultTableModel(0,0);
    protected JTable ptable = new JTable(pmodel);

    public ReadView(){}
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
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Exit");
        super.setVisible(false);
        super.remove(this);
        Controller.MenuV();
    }
}

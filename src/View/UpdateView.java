package src.View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import src.Controller.*;
import java.time.LocalDate;
public class UpdateView extends JPanel implements ActionListener {
    private MaskFormatter dateMask;
    private MaskFormatter dateMask2;
    protected JTextField nameField;
    private JLabel nameLabel;
    protected JFormattedTextField ageField = new JFormattedTextField();
    private JLabel ageLabel;
    protected JTextField addressField;
    private JLabel addressLabel;
    protected JFormattedTextField seniorityField = new JFormattedTextField();
    private JLabel seniorityLabel;
    protected JTextField lastnameField;
    private JLabel lastnameLabel;
    protected JTextField idField;
    private JLabel idLabel;
    private JLabel emptyLabel;
    protected JButton save;
    protected JButton exit;
    private final DefaultTableModel amodel = new DefaultTableModel(0,0);
    protected JTable atable = new JTable(amodel);
    private final DefaultTableModel pmodel = new DefaultTableModel(0,0);
    protected JTable ptable = new JTable(pmodel);
    private static final String solve = "Solve";

    public UpdateView(){}
    public UpdateView(int id,String name,String lastname,String address,String age,String seniority,List<String> actualProyects,List<String> pastProyects){
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
        try{
            dateMask = new MaskFormatter("####-##-##");
            dateMask2 = new MaskFormatter("####-##-##");
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        dateMask.install(ageField);
        dateMask2.install(seniorityField);
        idField = new JTextField(20);
        idLabel = new JLabel("Id");
        emptyLabel = new JLabel("");
        nameField = new JTextField(20);
        nameLabel = new JLabel("Name/s:");
        ageLabel = new JLabel("Birthday(yyyy-mm-dd):");
        addressField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        seniorityLabel = new JLabel("Contract date(yyyy-mm-dd):");
        lastnameField = new JTextField(20);
        lastnameLabel = new JLabel("Lastname/s:");
        save = new JButton("Save data");
        exit = new JButton("Go back");
        idField.setEditable(false);
        save.addActionListener(this);
        exit.addActionListener(this);
        idField.setText(String.valueOf(id));
        nameField.setText(name.replace("_"," "));
        lastnameField.setText(lastname.replace("_"," "));
        seniorityField.setText(seniority);
        ageField.setText(age);
        addressField.setText(address);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        atable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
        atable.getActionMap().put(solve, new atableRow());
        ptable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
        ptable.getActionMap().put(solve, new ptableRow());
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
                ageLabel.setForeground(Color.red);
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
                Controller.Update(nameField.getText().replace(" ","_")+" "+lastnameField.getText().replace(" ","_"),addressField.getText(),Integer.parseInt(idField.getText()),ageField.getText(),seniorityField.getText(),ppro,apro);
                System.out.println("Exit");
                super.setVisible(false);
                super.remove(this);
                Controller.MenuV();
            }
            else
                JOptionPane.showMessageDialog(this, "Please complete all required fields or verify the inputs");
        }
    }
    private boolean isDate(String date){
        try{
            LocalDate.parse(date);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

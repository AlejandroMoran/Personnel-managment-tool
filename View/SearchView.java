import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SearchView extends JPanel implements ActionListener {
    protected JTextField idField;
    private JLabel idLabel;
    protected JButton search;

    public SearchView() {
        super(new GridBagLayout());
        idField = new JTextField(20);
        idLabel = new JLabel("Enter id:");
        search = new JButton("Search");
        search.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        add(idLabel,c);
        c.gridy=1;
        add(idField,c);
        c.gridy=2;
        c.gridx=1;
        add(search,c);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Exit");
    }
}

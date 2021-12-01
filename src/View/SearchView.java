package src.View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.Controller.*;
public class SearchView extends JPanel implements ActionListener {
    protected JTextField idField;
    private final JLabel idLabel;
    protected JButton search;
    protected JButton exit;

    public SearchView() {
        super(new GridBagLayout());
        idField = new JTextField(10);
        idLabel = new JLabel("Enter id:");
        search = new JButton("Search");
        exit = new JButton("Go back");
        exit.addActionListener(this);
        search.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        add(idLabel,c);
        c.gridy=1;
        add(idField,c);
        c.gridy=2;
        c.gridx=1;
        add(search,c);
        c.gridx=0;
        c.gridy=2;
        c.insets = new Insets(4, 0, 4, 50);
        add(exit,c);
    }

    /**
     * If the exit button is pressed, removes the search view and calls the MenuV method of the controller
     * If the search button is presses, validates with the controller method Search if the id is on the database, if the id exists removes the search view and calls the either the ReadV, DeleteV or Update View methods of the controller depending on the controller state, Otherwise shows an error message
     * @param evt
     */
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == exit) {
            System.out.println("Exit");
            super.setVisible(false);
            super.remove(this);
            Controller.MenuV();
        }
        else {
            if(idField.getText().length()!=0&&Controller.Search(Integer.parseInt(idField.getText()))){
                System.out.println("Successful");
                super.setVisible(false);
                super.remove(this);
                if(Controller.state=='r')
                    Controller.ReadV(Integer.parseInt(idField.getText()));
                if(Controller.state=='u')
                    Controller.UpdateV(Integer.parseInt(idField.getText()));
                if(Controller.state=='d')
                    Controller.DeleteV(Integer.parseInt(idField.getText()));
            }
            else{
                JOptionPane.showMessageDialog(this, "This id is not in the database");
                System.out.println("Not in the database");
            }
        }
    }
}

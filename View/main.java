import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class main {
    private static MenuView menu = new MenuView();
    static boolean salir=true;
    private static void createV() {
        JFrame frame = new JFrame("Create");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        ArrayList<String> cars2 = new ArrayList<String>();
        cars2.add("Volvo2");
        cars2.add("BMW2");
        cars2.add("Ford2");
        cars2.add("Mazda2");
        //(4,"Alejandro","Moran","Patricio sanz 1028",19,1,cars,cars2)
        frame.add(menu);
        frame.setPreferredSize(new Dimension(698, 177));
        frame.pack();
        frame.setVisible(true);
        Rectangle r = frame.getBounds();
        System.out.println(r.height);
        System.out.println(r.width);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createV();
            }
        });
    }
}

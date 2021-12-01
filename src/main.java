package src;
/**
 *@author Alejandro Moran
 */
import src.Controller.Controller;

/**
 * The application's entry point.
 */
public class main {
    /**
     * Calls the Init and Test method of the controller
     * @param a An array of command-line arguments for the application
     */
    public static void main(String[] a) {
        Controller.Init();
        Controller.Test();
        System.out.println("DONE!");
    }
}

package src.Controller;

import src.Model.Model;
import src.Model.Worker;
import src.View.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Main and only class of the controller
 */
public class Controller{
    /**
     * Main frame of the application
     */
    public static JFrame frame = new JFrame();
    /**
     * Icon of the application
     */
    public static ImageIcon img = new ImageIcon("Resources/Images/icon.png");
    /**
     * Worker instance that storage the worker data while its in the controller
     */
    public static Worker data = new Worker();
    /**
     * Model instance which interacts with the database
     */
    public static Model model = new Model();
    /**
     * Menu panel
     */
    public static MenuView menuP = new MenuView();
    /**
     * Search panel
     */
    public static SearchView searchP = new SearchView();
    /**
     * Create panel
     */
    public static CreateView createP = new CreateView();
    /**
     * Update panel
     */
    public static UpdateView updateP = new UpdateView();
    /**
     * Read panel
     */
    public static ReadView readP = new ReadView();
    /**
     * Delete panel
     */
    public static DeleteView deleteP = new DeleteView();
    /**
     * Login panel
     */
    public static LoginView loginP = new LoginView();
    /**
     * Variable that indicates the state of the system('r','u','d')
     */
    public static char state;

    /**
     * Initializes the application, creates the window, sets the parameters, displays the login view and initializes the model
     */
    public static void Init(){
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(750, 200));
        frame.setResizable(false);
        frame.setTitle("Menu");
        frame.add(loginP);
        frame.pack();
        frame.setVisible(true);
        try {
        model.init();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
     * Displays the menu view
     */
    public static void MenuV(){
        menuP = new MenuView();
        frame.setTitle("Menu");
        frame.add(menuP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the search view
     */
    public static void SearchV(){
        searchP = new SearchView();
        frame.setTitle("Search");
        frame.add(searchP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the create view with the next smallest available id
     */
    public static void CreateV(){
        createP = new CreateView(model.getNextId());
        frame.setTitle("Create");
        frame.add(createP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the read view with the data of the worker with the id sent
     * @param id Id of the worker that we want to read
     */
    public static void ReadV(int id){
        Read(id);
        readP = new ReadView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),LocalDate.now().compareTo(LocalDate.parse(data.getAge())), LocalDate.now().compareTo(LocalDate.parse(data.getSeniority())), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Read");
        frame.add(readP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the update view with the data of the worker with the id sent
     * @param id Id of the worker that we want to update
     */
    public static void UpdateV(int id){
        Read(id);
        updateP = new UpdateView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),data.getAge(), data.getSeniority(), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Update");
        frame.add(updateP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Displays the delete view with the data of the worker with the id sent
     * @param id Id of the worker that we want to delete
     */
    public static void DeleteV(int id) {
        Read(id);
        deleteP = new DeleteView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),LocalDate.now().compareTo(LocalDate.parse(data.getAge())), LocalDate.now().compareTo(LocalDate.parse(data.getSeniority())), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Delete");
        frame.add(deleteP);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Calls the create method of model with the data sent
     * @param name Name of the worker
     * @param addr Address of the worker
     * @param id Id of the Worker
     * @param age Age of the worker (birthday)
     * @param seniority Seniority of the worker (birthday)
     * @param pastProyects Past projects of the worker
     * @param actualProyects Actual projects of the worker
     */
    public static void Create(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
        model.create(new Worker(name,addr,id,age,seniority,pastProyects,actualProyects));
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
     * Calls the search method of the model with the id sent
     * @param id Id of the worker
     * @return true if the id exists on the database and false otherwise
     */
    public static boolean Search(int id){
        return model.search(id);
    }

    /**
     * Calls the read method of the model with the id sent
     * @param id Id of the worker
     */
    public static void Read(int id){
        data = model.read(id);
    }

    /**
     * Calls the delete method of the model with the id sent
     * @param id Id of the worker
     */
    public static void Delete(int id){
        model.delete(id);
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
     * Calls the update method of the model with the data sent
     * @param name Name of the worker
     * @param addr Address of the worker
     * @param id Id of the Worker
     * @param age Age of the worker (birthday)
     * @param seniority Seniority of the worker (birthday)
     * @param pastProyects Past projects of the worker
     * @param actualProyects Actual projects of the worker
     */
    public static void Update(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
        model.update(id,new Worker(name,addr,id,age,seniority,pastProyects,actualProyects));
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
     * Generates the first 100 records with random data if they don't exist
     */
    public static void Test(){
        for(int i=1; i<=100; i++){
            if(!model.search(i)) {
                data = new Worker(i);
                model.create(data);
                GenerateName(i);
                GenerateProjects(i);
                GenerateData(i);
            }
        }
        try {
            model.writeData();
            }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate random address, birthday and contract date for the worker with the id sent
     * @param i Id of the worker
     */
    public static void GenerateData(int i){
        Random r =new Random();
        try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Addr.csv"))) {
            model.read(i).setAddr(lines.skip(r.nextInt(251900)).findFirst().get().replace(",", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDate date = LocalDate.now().withYear(LocalDate.now().getYear()-(r.nextInt(50)+18));
        model.read(i).setAge(date.toString());
        date = LocalDate.now().withYear(LocalDate.now().getYear()-(r.nextInt(LocalDate.now().compareTo(LocalDate.parse(model.read(i).getAge())))));
        model.read(i).setSeniority(date.toString());
    }

    /**
     * Generates a random name for the worker with the id sent
     * @param i Id of the worker
     */
    public static void GenerateName(int i){
        Random r =new Random();
        int r1=r.nextInt(20);
        int r2=r.nextInt(20);
        int r3=r.nextInt(20);
        String[] names= new String[] {"Victoria","Renata","Sofia","Valeria","Maria_José","Maria_Fernanda","Valentina","Ximena","Regina","Camila","Alexander","Mateo","Santiago","Daniel","Sebastian","Miguel_Angel","Leonardo","Diego","Matias","Emiliano"};
        String[] lastNames = new String[]  {"Hernandez","Garcia","Martinez","Lopez","Gonzalez","Perez","Rodriguez","Sanchez","Ramirez","Cruz","Flores","Gomez","Moran","Duque","Villota","Villanueva","Molina","Callejas","Obrador","Guerrero"};
        model.read(i).setName(names[r1]+" "+lastNames[r2]+"_"+lastNames[r3]);
    }

    /**
     * Generate a random number of actual projects between 1-3 and a random number of past projects between 0-50 for the worker with the id sent
     * @param i Id of the worker
     */
    public static void GenerateProjects(int i){
        int numProjectsA=9;
        int numProjectsP=40;
        Random r =new Random();
        int x = r.nextInt(3)+1;
        int y=2,z;
        while(model.read(i).getActualProyects().size()!=x){
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsA+1)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[0].length()!=0){
                    for (String element : model.read(i).getActualProyects()){
                        if (element.contains(arr[0])) {
                            y = 1;
                            break;
                        }
                    }
                    if(model.read(i).getActualProyects().size()==0||y==0)
                        model.read(i).getActualProyects().add(arr[0]);
                    y=0;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        x = r.nextInt((numProjectsA+numProjectsP)-model.read(i).getActualProyects().size()+2);
        while(model.read(i).getPastProyects().size()!=x){
            z=r.nextInt(2);
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsP)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[z].length()!=0){
                    for (String element : model.read(i).getActualProyects()){
                        if (element.contains(arr[z])) {
                            y = 1;
                            break;
                        }
                    }
                    for (String element : model.read(i).getPastProyects()){
                        if (element.contains(arr[z])) {
                            y = 1;
                            break;
                        }
                    }
                    if(model.read(i).getPastProyects().size()==0||y==0)
                        model.read(i).getPastProyects().add(arr[z]);
                    y=0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Validates the password sent
     * @param input Password inputted
     * @return true if the password inputted is correct and false otherwise
     */
    public static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect;

        if (input.length != model.getPassword().length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(input, model.getPassword());
        }
        Arrays.fill(model.getPassword(),'0');
        return isCorrect;
    }
}

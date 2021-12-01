package src.Controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import src.Model.*;
import src.View.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

public class Controller{
    public static JFrame frame = new JFrame();
    public static ImageIcon img = new ImageIcon("Resources/Images/icon.png");
    public static Worker data = new Worker();
    public static Model model = new Model();
    public static MenuView menuP = new MenuView();
    public static SearchView searchP = new SearchView();
    public static CreateView createP = new CreateView();
    public static UpdateView updateP = new UpdateView();
    public static ReadView readP = new ReadView();
    public static DeleteView deleteP = new DeleteView();
    public static LoginView loginP = new LoginView();
    public static char state;
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
        try {
        model.getData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
    public static void MenuV(){
        menuP = new MenuView();
        frame.setTitle("Menu");
        frame.add(menuP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void SearchV(){
        searchP = new SearchView();
        frame.setTitle("Search");
        frame.add(searchP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void CreateV(){
        createP = new CreateView(model.getNextId());
        frame.setTitle("Create");
        frame.add(createP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void ReadV(int id){
        Read(id);
        readP = new ReadView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),LocalDate.now().compareTo(LocalDate.parse(data.getAge())), LocalDate.now().compareTo(LocalDate.parse(data.getSeniority())), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Read");
        frame.add(readP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void UpdateV(int id){
        Read(id);
        updateP = new UpdateView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),data.getAge(), data.getSeniority(), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Update");
        frame.add(updateP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void DeleteV(int id) {
        Read(id);
        deleteP = new DeleteView(data.getId(), data.getName().split(" ")[0],data.getName().split(" ")[1], data.getAddr(),LocalDate.now().compareTo(LocalDate.parse(data.getAge())), LocalDate.now().compareTo(LocalDate.parse(data.getSeniority())), data.getActualProyects(),data.getPastProyects());
        frame.setTitle("Delete");
        frame.add(deleteP);
        frame.pack();
        frame.setVisible(true);
    }
    public static void Create(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
        model.create(new Worker(name,addr,id,age,seniority,pastProyects,actualProyects));
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
    public static boolean Search(int id){
        return model.search(id);
    }
    public static void Read(int id){
        data = model.read(id);
    }
    public static void Delete(int id){
        model.delete(id);
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
    public static void Update(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
        model.update(id,new Worker(name,addr,id,age,seniority,pastProyects,actualProyects));
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
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
    public static void GenerateData(int i){
        Random r =new Random();
        try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Addr.csv"))) {
            model.Workers[i].setAddr(lines.skip(r.nextInt(251900)).findFirst().get().replace(",", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDate date = LocalDate.now().withYear(LocalDate.now().getYear()-(r.nextInt(50)+18));
        model.Workers[i].setAge(date.toString());
        date = LocalDate.now().withYear(LocalDate.now().getYear()-(r.nextInt(LocalDate.now().compareTo(LocalDate.parse(model.Workers[i].getAge())))));
        model.Workers[i].setSeniority(date.toString());
    }
    public static void GenerateName(int i){
        Random r =new Random();
        int r1=r.nextInt(20);
        int r2=r.nextInt(20);
        int r3=r.nextInt(20);
        String[] names= new String[] {"Victoria","Renata","Sofia","Valeria","María_José","María_Fernanda","Valentina","Ximena","Regina","Camila","Alexander","Mateo","Santiago","Daniel","Sebastián","Miguel_Ángel","Leonardo","Diego","Matías","Emiliano"};
        String[] lastNames = new String[]  {"Hernández","García","Martínez","López","González","Pérez","Rodríguez","Sánchez","Ramírez","Cruz","Flores","Gómez","Morán","Duque","Villota","Villanueva","Molina","Callejas","Obrador","Guerrero"};
        model.Workers[i].setName(names[r1]+" "+lastNames[r2]+"_"+lastNames[r3]);
    }
    public static void GenerateProjects(int i){
        int numProjectsA=9;
        int numProjectsP=40;
        Random r =new Random();
        r.nextInt(numProjectsA);
        int x = r.nextInt(3)+1;
        int y=2,z;
        while(model.Workers[i].getActualProyects().size()!=x){
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsA)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[0].length()!=0){
                    for (String element : model.Workers[i].getActualProyects()){
                        if (element.contains(arr[0])) {
                            y = 1;
                            break;
                        }
                    }
                    if(model.Workers[i].getActualProyects().size()==0||y==0)
                        model.Workers[i].getActualProyects().add(arr[0]);
                    y=0;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        x = r.nextInt((numProjectsA+numProjectsP)-model.Workers[i].getActualProyects().size()+2);
        while(model.Workers[i].getPastProyects().size()!=x){
            z=r.nextInt(2);
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsP)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[z].length()!=0){
                    for (String element : model.Workers[i].getActualProyects()){
                        if (element.contains(arr[z])) {
                            y = 1;
                            break;
                        }
                    }
                    for (String element : model.Workers[i].getPastProyects()){
                        if (element.contains(arr[z])) {
                            y = 1;
                            break;
                        }
                    }
                    if(model.Workers[i].getPastProyects().size()==0||y==0)
                        model.Workers[i].getPastProyects().add(arr[z]);
                    y=0;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
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

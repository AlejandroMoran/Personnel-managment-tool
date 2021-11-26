package src.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import src.Model.*;
import src.View.*;
import java.text.ParseException;
import java.time.LocalDate;
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
        frame.setPreferredSize(new Dimension(698, 177));
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
        CreateView createP = new CreateView(model.getNextId());
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
        if(model.search(id)==true)
            return true;
        else
            return false;
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
            data = new Worker(i);
            data.GenerateName();
            data.GenerateProjects();
            data.GenerateData();
            model.create(data);
        }
        try {
            model.writeData();
            }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

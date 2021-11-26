package src.Model;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class Worker {
    private String name;
    private String addr;
    private int id;
    private int age;
    private int seniority;
    private List<String> pastProyects= new ArrayList<String>();
    private List<String> actualProyects=new ArrayList<String>();

    public Worker(){}
    public Worker(int id){
     this.id=id;
    }
    public Worker(String name,String addr,int id,int age,int seniority,List<String> pastProyects,List<String> actualProyects){
        this.name=name;
        this.addr=addr;
        this.id=id;
        this.age=age;
        this.seniority=seniority;
        this.pastProyects=pastProyects;
        this.actualProyects=actualProyects;
        }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAddr(){
        return addr;
    }
    public void setAddr(String addr){
        this.addr=addr;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getSeniority(){
        return seniority;
    }
    public void setSeniority(int seniority){
        this.seniority=seniority;
    }
    public List<String> getPastProyects(){
        return pastProyects;
    }
    public void setPastProyects(List<String> pastProyects){
        this.pastProyects=pastProyects;
    }
    public List<String> getActualProyects(){
        return actualProyects;
    }
    public void setActualProyects(List<String> actualProyects){
        this.actualProyects=actualProyects;
    }

    public void GenerateData(){
        Random r =new Random();
        try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Addr.csv"))) {
            this.addr = lines.skip(r.nextInt(251900)).findFirst().get();
            this.addr = this.addr.replace(",", "");
        } catch (IOException e) {
        e.printStackTrace();
        }
        this.age=r.nextInt(50)+18;
        this.seniority=r.nextInt(this.age-17);
    }
    public void GenerateName(){
        Random r =new Random();
        int r1=r.nextInt(20);
        int r2=r.nextInt(20);
        int r3=r.nextInt(20);
        String[] names= new String[] {"Victoria","Renata","Sofia","Valeria","María_José","María_Fernanda","Valentina","Ximena","Regina","Camila","Alexander","Mateo","Santiago","Daniel","Sebastián","Miguel_Ángel","Leonardo","Diego","Matías","Emiliano"};
        String[] lastNames = new String[]  {"Hernández","García","Martínez","López","González","Pérez","Rodríguez","Sánchez","Ramírez","Cruz","Flores","Gómez","Morán","Duque","Villota","Villanueva","Molina","Callejas","Obrador","Guerrero"};
        this.name= names[r1]+" "+lastNames[r2]+"_"+lastNames[r3];
    }
    public void GenerateProjects(){
        int numProjectsA=9;
        int numProjectsP=40;
        Random r =new Random();
        r.nextInt(numProjectsA);
        int x = r.nextInt(3)+1;
        int y=2,z;
        while(actualProyects.size()!=x){
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsA)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[0].length()!=0){
                    for (String element : actualProyects){
                        if (element.contains(arr[0]))
                            y=1;
                    }
                    if(actualProyects.size()==0||y==0)
                        this.actualProyects.add(arr[0]);
                    y=0;
                }

            } catch (IOException e) {
            e.printStackTrace();
            }
        }
        x = r.nextInt((numProjectsA+numProjectsP)-actualProyects.size()+2);
        while(pastProyects.size()!=x){
            z=r.nextInt(2);
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Projects.csv"))) {
                String line = lines.skip(r.nextInt(numProjectsP)+1).findFirst().get();
                String[] arr = line.split(",");
                if(arr[z].length()!=0){
                    for (String element : actualProyects){
                        if (element.contains(arr[z]))
                            y=1;
                    }
                    for (String element : pastProyects){
                        if (element.contains(arr[z]))
                            y=1;
                    }
                    if(pastProyects.size()==0||y==0)
                        this.pastProyects.add(arr[z]);
                    y=0;
                }

            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }
    @Override
    public String toString(){
        return id+","+name+","+age+","+addr+","+seniority+","+actualProyects.toString().replace(",", "")+","+pastProyects.toString().replace(",", "");
    }
}







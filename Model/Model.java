/**
 *@author Moran Duque, Jose Alejandro
 */
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import java.nio.file.StandardOpenOption;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model{
    int nLines;
    Worker[] Workers;
    public Model(){}
    public void init() throws IOException{
        try (Stream<String> stream = Files.lines(Paths.get("Resources/Data/Database.csv"))) {
            nLines = (int)stream.count()-1;
        }
        Workers = new Worker[nLines];
    }
    public int getData() throws IOException{
        int x=1;
        boolean exit=true;
        while(exit){
            try(Stream<String> lines = Files.lines(Paths.get("Resources/Data/Database.csv"))) {
                String line = lines.skip(x).findFirst().get();
                String[] arr = line.split(",");
                if(arr[0].length()!=0){
                    x++;
                    Workers[x-1] = new Worker(arr[1],arr[3],Integer.parseInt(arr[0]),Integer.parseInt(arr[2]),Integer.parseInt(arr[4]),new ArrayList<String>(Arrays.asList(arr[6].split(", "))),new ArrayList<String>(Arrays.asList(arr[5].split(", "))));
                }
                else
                    exit=false;
            }
        }
        return x;
    }
    public void writeData() throws IOException{
        int i;
        String header="id,name,age,address,seniority,actual proyects,past proyects";
        Files.writeString(Paths.get("Resources/Data/Database.csv"),header + System.lineSeparator());
        for(i=1;i<nLines;i++){
            Files.writeString(Paths.get("Resources/Data/Database.csv"),Workers[i].toString().replace("[","").replace("]","") + System.lineSeparator(),StandardOpenOption.APPEND);
        }
        String end=",,,,,,total="+(i-1);
        Files.writeString(Paths.get("Resources/Data/Database.csv"), end+ System.lineSeparator(),StandardOpenOption.APPEND);
    }
    public String create(Worker worker){
        for(int i=1;i<Workers.length;i++){
            if(Workers[i].getId()==worker.getId())
                return "Error";
        }
        nLines++;
        final int N = Workers.length;
        Workers = Arrays.copyOf(Workers, N + 1);
        Workers[N] = worker;
        return "SUCCESSFUL";
    }
    public Worker read(int id){
        for(int i=1;i<Workers.length;i++){
            if(Workers[i].getId()==id)
                return Workers[i];
        }
        return null;
    }
    public String delete(int id){
        for(int i=1;i<Workers.length;i++){
            if(Workers[i].getId()==id){
                Worker[] temp = new Worker[Workers.length - 1];
                System.arraycopy(Workers, 0, temp, 0, i);
                System.arraycopy(Workers, i + 1,temp, i, Workers.length - i - 1);
                Workers=temp;
                nLines--;
                return "SUCCESSFUL";
            }
        }
        return "Error";
    }
    public String update(int id, Worker worker){
        for(int i=1;i<Workers.length;i++){
            if(Workers[i].getId()==id){
                Workers[i]=worker;
                return "SUCCESSFUL";
            }
        }
        return "Error";
    }
    public int getNLines(){
        return nLines;
    }
}

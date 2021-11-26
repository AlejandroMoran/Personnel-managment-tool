/**
 *@author Moran Duque, Jose Alejandro
 */
package src.Model;
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
                    if(arr.length>6){
                        Workers[x-1] = new Worker(arr[1],arr[3],Integer.parseInt(arr[0]),arr[2],arr[4], new ArrayList<String>(Arrays.asList(arr[6].split(" "))),new ArrayList<String>(Arrays.asList(arr[5].split(" "))));
                        }
                    else if(arr.length==6){
                        Workers[x-1] = new Worker(arr[1],arr[3],Integer.parseInt(arr[0]),arr[2],arr[4],new ArrayList<String>(Arrays.asList("_")),new ArrayList<String>(Arrays.asList(arr[5].split(" "))));
                    }
                    else{
                        Workers[x-1] = new Worker(arr[1],arr[3],Integer.parseInt(arr[0]),arr[2],arr[4],new ArrayList<String>(Arrays.asList("_")),new ArrayList<String>(Arrays.asList("_")));
                    }

                }
                else
                    exit=false;
            }
        }
        return x;
    }
    public void writeData() throws IOException{
        int i;
        String header="id,name,age,address,seniority,actual projects,past projects";
        Files.writeString(Paths.get("Resources/Data/Database.csv"),header + System.lineSeparator());
        for(i=1;i<Workers.length;i++){
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
        System.out.println(nLines);
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
    public boolean search(int id){
        for(int i=1;i<Workers.length;i++){
            if(Workers[i].getId()==id)
                return true;
        }
        return false;
    }
    public int getNLines(){
        return nLines;
    }
    public int getNextId(){
        int[] ids = new int[nLines];
        for(int i=1;i<Workers.length;i++)
            ids[i]=Workers[i].getId();
        Arrays.sort(ids);
        for(int i=1;i<Workers.length;i++){
            if(ids[i]!=i)
                return i;
        }
        return Workers.length;
    }
}

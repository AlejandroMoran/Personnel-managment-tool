package src.Model;
import java.util.ArrayList;
import java.util.List;
public class Worker {
    private String name;
    private String addr;
    private int id;
    private String age;
    private String seniority;
    private List<String> pastProyects= new ArrayList<>();
    private List<String> actualProyects= new ArrayList<>();

    public Worker(){}
    public Worker(int id){
     this.id=id;
    }
    public Worker(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
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
    public String getAge(){
        return age;
    }
    public void setAge(String age){
        this.age=age;
    }
    public String getSeniority(){
        return seniority;
    }
    public void setSeniority(String seniority){
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

    @Override
    public String toString(){
        return id+","+name+","+age+","+addr+","+seniority+","+actualProyects.toString().replace(",", "")+","+pastProyects.toString().replace(",", "");
    }
}







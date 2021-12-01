package src.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Worker template
 */
public class Worker {
    /**
     * Name of the worker
     */
    private String name;
    /**
     * Address of the worker
     */
    private String addr;
    /**
     * Id of the worker
     */
    private int id;
    /**
     * Birthday of the worker
     */
    private String age;
    /**
     * Contract date of the worker
     */
    private String seniority;
    /**
     * Past projects of the worker
     */
    private List<String> pastProyects= new ArrayList<>();
    /**
     * Actual projects of the worker
     */
    private List<String> actualProyects= new ArrayList<>();

    public Worker(){}

    /**
     * Allocates a Worker object and initializes it with the id sent
     * @param id Id of the worker
     */
    public Worker(int id){
     this.id=id;
    }

    /**
     * Allocates a Worker object and initializes it with the data sent
     * @param name Name of the worker
     * @param addr Address of the worker
     * @param id Id of the worker
     * @param age Age of the worker
     * @param seniority Seniority of the worker
     * @param pastProyects Past projects of the worker
     * @param actualProyects Actual projects of the worker
     */
    public Worker(String name,String addr,int id,String age,String seniority,List<String> pastProyects,List<String> actualProyects){
        this.name=name;
        this.addr=addr;
        this.id=id;
        this.age=age;
        this.seniority=seniority;
        this.pastProyects=pastProyects;
        this.actualProyects=actualProyects;
    }

    /**
     *
     * @return Current name of the worker
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @param name name of the worker
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     *
     * @return Current address of the worker
     */
    public String getAddr(){
        return addr;
    }

    /**
     *
     * @param addr Address of the worker
     */
    public void setAddr(String addr){
        this.addr=addr;
    }

    /**
     *
     * @return Id of the worker
     */
    public int getId(){
        return id;
    }

    /**
     *
     * @return Current age of the worker (birthday)
     */
    public String getAge(){
        return age;
    }

    /**
     *
     * @param age Age of the worker (birthday)
     */
    public void setAge(String age){
        this.age=age;
    }

    /**
     *
     * @return Current seniority of the worker (date of contract)
     */
    public String getSeniority(){
        return seniority;
    }

    /**
     *
     * @param seniority Seniority of the worker (date of contract)
     */
    public void setSeniority(String seniority){
        this.seniority=seniority;
    }

    /**
     *
     * @return Current past project/s of the worker
     */
    public List<String> getPastProyects(){
        return pastProyects;
    }

    /**
     *
     * @param pastProyects Past project/s of the worker
     */
    public void setPastProyects(List<String> pastProyects){
        this.pastProyects=pastProyects;
    }

    /**
     *
     * @return Current actual projects of the worker
     */
    public List<String> getActualProyects(){
        return actualProyects;
    }

    /**
     *
     * @param actualProyects Actual projects of the worker
     */
    public void setActualProyects(List<String> actualProyects){
        this.actualProyects=actualProyects;
    }

    /**
     *
     * @return All the data of the worker split by commas
     */
    @Override
    public String toString(){
        return id+","+name+","+age+","+addr+","+seniority+","+actualProyects.toString().replace(",", "")+","+pastProyects.toString().replace(",", "");
    }
}







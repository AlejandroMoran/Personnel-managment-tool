import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class main{
    public static void main(String[] args){
        Worker test = new Worker();
        Model model = new Model();
        String status;
        String s = "no,tiene";
        List<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
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
        //model.read(0).setName("ximena");
        Worker test2 = new Worker(6);
        test2.GenerateName();
        test2.GenerateProjects(3,9);
        test2.GenerateData();
        System.out.println(test2);
        status = model.create(test2);
        System.out.println(model.read(6));
        status = model.delete(1);
        test2=model.read(2);
        test2.setName("Ximena");
        status=model.update(2,test2);
        try {
        model.writeData();
        }catch (IOException e) {
        e.printStackTrace();
        }
        System.out.println(model.read(2));

    }
}

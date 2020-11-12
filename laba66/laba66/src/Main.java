import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String filename="C:\\Users\\Alex\\Desktop\\Save2.ser";

      Serialize serialize=new Serialize(filename);
      List<Shape> list=new ArrayList<>();
      List<Shape> list1=new ArrayList<>();
      list1=serialize.load();
        Form form=new Form(filename);
        form.Run();
      //  serialize.save(form.list);
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\Users\\Alex\\Desktop\\Save22.ser";

SwingUtilities.invokeLater(()-> {
    JFrame form = new Form(filename);
form.setVisible(true);
    //  serialize.save(form.list);
});
    }
}

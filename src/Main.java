import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String filename="Save.ser";
        SwingUtilities.invokeLater(() -> {
            JFrame form=new Form(filename);
            form.setVisible(true);
        });
    }
}
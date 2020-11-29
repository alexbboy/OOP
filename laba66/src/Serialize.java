import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize implements Serializable {
    private List<Shape> list = new ArrayList<>();
    private String filename;

    Serialize(String string) {
        this.filename = string;
    }

    public void save(List<Shape> list) throws IOException, FileNotFoundException {
        File file1 = new File(filename);
        if (!file1.exists())
            file1.createNewFile();
        else {
            try (
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file)
            ) {
                objectOutputStream.writeObject(list);

            }


        }
    }

    public List<Shape> load() throws ClassNotFoundException, IOException, FileNotFoundException {

        List<Shape> newlist = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream objectOutputStream = new ObjectInputStream(file)) {

            newlist = (List<Shape>) objectOutputStream.readObject();

        }


        return newlist;
    }
}


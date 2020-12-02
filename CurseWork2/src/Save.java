import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Save implements Serializable {
    private List<For_Save> list=new ArrayList<For_Save>();
    private String filename;

    Save(String filename){
        this.filename=filename;
    }

    public void save(List<For_Save> list) throws IOException, FileNotFoundException {
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

    public List<For_Save> load() throws IOException, FileNotFoundException,ClassNotFoundException{

        List<For_Save> newlist = new ArrayList<For_Save>();
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream objectOutputStream = new ObjectInputStream(file)) {

            newlist = (List<For_Save>) objectOutputStream.readObject();

        }


        return newlist;
    }
}

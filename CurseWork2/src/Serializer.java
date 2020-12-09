import java.io.*;
import java.util.List;

public class Serializer implements Serializable {
    private static final long serialVersionUID = 4756177651864891859L;
    private final String filename;

    Serializer(String filename){
        this.filename=filename;
    }

    public void save(List<ReportData> list) throws IOException {
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

    public List<ReportData> load() throws IOException, ClassNotFoundException{

        List<ReportData> newlist;
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream objectOutputStream = new ObjectInputStream(file)) {

            newlist = (List<ReportData>) objectOutputStream.readObject();

        }


        return newlist;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize implements Serializable {
    private List<ReportData> list=new ArrayList<ReportData>();
    private String filename;

    Serialize(String filename){
        this.filename=filename;
    }

    public void save(List<ReportData> list) throws IOException, FileNotFoundException {
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

    public List<ReportData> load() throws IOException, FileNotFoundException,ClassNotFoundException{

        List<ReportData> newlist = new ArrayList<ReportData>();
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream objectOutputStream = new ObjectInputStream(file)) {

            newlist = (List<ReportData>) objectOutputStream.readObject();

        }


        return newlist;
    }
}

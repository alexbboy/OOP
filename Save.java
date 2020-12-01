import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Save {
    private List<Discipline> list=new ArrayList<>();
    private String filename;

    Save(String filename){
        this.filename=filename;
    }

    public void save(List<Discipline> list) {
        try(FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file)) {

            objectOutputStream.writeObject(list);

        }

        catch(FileNotFoundException d)
        {
            throw new RuntimeException("File dont found");

        }
        catch(IOException d){
            throw new RuntimeException("Exception");
        }


    }

    public List<Discipline> load(){

        List<Discipline> newlist = new ArrayList<>();
        try(FileInputStream file = new FileInputStream(filename);
            ObjectInputStream objectOutputStream = new ObjectInputStream(file)) {

            newlist=(List<Discipline>) objectOutputStream.readObject();


        }

        catch(FileNotFoundException d)
        {
            throw new RuntimeException("File dont found");

        }
        catch(IOException d){
            throw new RuntimeException("Exception");
        }
        catch (ClassNotFoundException d){
            throw new RuntimeException("Class not found",d);

        }

        return newlist;
    }
    }




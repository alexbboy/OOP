import java.io.Serializable;

public class For_Save implements Serializable {
    public static final long serialVersionUID = -1493672579665473344L;
    private String name;
    private Discipline discipline;

    public For_Save(String name,Discipline discipline){
        this.name=new String();
        this.name=name;
        this.discipline=discipline;
    }

    public String getName() {
        return name;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return name;
    }
}
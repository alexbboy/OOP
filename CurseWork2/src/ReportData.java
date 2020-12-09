import java.io.Serializable;

public class ReportData implements Serializable {
    public static final long serialVersionUID = -1493672579665473344L;
    private final String name;
    private final Discipline discipline;

    public ReportData(String name, Discipline discipline){
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
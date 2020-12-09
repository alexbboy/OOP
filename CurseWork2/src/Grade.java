import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialVersionUID = 6395985348110810358L;
    private final int grade;
    public enum type implements Serializable{
        Curse, Practic, Control
    }
    private final type Type;

    public Grade(int grade,type Type){
        this.grade=grade;
        this.Type=Type;
    }
    @Override
    public String toString() {
        return "" +
                "Grade=" + grade +
                ' '+ "Type: "+Type;
    }

    public int getGrade() {
        return grade;
    }

    public type getType() {
        return Type;
    }
}
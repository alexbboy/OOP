import java.util.Date;

public class Grade {
private int grade;
public enum type{
Curse, Practic, Control
}
private type Type;

public Grade(int grade,type Type){
    this.grade=grade;
    this.Type=Type;
}
    @Override
    public String toString() {
        return "" +
                "Оценка=" + grade +
                ' '+ "Тип: "+Type;
    }

    public int getGrade() {
        return grade;
    }
}

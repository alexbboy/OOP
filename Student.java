import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private String surname;
    private Integer group;
    private List<Grade> grades;
Student(int group,String name,String surname){
    this.name=new String();
    this.name=name;
    this.surname=new String();
    this.surname=surname;
    this.group=group;
    this.grades=new ArrayList<Grade>();
}
    public List<Grade> getGrades(){
        return Collections.unmodifiableList(grades);
    }
    public void AddGrade(Grade grade){
        grades.add(grade);
    }
    public void RemoveGrade(int index){
    grades.remove(index);
    }

    public Integer getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return  name + ' ' + surname + ' ' +
                ", Группа=" + group +
                ", оценки=" + grades;
    }
}


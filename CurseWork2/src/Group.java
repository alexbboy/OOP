import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group implements Serializable {
    private static final long serialVersionUID = -1885975818066730381L;
    private final Integer group;
    private final List<Student> students;
    Group(int group){
        this.group=group;
        this.students=new ArrayList<>();
    }
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students) ;
    }
    public void add(Student student){
        students.add(student);
    }

    public Integer getGroup() {
        return group;
    }

}
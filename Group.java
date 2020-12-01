import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
private Integer group;
private List<Student> students;
Group(int group){
    this.group=group;
    this.students=new ArrayList<>();
}
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students) ;
    }
    public void add(int group,String name,String surname){
        Student student=new Student(group,name,surname);
        students.add(student);
    }


}

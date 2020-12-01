import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Discipline {
    private String TeacherName;
    private String TeacherSurname;
    private String discipline;
    private Group groups;
    Discipline(String teacherName,String teacherSurname,String discipline,Group group){
        this.TeacherName=new String();
        this.TeacherName=teacherName;
        this.TeacherSurname=new String();
        this.TeacherSurname=teacherSurname;
        this.discipline=new String();
        this.groups=group;
    }


}

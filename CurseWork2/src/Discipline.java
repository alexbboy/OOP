import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Discipline implements Serializable {
    private static final long serialVersionUID = 198066817925244126L;
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
        this.discipline=discipline;
        this.groups=group;
    }

    public Group getGroups() {
        return groups;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public String getTeacherSurname() {
        return TeacherSurname;
    }

}


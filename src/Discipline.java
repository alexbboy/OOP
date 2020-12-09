import java.io.Serializable;

public class Discipline implements Serializable {
    private static final long serialVersionUID = 198066817925244126L;
    private final String TeacherName;
    private final String TeacherSurname;
    private final String discipline;
    private final Group groups;
    Discipline(String teacherName,String teacherSurname,String discipline,Group group){
        this.TeacherName=teacherName;
        this.TeacherSurname=teacherSurname;
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


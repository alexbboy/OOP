
    import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class Student implements Serializable {
        private static final long serialVersionUID = 2800700471898873736L;
        private final String name;
        private final String surname;
        private final Integer group;
        private final List<Grade> grades;
        Student(int group,String name,String surname){
            this.name=name;
            this.surname=surname;
            this.group=group;
            this.grades= new ArrayList<>();
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




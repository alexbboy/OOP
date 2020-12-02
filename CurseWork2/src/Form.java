import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private final JTable table = new JTable();
    private List<For_Save> List_for_Save = new ArrayList<>();
    private final JFrame newGroup = new JFrame("Создание группы");

    ///////////////////////////////////////////
    Form(String filename) {
        setTitle("Ведомость");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File file1 = new File(filename);
        if (!file1.exists())
            try {
                file1.createNewFile();
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(
                        this,
                        "Unable to store data",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE
                );
            }
        Save ObjectForSave = new Save(filename);
        if (file1.length() != 0)
             {
            try {
                List_for_Save = ObjectForSave.load();
            } catch (FileNotFoundException e) {
                JOptionPane.showConfirmDialog(
                        this,
                        "File not found",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (ClassNotFoundException d) {
                JOptionPane.showConfirmDialog(
                        this,
                        "Class Not found",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (IOException q) {
                JOptionPane.showConfirmDialog(
                        this,
                        "Unable to store data",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
//mainPanel.setBackground(Color.ORANGE);
        setContentPane(mainPanel);
        JLabel title = new JLabel("Приложение ведомость", JLabel.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 35));
        title.setForeground(Color.BLUE);
        mainPanel.add(title);
        JButton create = new JButton("Создать ведомость");
        JButton open = new JButton("Открыть ведомость");
        JPanel buttons = new JPanel();
        buttons.setSize(300, 500);
        mainPanel.add(buttons, BorderLayout.SOUTH);
        buttons.add(create, BorderLayout.CENTER);
        buttons.add(Box.createVerticalStrut(30));
        buttons.add(open, BorderLayout.CENTER);
//////////////////////////////////////////////////////////////////////////////
        create.addActionListener(e -> {
            JFrame newDiscipline = new JFrame("Создание дисциплины");
            newDiscipline.setSize(600, 400);
            newDiscipline.setVisible(true);
            JPanel Content = new JPanel(new BorderLayout(10, 10));
            Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
            newDiscipline.add(Content);
            JLabel discipline = new JLabel("Введите название дисциплины");
            discipline.setFont(new Font("Verdana", Font.PLAIN, 20));
            JLabel TeacherName = new JLabel("Введите имя преподавателя");
            TeacherName.setFont(new Font("Verdana", Font.PLAIN, 20));
            JLabel TeacherSurname = new JLabel("Введите фамилию преподователя");
            TeacherSurname.setFont(new Font("Verdana", Font.PLAIN, 20));
            JLabel group = new JLabel("Введите группу");
            group.setFont(new Font("Verdana", Font.PLAIN, 20));
            JTextField NameOfDoscipline = new JTextField();
            JTextField TeachersName = new JTextField();
            JTextField TeachersSurname = new JTextField();
            JTextField StudentGroup = new JTextField();
            JButton next = new JButton("Далее");
            JButton cancel = new JButton("Назад");
            JPanel buttonspanel = new JPanel();

            NameOfDoscipline.setSize(100, 50);
            Content.add(Box.createVerticalStrut(20));
            Content.add(discipline, BorderLayout.WEST);
            Content.add(NameOfDoscipline, BorderLayout.CENTER);
            Content.add(Box.createVerticalStrut(20));
            Content.add(group, BorderLayout.WEST);
            Content.add(StudentGroup, BorderLayout.CENTER);
            Content.add(Box.createVerticalStrut(20));
            Content.add(TeacherName, BorderLayout.WEST);
            Content.add(TeachersName, BorderLayout.CENTER);
            Content.add(Box.createVerticalStrut(20));
            Content.add(TeacherSurname, BorderLayout.WEST);
            Content.add(TeachersSurname, BorderLayout.CENTER);
            Content.add(Box.createVerticalStrut(20));
            Content.add(buttonspanel, BorderLayout.SOUTH);
            buttonspanel.add(next);
            buttonspanel.add(cancel);
            cancel.addActionListener(e15 -> newDiscipline.dispose());
            next.addActionListener(e1 -> {
                try {
                    Double.parseDouble(StudentGroup.getText());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Введите число в строку группы!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);

                }
                newDiscipline.setVisible(false);
                newGroup.setSize(600, 400);
                newGroup.setVisible(true);
                JLabel NameOfGroup = new JLabel("Группа: " + StudentGroup.getText());
                JLabel disciplineName = new JLabel("Дисциплина: " + NameOfDoscipline.getText());
                JLabel TeacherNames = new JLabel("Преподователь: " + TeachersName.getText() + " " + TeachersSurname.getText());
                JPanel grouppanel = new JPanel();
                JPanel AddStudent = new JPanel();
                JPanel forListOfStudent = new JPanel();
                grouppanel.setLayout(new BoxLayout(grouppanel, BoxLayout.Y_AXIS));
                AddStudent.setLayout(new BoxLayout(AddStudent, BoxLayout.Y_AXIS));
                newGroup.add(grouppanel, BorderLayout.NORTH);
                newGroup.add(AddStudent, BorderLayout.EAST);
                newGroup.add(forListOfStudent, BorderLayout.CENTER);
                grouppanel.add(NameOfGroup, BorderLayout.CENTER);
                grouppanel.add(Box.createVerticalStrut(10));
                grouppanel.add(disciplineName, BorderLayout.CENTER);
                grouppanel.add(Box.createVerticalStrut(10));
                grouppanel.add(TeacherNames, BorderLayout.CENTER);
                if ((StudentGroup.getText().isEmpty()) || (TeachersName.getText().isEmpty()) || (TeachersSurname.getText().isEmpty()) || (NameOfDoscipline.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Не введены данные", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    newGroup.setVisible(false);
                    newDiscipline.setVisible(true);
                }
                JButton add = new JButton("Добавить студента");
                JButton delete = new JButton("Удалить студента");
                JButton grades = new JButton("Редактировать оценки");
                JButton next_To_Save = new JButton("Далее");
                JButton Back_To_DisciplineCreation = new JButton("Назад");
                AddStudent.add(add);
                AddStudent.add(Box.createVerticalStrut(10));
                AddStudent.add(delete);
                AddStudent.add(Box.createVerticalStrut(10));
                AddStudent.add(grades);
                AddStudent.add(Box.createVerticalStrut(100));
                AddStudent.add(next_To_Save, BorderLayout.SOUTH);
                AddStudent.add(Box.createVerticalStrut(10));
                AddStudent.add(Back_To_DisciplineCreation, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////
                List<Student> studentList = new ArrayList<>();
                DefaultListModel<Student> students = new DefaultListModel<>();
                JList<Student> listOfStudent = new JList<>(students);
                JScrollPane jScrollPane = new JScrollPane(listOfStudent) {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(300, 350);
                    }
                };
                jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
                forListOfStudent.add(Box.createVerticalStrut(30));
                forListOfStudent.add(jScrollPane, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////
                Back_To_DisciplineCreation.addActionListener(e1111 -> {
                    newGroup.setVisible(false);
                    newDiscipline.setVisible(true);
                });
                add.addActionListener(e114 -> {
                    JTextField NewStudentName = new JTextField();
                    JTextField NewStudentSurname = new JTextField();
                    JButton ok = new JButton("Добавить");
                    JLabel nameOfStudent = new JLabel("Введите имя студента");
                    nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
                    surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JButton BackToStudentList = new JButton("Отмена");
                    JFrame AddNewStudent = new JFrame("Добавление студента");
                    AddNewStudent.setSize(300, 300);
                    AddNewStudent.setVisible(true);
                    JPanel addpanel = new JPanel();
                    JPanel buttons1 = new JPanel();
                    AddNewStudent.add(addpanel, BorderLayout.CENTER);
                    AddNewStudent.add(buttons1, BorderLayout.SOUTH);
                    buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));
                    addpanel.setLayout(new BoxLayout(addpanel, BoxLayout.Y_AXIS));
                    addpanel.add(nameOfStudent);
                    addpanel.add(Box.createVerticalStrut(10));
                    addpanel.add(NewStudentName);
                    addpanel.add(Box.createVerticalStrut(50));
                    addpanel.add(surnameOfStudent);
                    addpanel.add(Box.createVerticalStrut(10));
                    addpanel.add(NewStudentSurname);
                    addpanel.add(Box.createVerticalStrut(50));
                    buttons1.add(Box.createHorizontalStrut(50));
                    buttons1.add(ok);
                    buttons1.add(Box.createHorizontalStrut(10));
                    buttons1.add(BackToStudentList);
                    ok.addActionListener(e1141 -> {
                        Student student = new Student(Integer.parseInt(StudentGroup.getText()), NewStudentName.getText(), NewStudentSurname.getText());
                        students.addElement(student);
                        studentList.add(student);
                        AddNewStudent.dispose();
                        AddNewStudent.setVisible(false);
                    });
                    BackToStudentList.addActionListener(e11412 -> AddNewStudent.dispose());

                });
                delete.addActionListener(e1110 -> {
                    if (listOfStudent.getSelectedIndex() != -1) {
                        students.remove(listOfStudent.getSelectedIndex());
                        studentList.remove(listOfStudent.getSelectedIndex());

                    }
                });
////////////////////////////////////////////////////////////////////////////////
                grades.addActionListener(e113 -> {
                    if (listOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrades = new JFrame("Работа с оценками");
                    NewGrades.setSize(500, 500);
                    List<Grade> gradeList = new ArrayList<>(studentList.get(listOfStudent.getSelectedIndex()).getGrades());
                    DefaultListModel<Grade> grade = new DefaultListModel<>();
                    for (Grade value : gradeList) {
                        grade.addElement(value);
                    }
                    JList<Grade> listOfGrades = new JList<>(grade);
                    JScrollPane jScrollPane2 = new JScrollPane(listOfGrades) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(300, 350);
                        }
                    };
                    JPanel listpanel = new JPanel();
                    JPanel buttonspanel2 = new JPanel();
                    JPanel forlabel = new JPanel();
                    buttonspanel2.setLayout(new BoxLayout(buttonspanel2, BoxLayout.Y_AXIS));
                    JButton addGrade = new JButton("Добавить оценку");
                    JButton deleteGrade = new JButton("Удалить оценку");
                    JButton ready = new JButton("Готово");

                    JLabel gradetext = new JLabel("Оценки студента");
                    gradetext.setFont(new Font("Verdana", Font.PLAIN, 20));
                    NewGrades.setVisible(true);
                    NewGrades.add(forlabel, BorderLayout.NORTH);
                    NewGrades.add(listpanel, BorderLayout.CENTER);
                    NewGrades.add(buttonspanel2, BorderLayout.EAST);
                    forlabel.add(gradetext, BorderLayout.CENTER);
                    listpanel.add(jScrollPane2, BorderLayout.CENTER);
                    buttonspanel2.add(addGrade, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(20));
                    buttonspanel2.add(deleteGrade, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(20));
                    buttonspanel2.add(ready, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(100));
                    int index = listOfStudent.getSelectedIndex();
                    ready.addActionListener(e11316 -> NewGrades.dispose());
                    addGrade.addActionListener(e1131 -> {

                        JFrame NewGrade = new JFrame("Новая оценка");
                        NewGrade.setSize(300, 300);
                        NewGrade.setVisible(true);
                        JPanel forText = new JPanel();
                        JPanel forButtons = new JPanel();
                        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
                        JLabel Describtion = new JLabel("Введите оценку");
                        JButton CommitAdd = new JButton("Добавить");
                        JButton cancelGrade = new JButton("Отмена");

                        JPanel fake = new JPanel();
                        JPanel fake2 = new JPanel();
                        NewGrade.add(fake, BorderLayout.NORTH);
                        JComboBox<Grade.type> typeOfGrade = new JComboBox<>();
                        typeOfGrade.setModel(new DefaultComboBoxModel<Grade.type>(Grade.type.values()));
                        Describtion.setFont(new Font("Verdana", Font.PLAIN, 20));
                        JTextField GradesField = new JTextField();
                        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
                        NewGrade.add(forText, BorderLayout.CENTER);
                        NewGrade.add(forButtons, BorderLayout.SOUTH);
                        NewGrade.add(fake2, BorderLayout.EAST);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(Describtion);
                        forText.add(Box.createHorizontalStrut(60));
                        forText.add(Box.createVerticalStrut(20));
                        forText.add(GradesField);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(typeOfGrade);
                        forText.add(Box.createVerticalStrut(50));
                        forButtons.add(Box.createHorizontalStrut(30));
                        forButtons.add(CommitAdd);
                        forButtons.add(Box.createHorizontalStrut(20));
                        forButtons.add(cancelGrade);

                        CommitAdd.addActionListener(e113112 -> {
                            try {
                                Integer.parseInt(GradesField.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                            }
                            if ((Integer.parseInt(GradesField.getText()) != 1) && (Integer.parseInt(GradesField.getText()) != 2) && (Integer.parseInt(GradesField.getText()) != 3) && (Integer.parseInt(GradesField.getText()) != 4) && (Integer.parseInt(GradesField.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade grade1 = new Grade(Integer.parseInt(GradesField.getText()), (Grade.type) typeOfGrade.getSelectedItem());
                            grade.addElement(grade1);
                            gradeList.add(grade1);
                            studentList.get(listOfStudent.getSelectedIndex()).AddGrade(grade1);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(grade1);

                            NewGrade.dispose();
                            NewGrade.setVisible(false);
                            listOfStudent.updateUI();
                        });
                        cancelGrade.addActionListener(e11311 -> NewGrade.dispose());
                    });
                    deleteGrade.addActionListener(e11317 -> {
                        if (listOfGrades.getSelectedIndex() != -1) {
                            int index12 = listOfGrades.getSelectedIndex();
                            studentList.get(listOfStudent.getSelectedIndex()).RemoveGrade(index12);
                            grade.remove(listOfGrades.getSelectedIndex());
                            gradeList.remove(index12);
                        } else {
                            JOptionPane.showMessageDialog(null, "Выберите оценку для удаления", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                    //////////////////////////////////////////////////////////////////////////////////
                });
                next_To_Save.addActionListener(e11318 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        newGroup.setVisible(false);
                        save.setSize(600, 600);
                        save.setVisible(true);
                        Object[] Headers = {"Group", "Name", "Surname", "Grades"};
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.setColumnIdentifiers(Headers);

                        for (int i = 0; i < students.size(); i++) {
                            Object[] o = new Object[4];
                            o[0] = students.get(i).getGroup();
                            o[1] = students.get(i).getName();
                            o[2] = students.get(i).getSurname();
                            o[3] = students.get(i).getGrades();
                            defaultTableModel.addRow(o);
                        }
                        JLabel title1 = new JLabel("Итоговая ведомость");
                        JButton Saving = new JButton("Сохранить");
                        JButton excel = new JButton("Excel");
                        JButton Back = new JButton("Назад");
                        JPanel ForButtons = new JPanel();
                        save.add(ForButtons, BorderLayout.SOUTH);
                        ForButtons.setLayout(new BoxLayout(ForButtons, BoxLayout.X_AXIS));
                        ForButtons.add(Box.createHorizontalStrut(140));
                        ForButtons.add(Saving);
                        ForButtons.add(Box.createHorizontalStrut(20));
                        ForButtons.add(excel);
                        ForButtons.add(Box.createHorizontalStrut(20));
                        ForButtons.add(Back);
                        title1.setFont(new Font("Verdana", Font.PLAIN, 18));
                        JPanel forTitle = new JPanel();
                        table.setModel(defaultTableModel);
                        JScrollPane jScrollPane3 = new JScrollPane(table);
                        JPanel forTable = new JPanel();
                        save.add(forTable, BorderLayout.CENTER);
                        save.add(forTitle, BorderLayout.NORTH);
                        forTitle.add(title1);
                        forTable.add(jScrollPane3);
                        save.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        /////////////////////////////////////////////////////////////////////////////////////////////////
                        Saving.addActionListener(e113181 -> {
                            JFrame ForSave = new JFrame("Введите название");
                            ForSave.setSize(300, 300);
                            ForSave.setVisible(true);
                            JLabel SaveName = new JLabel("Введите название");
                            JPanel ForName = new JPanel();
                            JPanel ForButtonSave = new JPanel();
                            JTextField Forsave = new JTextField();
                            JButton CommitSave = new JButton("Готово");
                            JButton CancelSave = new JButton("Отмена");
                            ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
                            ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
                            ForButtonSave.add(Box.createHorizontalStrut(30));
                            ForButtonSave.add(CommitSave);
                            ForButtonSave.add(Box.createHorizontalStrut(20));
                            ForButtonSave.add(CancelSave);
                            SaveName.setFont(new Font("Verdana", Font.PLAIN, 20));
                            ForSave.add(ForName, BorderLayout.CENTER);
                            ForName.add(Box.createVerticalStrut(50));
                            ForName.add(SaveName);
                            ForName.add(Box.createVerticalStrut(30));
                            ForName.add(Forsave);
                            ForName.add(Box.createVerticalStrut(100));
                            ForSave.add(ForButtonSave, BorderLayout.SOUTH);
                            ForSave.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            CancelSave.addActionListener(e11312 -> ForSave.dispose());
                            CommitSave.addActionListener(e11313 -> {
                                Group saveGroup = new Group(Integer.parseInt(StudentGroup.getText()));
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(TeachersName.getText(), TeachersSurname.getText(), NameOfDoscipline.getText(), saveGroup);
                                For_Save object = new For_Save(Forsave.getText(), SavingDiscipline);
                                List_for_Save.add(object);
                                try {
                                    ObjectForSave.save(List_for_Save);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException d) {
                                    JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                }
                                ForSave.dispose();
                            });

                        });
                        excel.addActionListener(e11314 -> {
                            JFrame ExcelFrame = new JFrame("Введите название");
                            ExcelFrame.setSize(300, 300);
                            ExcelFrame.setVisible(true);
                            JLabel NameOfExcel = new JLabel("Введите название");
                            JPanel ForNameOfExcel = new JPanel();
                            JPanel ForExcelButtons = new JPanel();
                            JTextField ExcelName = new JTextField();
                            JButton Commit = new JButton("Готово");
                            JButton BackToTable = new JButton("Отмена");
                            ForExcelButtons.setLayout(new BoxLayout(ForExcelButtons, BoxLayout.X_AXIS));
                            ForNameOfExcel.setLayout(new BoxLayout(ForNameOfExcel, BoxLayout.Y_AXIS));
                            ForExcelButtons.add(Box.createHorizontalStrut(30));
                            ForExcelButtons.add(Commit);
                            ForExcelButtons.add(Box.createHorizontalStrut(20));
                            ForExcelButtons.add(BackToTable);
                            NameOfExcel.setFont(new Font("Verdana", Font.PLAIN, 20));
                            ExcelFrame.add(ForNameOfExcel, BorderLayout.CENTER);
                            ForNameOfExcel.add(Box.createVerticalStrut(50));
                            ForNameOfExcel.add(NameOfExcel);
                            ForNameOfExcel.add(Box.createVerticalStrut(30));
                            ForNameOfExcel.add(ExcelName);
                            ForNameOfExcel.add(Box.createVerticalStrut(100));
                            ExcelFrame.add(ForExcelButtons, BorderLayout.SOUTH);
                            ExcelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            BackToTable.addActionListener(e113141 -> ExcelFrame.dispose());
                            Commit.addActionListener(e112 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(),ExcelName.getText());
                                } catch (IOException d) {
                                    JOptionPane.showConfirmDialog(
                                            this,
                                            "Unable to store data",
                                            "Error",
                                            JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                                ExcelFrame.dispose();
                            });

                        });
                        Back.addActionListener(e11315 -> {
                            newGroup.setVisible(true);
                            save.setVisible(false);
                        });

                    }
                });
            });
        });
        open.addActionListener(e -> {
            try {
                List_for_Save = ObjectForSave.load();
            } catch (FileNotFoundException d) {
                throw new RuntimeException("File not found", d);
            } catch (ClassNotFoundException d) {
                throw new RuntimeException("Exception", d);
            } catch (IOException q) {
                throw new RuntimeException("Failed", q);
            }
            JFrame OpenFrame = new JFrame("Загрузка ведомости");
            OpenFrame.setSize(600, 400);
            OpenFrame.setVisible(true);
            OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JLabel Title = new JLabel("Открыть ведомость");
            JButton OpenListSaves = new JButton("Открыть");
            JButton DeleteSave = new JButton("Удалить Ведомость");
            JButton Back = new JButton("Назад");
            Title.setFont(new Font("Verdana", Font.PLAIN, 20));
            JPanel ForLabel = new JPanel();
            ForLabel.add(Title, BorderLayout.CENTER);
            OpenFrame.add(ForLabel, BorderLayout.NORTH);
            JPanel ForList = new JPanel();
            JPanel ForButtons = new JPanel();

            ForButtons.setLayout(new BoxLayout(ForButtons, BoxLayout.Y_AXIS));
            ForButtons.add(Box.createVerticalStrut(30));
            ForButtons.add(OpenListSaves);
            ForButtons.add(Box.createVerticalStrut(20));
            ForButtons.add(DeleteSave);
            ForButtons.add(Box.createVerticalStrut(20));
            ForButtons.add(Back);
            DefaultListModel<For_Save> titles = new DefaultListModel<>();
            JList<For_Save> listOfSaves = new JList<>(titles);
            for (For_Save for_save : List_for_Save) titles.addElement(for_save);
            JScrollPane jScrollPane = new JScrollPane(listOfSaves) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(300, 350);
                }
            };
            jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
            ForList.add(jScrollPane, BorderLayout.SOUTH);
            OpenFrame.add(ForList, BorderLayout.CENTER);
            OpenFrame.add(ForButtons, BorderLayout.EAST);
            OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Back.addActionListener(e13 -> OpenFrame.dispose());
            DeleteSave.addActionListener(e14 -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                if (SelectedIndex != -1) {
                    List_for_Save.remove(SelectedIndex);
                    titles.remove(SelectedIndex);
                    try {
                        ObjectForSave.save(List_for_Save);
                    } catch (FileNotFoundException d) {
                        throw new RuntimeException("File not found", d);
                    } catch (IOException q) {
                        throw new RuntimeException("Failed", q);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите сохранение", "Error!", JOptionPane.ERROR_MESSAGE);

                }
            });
            ///////////////////////////////////////////// PART 2
            OpenListSaves.addActionListener(e12 -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                OpenFrame.setVisible(false);
                newGroup.setSize(600, 400);
                newGroup.setVisible(true);
                JLabel group1 = new JLabel("Группа: " + List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                JLabel discipline1 = new JLabel("Дисциплина: " + List_for_Save.get(SelectedIndex).getDiscipline().getDiscipline());
                JLabel name1 = new JLabel("Преподователь: " + List_for_Save.get(SelectedIndex).getDiscipline().getTeacherName() + " " + List_for_Save.get(listOfSaves.getSelectedIndex()).getDiscipline().getTeacherSurname());
                JPanel grouppanel = new JPanel();
                JPanel newstudent = new JPanel();
                JPanel forList = new JPanel();
                grouppanel.setLayout(new BoxLayout(grouppanel, BoxLayout.Y_AXIS));
                newstudent.setLayout(new BoxLayout(newstudent, BoxLayout.Y_AXIS));
                newGroup.add(grouppanel, BorderLayout.NORTH);
                newGroup.add(newstudent, BorderLayout.EAST);
                newGroup.add(forList, BorderLayout.CENTER);
                grouppanel.add(group1, BorderLayout.CENTER);
                grouppanel.add(Box.createVerticalStrut(10));
                grouppanel.add(discipline1, BorderLayout.CENTER);
                grouppanel.add(Box.createVerticalStrut(10));
                grouppanel.add(name1, BorderLayout.CENTER);
                JButton add = new JButton("Добавить студента");
                JButton delete = new JButton("Удалить студента");
                JButton grades = new JButton("Редактировать оценки");
                JButton NextToTable = new JButton("Далее");
                JButton BackToStart = new JButton("Назад");
                newstudent.add(add);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(delete);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(grades);
                newstudent.add(Box.createVerticalStrut(100));
                newstudent.add(NextToTable, BorderLayout.SOUTH);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(BackToStart, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////
                List<Student> studentList = new ArrayList<>(List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getStudents());
                DefaultListModel<Student> students = new DefaultListModel<>();
                for (Student item : studentList) students.addElement(item);
                JList<Student> listOfStudent = new JList<>(students);
                JScrollPane jScrollPane1 = new JScrollPane(listOfStudent) {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(300, 350);
                    }
                };
                jScrollPane1.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
                forList.add(Box.createVerticalStrut(30));
                forList.add(jScrollPane1, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////
                BackToStart.addActionListener(e1 -> {
                    newGroup.setVisible(false);
                    OpenFrame.setVisible(true);
                });
                add.addActionListener(e1 -> {
                    JTextField NameOfStudent = new JTextField();
                    JTextField SurnameOfStudent = new JTextField();
                    JButton ok = new JButton("Добавить");
                    JLabel nameOfStudent = new JLabel("Введите имя студента");
                    nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
                    surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JButton BackToStudentsList = new JButton("Отмена");
                    JFrame newstudent1 = new JFrame("Добавление студента");
                    newstudent1.setSize(300, 300);
                    newstudent1.setVisible(true);
                    JPanel addpanel = new JPanel();
                    JPanel buttons1 = new JPanel();
                    newstudent1.add(addpanel, BorderLayout.CENTER);
                    newstudent1.add(buttons1, BorderLayout.SOUTH);
                    buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));
                    addpanel.setLayout(new BoxLayout(addpanel, BoxLayout.Y_AXIS));
                    addpanel.add(nameOfStudent);
                    addpanel.add(Box.createVerticalStrut(10));
                    addpanel.add(NameOfStudent);
                    addpanel.add(Box.createVerticalStrut(50));
                    addpanel.add(surnameOfStudent);
                    addpanel.add(Box.createVerticalStrut(10));
                    addpanel.add(SurnameOfStudent);
                    addpanel.add(Box.createVerticalStrut(50));
                    buttons1.add(Box.createHorizontalStrut(50));
                    buttons1.add(ok);
                    buttons1.add(Box.createHorizontalStrut(10));
                    buttons1.add(BackToStudentsList);
                    ok.addActionListener(e11 -> {
                        Student student = new Student((List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup()), NameOfStudent.getText(), SurnameOfStudent.getText());
                        students.addElement(student);
                        studentList.add(student);
                        newstudent1.dispose();
                        newstudent1.setVisible(false);
                    });
                    BackToStudentsList.addActionListener(e115 -> newstudent1.dispose());

                });
                delete.addActionListener(e1 -> {
                    if (SelectedIndex != -1) {
                        students.remove(listOfStudent.getSelectedIndex());
                        studentList.remove(listOfStudent.getSelectedIndex());

                    } else
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);

                });
////////////////////////////////////////////////////////////////////////////////
                grades.addActionListener(e1 -> {
                    if (listOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrade = new JFrame("Работа с оценками");
                    NewGrade.setSize(500, 500);
                    List<Grade> gradeList = new ArrayList<>(studentList.get(SelectedIndex).getGrades());
                    DefaultListModel<Grade> grade = new DefaultListModel<>();
                    for (Grade value : gradeList) {
                        grade.addElement(value);
                    }
                    JList<Grade> listOfGrades = new JList<>(grade);
                    JScrollPane jScrollPane2 = new JScrollPane(listOfGrades) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(300, 350);
                        }
                    };
                    JPanel Gradelistpanel = new JPanel();
                    JPanel ForGradePanelButtons = new JPanel();
                    JPanel forlabel = new JPanel();
                    ForGradePanelButtons.setLayout(new BoxLayout(ForGradePanelButtons, BoxLayout.Y_AXIS));
                    JButton addGrade = new JButton("Добавить оценку");
                    JButton deleteGrade = new JButton("Удалить оценку");
                    JButton ready = new JButton("Готово");

                    JLabel gradetext = new JLabel("Оценки студента");
                    gradetext.setFont(new Font("Verdana", Font.PLAIN, 20));
                    NewGrade.setVisible(true);
                    NewGrade.add(forlabel, BorderLayout.NORTH);
                    NewGrade.add(Gradelistpanel, BorderLayout.CENTER);
                    NewGrade.add(ForGradePanelButtons, BorderLayout.EAST);
                    forlabel.add(gradetext, BorderLayout.CENTER);
                    Gradelistpanel.add(jScrollPane2, BorderLayout.CENTER);
                    ForGradePanelButtons.add(addGrade, BorderLayout.EAST);
                    ForGradePanelButtons.add(Box.createVerticalStrut(20));
                    ForGradePanelButtons.add(deleteGrade, BorderLayout.EAST);
                    ForGradePanelButtons.add(Box.createVerticalStrut(20));
                    ForGradePanelButtons.add(ready, BorderLayout.EAST);
                    ForGradePanelButtons.add(Box.createVerticalStrut(100));
                    int index = listOfStudent.getSelectedIndex();
                    ready.addActionListener(e116 -> NewGrade.dispose());
                    addGrade.addActionListener(e117 -> {

                        JFrame UpdateGrades = new JFrame("Новая оценка");
                        UpdateGrades.setSize(300, 300);
                        UpdateGrades.setVisible(true);
                        JPanel forText = new JPanel();
                        JPanel forButtons = new JPanel();
                        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
                        JLabel describtionForUsers = new JLabel("Введите оценку");
                        JButton addGrade1 = new JButton("Добавить");
                        JButton BackToGradeList = new JButton("Отмена");

                        JPanel fake = new JPanel();
                        JPanel fake2 = new JPanel();
                        UpdateGrades.add(fake, BorderLayout.NORTH);
                        JComboBox<Grade.type> typeOfGrade = new JComboBox<>();
                        typeOfGrade.setModel(new DefaultComboBoxModel<Grade.type>(Grade.type.values()));
                        describtionForUsers.setFont(new Font("Verdana", Font.PLAIN, 20));
                        JTextField GradesField = new JTextField();
                        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
                        UpdateGrades.add(forText, BorderLayout.CENTER);
                        UpdateGrades.add(forButtons, BorderLayout.SOUTH);
                        UpdateGrades.add(fake2, BorderLayout.EAST);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(describtionForUsers);
                        forText.add(Box.createHorizontalStrut(60));
                        forText.add(Box.createVerticalStrut(20));
                        forText.add(GradesField);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(typeOfGrade);
                        forText.add(Box.createVerticalStrut(50));
                        forButtons.add(Box.createHorizontalStrut(30));
                        forButtons.add(addGrade1);
                        forButtons.add(Box.createHorizontalStrut(20));
                        forButtons.add(BackToGradeList);

                        addGrade1.addActionListener(e11712 -> {
                            try {
                                Integer.parseInt(GradesField.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                            }
                            if ((Integer.parseInt(GradesField.getText()) != 1) && (Integer.parseInt(GradesField.getText()) != 2) && (Integer.parseInt(GradesField.getText()) != 3) && (Integer.parseInt(GradesField.getText()) != 4) && (Integer.parseInt(GradesField.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade grade1 = new Grade(Integer.parseInt(GradesField.getText()), (Grade.type) typeOfGrade.getSelectedItem());
                            grade.addElement(grade1);
                            gradeList.add(grade1);
                            studentList.get(SelectedIndex).AddGrade(grade1);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(grade1);

                            UpdateGrades.dispose();
                            UpdateGrades.setVisible(false);
                            listOfStudent.updateUI();
                        });
                        BackToGradeList.addActionListener(e1171 -> UpdateGrades.dispose());
                    });
                    deleteGrade.addActionListener(e118 -> {
                        if (listOfGrades.getSelectedIndex() != -1) {
                            int index1 = listOfGrades.getSelectedIndex();
                            studentList.get(listOfStudent.getSelectedIndex()).RemoveGrade(index1);
                            grade.remove(listOfGrades.getSelectedIndex());
                            gradeList.remove(index1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Выберите оценку для удаления", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                    //////////////////////////////////////////////////////////////////////////////////
                });
                NextToTable.addActionListener(e119 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        newGroup.setVisible(false);
                        save.setSize(600, 600);
                        save.setVisible(true);
                        Object[] Headers = {"Группа", "Имя", "Фамилия", "Оценки"};
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.setColumnIdentifiers(Headers);

                        for (int i = 0; i < students.size(); i++) {
                            Object[] o = new Object[4];
                            o[0] = students.get(i).getGroup();
                            o[1] = students.get(i).getName();
                            o[2] = students.get(i).getSurname();
                            o[3] = students.get(i).getGrades();
                            defaultTableModel.addRow(o);
                        }
                        JLabel title1 = new JLabel("Итоговая ведомость");
                        JButton Saving = new JButton("Сохранить");
                        JButton excel = new JButton("Excel");
                        JButton Back1 = new JButton("Назад");
                        JPanel ForButtons1 = new JPanel();
                        save.add(ForButtons1, BorderLayout.SOUTH);
                        ForButtons1.setLayout(new BoxLayout(ForButtons1, BoxLayout.X_AXIS));
                        ForButtons1.add(Box.createHorizontalStrut(140));
                        ForButtons1.add(Saving);
                        ForButtons1.add(Box.createHorizontalStrut(20));
                        ForButtons1.add(excel);
                        ForButtons1.add(Box.createHorizontalStrut(20));
                        ForButtons1.add(Back1);
                        title1.setFont(new Font("Verdana", Font.PLAIN, 18));
                        JPanel forTitle = new JPanel();
                        table.setModel(defaultTableModel);
                        JScrollPane jScrollPane3 = new JScrollPane(table);
                        JPanel forTable = new JPanel();
                        save.add(forTable, BorderLayout.CENTER);
                        save.add(forTitle, BorderLayout.NORTH);
                        forTitle.add(title1);
                        forTable.add(jScrollPane3);
                        save.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        /////////////////////////////////////////////////////////////////////////////////////////////////
                        Saving.addActionListener(e11915 -> {
                            JFrame SavingName = new JFrame("Введите название");
                            SavingName.setSize(300, 300);
                            SavingName.setVisible(true);
                            JLabel SaveName = new JLabel("Введите название");
                            JPanel ForName = new JPanel();
                            JPanel ForButtonSave = new JPanel();
                            JTextField Forsave = new JTextField();
                            JButton CommitSave = new JButton("Готово");
                            JButton CancelSave = new JButton("Отмена");
                            ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
                            ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
                            ForButtonSave.add(Box.createHorizontalStrut(30));
                            ForButtonSave.add(CommitSave);
                            ForButtonSave.add(Box.createHorizontalStrut(20));
                            ForButtonSave.add(CancelSave);
                            SaveName.setFont(new Font("Verdana", Font.PLAIN, 20));
                            SavingName.add(ForName, BorderLayout.CENTER);
                            ForName.add(Box.createVerticalStrut(50));
                            ForName.add(SaveName);
                            ForName.add(Box.createVerticalStrut(30));
                            ForName.add(Forsave);
                            ForName.add(Box.createVerticalStrut(100));
                            SavingName.add(ForButtonSave, BorderLayout.SOUTH);
                            SavingName.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            CancelSave.addActionListener(e11912 -> SavingName.dispose());
                            CommitSave.addActionListener(e1191 -> {
                                Group saveGroup = new Group(List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(List_for_Save.get(SelectedIndex).getDiscipline().getTeacherName(), List_for_Save.get(SelectedIndex).getDiscipline().getTeacherSurname(), List_for_Save.get(SelectedIndex).getDiscipline().getDiscipline(), saveGroup);
                                For_Save object = new For_Save(Forsave.getText(), SavingDiscipline);
                                List_for_Save.add(object);
                                try {
                                    ObjectForSave.save(List_for_Save);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException d) {
                                    JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                }
                                SavingName.dispose();
                            });

                        });
                        excel.addActionListener(e11916 -> {
                            JFrame ExcelFrame = new JFrame("Введите название");
                            ExcelFrame.setSize(300, 300);
                            ExcelFrame.setVisible(true);
                            JLabel ExcelDescribtion = new JLabel("Введите название");
                            JPanel ForDescribtion = new JPanel();
                            JPanel ForExcelButtons = new JPanel();
                            JTextField ExcelName = new JTextField();
                            JButton Commit = new JButton("Готово");
                            JButton BackToTable = new JButton("Отмена");
                            ForExcelButtons.setLayout(new BoxLayout(ForExcelButtons, BoxLayout.X_AXIS));
                            ForDescribtion.setLayout(new BoxLayout(ForDescribtion, BoxLayout.Y_AXIS));
                            ForExcelButtons.add(Box.createHorizontalStrut(30));
                            ForExcelButtons.add(Commit);
                            ForExcelButtons.add(Box.createHorizontalStrut(20));
                            ForExcelButtons.add(BackToTable);
                            ExcelDescribtion.setFont(new Font("Verdana", Font.PLAIN, 20));
                            ExcelFrame.add(ForDescribtion, BorderLayout.CENTER);
                            ForDescribtion.add(Box.createVerticalStrut(50));
                            ForDescribtion.add(ExcelDescribtion);
                            ForDescribtion.add(Box.createVerticalStrut(30));
                            ForDescribtion.add(ExcelName);
                            ForDescribtion.add(Box.createVerticalStrut(100));
                            ExcelFrame.add(ForExcelButtons, BorderLayout.SOUTH);
                            ExcelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            BackToTable.addActionListener(e11913 -> ExcelFrame.dispose());
                            Commit.addActionListener(e119161 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(),ExcelName.getText());
                                } catch (IOException d) {
                                    JOptionPane.showConfirmDialog(
                                            this,
                                            "Unable to store data",
                                            "Error",
                                            JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                                ExcelFrame.dispose();
                            });

                        });
                        Back1.addActionListener(e11914 -> {
                            newGroup.setVisible(true);
                            save.setVisible(false);
                        });

                    }
                });
            });
        });
    }

}

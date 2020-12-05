import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private final JTable table = new JTable();
    private List<ReportData> List_for_Save = new ArrayList<>();
    private final JFrame NewGroup = new JFrame("Создание группы");
    private final JFrame ExcelFrame = new JFrame("Введите название");
    private final JFrame ForSaveReport = new JFrame("Введите название");
    private final JTextField DisciplineName = new JTextField();
    private final JTextField TeachersName = new JTextField();
    private final JTextField TeachersSurname = new JTextField();
    private final JTextField StudentGroup = new JTextField();
    private final Font font=new Font("Verdana", Font.PLAIN, 20);
    private final JButton NextToModidy = new JButton("Далее");
    private final JButton BackToStart = new JButton("Назад");
    private final JTextField ExcelName = new JTextField();
    private final JTextField NameOfSave = new JTextField();
    private final JButton ConfirmSave = new JButton("Готово");
    private final  JButton CancelSave = new JButton("Отмена");
    private final JButton SaveExcel = new JButton("Готово");
    private final JButton BackToTable = new JButton("Отмена");
    private final JTextField NewStudentName = new JTextField();
    private final JTextField NewStudentSurname = new JTextField();
    private final JTextField GradesField = new JTextField();
    private final JTextField GradesFieldFromSave = new JTextField();
    private final JComboBox<Grade.type> typeOfGrade = new JComboBox<>();
    private final JButton AddStudent = new JButton("Добавить студента");
    private final JButton DeleteStudent = new JButton("Удалить студента");
    private final JButton GradesOfStudent = new JButton("Редактировать оценки");
    private final JButton NextToSave = new JButton("Далее");
    private final JButton BackToDisciplineCreation = new JButton("Назад");
    private final JPanel forListOfStudent = new JPanel();
    private final JButton ReadyGrade = new JButton("Готово");
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private final JButton SaveReport = new JButton("Сохранить");
    private final JButton ToExcel = new JButton("Excel");
    private final JButton BackToReport = new JButton("Назад");
    private final JButton OpenListSaves = new JButton("Открыть");
    private final JButton DeleteSave = new JButton("Удалить Ведомость");
    private final JButton OpenFromSave = new JButton("Добавить студента");
    private final JButton DeleteStudentFromSave = new JButton("Удалить студента");
    private final JButton NextToTableFromSave = new JButton("Далее");
    private final JButton BackToSaves = new JButton("Назад");
    private final JTextField OpenSaves_NameOfStudent = new JTextField();
    private final JTextField OpenSaves_SurnameOfStudent = new JTextField();
    private final JButton OpenSaves_ready = new JButton("Готово");
    private final JButton GradesFromSave=new JButton("Редактировать оценки");
    private final JComboBox<Grade.type> typeOfGrade_Saves = new JComboBox<>();
    private final JButton SaveFromSave = new JButton("Сохранить");
    private final JButton ToExcelFromSave = new JButton("Excel");
    private final JButton BackToStudents = new JButton("Назад");
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
        Serialize ObjectForSave = new Serialize(filename);
        if (file1.length() != 0)
        {
            try {
                List_for_Save = ObjectForSave.load();
            } catch (FileNotFoundException e) {
               fileNotFoundError();
            } catch (ClassNotFoundException d) {
                ClassNotFoundError();
            } catch (IOException q) {
                IOError();
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
Create_Process(newDiscipline);
            BackToStart.addActionListener(e15 -> newDiscipline.dispose());
            NextToModidy.addActionListener(e1 -> {
                try {
                    Double.parseDouble(StudentGroup.getText());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Введите число в строку группы!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);

                }
                MainReport(newDiscipline);
/////////////////////////////////////////////////////////////////////////////////////
                List<Student> studentList = new ArrayList<>();
                DefaultListModel<Student> StudentModel = new DefaultListModel<>();
                JList<Student> jlistOfStudent = new JList<>(StudentModel);
                JScrollPane jScrollPane = new JScrollPane(jlistOfStudent) {
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
                BackToDisciplineCreation.addActionListener(e1111 -> {
                    NewGroup.setVisible(false);
                    newDiscipline.setVisible(true);
                });
                AddStudent.addActionListener(e114 -> {
                    JButton ConfirmNewStudent = new JButton("Добавить");
                    JButton BackToStudentList = new JButton("Отмена");
                    JFrame AddNewStudent = new JFrame("Добавление студента");
                    AddStudent_Process(AddNewStudent,ConfirmNewStudent,BackToStudentList);
                    ConfirmNewStudent.addActionListener(e1141 -> {
                        Student NewStudent = new Student(Integer.parseInt(StudentGroup.getText()), NewStudentName.getText(), NewStudentSurname.getText());
                        StudentModel.addElement(NewStudent);
                        studentList.add(NewStudent);
                        AddNewStudent.dispose();
                        AddNewStudent.setVisible(false);
                    });
                    BackToStudentList.addActionListener(e11412 -> AddNewStudent.dispose());
                });
                DeleteStudent.addActionListener(e1110 -> {
                    if (jlistOfStudent.getSelectedIndex() != -1) {
                        int SelectedIndex=jlistOfStudent.getSelectedIndex();
                        StudentModel.remove(SelectedIndex);
                        studentList.remove(SelectedIndex);
                    }
                });
                GradesOfStudent.addActionListener(e113 -> {
                    if (jlistOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrades = new JFrame("Работа с оценками");
                    NewGrades.setSize(500, 500);
                    List<Grade> GradeList = new ArrayList<>(studentList.get(jlistOfStudent.getSelectedIndex()).getGrades());
                    DefaultListModel<Grade> GradeListModel = new DefaultListModel<>();
                    for (Grade value : GradeList) {
                        GradeListModel.addElement(value);
                    }
                    JList<Grade> listOfGrades = new JList<>(GradeListModel);
                    JScrollPane jScrollPane2 = new JScrollPane(listOfGrades) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(300, 350);
                        }
                    };
                    JPanel listPanel=new JPanel();
                    JButton AddGrade = new JButton("Добавить оценку");
                    JButton DeleteGrade = new JButton("Удалить оценку");
                    NewGrades_Process(NewGrades,listPanel,jScrollPane2,AddGrade,DeleteGrade);
                    int index = jlistOfStudent.getSelectedIndex();
                    ReadyGrade.addActionListener(e11316 -> NewGrades.dispose());
                    AddGrade.addActionListener(e1131 -> {
                        listOfGrades.updateUI();
                        JFrame NewGrade = new JFrame("Новая оценка");
                        JButton ConfirmGrade = new JButton("Добавить");
                        JButton cancelGrade = new JButton("Отмена");
                        AddGrade_Process(NewGrade,ConfirmGrade,cancelGrade);
                        ConfirmGrade.addActionListener(e113112 -> {
                            try {
                                Integer.parseInt(GradesField.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                            if ((Integer.parseInt(GradesField.getText()) != 1) && (Integer.parseInt(GradesField.getText()) != 2) && (Integer.parseInt(GradesField.getText()) != 3) && (Integer.parseInt(GradesField.getText()) != 4) && (Integer.parseInt(GradesField.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade GradeToAdd = new Grade(Integer.parseInt(GradesField.getText()), (Grade.type) typeOfGrade.getSelectedItem());
                            GradeListModel.addElement(GradeToAdd);
                            GradeList.add(GradeToAdd);
                            studentList.get(jlistOfStudent.getSelectedIndex()).AddGrade(GradeToAdd);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(GradeToAdd);
                            NewGrade.dispose();
                            NewGrade.setVisible(false);
                            jlistOfStudent.updateUI();
                            GradesField.setText(null);
                        });
                        cancelGrade.addActionListener(e11311 -> NewGrade.dispose());
                    });
                    DeleteGrade.addActionListener(e11317 -> {
                        if (listOfGrades.getSelectedIndex() != -1) {
                            int SelectedIndex = listOfGrades.getSelectedIndex();
                            studentList.get(jlistOfStudent.getSelectedIndex()).RemoveGrade(SelectedIndex);
                            GradeListModel.remove(listOfGrades.getSelectedIndex());
                            GradeList.remove(SelectedIndex);
                        } else {
                            JOptionPane.showMessageDialog(null, "Выберите оценку для удаления", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
              });
                NextToSave.addActionListener(e11318 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        NewGroup.setVisible(false);
                        NextToSave_Process(save,StudentModel);
                     SaveReport.addActionListener(e113181 -> {
                            SaveReport_Process();
                            CancelSave.addActionListener(e11312 -> ForSaveReport.dispose());
                            ConfirmSave.addActionListener(e11313 -> {
                                Group saveGroup = new Group(Integer.parseInt(StudentGroup.getText()));
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(TeachersName.getText(), TeachersSurname.getText(), DisciplineName.getText(), saveGroup);
                                ReportData object = new ReportData(NameOfSave.getText(), SavingDiscipline);
                                List_for_Save.add(object);
                                try {
                                    ObjectForSave.save(List_for_Save);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException d) {
                                    JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                }
                                ForSaveReport.dispose();
                            });
                        });
                        ToExcel.addActionListener(e11314 -> {
                            Excel_Process();
                            BackToTable.addActionListener(e113141 -> ExcelFrame.dispose());
                            SaveExcel.addActionListener(e112 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(),ExcelName.getText());
                                } catch (IOException d) {
                                   IOError();
                                }
                                ExcelFrame.dispose();
                            });
                        });
                        BackToReport.addActionListener(e11315 -> {
                            NewGroup.setVisible(true);
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
                fileNotFoundError();
            } catch (ClassNotFoundException d) {
                ClassNotFoundError();
            } catch (IOException q) {
                IOError();
            }
            JFrame LoadFrame = new JFrame("Загрузка ведомости");
            JButton BackToStart = new JButton("Назад");
Open_Process(LoadFrame,BackToStart);
            DefaultListModel<ReportData> titles = new DefaultListModel<>();
            JList<ReportData> listOfSaves = new JList<>(titles);
            for (ReportData for_save : List_for_Save) titles.addElement(for_save);
            JScrollPane jScrollPane = new JScrollPane(listOfSaves) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(300, 350);
                }
            };
            JPanel ForList = new JPanel();
            jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
            ForList.add(jScrollPane, BorderLayout.SOUTH);
            LoadFrame.add(ForList, BorderLayout.CENTER);
            BackToStart.addActionListener(e13 -> LoadFrame.dispose());
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
                        IOError();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Выберите сохранение", "Error!", JOptionPane.ERROR_MESSAGE);

                }
            });
            OpenListSaves.addActionListener(e12 -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                LoadFrame.setVisible(false);
                NewGroup.setSize(600, 400);
                NewGroup.setVisible(true);
                JLabel GroupLabel = new JLabel("Группа: " + List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                JLabel DisciplineLabel = new JLabel("Дисциплина: " + List_for_Save.get(SelectedIndex).getDiscipline().getDiscipline());
                JLabel NameLabel = new JLabel("Преподователь: " + List_for_Save.get(SelectedIndex).getDiscipline().getTeacherName() + " " + List_for_Save.get(listOfSaves.getSelectedIndex()).getDiscipline().getTeacherSurname());
                JPanel forList = new JPanel();
                NewGroup.add(forList, BorderLayout.CENTER);
                AddStudentFromSaves_Process(GroupLabel,DisciplineLabel,NameLabel);
                List<Student> studentList = new ArrayList<>(List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getStudents());
                DefaultListModel<Student> StudentListModel = new DefaultListModel<>();
                for (Student item : studentList) StudentListModel.addElement(item);
                JList<Student> listOfStudent = new JList<>(StudentListModel);
                JScrollPane jScrollPane1 = new JScrollPane(listOfStudent) {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(300, 350);
                    }
                };
                jScrollPane1.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
                forList.add(Box.createVerticalStrut(30));
                forList.add(jScrollPane1, BorderLayout.SOUTH);
                BackToSaves.addActionListener(e1 -> {
                    NewGroup.setVisible(false);
                    LoadFrame.setVisible(true);
                });
                OpenFromSave.addActionListener(e1 -> {
                    JFrame newstudent1 = new JFrame("Добавление студента");
                    OpenSaves_NameOfStudent.setText(null);
                    OpenSaves_SurnameOfStudent.setText(null);
                    JButton OpenSaves_ConfirmNewStudent = new JButton("Добавить");
                    JButton OpenSaves_BackToStudentsList = new JButton("Отмена");
                    OpenSavesAddNewStudent_Process(newstudent1,OpenSaves_ConfirmNewStudent,OpenSaves_BackToStudentsList);
                    OpenSaves_ConfirmNewStudent.addActionListener(e11 -> {
                        Student student = new Student((List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup()), OpenSaves_NameOfStudent.getText(), OpenSaves_SurnameOfStudent.getText());
                        StudentListModel.addElement(student);
                        studentList.add(student);
                        newstudent1.dispose();
                        newstudent1.setVisible(false);
                    });
                    OpenSaves_BackToStudentsList.addActionListener(e115 -> newstudent1.dispose());
                });
                DeleteStudentFromSave.addActionListener(e1 -> {
                    if (SelectedIndex != -1) {
                        int index=listOfStudent.getSelectedIndex();
                        StudentListModel.remove(index);
                        studentList.remove(index);
                    } else
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                });
                GradesFromSave.addActionListener(e1 -> {
                    if (listOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrade = new JFrame("Работа с оценками");
                    NewGrade.setSize(500, 500);
                    int SelectedIndex2=listOfStudent.getSelectedIndex();
                    List<Grade> gradeList = new ArrayList<>(studentList.get(SelectedIndex2).getGrades());
                    DefaultListModel<Grade> GradeListModel = new DefaultListModel<>();
                    for (Grade value : gradeList) {
                        GradeListModel.addElement(value);
                    }
                    JList<Grade> listOfGrades = new JList<>(GradeListModel);
                    JScrollPane jScrollPane2 = new JScrollPane(listOfGrades) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(300, 350);
                        }
                    };
                    JPanel Gradelistpanel = new JPanel();
                    Gradelistpanel.add(jScrollPane2, BorderLayout.CENTER);
                     JButton OpenSaves_addGrade = new JButton("Добавить оценку");
                     JButton OpenSaves_deleteGrade = new JButton("Удалить оценку");
                    GradeFromSaves_Process(NewGrade,Gradelistpanel,OpenSaves_addGrade,OpenSaves_deleteGrade);
                    int index = listOfStudent.getSelectedIndex();
                    OpenSaves_ready.addActionListener(e116 -> {NewGrade.dispose(); listOfGrades.updateUI();});
                    OpenSaves_addGrade.addActionListener(e117 -> {
                        listOfGrades.updateUI();
                        JFrame UpdateGrades = new JFrame("Новая оценка");
                        UpdateGrades.setVisible(true);
                        JButton OpenSavesaddGrade1 = new JButton("Добавить");
                        JButton OpenSavesBackToGradeList = new JButton("Отмена");
                        AddGrade_Process2(UpdateGrades,OpenSavesaddGrade1,OpenSavesBackToGradeList);
                        OpenSavesaddGrade1.addActionListener(e11712 -> {
                            try {
                                Integer.parseInt(GradesFieldFromSave.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                            }
                            if ((Integer.parseInt(GradesFieldFromSave.getText()) != 1) && (Integer.parseInt(GradesFieldFromSave.getText()) != 2) && (Integer.parseInt(GradesFieldFromSave.getText()) != 3) && (Integer.parseInt(GradesFieldFromSave.getText()) != 4) && (Integer.parseInt(GradesFieldFromSave.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade GradeToAdd = new Grade(Integer.parseInt(GradesFieldFromSave.getText()), (Grade.type) typeOfGrade_Saves.getSelectedItem());
                            GradeListModel.addElement(GradeToAdd);
                            gradeList.add(GradeToAdd);
                            studentList.get(SelectedIndex2).AddGrade(GradeToAdd);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(GradeToAdd);
                            UpdateGrades.dispose();
                            listOfStudent.updateUI();
                            GradesFieldFromSave.setText(null);
                        });
                        OpenSavesBackToGradeList.addActionListener(e1171 -> UpdateGrades.dispose());
                    });
                    OpenSaves_deleteGrade.addActionListener(e118 -> {
                        int index1 = listOfGrades.getSelectedIndex();
                        if (index1 != -1) {
                            studentList.get(listOfStudent.getSelectedIndex()).RemoveGrade(index1);
                            GradeListModel.remove(listOfGrades.getSelectedIndex());
                            gradeList.remove(index1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Выберите оценку для удаления", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                    //////////////////////////////////////////////////////////////////////////////////
                });
                NextToTableFromSave.addActionListener(e119 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        NewGroup.setVisible(false);
                        OpenSavesNextToSave_Process(save,StudentListModel);
                        /////////////////////////////////////////////////////////////////////////////////////////////////
                        SaveFromSave.addActionListener(e11915 -> {
                            JFrame SavingName = new JFrame("Введите название");
                            JTextField Forsave2 = new JTextField();
                            JButton CommitSave2 = new JButton("Готово");
                            JButton CancelSave2 = new JButton("Отмена");
                            SaveFromSave_Process(SavingName,CommitSave2,CancelSave2,Forsave2);
                            CancelSave2.addActionListener(e11912 -> SavingName.dispose());
                            CommitSave2.addActionListener(e1191 -> {
                                Group saveGroup = new Group(List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(List_for_Save.get(SelectedIndex).getDiscipline().getTeacherName(), List_for_Save.get(SelectedIndex).getDiscipline().getTeacherSurname(), List_for_Save.get(SelectedIndex).getDiscipline().getDiscipline(), saveGroup);
                                ReportData object = new ReportData(Forsave2.getText(), SavingDiscipline);
                                List_for_Save.add(object);
                                try {
                                    ObjectForSave.save(List_for_Save);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException d) {
                                    IOError();
                                }
                                SavingName.dispose();
                            });

                        });
                        ToExcelFromSave.addActionListener(e11916 -> {
                            Excel_Process();
                            BackToTable.addActionListener(e11913 -> ExcelFrame.dispose());
                            SaveExcel.addActionListener(e119161 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(),ExcelName.getText());
                                } catch (IOException d) {
                                 IOError();
                                }
                                ExcelFrame.dispose();
                            });

                        });
                    }
                });
            });
        });
    }


    //////////////////////////////////////////////METHODS
private void Create_Process(JFrame newDiscipline){
    newDiscipline.setSize(600, 400);
    newDiscipline.setVisible(true);
    JPanel Content = new JPanel(new BorderLayout(10, 10));
    Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
    newDiscipline.add(Content);
    JLabel discipline = new JLabel("Введите название дисциплины");
    discipline.setFont(font);
    JLabel TeacherName = new JLabel("Введите имя преподавателя");
    TeacherName.setFont(font);
    JLabel TeacherSurname = new JLabel("Введите фамилию преподователя");
    TeacherSurname.setFont(font);
    JLabel group = new JLabel("Введите группу");
    group.setFont(font);
    JPanel buttonspanel = new JPanel();
    DisciplineName.setSize(100, 50);
    Content.add(Box.createVerticalStrut(20));
    Content.add(discipline, BorderLayout.WEST);
    Content.add(DisciplineName, BorderLayout.CENTER);
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
    buttonspanel.add(NextToModidy);
    buttonspanel.add(BackToStart);
}

private void AddGrade_Process(JFrame NewGrade,JButton ConfirmGrade,JButton cancelGrade){
    NewGrade.setSize(300, 300);
    NewGrade.setVisible(true);
    JPanel forText = new JPanel();
    JPanel forButtons = new JPanel();
    forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
    JLabel Describtion = new JLabel("Введите оценку");
    typeOfGrade.setModel(new DefaultComboBoxModel<>(Grade.type.values()));
    Describtion.setFont(font);
    forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
    NewGrade.add(forText, BorderLayout.CENTER);
    NewGrade.add(forButtons, BorderLayout.SOUTH);
    forText.add(Box.createVerticalStrut(50));
    forText.add(Describtion);
    forText.add(Box.createHorizontalStrut(60));
    forText.add(Box.createVerticalStrut(20));
    forText.add(GradesField);
    forText.add(Box.createVerticalStrut(50));
    forText.add(typeOfGrade);
    forText.add(Box.createVerticalStrut(50));
    forButtons.add(Box.createHorizontalStrut(30));
    forButtons.add(ConfirmGrade);
    forButtons.add(Box.createHorizontalStrut(20));
    forButtons.add(cancelGrade);
    }

    private void AddGrade_Process2(JFrame NewGrade,JButton ConfirmGrade2,JButton cancelGrade2){

        NewGrade.setSize(300, 300);
        NewGrade.setVisible(true);
        JPanel forText = new JPanel();
        JPanel forButtons = new JPanel();
        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
        JLabel Describtion = new JLabel("Введите оценку");
        typeOfGrade_Saves.setModel(new DefaultComboBoxModel<>(Grade.type.values()));
        Describtion.setFont(font);
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        NewGrade.add(forText, BorderLayout.CENTER);
        NewGrade.add(forButtons, BorderLayout.SOUTH);
        forText.add(Box.createVerticalStrut(50));
        forText.add(Describtion);
        forText.add(Box.createHorizontalStrut(60));
        forText.add(Box.createVerticalStrut(20));
        forText.add(GradesFieldFromSave);
        forText.add(Box.createVerticalStrut(50));
        forText.add(typeOfGrade_Saves);
        forText.add(Box.createVerticalStrut(50));
        forButtons.add(Box.createHorizontalStrut(30));
        forButtons.add(ConfirmGrade2);
        forButtons.add(Box.createHorizontalStrut(20));
        forButtons.add(cancelGrade2);
    }

private void AddStudent_Process(JFrame AddNewStudent, JButton ConfirmNewStudent, JButton BackToStudentList){
    NewStudentName.setText(null);
    NewStudentSurname.setText(null);
    JLabel nameOfStudent = new JLabel("Введите имя студента");
    nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
    JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
    surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
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
    buttons1.add(ConfirmNewStudent);
    buttons1.add(Box.createHorizontalStrut(10));
    buttons1.add(BackToStudentList);
}

private void MainReport(JFrame newDiscipline){
    newDiscipline.setVisible(false);
    NewGroup.setSize(600, 400);
    NewGroup.setVisible(true);
    JLabel NameOfGroup = new JLabel("Группа: " + StudentGroup.getText());
    JLabel disciplineName = new JLabel("Дисциплина: " + DisciplineName.getText());
    JLabel TeacherNames = new JLabel("Преподователь: " + TeachersName.getText() + " " + TeachersSurname.getText());
    JPanel grouppanel = new JPanel();
    JPanel AddStudent = new JPanel();
    grouppanel.setLayout(new BoxLayout(grouppanel, BoxLayout.Y_AXIS));
    AddStudent.setLayout(new BoxLayout(AddStudent, BoxLayout.Y_AXIS));
    NewGroup.add(grouppanel, BorderLayout.NORTH);
    NewGroup.add(AddStudent, BorderLayout.EAST);
    NewGroup.add(forListOfStudent, BorderLayout.CENTER);
    grouppanel.add(NameOfGroup, BorderLayout.CENTER);
    grouppanel.add(Box.createVerticalStrut(10));
    grouppanel.add(disciplineName, BorderLayout.CENTER);
    grouppanel.add(Box.createVerticalStrut(10));
    grouppanel.add(TeacherNames, BorderLayout.CENTER);
    if ((StudentGroup.getText().isEmpty()) || (TeachersName.getText().isEmpty()) || (TeachersSurname.getText().isEmpty()) || (DisciplineName.getText().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Не введены данные", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        NewGroup.setVisible(false);
        newDiscipline.setVisible(true);
    }

    AddStudent.add(this.AddStudent);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(DeleteStudent);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(GradesOfStudent);
    AddStudent.add(Box.createVerticalStrut(100));
    AddStudent.add(NextToSave, BorderLayout.SOUTH);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(BackToDisciplineCreation, BorderLayout.SOUTH);
}

private void NextToSave_Process(JFrame save,DefaultListModel<Student> students){
    save.setSize(600, 600);
    save.setVisible(true);
    Object[] Headers = {"Group", "Name", "Surname", "Grades"};
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
    JPanel ForButtons = new JPanel();
    save.add(ForButtons, BorderLayout.SOUTH);
    ForButtons.setLayout(new BoxLayout(ForButtons, BoxLayout.X_AXIS));
    ForButtons.add(Box.createHorizontalStrut(140));
    ForButtons.add(SaveReport);
    ForButtons.add(Box.createHorizontalStrut(20));
    ForButtons.add(ToExcel);
    ForButtons.add(Box.createHorizontalStrut(20));
    ForButtons.add(BackToReport);
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
}

private void SaveReport_Process(){
    ForSaveReport.setSize(300, 300);
    ForSaveReport.setVisible(true);
    JLabel SaveName = new JLabel("Введите название");
    JPanel ForName = new JPanel();
    JPanel ForButtonSave = new JPanel();
    ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
    ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
    ForButtonSave.add(Box.createHorizontalStrut(30));
    ForButtonSave.add(ConfirmSave);
    ForButtonSave.add(Box.createHorizontalStrut(20));
    ForButtonSave.add(CancelSave);
    SaveName.setFont(font);
    ForSaveReport.add(ForName, BorderLayout.CENTER);
    ForName.add(Box.createVerticalStrut(50));
    ForName.add(SaveName);
    ForName.add(Box.createVerticalStrut(30));
    ForName.add(NameOfSave);
    ForName.add(Box.createVerticalStrut(100));
    ForSaveReport.add(ForButtonSave, BorderLayout.SOUTH);
    ForSaveReport.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void NewGrades_Process(JFrame NewGrades,JPanel listpanel,JScrollPane jScrollPane2,JButton addGrade,JButton deleteGrade){
    JPanel ButtonsPanel = new JPanel();
    JPanel ForLabel = new JPanel();
    ButtonsPanel.setLayout(new BoxLayout(ButtonsPanel, BoxLayout.Y_AXIS));
    JLabel GradeText = new JLabel("Оценки студента");
    GradeText.setFont(new Font("Verdana", Font.PLAIN, 20));
    NewGrades.setVisible(true);
    NewGrades.add(ForLabel, BorderLayout.NORTH);
    NewGrades.add(listpanel, BorderLayout.CENTER);
    NewGrades.add(ButtonsPanel, BorderLayout.EAST);
    ForLabel.add(GradeText, BorderLayout.CENTER);
    listpanel.add(jScrollPane2, BorderLayout.CENTER);
    ButtonsPanel.add(addGrade, BorderLayout.EAST);
    ButtonsPanel.add(Box.createVerticalStrut(20));
    ButtonsPanel.add(deleteGrade, BorderLayout.EAST);
    ButtonsPanel.add(Box.createVerticalStrut(20));
    ButtonsPanel.add(ReadyGrade, BorderLayout.EAST);
    ButtonsPanel.add(Box.createVerticalStrut(100));
}

private void Open_Process(JFrame OpenFrame,JButton BackToStart){
    OpenFrame.setSize(600, 400);
    OpenFrame.setVisible(true);
    OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    JLabel Title = new JLabel("Открыть ведомость");
    Title.setFont(font);
    JPanel ForLabel = new JPanel();
    ForLabel.add(Title, BorderLayout.CENTER);
    OpenFrame.add(ForLabel, BorderLayout.NORTH);
    JPanel ForButtons = new JPanel();
    ForButtons.setLayout(new BoxLayout(ForButtons, BoxLayout.Y_AXIS));
    ForButtons.add(Box.createVerticalStrut(30));
    ForButtons.add(OpenListSaves);
    ForButtons.add(Box.createVerticalStrut(20));
    ForButtons.add(DeleteSave);
    ForButtons.add(Box.createVerticalStrut(20));
    ForButtons.add(BackToStart);
    OpenFrame.add(ForButtons, BorderLayout.EAST);
    OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void Excel_Process(){
    ExcelFrame.setSize(300, 300);
    ExcelFrame.setVisible(true);
    JLabel NameOfExcel = new JLabel("Введите название");
    JPanel ForNameOfExcel = new JPanel();
    JPanel ForExcelButtons = new JPanel();
    ForExcelButtons.setLayout(new BoxLayout(ForExcelButtons, BoxLayout.X_AXIS));
    ForNameOfExcel.setLayout(new BoxLayout(ForNameOfExcel, BoxLayout.Y_AXIS));
    ForExcelButtons.add(Box.createHorizontalStrut(30));
    ForExcelButtons.add(SaveExcel);
    ForExcelButtons.add(Box.createHorizontalStrut(20));
    ForExcelButtons.add(BackToTable);
    NameOfExcel.setFont(font);
    ExcelFrame.add(ForNameOfExcel, BorderLayout.CENTER);
    ForNameOfExcel.add(Box.createVerticalStrut(50));
    ForNameOfExcel.add(NameOfExcel);
    ForNameOfExcel.add(Box.createVerticalStrut(30));
    ForNameOfExcel.add(ExcelName);
    ForNameOfExcel.add(Box.createVerticalStrut(100));
    ExcelFrame.add(ForExcelButtons, BorderLayout.SOUTH);
    ExcelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void AddStudentFromSaves_Process(JLabel group1, JLabel discipline1, JLabel name1){
    JPanel GroupPanel = new JPanel();
    JPanel NewStudentFromSave = new JPanel();
    GroupPanel.setLayout(new BoxLayout(GroupPanel, BoxLayout.Y_AXIS));
    NewStudentFromSave.setLayout(new BoxLayout(NewStudentFromSave, BoxLayout.Y_AXIS));
    NewGroup.add(GroupPanel, BorderLayout.NORTH);
    NewGroup.add(NewStudentFromSave, BorderLayout.EAST);
    GroupPanel.add(group1, BorderLayout.CENTER);
    GroupPanel.add(Box.createVerticalStrut(10));
    GroupPanel.add(discipline1, BorderLayout.CENTER);
    GroupPanel.add(Box.createVerticalStrut(10));
    GroupPanel.add(name1, BorderLayout.CENTER);
    NewStudentFromSave.add(OpenFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(DeleteStudentFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(GradesFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(100));
    NewStudentFromSave.add(NextToTableFromSave, BorderLayout.SOUTH);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(BackToSaves, BorderLayout.SOUTH);
}

private void OpenSavesAddNewStudent_Process(JFrame newstudent1,JButton OpenSaves_ConfirmNewStudent,JButton OpenSaves_BackToStudentsList){
    JLabel nameOfStudent = new JLabel("Введите имя студента");
    nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
    JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
    surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
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
    addpanel.add(OpenSaves_NameOfStudent);
    addpanel.add(Box.createVerticalStrut(50));
    addpanel.add(surnameOfStudent);
    addpanel.add(Box.createVerticalStrut(10));
    addpanel.add(OpenSaves_SurnameOfStudent);
    addpanel.add(Box.createVerticalStrut(50));
    buttons1.add(Box.createHorizontalStrut(50));
    buttons1.add(OpenSaves_ConfirmNewStudent);
    buttons1.add(Box.createHorizontalStrut(10));
    buttons1.add(OpenSaves_BackToStudentsList);
}

private void GradeFromSaves_Process(JFrame NewGrade, JPanel Gradelistpanel,JButton OpenSaves_addGrade,JButton OpenSaves_deleteGrade){
    JPanel ForGradePanelButtons = new JPanel();
    JPanel ForLabel = new JPanel();
    ForGradePanelButtons.setLayout(new BoxLayout(ForGradePanelButtons, BoxLayout.Y_AXIS));
    JLabel GradeText = new JLabel("Оценки студента");
    GradeText.setFont(font);
    NewGrade.setVisible(true);
    NewGrade.add(ForLabel, BorderLayout.NORTH);
    NewGrade.add(Gradelistpanel, BorderLayout.CENTER);
    NewGrade.add(ForGradePanelButtons, BorderLayout.EAST);
    ForLabel.add(GradeText, BorderLayout.CENTER);
    ForGradePanelButtons.add(OpenSaves_addGrade, BorderLayout.EAST);
    ForGradePanelButtons.add(Box.createVerticalStrut(20));
    ForGradePanelButtons.add(OpenSaves_deleteGrade, BorderLayout.EAST);
    ForGradePanelButtons.add(Box.createVerticalStrut(20));
    ForGradePanelButtons.add(OpenSaves_ready, BorderLayout.EAST);
    ForGradePanelButtons.add(Box.createVerticalStrut(100));
}



private void OpenSavesNextToSave_Process(JFrame save,DefaultListModel<Student> students){
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
    JPanel ForButtons1 = new JPanel();
    save.add(ForButtons1, BorderLayout.SOUTH);
    ForButtons1.setLayout(new BoxLayout(ForButtons1, BoxLayout.X_AXIS));
    ForButtons1.add(Box.createHorizontalStrut(140));
    ForButtons1.add(SaveFromSave);
    ForButtons1.add(Box.createHorizontalStrut(20));
    ForButtons1.add(ToExcelFromSave);
    ForButtons1.add(Box.createHorizontalStrut(20));
    ForButtons1.add(BackToStudents);
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
}

private void SaveFromSave_Process(JFrame SavingName,JButton CommitSave2,JButton CancelSave2,JTextField Forsave2){
    SavingName.setSize(300, 300);
    SavingName.setVisible(true);
    JLabel SaveName = new JLabel("Введите название");
    JPanel ForName = new JPanel();
    JPanel ForButtonSave = new JPanel();
    ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
    ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
    ForButtonSave.add(Box.createHorizontalStrut(30));
    ForButtonSave.add(CommitSave2);
    ForButtonSave.add(Box.createHorizontalStrut(20));
    ForButtonSave.add(CancelSave2);
    SaveName.setFont(font);
    SavingName.add(ForName, BorderLayout.CENTER);
    ForName.add(Box.createVerticalStrut(50));
    ForName.add(SaveName);
    ForName.add(Box.createVerticalStrut(30));
    ForName.add(Forsave2);
    ForName.add(Box.createVerticalStrut(100));
    SavingName.add(ForButtonSave, BorderLayout.SOUTH);
    SavingName.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void IOError(){
    JOptionPane.showConfirmDialog(
            this,
            "Unable to store data",
            "Error",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.ERROR_MESSAGE
    );
}
    private void ClassNotFoundError(){
        JOptionPane.showConfirmDialog(
                this,
                "Class Not Found",
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE
        );
    }
    private void fileNotFoundError(){
        JOptionPane.showConfirmDialog(
                this,
                "File not found",
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE
        );
    }
}
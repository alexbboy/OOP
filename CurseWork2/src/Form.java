import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private final JTable table = new JTable();
    private List<ReportData> List_for_Save = new ArrayList<>();
    private final JFrame newGroup = new JFrame("Создание группы");
    private final JFrame excelFrame = new JFrame("Введите название");
    private final JFrame forSaveReport = new JFrame("Введите название");
    private final JTextField disciplineName = new JTextField();
    private final JTextField teachersName = new JTextField();
    private final JTextField teachersSurname = new JTextField();
    private final JTextField studentGroup = new JTextField();
    private final Font font=new Font("Verdana", Font.PLAIN, 20);
    private final JButton nextToModidy = new JButton("Далее");
    private final JButton backToStart = new JButton("Назад");
    private final JTextField excelName = new JTextField();
    private final JTextField nameOfSave = new JTextField();
    private final JButton confirmSave = new JButton("Готово");
    private final  JButton cancelSave = new JButton("Отмена");
    private final JButton saveExcel = new JButton("Готово");
    private final JButton backToTable = new JButton("Отмена");
    private final JTextField newStudentName = new JTextField();
    private final JTextField newStudentSurname = new JTextField();
    private final JTextField gradesField = new JTextField();
    private final JTextField gradesFieldFromSave = new JTextField();
    private final JComboBox<Grade.type> typeOfGrade = new JComboBox<>();
    private final JButton addStudent = new JButton("Добавить студента");
    private final JButton deleteStudent = new JButton("Удалить студента");
    private final JButton gradesOfStudent = new JButton("Редактировать оценки");
    private final JButton nextToSave = new JButton("Далее");
    private final JButton backToDisciplineCreation = new JButton("Назад");
    private final JPanel forListOfStudent = new JPanel();
    private final JButton readyGrade = new JButton("Готово");
    private final DefaultTableModel defaultTableModel = new DefaultTableModel();
    private final JButton saveReport = new JButton("Сохранить");
    private final JButton toExcel = new JButton("Excel");
    private final JButton backToReport = new JButton("Назад");
    private final JButton openListSaves = new JButton("Открыть");
    private final JButton deleteSave = new JButton("Удалить Ведомость");
    private final JTextField openSaves_NameOfStudent = new JTextField();
    private final JTextField openSaves_SurnameOfStudent = new JTextField();
    private final JButton openSaves_ready = new JButton("Готово");
    private final JComboBox<Grade.type> typeOfGrade_Saves = new JComboBox<>();
    private final JButton saveFromSave = new JButton("Сохранить");
    private final JButton toExcelFromSave = new JButton("Excel");
    private final JButton backToStudents = new JButton("Назад");
    private final JButton exit=new JButton("Выйти");
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
сreate_Process(newDiscipline);
            backToStart.addActionListener(e15 -> newDiscipline.dispose());
            nextToModidy.addActionListener(e1 -> {
                try {
                    Double.parseDouble(studentGroup.getText());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Введите число в строку группы!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);

                }
                mainReport(newDiscipline);
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
                backToDisciplineCreation.addActionListener(e1111 -> {
                    newGroup.setVisible(false);
                    newDiscipline.setVisible(true);
                });
                addStudent.addActionListener(e114 -> {
                    JButton ConfirmNewStudent = new JButton("Добавить");
                    JButton BackToStudentList = new JButton("Отмена");
                    JFrame AddNewStudent = new JFrame("Добавление студента");
                    addStudent_Process(AddNewStudent,ConfirmNewStudent,BackToStudentList);
                    ConfirmNewStudent.addActionListener(e1141 -> {
                        Student NewStudent = new Student(Integer.parseInt(studentGroup.getText()), newStudentName.getText(), newStudentSurname.getText());
                        StudentModel.addElement(NewStudent);
                        studentList.add(NewStudent);
                        AddNewStudent.dispose();
                        AddNewStudent.setVisible(false);
                    });
                    BackToStudentList.addActionListener(e11412 -> AddNewStudent.dispose());
                });
                deleteStudent.addActionListener(e1110 -> {
                    if (jlistOfStudent.getSelectedIndex() != -1) {
                        int SelectedIndex=jlistOfStudent.getSelectedIndex();
                        StudentModel.remove(SelectedIndex);
                        studentList.remove(SelectedIndex);
                    }
                });
                gradesOfStudent.addActionListener(e113 -> {
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
                    newGrades_Process(NewGrades,listPanel,jScrollPane2,AddGrade,DeleteGrade);
                    int index = jlistOfStudent.getSelectedIndex();
                    readyGrade.addActionListener(e11316 -> NewGrades.dispose());
                    AddGrade.addActionListener(e1131 -> {
                        listOfGrades.updateUI();
                        JFrame NewGrade = new JFrame("Новая оценка");
                        JButton ConfirmGrade = new JButton("Добавить");
                        JButton cancelGrade = new JButton("Отмена");
                        addGrade_Process(NewGrade,ConfirmGrade,cancelGrade);
                        ConfirmGrade.addActionListener(e113112 -> {
                            try {
                                Integer.parseInt(gradesField.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                            if ((Integer.parseInt(gradesField.getText()) != 1) && (Integer.parseInt(gradesField.getText()) != 2) && (Integer.parseInt(gradesField.getText()) != 3) && (Integer.parseInt(gradesField.getText()) != 4) && (Integer.parseInt(gradesField.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade GradeToAdd = new Grade(Integer.parseInt(gradesField.getText()), (Grade.type) typeOfGrade.getSelectedItem());
                            GradeListModel.addElement(GradeToAdd);
                            GradeList.add(GradeToAdd);
                            studentList.get(jlistOfStudent.getSelectedIndex()).AddGrade(GradeToAdd);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(GradeToAdd);
                            NewGrade.dispose();
                            NewGrade.setVisible(false);
                            jlistOfStudent.updateUI();
                            gradesField.setText(null);
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
                nextToSave.addActionListener(e11318 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        newGroup.setVisible(false);
                        nextToSave_Process(save,StudentModel);
                     saveReport.addActionListener(e113181 -> {
                            saveReport_Process();
                            cancelSave.addActionListener(e11312 -> forSaveReport.dispose());
                            confirmSave.addActionListener(e11313 -> {
                                Group saveGroup = new Group(Integer.parseInt(studentGroup.getText()));
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(teachersName.getText(), teachersSurname.getText(), disciplineName.getText(), saveGroup);
                                ReportData object = new ReportData(nameOfSave.getText(), SavingDiscipline);
                                List_for_Save.add(object);
                                try {
                                    ObjectForSave.save(List_for_Save);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException d) {
                                    JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                }
                                forSaveReport.dispose();
                            });
                        });
                        toExcel.addActionListener(e11314 -> {
                            excel_Process();
                            backToTable.addActionListener(e113141 -> excelFrame.dispose());
                            saveExcel.addActionListener(e112 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(), excelName.getText());
                                } catch (IOException d) {
                                   IOError();
                                }
                                excelFrame.dispose();
                            });
                        });
                        backToReport.addActionListener(e11315 -> {
                            newGroup.setVisible(true);
                            save.setVisible(false);
                        });
                        exit.addActionListener(e2 -> System.exit(0));
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
open_Process(LoadFrame,BackToStart);
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
            deleteSave.addActionListener(e14 -> {
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
            openListSaves.addActionListener(e12 -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                LoadFrame.setVisible(false);
                newGroup.setSize(600, 400);
                newGroup.setVisible(true);
                JLabel GroupLabel = new JLabel("Группа: " + List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                JLabel DisciplineLabel = new JLabel("Дисциплина: " + List_for_Save.get(SelectedIndex).getDiscipline().getDiscipline());
                JLabel NameLabel = new JLabel("Преподователь: " + List_for_Save.get(SelectedIndex).getDiscipline().getTeacherName() + " " + List_for_Save.get(listOfSaves.getSelectedIndex()).getDiscipline().getTeacherSurname());
                JPanel forList = new JPanel();
                JButton openFromSave = new JButton("Добавить студента");
                JButton deleteStudentFromSave = new JButton("Удалить студента");
                JButton nextToTableFromSave = new JButton("Далее");
                JButton backToSaves = new JButton("Назад");
                JButton gradesFromSave =new JButton("Редактировать оценки");
                newGroup.add(forList, BorderLayout.CENTER);
                addStudentFromSaves_Process(GroupLabel,DisciplineLabel,NameLabel,openFromSave,deleteStudentFromSave,nextToTableFromSave,backToSaves,gradesFromSave);
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
                backToSaves.addActionListener(e1 -> {
                    newGroup.setVisible(false);
                    LoadFrame.setVisible(true);
                });
                openFromSave.addActionListener(e1 -> {
                    JFrame newstudent1 = new JFrame("Добавление студента");
                    openSaves_NameOfStudent.setText(null);
                    openSaves_SurnameOfStudent.setText(null);
                    JButton OpenSaves_ConfirmNewStudent = new JButton("Добавить");
                    JButton OpenSaves_BackToStudentsList = new JButton("Отмена");
                    openSavesAddNewStudent_Process(newstudent1,OpenSaves_ConfirmNewStudent,OpenSaves_BackToStudentsList);
                    OpenSaves_ConfirmNewStudent.addActionListener(e11 -> {
                        Student student = new Student((List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup()), openSaves_NameOfStudent.getText(), openSaves_SurnameOfStudent.getText());
                        StudentListModel.addElement(student);
                        studentList.add(student);
                        newstudent1.dispose();
                        newstudent1.setVisible(false);
                    });
                    OpenSaves_BackToStudentsList.addActionListener(e115 -> newstudent1.dispose());
                });
                deleteStudentFromSave.addActionListener(e1 -> {
                    if (SelectedIndex != -1) {
                        int index=listOfStudent.getSelectedIndex();
                        StudentListModel.remove(index);
                        studentList.remove(index);
                    } else
                        JOptionPane.showMessageDialog(null, "Выберите студента", "Error!", JOptionPane.ERROR_MESSAGE);
                });
                gradesFromSave.addActionListener(e1 -> {
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
                    gradeFromSaves_Process(NewGrade,Gradelistpanel,OpenSaves_addGrade,OpenSaves_deleteGrade);
                    int index = listOfStudent.getSelectedIndex();
                    openSaves_ready.addActionListener(e116 -> {NewGrade.dispose(); listOfGrades.updateUI();});
                    OpenSaves_addGrade.addActionListener(e117 -> {
                        listOfGrades.updateUI();
                        JFrame UpdateGrades = new JFrame("Новая оценка");
                        UpdateGrades.setVisible(true);
                        JButton OpenSavesaddGrade1 = new JButton("Добавить");
                        JButton OpenSavesBackToGradeList = new JButton("Отмена");
                        addGrade_Process2(UpdateGrades,OpenSavesaddGrade1,OpenSavesBackToGradeList);
                        OpenSavesaddGrade1.addActionListener(e11712 -> {
                            try {
                                Integer.parseInt(gradesFieldFromSave.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                            }
                            if ((Integer.parseInt(gradesFieldFromSave.getText()) != 1) && (Integer.parseInt(gradesFieldFromSave.getText()) != 2) && (Integer.parseInt(gradesFieldFromSave.getText()) != 3) && (Integer.parseInt(gradesFieldFromSave.getText()) != 4) && (Integer.parseInt(gradesFieldFromSave.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Введите оценку по пятибальной шкале!", "Error!", JOptionPane.ERROR_MESSAGE);
                            Grade GradeToAdd = new Grade(Integer.parseInt(gradesFieldFromSave.getText()), (Grade.type) typeOfGrade_Saves.getSelectedItem());
                            GradeListModel.addElement(GradeToAdd);
                            gradeList.add(GradeToAdd);
                            studentList.get(SelectedIndex2).AddGrade(GradeToAdd);
                            Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                            student.AddGrade(GradeToAdd);
                            UpdateGrades.dispose();
                            listOfStudent.updateUI();
                            gradesFieldFromSave.setText(null);
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
                nextToTableFromSave.addActionListener(e119 -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Ведомость пустая!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Просмотр и сохранение ведомости");
                        newGroup.setVisible(false);
                        openSavesNextToSave_Process(save,StudentListModel);
                        /////////////////////////////////////////////////////////////////////////////////////////////////
                        saveFromSave.addActionListener(e11915 -> {
                            JFrame SavingName = new JFrame("Введите название");
                            JTextField Forsave2 = new JTextField();
                            JButton CommitSave2 = new JButton("Готово");
                            JButton CancelSave2 = new JButton("Отмена");
                            saveFromSave_Process(SavingName,CommitSave2,CancelSave2,Forsave2);
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
                                    fileNotFoundError();
                                } catch (IOException d) {
                                    IOError();
                                }
                                SavingName.dispose();
                            });

                        });
                        toExcelFromSave.addActionListener(e11916 -> {
                            excel_Process();
                            backToTable.addActionListener(e11913 -> excelFrame.dispose());
                            saveExcel.addActionListener(e119161 -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(), excelName.getText());
                                } catch (IOException d) {
                                 IOError();
                                }
                                excelFrame.dispose();
                            });

                        });
                       backToStudents.addActionListener(e16 -> {
                           newGroup.setVisible(true);
                           save.setVisible(false);
                       });
exit.addActionListener(e17 -> System.exit(0));
                    }
                });
            });
        });
    }


    //////////////////////////////////////////////METHODS
private void сreate_Process(JFrame newDiscipline){
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
    disciplineName.setSize(100, 50);
    Content.add(Box.createVerticalStrut(20));
    Content.add(discipline, BorderLayout.WEST);
    Content.add(disciplineName, BorderLayout.CENTER);
    Content.add(Box.createVerticalStrut(20));
    Content.add(group, BorderLayout.WEST);
    Content.add(studentGroup, BorderLayout.CENTER);
    Content.add(Box.createVerticalStrut(20));
    Content.add(TeacherName, BorderLayout.WEST);
    Content.add(teachersName, BorderLayout.CENTER);
    Content.add(Box.createVerticalStrut(20));
    Content.add(TeacherSurname, BorderLayout.WEST);
    Content.add(teachersSurname, BorderLayout.CENTER);
    Content.add(Box.createVerticalStrut(20));
    Content.add(buttonspanel, BorderLayout.SOUTH);
    buttonspanel.add(nextToModidy);
    buttonspanel.add(backToStart);
}

private void addGrade_Process(JFrame NewGrade, JButton ConfirmGrade, JButton cancelGrade){
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
    forText.add(gradesField);
    forText.add(Box.createVerticalStrut(50));
    forText.add(typeOfGrade);
    forText.add(Box.createVerticalStrut(50));
    forButtons.add(Box.createHorizontalStrut(30));
    forButtons.add(ConfirmGrade);
    forButtons.add(Box.createHorizontalStrut(20));
    forButtons.add(cancelGrade);
    }

    private void addGrade_Process2(JFrame NewGrade, JButton ConfirmGrade2, JButton cancelGrade2){

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
        forText.add(gradesFieldFromSave);
        forText.add(Box.createVerticalStrut(50));
        forText.add(typeOfGrade_Saves);
        forText.add(Box.createVerticalStrut(50));
        forButtons.add(Box.createHorizontalStrut(30));
        forButtons.add(ConfirmGrade2);
        forButtons.add(Box.createHorizontalStrut(20));
        forButtons.add(cancelGrade2);
    }

private void addStudent_Process(JFrame AddNewStudent, JButton ConfirmNewStudent, JButton BackToStudentList){
    newStudentName.setText(null);
    newStudentSurname.setText(null);
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
    addpanel.add(newStudentName);
    addpanel.add(Box.createVerticalStrut(50));
    addpanel.add(surnameOfStudent);
    addpanel.add(Box.createVerticalStrut(10));
    addpanel.add(newStudentSurname);
    addpanel.add(Box.createVerticalStrut(50));
    buttons1.add(Box.createHorizontalStrut(50));
    buttons1.add(ConfirmNewStudent);
    buttons1.add(Box.createHorizontalStrut(10));
    buttons1.add(BackToStudentList);
}

private void mainReport(JFrame newDiscipline){
    newDiscipline.setVisible(false);
    newGroup.setSize(600, 400);
    newGroup.setVisible(true);
    JLabel NameOfGroup = new JLabel("Группа: " + studentGroup.getText());
    JLabel disciplineName = new JLabel("Дисциплина: " + this.disciplineName.getText());
    JLabel TeacherNames = new JLabel("Преподователь: " + teachersName.getText() + " " + teachersSurname.getText());
    JPanel grouppanel = new JPanel();
    JPanel AddStudent = new JPanel();
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
    if ((studentGroup.getText().isEmpty()) || (teachersName.getText().isEmpty()) || (teachersSurname.getText().isEmpty()) || (this.disciplineName.getText().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Не введены данные", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        newGroup.setVisible(false);
        newDiscipline.setVisible(true);
    }

    AddStudent.add(this.addStudent);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(deleteStudent);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(gradesOfStudent);
    AddStudent.add(Box.createVerticalStrut(100));
    AddStudent.add(nextToSave, BorderLayout.SOUTH);
    AddStudent.add(Box.createVerticalStrut(10));
    AddStudent.add(backToDisciplineCreation, BorderLayout.SOUTH);
}

private void nextToSave_Process(JFrame save, DefaultListModel<Student> students){
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
    ForButtons.add(Box.createHorizontalStrut(100));
    ForButtons.add(saveReport);
    ForButtons.add(Box.createHorizontalStrut(20));
    ForButtons.add(toExcel);
    ForButtons.add(Box.createHorizontalStrut(20));
    ForButtons.add(backToReport);
    ForButtons.add(Box.createHorizontalStrut(20));
    ForButtons.add(exit);
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

private void saveReport_Process(){
    forSaveReport.setSize(300, 300);
    forSaveReport.setVisible(true);
    JLabel SaveName = new JLabel("Введите название");
    JPanel ForName = new JPanel();
    JPanel ForButtonSave = new JPanel();
    ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
    ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
    ForButtonSave.add(Box.createHorizontalStrut(30));
    ForButtonSave.add(confirmSave);
    ForButtonSave.add(Box.createHorizontalStrut(20));
    ForButtonSave.add(cancelSave);
    SaveName.setFont(font);
    forSaveReport.add(ForName, BorderLayout.CENTER);
    ForName.add(Box.createVerticalStrut(50));
    ForName.add(SaveName);
    ForName.add(Box.createVerticalStrut(30));
    ForName.add(nameOfSave);
    ForName.add(Box.createVerticalStrut(100));
    forSaveReport.add(ForButtonSave, BorderLayout.SOUTH);
    forSaveReport.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void newGrades_Process(JFrame NewGrades, JPanel listpanel, JScrollPane jScrollPane2, JButton addGrade, JButton deleteGrade){
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
    ButtonsPanel.add(readyGrade, BorderLayout.EAST);
    ButtonsPanel.add(Box.createVerticalStrut(100));
}

private void open_Process(JFrame OpenFrame, JButton BackToStart){
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
    ForButtons.add(openListSaves);
    ForButtons.add(Box.createVerticalStrut(20));
    ForButtons.add(deleteSave);
    ForButtons.add(Box.createVerticalStrut(20));
    ForButtons.add(BackToStart);
    OpenFrame.add(ForButtons, BorderLayout.EAST);
    OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void excel_Process(){
    excelFrame.setSize(300, 300);
    excelFrame.setVisible(true);
    JLabel NameOfExcel = new JLabel("Введите название");
    JPanel ForNameOfExcel = new JPanel();
    JPanel ForExcelButtons = new JPanel();
    ForExcelButtons.setLayout(new BoxLayout(ForExcelButtons, BoxLayout.X_AXIS));
    ForNameOfExcel.setLayout(new BoxLayout(ForNameOfExcel, BoxLayout.Y_AXIS));
    ForExcelButtons.add(Box.createHorizontalStrut(30));
    ForExcelButtons.add(saveExcel);
    ForExcelButtons.add(Box.createHorizontalStrut(20));
    ForExcelButtons.add(backToTable);
    NameOfExcel.setFont(font);
    excelFrame.add(ForNameOfExcel, BorderLayout.CENTER);
    ForNameOfExcel.add(Box.createVerticalStrut(50));
    ForNameOfExcel.add(NameOfExcel);
    ForNameOfExcel.add(Box.createVerticalStrut(30));
    ForNameOfExcel.add(excelName);
    ForNameOfExcel.add(Box.createVerticalStrut(100));
    excelFrame.add(ForExcelButtons, BorderLayout.SOUTH);
    excelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void addStudentFromSaves_Process(JLabel group1, JLabel discipline1, JLabel name1,JButton openFromSave,JButton deleteStudentFromSave,JButton nextToTableFromSave,JButton backToSaves,JButton gradesFromSave){
    JPanel GroupPanel = new JPanel();
    JPanel NewStudentFromSave = new JPanel();
    GroupPanel.setLayout(new BoxLayout(GroupPanel, BoxLayout.Y_AXIS));
    NewStudentFromSave.setLayout(new BoxLayout(NewStudentFromSave, BoxLayout.Y_AXIS));
    newGroup.add(GroupPanel, BorderLayout.NORTH);
    newGroup.add(NewStudentFromSave, BorderLayout.EAST);
    GroupPanel.add(group1, BorderLayout.CENTER);
    GroupPanel.add(Box.createVerticalStrut(10));
    GroupPanel.add(discipline1, BorderLayout.CENTER);
    GroupPanel.add(Box.createVerticalStrut(10));
    GroupPanel.add(name1, BorderLayout.CENTER);
    NewStudentFromSave.add(openFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(deleteStudentFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(gradesFromSave);
    NewStudentFromSave.add(Box.createVerticalStrut(100));
    NewStudentFromSave.add(nextToTableFromSave, BorderLayout.SOUTH);
    NewStudentFromSave.add(Box.createVerticalStrut(10));
    NewStudentFromSave.add(backToSaves, BorderLayout.SOUTH);
}

private void openSavesAddNewStudent_Process(JFrame newstudent1, JButton OpenSaves_ConfirmNewStudent, JButton OpenSaves_BackToStudentsList){
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
    addpanel.add(openSaves_NameOfStudent);
    addpanel.add(Box.createVerticalStrut(50));
    addpanel.add(surnameOfStudent);
    addpanel.add(Box.createVerticalStrut(10));
    addpanel.add(openSaves_SurnameOfStudent);
    addpanel.add(Box.createVerticalStrut(50));
    buttons1.add(Box.createHorizontalStrut(50));
    buttons1.add(OpenSaves_ConfirmNewStudent);
    buttons1.add(Box.createHorizontalStrut(10));
    buttons1.add(OpenSaves_BackToStudentsList);
}

private void gradeFromSaves_Process(JFrame NewGrade, JPanel Gradelistpanel, JButton OpenSaves_addGrade, JButton OpenSaves_deleteGrade){
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
    ForGradePanelButtons.add(openSaves_ready, BorderLayout.EAST);
    ForGradePanelButtons.add(Box.createVerticalStrut(100));
}



private void openSavesNextToSave_Process(JFrame save, DefaultListModel<Student> students){
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
    ForButtons1.add(Box.createHorizontalStrut(100));
    ForButtons1.add(saveFromSave);
    ForButtons1.add(Box.createHorizontalStrut(20));
    ForButtons1.add(toExcelFromSave);
    ForButtons1.add(Box.createHorizontalStrut(20));
    ForButtons1.add(backToStudents);
    ForButtons1.add(Box.createHorizontalStrut(20));
    ForButtons1.add(exit);
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

private void saveFromSave_Process(JFrame SavingName, JButton CommitSave2, JButton CancelSave2, JTextField Forsave2){
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
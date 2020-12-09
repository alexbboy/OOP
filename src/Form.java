import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {
    private final JTable table = new JTable();
    private List<ReportData> listForSave = new ArrayList<>();
    private final JFrame newGroup = new JFrame("New group");
    private final JFrame excelFrame = new JFrame("Enter a name of excel");
    private final JFrame forSaveReport = new JFrame("Enter a name of save");
    private final JTextField disciplineNameTxt = new JTextField();
    private final JTextField teachersNameTxt = new JTextField();
    private final JTextField teachersSurnameTxt = new JTextField();
    private final JTextField studentGroupTxt = new JTextField();
    private final Font font = new Font("Verdana", Font.PLAIN, 20);
    private final JButton nextToModidyBtn = new JButton("Confirm");
    private final JButton backToStartBtn = new JButton("Back");
    private final JTextField excelNameTxt = new JTextField();
    private final JTextField nameOfSaveTxt = new JTextField();
    private final JTextField newStudentNameTxt = new JTextField();
    private final JTextField newStudentSurnameTxt = new JTextField();
    private final JTextField gradesFieldTxt = new JTextField();
    private final JTextField gradesFieldFromSaveTxt = new JTextField();
    private final JComboBox<Grade.type> typeOfGradeCbx = new JComboBox<>();
    private final JPanel forListOfStudent = new JPanel();
    private final JButton readyGradeBtn = new JButton("Ready");
    private final JButton deleteSaveBtn = new JButton("Delete report");
    private final JTextField openSavesNameOfStudentTxt = new JTextField();
    private final JTextField openSavesSurnameOfStudentBtn = new JTextField();
    private final JButton openSavesReadyBtn = new JButton("Confirm");
    private final JComboBox<Grade.type> typeOfGradeFromSavesCbx = new JComboBox<>();
    private final JButton saveFromSaveBtn = new JButton("Save");
    private final JButton toExcelFromSaveBtn = new JButton("Excel");
    private final JButton backToStudentsBtn = new JButton("Back");
    private final JButton exitBtn = new JButton("Exit");

    ///////////////////////////////////////////
    Form(String filename) {
        setTitle("Ведомость");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File file = new File(filename);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                IOError();
            }
        Serializer ObjectForSave = new Serializer(filename);
        if (file.length() != 0) {
            try {
                listForSave = ObjectForSave.load();
            } catch (FileNotFoundException e) {
                fileNotFoundError();
            } catch (ClassNotFoundException | IOException d) {
                IOError();
            }
        }
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        setContentPane(mainPanel);
        JLabel title = new JLabel("Report app", JLabel.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 35));
        title.setForeground(Color.BLUE);
        mainPanel.add(title);
        JButton create = new JButton("Create report");
        JButton open = new JButton("Open report");
        JPanel buttons = new JPanel();
        buttons.setSize(300, 500);
        mainPanel.add(buttons, BorderLayout.SOUTH);
        buttons.add(create, BorderLayout.CENTER);
        buttons.add(Box.createVerticalStrut(30));
        buttons.add(open, BorderLayout.CENTER);
//////////////////////////////////////////////////////////////////////////////
        create.addActionListener(creationEvenet -> {
            JFrame newDiscipline = new JFrame("Creation of discipline");
            createProcess(newDiscipline);
            backToStartBtn.addActionListener(backToStartEvent -> {
                int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION)
                    newDiscipline.dispose();
            });
            nextToModidyBtn.addActionListener(nextToModifyEvent -> {
                if (!isDigits(teachersNameTxt.getText())) {
                    JOptionPane.showMessageDialog(null, "Enter an alphas in name!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isDigits(disciplineNameTxt.getText())) {
                    JOptionPane.showMessageDialog(null, "Enter an alphas in Discipline!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isDigits(teachersSurnameTxt.getText())) {
                    JOptionPane.showMessageDialog(null, "Enter an alphas in Surname!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Double.parseDouble(studentGroupTxt.getText());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Enter a number of group!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JButton addStudent = new JButton("Add student");
                JButton deleteStudent = new JButton("Delete student");
                JButton gradesOfStudent = new JButton("Upgrade grades");
                JButton nextToSave = new JButton("Next");
                JButton backToDisciplineCreation = new JButton("Back");
                mainReport(newDiscipline, addStudent, deleteStudent, nextToSave, gradesOfStudent, backToDisciplineCreation);
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
                backToDisciplineCreation.addActionListener(backToDisciplineCreationEvent -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        newGroup.setVisible(false);
                        newDiscipline.setVisible(true);
                    }
                });
                addStudent.addActionListener(addStudentEvent -> {
                    JButton ConfirmNewStudent = new JButton("Confirm");
                    JButton BackToStudentList = new JButton("Cancel");
                    JFrame AddNewStudent = new JFrame("Add student");
                    addStudentProcess(AddNewStudent, ConfirmNewStudent, BackToStudentList);
                    ConfirmNewStudent.addActionListener(confirmNewStudentEvent -> {
                        if (!isDigits(newStudentNameTxt.getText())) {
                            JOptionPane.showMessageDialog(null, "Enter an alphas in Name!",
                                    "Error!",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!isDigits(newStudentSurnameTxt.getText())) {
                            JOptionPane.showMessageDialog(null, "Enter an alphas in surname!",
                                    "Error!",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Student NewStudent = new Student(Integer.parseInt(studentGroupTxt.getText()), newStudentNameTxt.getText(), newStudentSurnameTxt.getText());
                        StudentModel.addElement(NewStudent);
                        studentList.add(NewStudent);
                        AddNewStudent.dispose();
                        AddNewStudent.setVisible(false);
                    });
                    BackToStudentList.addActionListener(backToStudentListEvent -> AddNewStudent.dispose());
                });
                deleteStudent.addActionListener(deleteStudentEvent -> {
                    if (jlistOfStudent.getSelectedIndex() != -1) {
                        int SelectedIndex = jlistOfStudent.getSelectedIndex();
                        StudentModel.remove(SelectedIndex);
                        studentList.remove(SelectedIndex);
                    }
                });
                gradesOfStudent.addActionListener(gradesOfStudentEvent -> {
                    if (jlistOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Choose student", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrades = new JFrame("Work with grades");
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
                    JPanel listPanel = new JPanel();
                    JButton AddGrade = new JButton("Add grade");
                    JButton DeleteGrade = new JButton("Delete grade");
                    newGradesProcess(NewGrades, listPanel, jScrollPane2, AddGrade, DeleteGrade);
                    int index = jlistOfStudent.getSelectedIndex();
                    readyGradeBtn.addActionListener(e11316 -> NewGrades.dispose());
                    AddGrade.addActionListener(AddGradeEvent -> {
                        listOfGrades.updateUI();
                        JFrame NewGrade = new JFrame("New grade");
                        JButton ConfirmGrade = new JButton("Confirm");
                        JButton cancelGrade = new JButton("Cancel");
                        addGradeProcess(NewGrade, ConfirmGrade, cancelGrade);
                        ConfirmGrade.addActionListener(e113112 -> {
                            try {
                                Integer.parseInt(gradesFieldTxt.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Enter a number", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                            if ((Integer.parseInt(gradesFieldTxt.getText()) != 1) && (Integer.parseInt(gradesFieldTxt.getText()) != 2) && (Integer.parseInt(gradesFieldTxt.getText()) != 3) && (Integer.parseInt(gradesFieldTxt.getText()) != 4) && (Integer.parseInt(gradesFieldTxt.getText()) != 5))
                                JOptionPane.showMessageDialog(null, "Enter grade 1-5!", "Error!", JOptionPane.ERROR_MESSAGE);
                            else {
                                Grade GradeToAdd = new Grade(Integer.parseInt(gradesFieldTxt.getText()), (Grade.type) typeOfGradeCbx.getSelectedItem());
                                GradeListModel.addElement(GradeToAdd);
                                GradeList.add(GradeToAdd);
                                studentList.get(jlistOfStudent.getSelectedIndex()).AddGrade(GradeToAdd);
                                Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                                student.AddGrade(GradeToAdd);
                                NewGrade.dispose();
                                NewGrade.setVisible(false);
                                jlistOfStudent.updateUI();
                                gradesFieldTxt.setText(null);
                            }
                        });
                        cancelGrade.addActionListener(cancelGradeEvent -> NewGrade.dispose());
                    });
                    DeleteGrade.addActionListener(deleteGradeEvent -> {
                        if (listOfGrades.getSelectedIndex() != -1) {
                            int SelectedIndex = listOfGrades.getSelectedIndex();
                            studentList.get(jlistOfStudent.getSelectedIndex()).RemoveGrade(SelectedIndex);
                            GradeListModel.remove(listOfGrades.getSelectedIndex());
                            GradeList.remove(SelectedIndex);
                        } else {
                            JOptionPane.showMessageDialog(null, "Choose grade to delete", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                });
                nextToSave.addActionListener(nextToSaveEvent -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Report is empty!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Show and save report");
                        newGroup.setVisible(false);
                        JButton saveReport = new JButton("Save");
                        JButton toExcel = new JButton("Excel");
                        JButton backToReport = new JButton("Back");
                        nextToSaveProcess(save, StudentModel, saveReport, toExcel, backToReport);
                        saveReport.addActionListener(saveReportEvent -> {
                            JButton confirmSaveBtn = new JButton("Confirm");
                            JButton cancelSaveBtn = new JButton("Back");
                            saveReportProcess(confirmSaveBtn,cancelSaveBtn);
                            cancelSaveBtn.addActionListener(cancelSaveEvent -> forSaveReport.dispose());
                            confirmSaveBtn.addActionListener(confirmSaveEvent -> {
                                Group saveGroup = new Group(Integer.parseInt(studentGroupTxt.getText()));
                                for (Student student : studentList) saveGroup.add(student);
                                Discipline SavingDiscipline = new Discipline(teachersNameTxt.getText(), teachersSurnameTxt.getText(), disciplineNameTxt.getText(), saveGroup);
                                ReportData object = new ReportData(nameOfSaveTxt.getText(), SavingDiscipline);
                                listForSave.add(object);
                                try {
                                    ObjectForSave.save(listForSave);
                                } catch (FileNotFoundException d) {
                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                } catch (IOException ignored) {
                                }
                                forSaveReport.dispose();
                            });
                        });
                        toExcel.addActionListener(toExcelEvent -> {
                            JButton saveExcel = new JButton("Confirm");
                            JButton backToTable = new JButton("Back");
                            excelProcess(saveExcel, backToTable);
                            backToTable.addActionListener(e113141 -> excelFrame.dispose());
                            saveExcel.addActionListener(saveExcelEvent -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(), excelNameTxt.getText());
                                } catch (IOException d) {
                                    IOError();
                                }
                                excelFrame.dispose();
                            });
                        });
                        backToReport.addActionListener(backToReportEvent -> {
                            int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                newGroup.setVisible(true);
                                save.setVisible(false);
                            }
                        });
                        exitBtn.addActionListener(exitEvent -> {
                            int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION)
                                System.exit(0);
                        });
                    }
                });
            });
        });
        /////////////////////////////////////////////////////////////
        open.addActionListener(OpenEvent -> {
            try {
                listForSave = ObjectForSave.load();
            } catch (FileNotFoundException d) {
                fileNotFoundError();
            } catch (ClassNotFoundException d) {
                IOError();
            } catch (IOException ignored) {
            }
            JFrame LoadFrame = new JFrame("Load a report");
            JButton BackToStart = new JButton("Back");
            JButton openListSaves = new JButton("Open");
            openProcess(LoadFrame, openListSaves, BackToStart);
            DefaultListModel<ReportData> titles = new DefaultListModel<>();
            JList<ReportData> listOfSaves = new JList<>(titles);
            for (ReportData for_save : listForSave) titles.addElement(for_save);
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
            BackToStart.addActionListener(backToStartEvent -> LoadFrame.dispose());
            deleteSaveBtn.addActionListener(deleteSaveEvent -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                if (SelectedIndex != -1) {
                    listForSave.remove(SelectedIndex);
                    titles.remove(SelectedIndex);
                    try {
                        ObjectForSave.save(listForSave);
                    } catch (FileNotFoundException d) {
                        throw new RuntimeException("File not found", d);
                    } catch (IOException q) {
                        IOError();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Choose save", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            });
            openListSaves.addActionListener(openListSavesEvent -> {
                int SelectedIndex = listOfSaves.getSelectedIndex();
                if (SelectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Choose save", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LoadFrame.setVisible(false);
                newGroup.setSize(600, 400);
                newGroup.setVisible(true);
                JLabel GroupLabel = new JLabel("Group: " + listForSave.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                JLabel DisciplineLabel = new JLabel("Discipline: " + listForSave.get(SelectedIndex).getDiscipline().getDiscipline());
                JLabel NameLabel = new JLabel("Teacher: " + listForSave.get(SelectedIndex).getDiscipline().getTeacherName() + " " + listForSave.get(listOfSaves.getSelectedIndex()).getDiscipline().getTeacherSurname());
                JPanel forList = new JPanel();
                JButton openFromSave = new JButton("Add student");
                JButton deleteStudentFromSave = new JButton("Delete student");
                JButton nextToTableFromSave = new JButton("Next");
                JButton backToSaves = new JButton("Back");
                JButton gradesFromSave = new JButton("Upgrade grades");
                newGroup.add(forList, BorderLayout.CENTER);
                addStudentFromSavesProcess(GroupLabel, DisciplineLabel, NameLabel, openFromSave, deleteStudentFromSave, nextToTableFromSave, backToSaves, gradesFromSave);
                List<Student> studentList = new ArrayList<>(listForSave.get(SelectedIndex).getDiscipline().getGroups().getStudents());
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

                backToSaves.addActionListener(backToSavesEvent -> {
                    int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        newGroup.setVisible(false);
                        LoadFrame.setVisible(true);
                    }
                });
                openFromSave.addActionListener(openFromSavesEvent -> {
                    JFrame newstudent1 = new JFrame("Add new student");
                    openSavesNameOfStudentTxt.setText(null);
                    openSavesSurnameOfStudentBtn.setText(null);
                    JButton openSavesConfirmNewStudent = new JButton("Confirm");
                    JButton openSavesBackToStudentsList = new JButton("Cancel");
                    openSavesAddNewStudentProcess(newstudent1, openSavesConfirmNewStudent, openSavesBackToStudentsList);
                    openSavesConfirmNewStudent.addActionListener(openSavesConfirmNewStudentEvent -> {
                        if (!isDigits(openSavesNameOfStudentTxt.getText())) {
                            JOptionPane.showMessageDialog(null, "Enter an alphas in Name!",
                                    "Error!",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!isDigits(openSavesSurnameOfStudentBtn.getText())) {
                            JOptionPane.showMessageDialog(null, "Enter an alphas in surname!",
                                    "Error!",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Student student = new Student((listForSave.get(SelectedIndex).getDiscipline().getGroups().getGroup()), openSavesNameOfStudentTxt.getText(), openSavesSurnameOfStudentBtn.getText());
                        StudentListModel.addElement(student);
                        studentList.add(student);
                        newstudent1.dispose();
                        newstudent1.setVisible(false);
                    });
                    openSavesBackToStudentsList.addActionListener(openSavesBackToStudentsListEvent -> newstudent1.dispose());
                });
                deleteStudentFromSave.addActionListener(deleteStudentEvent -> {
                    if (SelectedIndex != -1) {
                        int index = listOfStudent.getSelectedIndex();
                        StudentListModel.remove(index);
                        studentList.remove(index);
                    } else
                        JOptionPane.showMessageDialog(null, "Choose student", "Error!", JOptionPane.ERROR_MESSAGE);
                });
                gradesFromSave.addActionListener(gradeFromSaveEvent -> {
                    if (listOfStudent.getSelectedIndex() == -1) {
                        JOptionPane.showMessageDialog(null, "Choose student", "Error!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JFrame NewGrade = new JFrame("Work with grades");
                    NewGrade.setSize(500, 500);
                    int SelectedIndex2 = listOfStudent.getSelectedIndex();
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
                    JButton OpenSaves_addGrade = new JButton("Add grade");
                    JButton openSavesDeleteGrade = new JButton("Delete grade");
                    gradeFromSavesProcess(NewGrade, Gradelistpanel, OpenSaves_addGrade, openSavesDeleteGrade);
                    int index = listOfStudent.getSelectedIndex();
                    openSavesReadyBtn.addActionListener(readyEvent -> {
                        NewGrade.dispose();
                        listOfGrades.updateUI();
                    });
                    OpenSaves_addGrade.addActionListener(addGradeEvent -> {
                        listOfGrades.updateUI();
                        JFrame UpdateGrades = new JFrame("New grade");
                        UpdateGrades.setVisible(true);
                        JButton confirmNewGrade = new JButton("Confirm");
                        JButton OpenSavesBackToGradeList = new JButton("Cancel");
                        addGradeProcess2(UpdateGrades, confirmNewGrade, OpenSavesBackToGradeList);
                        confirmNewGrade.addActionListener(confirmNewGradeEvent -> {
                            try {
                                Integer.parseInt(gradesFieldFromSaveTxt.getText());
                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null, "Enter a number", "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                            if ((Integer.parseInt(gradesFieldFromSaveTxt.getText()) != 1) && (Integer.parseInt(gradesFieldFromSaveTxt.getText()) != 2) && (Integer.parseInt(gradesFieldFromSaveTxt.getText()) != 3) && (Integer.parseInt(gradesFieldFromSaveTxt.getText()) != 4) && (Integer.parseInt(gradesFieldFromSaveTxt.getText()) != 5)) {
                                JOptionPane.showMessageDialog(null, "Enter a grade 1-5!", "Error!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                Grade GradeToAdd = new Grade(Integer.parseInt(gradesFieldFromSaveTxt.getText()), (Grade.type) typeOfGradeFromSavesCbx.getSelectedItem());
                                GradeListModel.addElement(GradeToAdd);
                                gradeList.add(GradeToAdd);
                                studentList.get(SelectedIndex2).AddGrade(GradeToAdd);
                                Student student = new Student(studentList.get(index).getGroup(), studentList.get(index).getName(), studentList.get(index).getSurname());
                                student.AddGrade(GradeToAdd);
                                UpdateGrades.dispose();
                                listOfStudent.updateUI();
                                gradesFieldFromSaveTxt.setText(null);
                            }
                        });
                        OpenSavesBackToGradeList.addActionListener(backToGradeList -> UpdateGrades.dispose());
                    });
                    openSavesDeleteGrade.addActionListener(deleteGradeEvent -> {
                        int index1 = listOfGrades.getSelectedIndex();
                        if (index1 != -1) {
                            studentList.get(listOfStudent.getSelectedIndex()).RemoveGrade(index1);
                            GradeListModel.remove(listOfGrades.getSelectedIndex());
                            gradeList.remove(index1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Choose a grade to delete", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                });
                nextToTableFromSave.addActionListener(nextToTableEvent -> {
                    if (studentList.size() == 0)
                        JOptionPane.showMessageDialog(null, "Empty report!", "Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        JFrame save = new JFrame("Show and save of report");
                        newGroup.setVisible(false);
                        openSavesNextToSaveProcess(save, StudentListModel);
                        saveFromSaveBtn.addActionListener(saveFromSaveEvent -> {
                            Group saveGroup = new Group(listForSave.get(SelectedIndex).getDiscipline().getGroups().getGroup());
                            for (Student student : studentList) saveGroup.add(student);
                            Discipline SavingDiscipline = new Discipline(listForSave.get(SelectedIndex).getDiscipline().getTeacherName(), listForSave.get(SelectedIndex).getDiscipline().getTeacherSurname(), listForSave.get(SelectedIndex).getDiscipline().getDiscipline(), saveGroup);
                            ReportData object = new ReportData(listForSave.get(SelectedIndex).getName(), SavingDiscipline);
                            listForSave.remove(SelectedIndex);
                            listForSave.add(object);
                            try {
                                ObjectForSave.save(listForSave);
                            } catch (FileNotFoundException d) {
                                fileNotFoundError();
                            } catch (IOException d) {
                                IOError();
                            }
                            JOptionPane.showMessageDialog(null, "Success save", "Success", JOptionPane.INFORMATION_MESSAGE);
                        });
                        toExcelFromSaveBtn.addActionListener(toExcelEvent -> {
                            JButton saveExcel = new JButton("Confirm");
                            JButton backToTable = new JButton("Back");
                            excelProcess(saveExcel, backToTable);
                            backToTable.addActionListener(backToTableEvent -> excelFrame.dispose());
                            saveExcel.addActionListener(saveExcelEvent -> {
                                try {
                                    CsvGenerator.generateCsvFile(table.getModel(), excelNameTxt.getText());
                                } catch (IOException d) {
                                    IOError();
                                }
                                excelFrame.dispose();
                            });
                        });
                        backToStudentsBtn.addActionListener(backToStudentListEvent -> {
                            int confirm = JOptionPane.showConfirmDialog(this, "You can loose unsafe data, are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                newGroup.setVisible(true);
                                save.setVisible(false);
                            }
                        });
                        exitBtn.addActionListener(exitEvent -> System.exit(0));
                    }
                });
            });
        });
    }

    //////////////////////////////////////////////METHODS
    private void createProcess(JFrame newDiscipline) {
        newDiscipline.setSize(600, 400);
        newDiscipline.setVisible(true);
        JPanel Content = new JPanel(new BorderLayout(10, 10));
        Content.setLayout(new BoxLayout(Content, BoxLayout.Y_AXIS));
        newDiscipline.add(Content);
        JLabel discipline = new JLabel("Enter discipline");
        discipline.setFont(font);
        JLabel TeacherName = new JLabel("Enter a name of teacher");
        TeacherName.setFont(font);
        JLabel TeacherSurname = new JLabel("Enter a surname of teacher");
        TeacherSurname.setFont(font);
        JLabel group = new JLabel("Enter a group");
        group.setFont(font);
        JPanel buttonspanel = new JPanel();
        disciplineNameTxt.setSize(100, 50);
        Content.add(Box.createVerticalStrut(20));
        Content.add(discipline, BorderLayout.WEST);
        Content.add(disciplineNameTxt, BorderLayout.CENTER);
        Content.add(Box.createVerticalStrut(20));
        Content.add(group, BorderLayout.WEST);
        Content.add(studentGroupTxt, BorderLayout.CENTER);
        Content.add(Box.createVerticalStrut(20));
        Content.add(TeacherName, BorderLayout.WEST);
        Content.add(teachersNameTxt, BorderLayout.CENTER);
        Content.add(Box.createVerticalStrut(20));
        Content.add(TeacherSurname, BorderLayout.WEST);
        Content.add(teachersSurnameTxt, BorderLayout.CENTER);
        Content.add(Box.createVerticalStrut(20));
        Content.add(buttonspanel, BorderLayout.SOUTH);
        buttonspanel.add(nextToModidyBtn);
        buttonspanel.add(backToStartBtn);
    }

    private void addGradeProcess(JFrame NewGrade, JButton ConfirmGrade, JButton cancelGrade) {
        NewGrade.setSize(300, 300);
        NewGrade.setVisible(true);
        JPanel forText = new JPanel();
        JPanel forButtons = new JPanel();
        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
        JLabel Describtion = new JLabel("Enter a grade");
        typeOfGradeCbx.setModel(new DefaultComboBoxModel<>(Grade.type.values()));
        Describtion.setFont(font);
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        NewGrade.add(forText, BorderLayout.CENTER);
        NewGrade.add(forButtons, BorderLayout.SOUTH);
        forText.add(Box.createVerticalStrut(50));
        forText.add(Describtion);
        forText.add(Box.createHorizontalStrut(60));
        forText.add(Box.createVerticalStrut(20));
        forText.add(gradesFieldTxt);
        forText.add(Box.createVerticalStrut(50));
        forText.add(typeOfGradeCbx);
        forText.add(Box.createVerticalStrut(50));
        forButtons.add(Box.createHorizontalStrut(30));
        forButtons.add(ConfirmGrade);
        forButtons.add(Box.createHorizontalStrut(20));
        forButtons.add(cancelGrade);
    }

    private void addGradeProcess2(JFrame NewGrade, JButton ConfirmGrade2, JButton cancelGrade2) {
        NewGrade.setSize(300, 300);
        NewGrade.setVisible(true);
        JPanel forText = new JPanel();
        JPanel forButtons = new JPanel();
        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
        JLabel Describtion = new JLabel("Enter grade");
        typeOfGradeFromSavesCbx.setModel(new DefaultComboBoxModel<>(Grade.type.values()));
        Describtion.setFont(font);
        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
        NewGrade.add(forText, BorderLayout.CENTER);
        NewGrade.add(forButtons, BorderLayout.SOUTH);
        forText.add(Box.createVerticalStrut(50));
        forText.add(Describtion);
        forText.add(Box.createHorizontalStrut(60));
        forText.add(Box.createVerticalStrut(20));
        forText.add(gradesFieldFromSaveTxt);
        forText.add(Box.createVerticalStrut(50));
        forText.add(typeOfGradeFromSavesCbx);
        forText.add(Box.createVerticalStrut(50));
        forButtons.add(Box.createHorizontalStrut(30));
        forButtons.add(ConfirmGrade2);
        forButtons.add(Box.createHorizontalStrut(20));
        forButtons.add(cancelGrade2);
    }

    private void addStudentProcess(JFrame AddNewStudent, JButton ConfirmNewStudent, JButton BackToStudentList) {
        newStudentNameTxt.setText(null);
        newStudentSurnameTxt.setText(null);
        JLabel nameOfStudent = new JLabel("Enter a name of student");
        nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
        JLabel surnameOfStudent = new JLabel("Enter a surname of student");
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
        addpanel.add(newStudentNameTxt);
        addpanel.add(Box.createVerticalStrut(50));
        addpanel.add(surnameOfStudent);
        addpanel.add(Box.createVerticalStrut(10));
        addpanel.add(newStudentSurnameTxt);
        addpanel.add(Box.createVerticalStrut(50));
        buttons1.add(Box.createHorizontalStrut(50));
        buttons1.add(ConfirmNewStudent);
        buttons1.add(Box.createHorizontalStrut(10));
        buttons1.add(BackToStudentList);
    }

    private void mainReport(JFrame newDiscipline, JButton addStudent, JButton deleteStudent, JButton nextToSave, JButton gradesOfStudent, JButton backToDisciplineCreation) {
        newDiscipline.setVisible(false);
        newGroup.setSize(600, 400);
        newGroup.setVisible(true);
        JLabel NameOfGroup = new JLabel("Group: " + studentGroupTxt.getText());
        JLabel disciplineName = new JLabel("Discipline: " + this.disciplineNameTxt.getText());
        JLabel TeacherNames = new JLabel("Teacher: " + teachersNameTxt.getText() + " " + teachersSurnameTxt.getText());
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
        if ((studentGroupTxt.getText().isEmpty()) || (teachersNameTxt.getText().isEmpty()) || (teachersSurnameTxt.getText().isEmpty()) || (this.disciplineNameTxt.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Enter a data", "Error!", JOptionPane.ERROR_MESSAGE);
            newGroup.setVisible(false);
            newDiscipline.setVisible(true);
        }

        AddStudent.add(addStudent);
        AddStudent.add(Box.createVerticalStrut(10));
        AddStudent.add(deleteStudent);
        AddStudent.add(Box.createVerticalStrut(10));
        AddStudent.add(gradesOfStudent);
        AddStudent.add(Box.createVerticalStrut(100));
        AddStudent.add(nextToSave, BorderLayout.SOUTH);
        AddStudent.add(Box.createVerticalStrut(10));
        AddStudent.add(backToDisciplineCreation, BorderLayout.SOUTH);
    }

    private void nextToSaveProcess(JFrame save, DefaultListModel<Student> students, JButton saveReport, JButton toExcel, JButton backToReport) {
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
        JLabel title1 = new JLabel("Final report");
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
        ForButtons.add(exitBtn);
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

    private void saveReportProcess(JButton confirmSaveBtn,JButton cancelSaveBtn) {
        forSaveReport.setSize(300, 300);
        forSaveReport.setVisible(true);
        JLabel SaveName = new JLabel("Enter a name of save");
        JPanel ForName = new JPanel();
        JPanel ForButtonSave = new JPanel();
        ForButtonSave.setLayout(new BoxLayout(ForButtonSave, BoxLayout.X_AXIS));
        ForName.setLayout(new BoxLayout(ForName, BoxLayout.Y_AXIS));
        ForButtonSave.add(Box.createHorizontalStrut(30));
        ForButtonSave.add(confirmSaveBtn);
        ForButtonSave.add(Box.createHorizontalStrut(20));
        ForButtonSave.add(cancelSaveBtn);
        SaveName.setFont(font);
        forSaveReport.add(ForName, BorderLayout.CENTER);
        ForName.add(Box.createVerticalStrut(50));
        ForName.add(SaveName);
        ForName.add(Box.createVerticalStrut(30));
        ForName.add(nameOfSaveTxt);
        ForName.add(Box.createVerticalStrut(100));
        forSaveReport.add(ForButtonSave, BorderLayout.SOUTH);
        forSaveReport.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void newGradesProcess(JFrame NewGrades, JPanel listpanel, JScrollPane jScrollPane2, JButton addGrade, JButton deleteGrade) {
        JPanel ButtonsPanel = new JPanel();
        JPanel ForLabel = new JPanel();
        ButtonsPanel.setLayout(new BoxLayout(ButtonsPanel, BoxLayout.Y_AXIS));
        JLabel GradeText = new JLabel("Grades of student");
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
        ButtonsPanel.add(readyGradeBtn, BorderLayout.EAST);
        ButtonsPanel.add(Box.createVerticalStrut(100));
    }

    private void openProcess(JFrame OpenFrame, JButton openListSaves, JButton BackToStart) {
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
        ForButtons.add(deleteSaveBtn);
        ForButtons.add(Box.createVerticalStrut(20));
        ForButtons.add(BackToStart);
        OpenFrame.add(ForButtons, BorderLayout.EAST);
        OpenFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void excelProcess(JButton saveExcel, JButton backToTable) {
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
        ForNameOfExcel.add(excelNameTxt);
        ForNameOfExcel.add(Box.createVerticalStrut(100));
        excelFrame.add(ForExcelButtons, BorderLayout.SOUTH);
        excelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addStudentFromSavesProcess(JLabel group1, JLabel discipline1, JLabel name1, JButton openFromSave, JButton deleteStudentFromSave, JButton nextToTableFromSave, JButton backToSaves, JButton gradesFromSave) {
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

    private void openSavesAddNewStudentProcess(JFrame newstudent1, JButton OpenSaves_ConfirmNewStudent, JButton OpenSaves_BackToStudentsList) {
        JLabel nameOfStudent = new JLabel("Enter a name of student");
        nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
        JLabel surnameOfStudent = new JLabel("Enter a surname of student");
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
        addpanel.add(openSavesNameOfStudentTxt);
        addpanel.add(Box.createVerticalStrut(50));
        addpanel.add(surnameOfStudent);
        addpanel.add(Box.createVerticalStrut(10));
        addpanel.add(openSavesSurnameOfStudentBtn);
        addpanel.add(Box.createVerticalStrut(50));
        buttons1.add(Box.createHorizontalStrut(50));
        buttons1.add(OpenSaves_ConfirmNewStudent);
        buttons1.add(Box.createHorizontalStrut(10));
        buttons1.add(OpenSaves_BackToStudentsList);
    }

    private void gradeFromSavesProcess(JFrame NewGrade, JPanel Gradelistpanel, JButton OpenSaves_addGrade, JButton OpenSaves_deleteGrade) {
        JPanel ForGradePanelButtons = new JPanel();
        JPanel ForLabel = new JPanel();
        ForGradePanelButtons.setLayout(new BoxLayout(ForGradePanelButtons, BoxLayout.Y_AXIS));
        JLabel GradeText = new JLabel("Grades of student");
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
        ForGradePanelButtons.add(openSavesReadyBtn, BorderLayout.EAST);
        ForGradePanelButtons.add(Box.createVerticalStrut(100));
    }

    private void openSavesNextToSaveProcess(JFrame save, DefaultListModel<Student> students) {
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
        JLabel title1 = new JLabel("Final report");
        JPanel ForButtons1 = new JPanel();
        save.add(ForButtons1, BorderLayout.SOUTH);
        ForButtons1.setLayout(new BoxLayout(ForButtons1, BoxLayout.X_AXIS));
        ForButtons1.add(Box.createHorizontalStrut(100));
        ForButtons1.add(saveFromSaveBtn);
        ForButtons1.add(Box.createHorizontalStrut(20));
        ForButtons1.add(toExcelFromSaveBtn);
        ForButtons1.add(Box.createHorizontalStrut(20));
        ForButtons1.add(backToStudentsBtn);
        ForButtons1.add(Box.createHorizontalStrut(20));
        ForButtons1.add(exitBtn);
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

    private boolean isDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    private void IOError() {
        JOptionPane.showConfirmDialog(
                this,
                "Unable to store data",
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void fileNotFoundError() {
        JOptionPane.showConfirmDialog(
                this,
                "File not found",
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE
        );
    }
}
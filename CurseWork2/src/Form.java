import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Form extends JFrame {
    private final JTable table = new JTable();
    private String filename;
    private List<For_Save> List_for_Save = new ArrayList<For_Save>();
    private final JFrame jFrame = new JFrame("Ведомость");
    private final JFrame newDiscipline = new JFrame("Создание дисциплины");
    private final JFrame newGroup = new JFrame("Создание группы");
    private final JList<Student> studentJList = new JList<Student>();
    private final List<Discipline> disciplineList = new ArrayList<Discipline>();

    ///////////////////////////////////////////
    Form(String filename) {
        this.filename = filename;
        setTitle("Ведомость");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File file1 = new File(filename);
        int g = 0;
        if (!file1.exists())
            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Exception,", e);
            }
        Save ObjectSave = new Save(filename);
        if (file1.length() == 0)
            g++;
        else {
            try {
                List_for_Save = ObjectSave.load();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found", e);
            } catch (ClassNotFoundException d) {
                throw new RuntimeException("Exception", d);
            } catch (IOException q) {
                throw new RuntimeException("Failed", q);
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
        // buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
        buttons.add(create, BorderLayout.CENTER);
        buttons.add(Box.createVerticalStrut(30));
        buttons.add(open, BorderLayout.CENTER);
//////////////////////////////////////////////////////////////////////////////
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newDiscipline = new JFrame("Создание дисциплины");
                newDiscipline.setSize(600, 400);
                newDiscipline.setVisible(true);
                JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                newDiscipline.add(mainPanel);
                JLabel discipline = new JLabel("Введите название дисциплины");
                discipline.setFont(new Font("Verdana", Font.PLAIN, 20));
                JLabel name = new JLabel("Введите имя преподавателя");
                name.setFont(new Font("Verdana", Font.PLAIN, 20));
                JLabel surname = new JLabel("Введите фамилию преподователя");
                surname.setFont(new Font("Verdana", Font.PLAIN, 20));
                JLabel group = new JLabel("Введите группу");
                group.setFont(new Font("Verdana", Font.PLAIN, 20));
                JTextField disciplinetext = new JTextField();
                JTextField nametext = new JTextField();
                JTextField surnametext = new JTextField();
                JTextField grouptext = new JTextField();
                JButton next = new JButton("Далее");
                JButton cancel = new JButton("Назад");
                JPanel buttonspanel = new JPanel();

                disciplinetext.setSize(100, 50);
                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(discipline, BorderLayout.WEST);
                mainPanel.add(disciplinetext, BorderLayout.CENTER);
                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(group, BorderLayout.WEST);
                mainPanel.add(grouptext, BorderLayout.CENTER);
                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(name, BorderLayout.WEST);
                mainPanel.add(nametext, BorderLayout.CENTER);
                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(surname, BorderLayout.WEST);
                mainPanel.add(surnametext, BorderLayout.CENTER);
                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(buttonspanel, BorderLayout.SOUTH);
                buttonspanel.add(next);
                buttonspanel.add(cancel);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newDiscipline.dispose();
                    }
                });
                next.addActionListener(e1 -> {
                    try {
                        Double.parseDouble(grouptext.getText());
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Введите число в строку группы!", "Error!", JOptionPane.ERROR_MESSAGE);

                    }
                    newDiscipline.setVisible(false);
                    newGroup.setSize(600, 400);
                    newGroup.setVisible(true);
                    JLabel group1 = new JLabel("Группа: " + grouptext.getText());
                    JLabel discipline1 = new JLabel("Дисциплина: " + disciplinetext.getText());
                    JLabel name1 = new JLabel("Преподователь: " + nametext.getText() + " " + surnametext.getText());
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
                    if ((grouptext.getText().isEmpty()) || (nametext.getText().isEmpty()) || (surnametext.getText().isEmpty() == true) || (disciplinetext.getText().isEmpty() == true)) {
                        JOptionPane.showMessageDialog(null, "Не введены данные", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                        newGroup.setVisible(false);
                        newDiscipline.setVisible(true);
                    }
                    JButton add = new JButton("Добавить студента");
                    JButton delete = new JButton("Удалить студента");
                    JButton grades = new JButton("Редактировать оценки");
                    JButton next2 = new JButton("Далее");
                    JButton cancel2 = new JButton("Назад");
                    newstudent.add(add);
                    newstudent.add(Box.createVerticalStrut(10));
                    newstudent.add(delete);
                    newstudent.add(Box.createVerticalStrut(10));
                    newstudent.add(grades);
                    newstudent.add(Box.createVerticalStrut(100));
                    newstudent.add(next2, BorderLayout.SOUTH);
                    newstudent.add(Box.createVerticalStrut(10));
                    newstudent.add(cancel2, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////
                    List<Student> studentList = new ArrayList<Student>();
                    DefaultListModel<Student> students = new DefaultListModel<Student>();
                    JList<Student> listOfStudent = new JList<Student>(students);
                    JScrollPane jScrollPane = new JScrollPane(listOfStudent) {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(300, 350);
                        }
                    };
                    jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);
                    forList.add(Box.createVerticalStrut(30));
                    forList.add(jScrollPane, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////////////
                    cancel2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            newGroup.setVisible(false);
                            newDiscipline.setVisible(true);
                        }
                        //////////////////////////////////////////////
                    });
                    add.addActionListener(e114 -> {
                        JTextField name112 = new JTextField();
                        JTextField surname1 = new JTextField();
                        JButton ok = new JButton("Добавить");
                        JLabel nameOfStudent = new JLabel("Введите имя студента");
                        nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                        JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
                        surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                        JButton cancel3 = new JButton("Отмена");
                        JFrame newstudent12 = new JFrame("Добавление студента");
                        newstudent12.setSize(300, 300);
                        newstudent12.setVisible(true);
                        JPanel addpanel = new JPanel();
                        JPanel buttons1 = new JPanel();
                        newstudent12.add(addpanel, BorderLayout.CENTER);
                        newstudent12.add(buttons1, BorderLayout.SOUTH);
                        buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));
                        addpanel.setLayout(new BoxLayout(addpanel, BoxLayout.Y_AXIS));
                        addpanel.add(nameOfStudent);
                        addpanel.add(Box.createVerticalStrut(10));
                        addpanel.add(name112);
                        addpanel.add(Box.createVerticalStrut(50));
                        addpanel.add(surnameOfStudent);
                        addpanel.add(Box.createVerticalStrut(10));
                        addpanel.add(surname1);
                        addpanel.add(Box.createVerticalStrut(50));
                        buttons1.add(Box.createHorizontalStrut(50));
                        buttons1.add(ok);
                        buttons1.add(Box.createHorizontalStrut(10));
                        buttons1.add(cancel3);
                        ok.addActionListener(e1141 -> {
                            Student student = new Student(Integer.parseInt(grouptext.getText()), name112.getText(), surname1.getText());
                            students.addElement(student);
                            studentList.add(student);
                            newstudent12.dispose();
                            newstudent12.setVisible(false);
                        });
                        cancel3.addActionListener(e11412 -> newstudent12.dispose());

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
                        JFrame newgrade = new JFrame("Работа с оценками");
                        newgrade.setSize(500, 500);
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
                        newgrade.setVisible(true);
                        newgrade.add(forlabel, BorderLayout.NORTH);
                        newgrade.add(listpanel, BorderLayout.CENTER);
                        newgrade.add(buttonspanel2, BorderLayout.EAST);
                        forlabel.add(gradetext, BorderLayout.CENTER);
                        listpanel.add(jScrollPane2, BorderLayout.CENTER);
                        buttonspanel2.add(addGrade, BorderLayout.EAST);
                        buttonspanel2.add(Box.createVerticalStrut(20));
                        buttonspanel2.add(deleteGrade, BorderLayout.EAST);
                        buttonspanel2.add(Box.createVerticalStrut(20));
                        buttonspanel2.add(ready, BorderLayout.EAST);
                        buttonspanel2.add(Box.createVerticalStrut(100));
                        int index = listOfStudent.getSelectedIndex();
                        int count = 0;
                        ready.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e113) {
                                newgrade.dispose();
                            }
                        });
                        addGrade.addActionListener(e1131 -> {

                            JFrame updateGrade = new JFrame("Новая оценка");
                            updateGrade.setSize(300, 300);
                            updateGrade.setVisible(true);
                            JPanel forText = new JPanel();
                            JPanel forButtons = new JPanel();
                            forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
                            JLabel describtion = new JLabel("Введите оценку");
                            JButton addGrade12 = new JButton("Добавить");
                            JButton cancelGrade = new JButton("Отмена");

                            JPanel fake = new JPanel();
                            JPanel fake2 = new JPanel();
                            updateGrade.add(fake, BorderLayout.NORTH);
                            JComboBox typeOfGrade = new JComboBox();
                            typeOfGrade.setModel(new DefaultComboBoxModel(Grade.type.values()));
                            describtion.setFont(new Font("Verdana", Font.PLAIN, 20));
                            JTextField GradesField = new JTextField();
                            forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
                            updateGrade.add(forText, BorderLayout.CENTER);
                            updateGrade.add(forButtons, BorderLayout.SOUTH);
                            updateGrade.add(fake2, BorderLayout.EAST);
                            forText.add(Box.createVerticalStrut(50));
                            forText.add(describtion);
                            forText.add(Box.createHorizontalStrut(60));
                            forText.add(Box.createVerticalStrut(20));
                            forText.add(GradesField);
                            forText.add(Box.createVerticalStrut(50));
                            forText.add(typeOfGrade);
                            forText.add(Box.createVerticalStrut(50));
                            forButtons.add(Box.createHorizontalStrut(30));
                            forButtons.add(addGrade12);
                            forButtons.add(Box.createHorizontalStrut(20));
                            forButtons.add(cancelGrade);

                            addGrade12.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1131) {
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

                                    updateGrade.dispose();
                                    updateGrade.setVisible(false);
                                    listOfStudent.updateUI();
                                }

                            });
                            cancelGrade.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e1131) {
                                    updateGrade.dispose();
                                }
                            });
                        });
                        deleteGrade.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e113) {
                                if (listOfGrades.getSelectedIndex() != -1) {
                                    int index = listOfGrades.getSelectedIndex();
                                    studentList.get(listOfStudent.getSelectedIndex()).RemoveGrade(index);
                                    grade.remove(listOfGrades.getSelectedIndex());
                                    gradeList.remove(index);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Выберите оценку для удаления", "Error!", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                        //////////////////////////////////////////////////////////////////////////////////
                        next2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e113) {
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
                                    Saving.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e113) {
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
                                            CancelSave.addActionListener(e11312 -> SavingName.dispose());
                                            CommitSave.addActionListener(e11313 -> {
                                                Group saveGroup = new Group(Integer.parseInt(grouptext.getText()));
                                                for (Student student : studentList) saveGroup.add(student);
                                                Discipline SavingDiscipline = new Discipline(nametext.getText(), surnametext.getText(), disciplinetext.getText(), saveGroup);
                                                For_Save object = new For_Save(Forsave.getText(), SavingDiscipline);
                                                List_for_Save.add(object);
                                                try {
                                                    ObjectSave.save(List_for_Save);
                                                } catch (FileNotFoundException d) {
                                                    JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                                } catch (IOException d) {
                                                    JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                                }
                                                SavingName.dispose();
                                            });

                                        }
                                    });
                                    excel.addActionListener(e11314 -> {
                                        JFrame ExcSavingName = new JFrame("Введите название");
                                        ExcSavingName.setSize(300, 300);
                                        ExcSavingName.setVisible(true);
                                        JLabel ExcSaveName = new JLabel("Введите название");
                                        JPanel ExcForName = new JPanel();
                                        JPanel ExcForButtonSave = new JPanel();
                                        JTextField ExcForsave = new JTextField();
                                        JButton ExcCommitSave = new JButton("Готово");
                                        JButton ExcCancelSave = new JButton("Отмена");
                                        ExcForButtonSave.setLayout(new BoxLayout(ExcForButtonSave, BoxLayout.X_AXIS));
                                        ExcForName.setLayout(new BoxLayout(ExcForName, BoxLayout.Y_AXIS));
                                        ExcForButtonSave.add(Box.createHorizontalStrut(30));
                                        ExcForButtonSave.add(ExcCommitSave);
                                        ExcForButtonSave.add(Box.createHorizontalStrut(20));
                                        ExcForButtonSave.add(ExcCancelSave);
                                        ExcSaveName.setFont(new Font("Verdana", Font.PLAIN, 20));
                                        ExcSavingName.add(ExcForName, BorderLayout.CENTER);
                                        ExcForName.add(Box.createVerticalStrut(50));
                                        ExcForName.add(ExcSaveName);
                                        ExcForName.add(Box.createVerticalStrut(30));
                                        ExcForName.add(ExcForsave);
                                        ExcForName.add(Box.createVerticalStrut(100));
                                        ExcSavingName.add(ExcForButtonSave, BorderLayout.SOUTH);
                                        ExcSavingName.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                        ExcCancelSave.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e11314) {
                                                ExcSavingName.dispose();
                                            }
                                        });
                                        ExcCommitSave.addActionListener(e112 -> {
                                            try {
                                                ExportExcel(table, ExcForsave.getText());
                                            } catch (IOException d) {
                                                throw new RuntimeException("Exception");
                                            }
                                            ExcSavingName.dispose();
                                        });

                                    });
                                    Back.addActionListener(e11315 -> {
                                        newGroup.setVisible(true);
                                        save.setVisible(false);
                                    });

                                }
                            }

                        });
                    });
                });
            }
        });
        open.addActionListener(e -> {
            try {
                List_for_Save = ObjectSave.load();
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
            JButton OpenList = new JButton("Открыть");
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
            ForButtons.add(OpenList);
            ForButtons.add(Box.createVerticalStrut(20));
            ForButtons.add(DeleteSave);
            ForButtons.add(Box.createVerticalStrut(20));
            ForButtons.add(Back);
            DefaultListModel<For_Save> titles = new DefaultListModel<For_Save>();
            JList<For_Save> listOfSaves = new JList<For_Save>(titles);
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
            DeleteSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int SelectedIndex = listOfSaves.getSelectedIndex();
                    if (SelectedIndex != -1) {
                        List_for_Save.remove(SelectedIndex);
                        titles.remove(SelectedIndex);
                        try {
                            ObjectSave.save(List_for_Save);
                        } catch (FileNotFoundException d) {
                            throw new RuntimeException("File not found", d);
                        } catch (IOException q) {
                            throw new RuntimeException("Failed", q);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Выберите сохранение", "Error!", JOptionPane.ERROR_MESSAGE);

                    }
                }

            });
            ///////////////////////////////////////////// PART 2
            OpenList.addActionListener(e12 -> {
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
                JButton next2 = new JButton("Далее");
                JButton cancel2 = new JButton("Назад");
                newstudent.add(add);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(delete);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(grades);
                newstudent.add(Box.createVerticalStrut(100));
                newstudent.add(next2, BorderLayout.SOUTH);
                newstudent.add(Box.createVerticalStrut(10));
                newstudent.add(cancel2, BorderLayout.SOUTH);
/////////////////////////////////////////////////////////////////////////////////////
                List<Student> studentList = new ArrayList<>(List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getStudents());
                DefaultListModel<Student> students = new DefaultListModel<>();
                for (int i = 0; i < studentList.size(); i++)
                    students.addElement(studentList.get(i));
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
                cancel2.addActionListener(e1 -> {
                    newGroup.setVisible(false);
                    OpenFrame.setVisible(true);
                });
                add.addActionListener(e1 -> {
                    JTextField name11 = new JTextField();
                    JTextField surname1 = new JTextField();
                    JButton ok = new JButton("Добавить");
                    JLabel nameOfStudent = new JLabel("Введите имя студента");
                    nameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JLabel surnameOfStudent = new JLabel("Введите фамилию студента");
                    surnameOfStudent.setFont(new Font("Verdana", Font.PLAIN, 18));
                    JButton cancel3 = new JButton("Отмена");
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
                    addpanel.add(name11);
                    addpanel.add(Box.createVerticalStrut(50));
                    addpanel.add(surnameOfStudent);
                    addpanel.add(Box.createVerticalStrut(10));
                    addpanel.add(surname1);
                    addpanel.add(Box.createVerticalStrut(50));
                    buttons1.add(Box.createHorizontalStrut(50));
                    buttons1.add(ok);
                    buttons1.add(Box.createHorizontalStrut(10));
                    buttons1.add(cancel3);
                    ok.addActionListener(e11 -> {
                        Student student = new Student((List_for_Save.get(SelectedIndex).getDiscipline().getGroups().getGroup()), name11.getText(), surname1.getText());
                        students.addElement(student);
                        studentList.add(student);
                        newstudent1.dispose();
                        newstudent1.setVisible(false);
                    });
                    cancel3.addActionListener(e115 -> newstudent1.dispose());

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
                    JFrame newgrade = new JFrame("Работа с оценками");
                    newgrade.setSize(500, 500);
                    List<Grade> gradeList = new ArrayList<Grade>(studentList.get(SelectedIndex).getGrades());
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
                    newgrade.setVisible(true);
                    newgrade.add(forlabel, BorderLayout.NORTH);
                    newgrade.add(listpanel, BorderLayout.CENTER);
                    newgrade.add(buttonspanel2, BorderLayout.EAST);
                    forlabel.add(gradetext, BorderLayout.CENTER);
                    listpanel.add(jScrollPane2, BorderLayout.CENTER);
                    buttonspanel2.add(addGrade, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(20));
                    buttonspanel2.add(deleteGrade, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(20));
                    buttonspanel2.add(ready, BorderLayout.EAST);
                    buttonspanel2.add(Box.createVerticalStrut(100));
                    int index = listOfStudent.getSelectedIndex();
                    ready.addActionListener(e116 -> newgrade.dispose());
                    addGrade.addActionListener(e117 -> {

                        JFrame updateGrade = new JFrame("Новая оценка");
                        updateGrade.setSize(300, 300);
                        updateGrade.setVisible(true);
                        JPanel forText = new JPanel();
                        JPanel forButtons = new JPanel();
                        forButtons.setLayout(new BoxLayout(forButtons, BoxLayout.X_AXIS));
                        JLabel describtion = new JLabel("Введите оценку");
                        JButton addGrade1 = new JButton("Добавить");
                        JButton cancelGrade = new JButton("Отмена");

                        JPanel fake = new JPanel();
                        JPanel fake2 = new JPanel();
                        updateGrade.add(fake, BorderLayout.NORTH);
                        JComboBox typeOfGrade = new JComboBox();
                        typeOfGrade.setModel(new DefaultComboBoxModel(Grade.type.values()));
                        describtion.setFont(new Font("Verdana", Font.PLAIN, 20));
                        JTextField GradesField = new JTextField();
                        forText.setLayout(new BoxLayout(forText, BoxLayout.Y_AXIS));
                        updateGrade.add(forText, BorderLayout.CENTER);
                        updateGrade.add(forButtons, BorderLayout.SOUTH);
                        updateGrade.add(fake2, BorderLayout.EAST);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(describtion);
                        forText.add(Box.createHorizontalStrut(60));
                        forText.add(Box.createVerticalStrut(20));
                        forText.add(GradesField);
                        forText.add(Box.createVerticalStrut(50));
                        forText.add(typeOfGrade);
                        forText.add(Box.createVerticalStrut(50));
                        forButtons.add(Box.createHorizontalStrut(30));
                        forButtons.add(addGrade1);
                        forButtons.add(Box.createHorizontalStrut(20));
                        forButtons.add(cancelGrade);

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

                            updateGrade.dispose();
                            updateGrade.setVisible(false);
                            listOfStudent.updateUI();
                        });
                        cancelGrade.addActionListener(e1171 -> updateGrade.dispose());
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
                    next2.addActionListener(e119 -> {
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
                                        ObjectSave.save(List_for_Save);
                                    } catch (FileNotFoundException d) {
                                        JOptionPane.showMessageDialog(null, "File not found!", "Error!", JOptionPane.ERROR_MESSAGE);
                                    } catch (IOException d) {
                                        JOptionPane.showMessageDialog(null, "Такой файл существует!", "Error!", JOptionPane.ERROR_MESSAGE);

                                    }
                                    SavingName.dispose();
                                });

                            });
                            excel.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e119) {
                                    JFrame ExcSavingName = new JFrame("Введите название");
                                    ExcSavingName.setSize(300, 300);
                                    ExcSavingName.setVisible(true);
                                    JLabel ExcSaveName = new JLabel("Введите название");
                                    JPanel ExcForName = new JPanel();
                                    JPanel ExcForButtonSave = new JPanel();
                                    JTextField ExcForsave = new JTextField();
                                    JButton ExcCommitSave = new JButton("Готово");
                                    JButton ExcCancelSave = new JButton("Отмена");
                                    ExcForButtonSave.setLayout(new BoxLayout(ExcForButtonSave, BoxLayout.X_AXIS));
                                    ExcForName.setLayout(new BoxLayout(ExcForName, BoxLayout.Y_AXIS));
                                    ExcForButtonSave.add(Box.createHorizontalStrut(30));
                                    ExcForButtonSave.add(ExcCommitSave);
                                    ExcForButtonSave.add(Box.createHorizontalStrut(20));
                                    ExcForButtonSave.add(ExcCancelSave);
                                    ExcSaveName.setFont(new Font("Verdana", Font.PLAIN, 20));
                                    ExcSavingName.add(ExcForName, BorderLayout.CENTER);
                                    ExcForName.add(Box.createVerticalStrut(50));
                                    ExcForName.add(ExcSaveName);
                                    ExcForName.add(Box.createVerticalStrut(30));
                                    ExcForName.add(ExcForsave);
                                    ExcForName.add(Box.createVerticalStrut(100));
                                    ExcSavingName.add(ExcForButtonSave, BorderLayout.SOUTH);
                                    ExcSavingName.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                    ExcCancelSave.addActionListener(e11913 -> ExcSavingName.dispose());
                                    ExcCommitSave.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e119) {
                                            try {
                                                ExportExcel(table, ExcForsave.getText());
                                            } catch (IOException d) {
                                                throw new RuntimeException("Exception");
                                            }
                                            ExcSavingName.dispose();
                                        }
                                    });

                                }
                            });
                            Back1.addActionListener(e11914 -> {
                                newGroup.setVisible(true);
                                save.setVisible(false);
                            });

                        }
                    });
                });
            });
        });
    }

    private void ExportExcel(JTable table, String name) throws IOException {
        File file = new File("C:\\Users\\Alex\\" + name + ".xls");
        TableModel model = table.getModel();
        try (
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
        ) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i) + "\t");
            }
            out.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    out.write(model.getValueAt(i, j) + "\t");
                }
                out.write("\n");
            }

        }
    }
}

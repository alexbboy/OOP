import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame implements Serializable {
    private String filename;
    private DefaultListModel<Shape> model = new DefaultListModel<Shape>();
    private List<Shape> list = new ArrayList<>();
    private JList<Shape> jList = new JList<Shape>();

    private JFrame newTriangle = new JFrame("New triangle");
    private JFrame newRectangle = new JFrame("New rectangle");
    private JFrame newSquare = new JFrame("New square");
    private JFrame newCircle = new JFrame("New circle");
    private JButton remove = new JButton("Remove");
    private JButton createTriangle = new JButton("Create Triangle");
    private JButton createRectangle = new JButton("Create Rectangle");
    private JButton createSquare = new JButton("Create  Square");
    private JButton createCircle = new JButton("Create Circle");
    private JButton move_up = new JButton("Move up");
    private JButton move_down = new JButton("Move down");


    public Form(String filename) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        this.filename = filename;
        setSize(600, 600);
        setTitle("Shapes app");
        File file1 = new File(filename);
        if (!file1.exists())
            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Exception,", e);
            }

        Serialize serialize = new Serialize(filename);
        int g = 0;
        if (file1.length() == 0)
            g++;
        else {
            try {
                list = serialize.load();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found", e);
            } catch (ClassNotFoundException d) {
                throw new RuntimeException("Exception", d);
            } catch (IOException q) {
                throw new RuntimeException("Failed", q);
            }
        }
        jList = new JList<Shape>(model);
        for (int i = 0; i < list.size(); i++) {
            model.addElement(list.get(i));
        }
        setContentPane(panel);
        JPanel listpanel = new JPanel();
        panel.add(listpanel, BorderLayout.CENTER);
        JScrollPane jScrollPane = new JScrollPane(jList) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 350);
            }
        };
        jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);

        JLabel label = new JLabel("Shapes", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(label, BorderLayout.NORTH);
        listpanel.add(jScrollPane);
        JPanel buttons = new JPanel();
        panel.add(buttons, BorderLayout.EAST);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(remove, BorderLayout.CENTER);
        remove.setBackground(Color.RED);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(createTriangle, BorderLayout.CENTER);
        createTriangle.setBackground(Color.green);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(createRectangle, BorderLayout.CENTER);
        createRectangle.setBackground(Color.GREEN);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(createCircle, BorderLayout.CENTER);
        createCircle.setBackground(Color.GREEN);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(createSquare, BorderLayout.CENTER);
        createSquare.setBackground(Color.GREEN);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(move_up, BorderLayout.CENTER);
        move_up.setBackground(Color.lightGray);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(move_down, BorderLayout.CENTER);
        move_down.setBackground(Color.lightGray);
        buttons.add(Box.createVerticalStrut(10));
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setSize(300, 500);
        panel.add(ButtonPanel, BorderLayout.SOUTH);
        label.setVisible(true);
        label.setForeground(Color.BLUE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    serialize.save(list);
                } catch (FileNotFoundException d) {
                    throw new RuntimeException("File not found", d);
                } catch (IOException d) {
                    throw new RuntimeException("Failed", d);
                }
                System.exit(0);
            }
        });


        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedIndex() != -1) {

                    int i = jList.getSelectedIndex();

                    model.remove(i);
                    list.remove(i);
                }
            }
        });

        createTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField1 = new JTextField("Side a");
                JTextField jTextField2 = new JTextField("Side b");
                JTextField jTextField3 = new JTextField("Side c");
                JButton jButton = new JButton("Ok");
                JButton jButton1 = new JButton("Cancel");
                newTriangle.setSize(300, 300);
                newTriangle.setLayout(null);
                newTriangle.setVisible(true);
                newTriangle.setBackground(Color.lightGray);
                jTextField1.setBounds(100, 50, 100, 30);
                jTextField1.setBackground(Color.white);
                jTextField2.setBounds(100, 100, 100, 30);
                jTextField2.setBackground(Color.white);
                jTextField3.setBounds(100, 150, 100, 30);
                jTextField3.setBackground(Color.white);
                jButton.setBounds(20, 200, 100, 30);
                jButton1.setBounds(170, 200, 100, 30);
                newTriangle.add(jButton);
                newTriangle.add(jButton1);
                newTriangle.add(jTextField1);
                newTriangle.add(jTextField2);
                newTriangle.add(jTextField3);
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newTriangle.dispose();
                    }
                });
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String string = jTextField1.getText();
                        if ((jTextField1.getText().equals("")) || (jTextField1.getText().equals("Side a"))) {
                            JOptionPane.showMessageDialog(null, "Enter side a", "Error!", JOptionPane.ERROR_MESSAGE);

                        }


                        String string2 = jTextField2.getText();
                        if ((jTextField2.getText().equals("")) || (jTextField2.getText().equals("Side b"))) {
                            JOptionPane.showMessageDialog(null, "Enter side b", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        if ((jTextField3.getText().equals("")) || (jTextField3.getText().equals("Side c"))) {
                            JOptionPane.showMessageDialog(null, "Enter side c", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        String string3 = jTextField3.getText();
                        try {
                            Double.parseDouble(string);
                            Double.parseDouble(string2);
                            Double.parseDouble(string3);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        double x1 = Double.parseDouble(string);
                        double x2 = Double.parseDouble(string2);
                        double x3 = Double.parseDouble(string3);
                        if (((x1 <= 0) || (x2 <= 0) || (x3 <= 0)) || ((x1 + x2 <= x3) || (x1 + x3 <= x2) || (x2 + x3 <= x1)))
                            JOptionPane.showMessageDialog(null, "Whong sides", "Error!", JOptionPane.ERROR_MESSAGE);

                        list.add(new Triangle(x1, x2, x3));
                        model.addElement(new Triangle(x1, x2, x3));
                        newTriangle.dispose();
                    }
                });
            }
        });
        createRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField1 = new JTextField("Width");
                JTextField jTextField2 = new JTextField("Height");
                JButton jButton = new JButton("Ok");
                JButton jButton1 = new JButton("Cancel");
                newRectangle.setSize(300, 300);
                newRectangle.setLayout(null);
                newRectangle.setVisible(true);
                newRectangle.setBackground(Color.lightGray);
                jTextField1.setBounds(100, 50, 100, 30);
                jTextField1.setBackground(Color.white);
                jTextField2.setBounds(100, 100, 100, 30);
                jTextField2.setBackground(Color.white);
                jButton.setBounds(20, 150, 100, 30);
                jButton1.setBounds(170, 150, 100, 30);
                newRectangle.add(jButton);
                newRectangle.add(jButton1);
                newRectangle.add(jTextField1);
                newRectangle.add(jTextField2);

                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newRectangle.dispose();
                    }
                });
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String string = jTextField1.getText();
                        if ((jTextField1.getText().equals("")) || (jTextField1.getText().equals("Width"))) {
                            JOptionPane.showMessageDialog(null, "Enter width", "Error!", JOptionPane.ERROR_MESSAGE);

                        }

                        String string2 = jTextField2.getText();
                        if ((jTextField2.getText().equals("")) || (jTextField2.getText().equals("Height"))) {
                            JOptionPane.showMessageDialog(null, "Enter height", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        try {
                            Double.parseDouble(string);
                            Double.parseDouble(string2);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        double x1 = Double.parseDouble(string);
                        double x2 = Double.parseDouble(string2);
                        if ((x1 <= 0) || (x2 <= 0))
                            JOptionPane.showMessageDialog(null, "Wrong sides", "Error!", JOptionPane.ERROR_MESSAGE);

                        list.add(new Rectangle(x1, x2));
                        model.addElement(new Rectangle(x1, x2));
                        newRectangle.dispose();
                    }
                });
            }
        });
        createSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField1 = new JTextField("Side");
                JButton jButton = new JButton("Ok");
                JButton jButton1 = new JButton("Cancel");
                newSquare.setSize(300, 300);
                newSquare.setLayout(null);
                newSquare.setVisible(true);
                newSquare.setBackground(Color.lightGray);
                jTextField1.setBounds(100, 50, 100, 30);
                jTextField1.setBackground(Color.white);
                jButton.setBounds(20, 150, 100, 30);
                jButton1.setBounds(170, 150, 100, 30);
                newSquare.add(jButton);
                newSquare.add(jButton1);
                newSquare.add(jTextField1);

                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newSquare.dispose();
                    }
                });
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String string = jTextField1.getText();
                        if ((jTextField1.getText().equals("")) || (jTextField1.getText().equals("Side"))) {
                            JOptionPane.showMessageDialog(null, "Side", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        try {
                            Double.parseDouble(string);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        double x1 = Double.parseDouble(string);

                        if (x1 <= 0)
                            JOptionPane.showMessageDialog(null, "Wrong side", "Error!", JOptionPane.ERROR_MESSAGE);

                        list.add(new Square(x1));
                        model.addElement(new Square(x1));
                        newSquare.dispose();
                    }
                });
            }
        });
        createCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField1 = new JTextField("Radius");
                JButton jButton = new JButton("Ok");
                JButton jButton1 = new JButton("Cancel");
                newCircle.setSize(300, 300);
                newCircle.setLayout(null);
                newCircle.setVisible(true);
                newCircle.setBackground(Color.lightGray);
                jTextField1.setBounds(100, 50, 100, 30);
                jTextField1.setBackground(Color.white);
                jButton.setBounds(20, 150, 100, 30);
                jButton1.setBounds(170, 150, 100, 30);
                newCircle.add(jButton);
                newCircle.add(jButton1);
                newCircle.add(jTextField1);

                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newCircle.dispose();
                    }
                });
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String string = jTextField1.getText();
                        if ((jTextField1.getText().equals("")) || (jTextField1.getText().equals("Radius"))) {
                            JOptionPane.showMessageDialog(null, "Enter radius", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        try {
                            Double.parseDouble(string);
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Введите число, а не букву", "Error!", JOptionPane.ERROR_MESSAGE);

                        }
                        double x1 = Double.parseDouble(string);
                        if (x1 <= 0)
                            JOptionPane.showMessageDialog(null, "Wrong side", "Error!", JOptionPane.ERROR_MESSAGE);


                        list.add(new Round(x1));
                        model.addElement(new Round(x1));
                        newCircle.dispose();
                    }
                });
            }
        });

        move_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedIndex() != -1) {
                    int j = jList.getSelectedIndex();
                    int i = j - 1;
                    Shape save = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, save);
                    model.set(i, model.get(j));
                    model.set(j, save);
                }
            }
        });
        move_down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedIndex() != -1) {
                    int j = jList.getSelectedIndex();
                    int i = j + 1;
                    Shape save = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, save);
                    model.set(i, model.get(j));
                    model.set(j, save);

                }
            }
        });


    }


    private String[] toString(List<Shape> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).toString();
        }
        return array;
    }
}

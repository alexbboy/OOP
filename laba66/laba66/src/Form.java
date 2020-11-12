import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame implements Serializable {
    private String filename;
    private DefaultListModel<Shape> model=new DefaultListModel();
    private List<Shape> list=new ArrayList<>();
   private  JList jList=new JList();

   private JFrame frame=new JFrame("Shapes App");
    private JFrame frame2=new JFrame("New triangle");
    private JFrame frame3=new JFrame("New rectangle");
    private JFrame frame4=new JFrame("New square");
    private JFrame frame5=new JFrame("New circle");
   private JButton b=new JButton("Remove");
    private JButton b2=new JButton("Create Triangle");
    private JButton b3=new JButton("Create Rectangle");
    private JButton b4=new JButton("Create  Square");
    private JButton b5=new JButton("Create Circle");
    private JButton b6=new JButton("Move up");
    private JButton b7=new JButton("Move down");
   private JPanel panel=new JPanel();
    public Form(String filename){
        this.filename=filename;
    }



  public void Run(){

       Serialize serialize=new Serialize(filename);
      list=serialize.load();

      jList=new JList(model);
      for(int i=0;i<list.size();i++){
          model.addElement(list.get(i));
      }

      frame.add(panel);
      panel.setBackground(frame.getBackground());
      JScrollPane jScrollPane=new JScrollPane(jList){
          @Override
          public Dimension getPreferredSize() {
              return new Dimension(300,350);
          }
      };
      jScrollPane.setAlignmentY(JScrollPane.TOP_ALIGNMENT);

      JLabel label=new JLabel("Shapes",JLabel.CENTER);
      panel.add(label);
panel.add(jScrollPane);


      panel.setBounds(0,50,500,400);
      b.setBounds(600,50,150,30);
      b.setBackground(Color.RED);
      b2.setBounds(600,90,150,30);
      b2.setBackground(Color.green);
      b3.setBounds(600,130,150,30);
      b3.setBackground(Color.GREEN);
      b4.setBounds(600,170,150,30);
      b4.setBackground(Color.GREEN);
      b5.setBounds(600,210,150,30);
      b5.setBackground(Color.GREEN);
      b6.setBounds(570,250,85,30);
      b6.setBackground(Color.lightGray);
      b7.setBounds(670,250,105,30);
      b7.setBackground(Color.lightGray);

      label.setVisible(true);

label.setBounds(0,100,100,50);
label.setForeground(Color.BLUE);
      frame.add(b);
      frame.add(b2);
      frame.add(b3);
      frame.add(b4);
      frame.add(b5);
      frame.add(b6);
      frame.add(b7);

      frame.setSize(800,500);
      frame.setLayout(null);
      frame.setVisible(true);


b.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int i=jList.getSelectedIndex();

model.remove(i);
list.remove(i);
serialize.save(list);
        list=serialize.load();
    }
});

b2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       JTextField jTextField1=new JTextField("Side a");
        JTextField jTextField2=new JTextField("Side b");
        JTextField jTextField3=new JTextField("Side c");
        JButton jButton=new JButton("Ok");
        JButton jButton1=new JButton("Cancel");
        frame2.setSize(300,300);
        frame2.setLayout(null);
        frame2.setVisible(true);
        frame2.setBackground(Color.lightGray);
        jTextField1.setBounds(100,50,100,30);
        jTextField1.setBackground(Color.white);
        jTextField2.setBounds(100,100,100,30);
        jTextField2.setBackground(Color.white);
        jTextField3.setBounds(100,150,100,30);
        jTextField3.setBackground(Color.white);
        jButton.setBounds(20,200,100,30);
        jButton1.setBounds(170,200,100,30);
        frame2.add(jButton);
        frame2.add(jButton1);
        frame2.add(jTextField1);
        frame2.add(jTextField2);
        frame2.add(jTextField3);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
 }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=jTextField1.getText();
                if((jTextField1.getText().equals(""))||(jTextField1.getText().equals("Side a"))){
                    JOptionPane.showMessageDialog(null,"Enter side a","Error!",JOptionPane.ERROR_MESSAGE);

                }
                double x1=Double.parseDouble(string);
                String string2=jTextField2.getText();
                if((jTextField2.getText().equals(""))||(jTextField2.getText().equals("Side b"))){
                    JOptionPane.showMessageDialog(null,"Enter side b","Error!",JOptionPane.ERROR_MESSAGE);

                }
                if((jTextField3.getText().equals(""))||(jTextField3.getText().equals("Side c"))){
                    JOptionPane.showMessageDialog(null,"Enter side c","Error!",JOptionPane.ERROR_MESSAGE);

                }
                double x2=Double.parseDouble(string2);
                String string3=jTextField3.getText();
                double x3=Double.parseDouble(string3);
                list.add(new Triangle(x1,x2,x3));
                model.addElement(new Triangle(x1,x2,x3));
                serialize.save(list);
                frame2.dispose();
            }
        });
    }
});
b3.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField jTextField1=new JTextField("Width");
        JTextField jTextField2=new JTextField("Height");
        JButton jButton=new JButton("Ok");
        JButton jButton1=new JButton("Cancel");
        frame3.setSize(300,300);
        frame3.setLayout(null);
        frame3.setVisible(true);
        frame3.setBackground(Color.lightGray);
        jTextField1.setBounds(100,50,100,30);
        jTextField1.setBackground(Color.white);
        jTextField2.setBounds(100,100,100,30);
        jTextField2.setBackground(Color.white);
        jButton.setBounds(20,150,100,30);
        jButton1.setBounds(170,150,100,30);
        frame3.add(jButton);
        frame3.add(jButton1);
        frame3.add(jTextField1);
        frame3.add(jTextField2);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=jTextField1.getText();
                if((jTextField1.getText().equals(""))||(jTextField1.getText().equals("Width"))){
                    JOptionPane.showMessageDialog(null,"Enter width","Error!",JOptionPane.ERROR_MESSAGE);

                }
                double x1=Double.parseDouble(string);
                String string2=jTextField2.getText();
                if((jTextField2.getText().equals(""))||(jTextField2.getText().equals("Height"))){
                    JOptionPane.showMessageDialog(null,"Enter height","Error!",JOptionPane.ERROR_MESSAGE);

                }

                double x2=Double.parseDouble(string2);

                list.add(new Rectangle(x1,x2));
                model.addElement(new Rectangle(x1,x2));
                serialize.save(list);
                frame3.dispose();
            }
        });
    }
});
b4.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField jTextField1=new JTextField("Side");
        JButton jButton=new JButton("Ok");
        JButton jButton1=new JButton("Cancel");
        frame4.setSize(300,300);
        frame4.setLayout(null);
        frame4.setVisible(true);
        frame4.setBackground(Color.lightGray);
        jTextField1.setBounds(100,50,100,30);
        jTextField1.setBackground(Color.white);
        jButton.setBounds(20,150,100,30);
        jButton1.setBounds(170,150,100,30);
        frame4.add(jButton);
        frame4.add(jButton1);
        frame4.add(jTextField1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.dispose();
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=jTextField1.getText();
                if((jTextField1.getText().equals(""))||(jTextField1.getText().equals("Side"))){
                    JOptionPane.showMessageDialog(null,"Side","Error!",JOptionPane.ERROR_MESSAGE);

                }
                double x1=Double.parseDouble(string);


                list.add(new Square(x1));
                model.addElement(new Square(x1));
                serialize.save(list);
                frame4.dispose();
            }
        });
    }
});
b5.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField jTextField1=new JTextField("Radius");
        JButton jButton=new JButton("Ok");
        JButton jButton1=new JButton("Cancel");
        frame5.setSize(300,300);
        frame5.setLayout(null);
        frame5.setVisible(true);
        frame5.setBackground(Color.lightGray);
        jTextField1.setBounds(100,50,100,30);
        jTextField1.setBackground(Color.white);
        jButton.setBounds(20,150,100,30);
        jButton1.setBounds(170,150,100,30);
        frame5.add(jButton);
        frame5.add(jButton1);
        frame5.add(jTextField1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.dispose();
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string=jTextField1.getText();
                if((jTextField1.getText().equals(""))||(jTextField1.getText().equals("Radius"))){
                    JOptionPane.showMessageDialog(null,"Enter radius","Error!",JOptionPane.ERROR_MESSAGE);

                }
                double x1=Double.parseDouble(string);


                list.add(new Round(x1));
                model.addElement(new Round(x1));
                serialize.save(list);
                frame5.dispose();
            }
        });
    }
});
frame.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        System.exit(0);
        serialize.save(list);
list=serialize.load();
    }
});
b6.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int j=jList.getSelectedIndex();
        int i=j-1;
        Shape save=list.get(i);
        list.set(i,list.get(j));
        list.set(j,save);
        model.set(i,model.get(j));
        model.set(j,save);
        serialize.save(list);
        list=serialize.load();
    }
});
      b7.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int j=jList.getSelectedIndex();
              int i=j+1;
              Shape save=list.get(i);
              list.set(i,list.get(j));
              list.set(j,save);
              model.set(i,model.get(j));
              model.set(j,save);
              serialize.save(list);
              list=serialize.load();
          }
      });
  }


  private String[] toString(List<Shape> list){
      String[] array=new String[list.size()];
      for(int i=0;i<list.size();i++){
          array[i]=list.get(i).toString();
      }
      return array;
  }
}

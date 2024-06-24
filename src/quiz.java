import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Quiz extends JFrame implements ActionListener
{
    JLabel l, nameLabel, mobileLabel, emailLabel, timerLabel, optionLabels[];
    JTextField nameField, mobileField, emailField;
    JRadioButton jb[]=new JRadioButton[4];
    JButton b1,b2, submitButton, easyButton, moderateButton, hardButton;
    ButtonGroup bg;
    int count=0,current=0,x=1,y=1,now=0;
    int m[]=new int[10];
    String name;
    Timer timer;
    int time = 60; // 1 minute in seconds
    private boolean isBlinking = false;
    String[][][] questions = {
            {
                    {"Easy Q1: What is Java?", "A) A programming language", "B) A fruit", "C) A country", "D) A car brand"},
                    {"Easy Q2: What is a class?", "A) A blueprint for objects", "B) A group of students", "C) A type of food", "D) A kind of car"},
                    {"Easy Q3: What is an object?", "A) An instance of a class", "B) A piece of furniture", "C) A type of animal", "D) A type of computer"}
            },
            {
                    {"Moderate Q1: What is inheritance in OOP?", "A) The mechanism in Java by which one class can inherit the fields and methods of another class", "B) Passing on genetic traits in biology", "C) A type of mathematical operation", "D) A kind of food"},
                    {"Moderate Q2: What is polymorphism in OOP?", "A) The ability of a method to do different things based on the object that it is acting upon", "B) The study of multiple forms in nature", "C) A type of mathematical equation", "D) A type of programming language"},
                    {"Moderate Q3: What is encapsulation in OOP?", "A) The concept of wrapping data (variables) and code together as a single unit", "B) A type of insect", "C) A type of clothing", "D) A type of computer component"}
            },
            {
                    {"Hard Q1: How does abstraction work in OOP?", "A) It focuses on the idea of hiding the implementation details and showing only the functionality", "B) It involves creating physical objects in the real world", "C) It is a type of mathematical concept", "D) It is a type of art form"},
                    {"Hard Q2: How does interface differ from abstract class in OOP?", "A) An interface can only declare method signatures, while an abstract class can have method implementations", "B) They are the same thing", "C) An interface is used for user interfaces in software", "D) An abstract class can be instantiated directly"},
                    {"Hard Q3: How does multiple inheritance work in OOP?", "A) Java does not support multiple inheritance of classes", "B) It allows a class to inherit from multiple superclasses", "C) It is a type of inheritance found in biological organisms", "D) It is a feature specific to the C++ programming language"}
            }
    };
    int difficulty = 0; // 0: Easy, 1: Moderate, 2: Hard

    Quiz(String s)
    {
        super(s);

        JLabel sectionLabel = new JLabel("Exam Section NIRMA");
        sectionLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 12)); // Set font to bold and italic
        sectionLabel.setHorizontalAlignment(JLabel.CENTER);
        sectionLabel.setBounds(0, 300, 550, 20); // Adjust position and size
        add(sectionLabel);


        try {
            // Add Nirma University logo from a local file
            Image nirmaLogo = ImageIO.read(new File("src/nirma_logo.jpeg")); // Adjust the file name if needed
            ImageIcon nirmaIcon = new ImageIcon(nirmaLogo);
            JLabel logoLabel = new JLabel(nirmaIcon);
            logoLabel.setBounds(420, 10, nirmaIcon.getIconWidth(), nirmaIcon.getIconHeight());
            add(logoLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setVisible(true);



        nameLabel = new JLabel("Name:");
        mobileLabel = new JLabel("Roll Number:");
        emailLabel = new JLabel("Email Address:");
        timerLabel = new JLabel("Time: 1:00");

        nameField = new JTextField(20);
        mobileField = new JTextField(20);
        emailField = new JTextField(20);

        submitButton = new JButton("Start Test");
        submitButton.addActionListener(this);

        setLayout(new FlowLayout());

        add(nameLabel);
        add(nameField);
        add(mobileLabel);
        add(mobileField);
        add(emailLabel);
        add(emailField);
        add(submitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitButton)
        {
            name = nameField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();

            System.out.println("Name: " + name);
            System.out.println("Roll Number: " + mobile);
            System.out.println("Email ID: " + email);

            remove(nameLabel);
            remove(nameField);
            remove(mobileLabel);
            remove(mobileField);
            remove(emailLabel);
            remove(emailField);
            remove(submitButton);

            easyButton = new JButton("Easy");
            moderateButton = new JButton("Moderate");
            hardButton = new JButton("Hard");
            easyButton.addActionListener(this);
            moderateButton.addActionListener(this);
            hardButton.addActionListener(this);

            add(easyButton);
            add(moderateButton);
            add(hardButton);

            revalidate();
            repaint();
        }
        else if(e.getSource() == easyButton || e.getSource() == moderateButton || e.getSource() == hardButton)
        {
            if(e.getSource() == easyButton)
                difficulty = 0;
            else if(e.getSource() == moderateButton)
                difficulty = 1;
            else if(e.getSource() == hardButton)
                difficulty = 2;

            remove(easyButton);
            remove(moderateButton);
            remove(hardButton);

            l=new JLabel();
            add(l);
            bg=new ButtonGroup();
            optionLabels = new JLabel[4];
            for(int i=0;i<4;i++)
            {
                jb[i]=new JRadioButton();
                add(jb[i]);
                bg.add(jb[i]);
                optionLabels[i] = new JLabel();
                add(optionLabels[i]);
            }
            b1=new JButton("Next");
            b2=new JButton("Bookmark");
            b1.addActionListener(this);
            b2.addActionListener(this);
            add(b1);
            add(b2);
            set();
            l.setBounds(30,40,450,20);

            for(int i=0; i<4; i++) {
                jb[i].setBounds(50,80+i*30,20,20);
                optionLabels[i].setBounds(80, 80+i*30, 300, 20);
            }

            b1.setBounds(100,240,100,30);
            b2.setBounds(270,240,100,30);
            timerLabel.setBounds(250,10,100,20);
            add(timerLabel);
            setLayout(null);
            setLocation(250,100);
            setSize(600,350);
            setVisible(true);

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    time--;
                    int minutes = time / 60;
                    int seconds = time % 60;
                    String timerText = "Time: " + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;

                    if(time <= 10) {
                        if(!isBlinking) {
                            timerLabel.setForeground(Color.RED);
                            isBlinking = true;
                        } else {
                            timerLabel.setForeground(Color.BLACK);
                            isBlinking = false;
                        }
                    } else {
                        timerLabel.setForeground(Color.BLACK);
                        isBlinking = false;
                    }

                    timerLabel.setText(timerText);

                    if(time <= 0) {
                        timer.cancel();
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(null, "Time's up!");
                                // End the test
                                if(check())
                                    count=count+1;
                                current++;
                                if(current == 3) {
                                    count--;
                                    JOptionPane.showMessageDialog(null,"Name: "+name+"\ncorrect ans="+count);
                                    System.exit(0);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please complete all questions before submitting.");
                                }
                            }
                        });
                    }
                }
            }, 0, 1000);
        }
        else if(e.getSource()==b1)
        {
            if(check())
                count=count+1;
            current++;
            set();
            if(current==3)
            {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        else if(e.getActionCommand().equals("Bookmark"))
        {
            JButton bk=new JButton("Bookmark"+x);
            bk.setBounds(480,20+30*x,100,30);
            add(bk);
            bk.addActionListener(this);
            m[x]=current;
            x++;
            current++;
            set();
            if(current==3)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        else if(e.getActionCommand().equals("Result")) {
            if(check())
                count=count+1;
            timer.cancel(); // Stop the timer
            int totalQuestions = questions[difficulty].length;
            if (current == totalQuestions) {
                count--;
                JOptionPane.showMessageDialog(this,"Name: "+name+"\ncorrect ans="+count);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Please complete all questions before submitting.");
            }
        }
        else
        {
            for(int i=0,y=1;i<x;i++,y++)
            {
                if(e.getActionCommand().equals("Bookmark"+y))
                {
                    if(check())
                        count=count+1;
                    now=current;
                    current=m[y];
                    set();
                    ((JButton)e.getSource()).setEnabled(false);
                    current=now;
                }
            }
        }
    }
    void set()
    {
        if (current >= questions[difficulty].length) {
            return; // Ensure current is within bounds
        }
        jb[0].setSelected(true);
        l.setText(questions[difficulty][current][0]);
        for(int i=0; i<4; i++) {
            jb[i].setText("");
            optionLabels[i].setText(questions[difficulty][current][i + 1]);
        }
    }
    boolean check()
    {
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (jb[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        return selectedOption == 0; // Assuming correct answer is always Option A
    }

    public static void main(String[] args)
    {
        new Quiz("Online Test");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;


public class UI extends JFrame implements ActionListener {
    JButton button1 = new JButton("Start");
    JButton button3 = new JButton("Reset");
    JButton button2 = new JButton("Stop");
    JLabel panel = new JLabel("0" + ".");
    JLabel panel1 = new JLabel("0");
    JLabel panel2 = new JLabel("0");
    Rectangle rectangle = new Rectangle();
    Task task = new Task();
    Task1 task1 = new Task1();
    Task2 task2 = new Task2();


    UI() {
        setLayout(null);
        setSize(750, 500);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        add(button1);
        add(button3);
        add(panel);
        add(panel1);
        add(panel2);
        add(rectangle);
        add(button2);
        button1.setBackground(Color.green);
        button2.setBackground(Color.RED);
        button1.setBounds(50, 50, 120, 50);
        button2.setBounds(180, 50, 120, 50);
        button3.setBounds(50, 115, 250, 50);

        panel.setBounds(80, 190, 100, 100);
        panel1.setBounds(90, 190, 100, 100);
        panel2.setBounds(97, 190, 100, 100);
        rectangle.setBounds(50, 190, 250, 100);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        switch (b.getText()) {
            case "Start":
                task = new Task();
                task1 = new Task1();
                task2 = new Task2();
                task.start();
                task1.start();
                task2.start();
                break;
            case "Reset":
                panel.setText("0" + ".");
                panel1.setText("0");
                panel2.setText("0");
                task.interrupt();
                task1.interrupt();
                task2.interrupt();
                break;
            case "Stop":
                task.interrupt();
                task1.interrupt();
                task2.interrupt();
                break;

        }
    }

    class Task extends Thread {
        @Override
        public void run() {
            for (int x = 0; ; x++) {
                synchronized (panel) {
                    panel.setText(String.valueOf(x + "."));
                    if (x == 10) {
                        panel.setBounds(72, 190, 100, 100);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    class Task1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; ; i++) {
                synchronized (panel1) {
                    panel1.setText(String.valueOf(i));
                    if (i == 9) {
                        i = 0;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
    class Task2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; ; i++) {
                synchronized (panel2) {
                    panel2.setText(String.valueOf(i));
                    if (i == 9) {
                        i = 0;
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }


    class Rectangle extends JPanel {
        public Rectangle() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
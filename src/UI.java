import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {
    JButton button1 = new JButton("Start");
    JButton button3 = new JButton("Reset");
    JButton button2 = new JButton("Stop");
    JLabel panel = new JLabel("0" + ".");
    JLabel panel1 = new JLabel("0");
    Rectangle rectangle = new Rectangle();
    Task task = new Task();
    Task1 task1 = new Task1();


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
        add(rectangle);
        add(button2);
        button1.setBounds(50, 50, 120, 50);
        button2.setBounds(180, 50, 120, 50);
        button3.setBounds(50, 115, 250, 50);

        panel.setBounds(80, 190, 100, 100);
        panel1.setBounds(90, 190, 100, 100);
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
                task.start();
                task1.start();
                break;
            case "Reset":
                panel.setText("0" + ".");
                panel1.setText("0");
                task.interrupt();
                task1.interrupt();
                break;
            case "Stop":
                task.interrupt();
                task1.interrupt();
                break;
        }
    }

    class Task extends Thread {
        @Override
        public void run() {
            for (int i = 0; ; i++) {
                synchronized (panel) {
                    panel.setText(String.valueOf(i + "."));
                    if (i == 10) {
                        panel.setBounds(75, 190, 100, 100);
                        try {
                            panel.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
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


    class Rectangle extends JPanel {
        public Rectangle() {
            setBackground(Color.GRAY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
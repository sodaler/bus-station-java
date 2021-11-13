import javax.swing.*;
import java.awt.*;

public class FormBus extends JFrame {

    private JButton createBusButton;
    private JButton createDoubleDeckerButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<Integer> choiceShapeDoorButton;
    private JComboBox<Integer> choiceDoorNumberButton;
    private Bus bus;
    private JFrame frame;
    private Draw draw;

    public void direction(JButton button) {
        String temp = button.getName();
        if (bus != null) {
            switch (temp) {
                case "Up":
                    bus.MoveTransport(Direction.Up);
                    break;
                case "Down":
                    bus.MoveTransport(Direction.Down);
                    break;
                case "Left":
                    bus.MoveTransport(Direction.Left);
                    break;
                case "Right":
                    bus.MoveTransport(Direction.Right);
                    break;
            }
        }
        frame.repaint();
    }

    public void initialization() {
        ImageIcon up = new ImageIcon("src/Images/up.jpg");
        ImageIcon down = new ImageIcon("src/Images/down.jpg");
        ImageIcon left = new ImageIcon("src/Images/left.jpg");
        ImageIcon right = new ImageIcon("src/Images/right.jpg");
        Image imgTake = up.getImage();
        Image turnScale = imgTake.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        up = new ImageIcon(turnScale);
        upButton = new JButton(up);
        upButton.setName("Up");
        upButton.setBounds(750, 300, 40, 40);
        upButton.addActionListener(e -> direction(upButton));

        imgTake = down.getImage();
        turnScale = imgTake.getScaledInstance(50, 55, Image.SCALE_SMOOTH);
        down = new ImageIcon(turnScale);
        downButton = new JButton(down);
        downButton.setName("Down");
        downButton.setBounds(750, 350, 40, 40);
        downButton.addActionListener(e -> direction(downButton));

        imgTake = right.getImage();
        turnScale = imgTake.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        right = new ImageIcon(turnScale);
        rightButton = new JButton(right);
        rightButton.setName("Right");
        rightButton.setBounds(800, 350, 40, 40);
        rightButton.addActionListener(e -> direction(rightButton));

        imgTake = left.getImage();
        turnScale = imgTake.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        left = new ImageIcon(turnScale);
        leftButton = new JButton(left);
        leftButton.setName("Left");
        leftButton.setBounds(700, 350, 40, 40);
        leftButton.addActionListener(e -> direction(leftButton));

        createBusButton = new JButton("Автобус");
        createBusButton.setBounds(10, 0, 130, 30);
        createBusButton.addActionListener(e -> {
            bus = new Bus(300 + ((int) (Math.random() * 300)), 800 + ((int) (Math.random() * 2000)), Color.BLACK, 160, 120);
            bus.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setBus(bus);
            frame.repaint();
        });

        createDoubleDeckerButton = new JButton("Двухэтажный автобус");
        createDoubleDeckerButton.setBounds(160, 0, 170, 30);
        createDoubleDeckerButton.addActionListener(e -> {
            bus = new DoubleDecker(300 + ((int) (Math.random() * 300)), 800 + ((int) (Math.random() * 2000)), Color.BLACK, Color.RED,
                    true, true,  choiceDoorNumberButton.getSelectedIndex() + 3, choiceShapeDoorButton.getSelectedIndex() + 3);
            bus.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setBus(bus);
            frame.repaint();
        });

        choiceDoorNumberButton = new JComboBox<Integer>(new Integer[]{0, 1, 2});
        choiceDoorNumberButton.setBounds(40, 40, 70, 30);

        choiceShapeDoorButton = new JComboBox<Integer>(new Integer[]{3, 4, 5});
        choiceShapeDoorButton.setBounds(210, 40, 70, 30);
    }

    public FormBus() {
        draw = new Draw();
        frame = new JFrame("Автобус");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createBusButton);
        frame.getContentPane().add(createDoubleDeckerButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceDoorNumberButton);
        frame.getContentPane().add(choiceShapeDoorButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public static void main(String[] args) {
        FormBus form = new FormBus();
    }
}
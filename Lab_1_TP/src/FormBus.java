import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FormBus extends JFrame{


    private JButton createTrackedButton;
    private JButton createDoubleDeckerButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private  JComboBox<String> listNumberOfDoors;
    private  JComboBox<String> listAdditionalElems;
    private ITransport bus;
    private JFrame frame;
    public Draw draw;


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
        Icon up = new ImageIcon("Images/up.png");
        Icon down = new ImageIcon("Images/down.png");
        Icon left = new ImageIcon("Images/left.png");
        Icon right = new ImageIcon("Images/right.png");
        upButton = new JButton(up);
        upButton.setName("Up");
        upButton.setBounds(750, 300, 40, 40);
        upButton.addActionListener(e -> direction(upButton));

        downButton = new JButton(down);
        downButton.setName("Down");
        downButton.setBounds(750, 350, 40, 40);
        downButton.addActionListener(e -> direction(downButton));

        rightButton = new JButton(right);
        rightButton.setName("Right");
        rightButton.setBounds(800, 350, 40, 40);
        rightButton.addActionListener(e -> direction(rightButton));

        leftButton = new JButton(left);
        leftButton.setName("Left");
        leftButton.setBounds(700, 350, 40, 40);
        leftButton.addActionListener(e -> direction(leftButton));

        createTrackedButton = new JButton("Автобус");
        createTrackedButton.setBounds(10, 0, 130, 30);
        createTrackedButton.addActionListener(e -> {
            bus = new Bus(300 + ((int) (Math.random() * 300)), 800 + ((int) (Math.random() * 2000)), Color.BLACK, 160, 120);
            bus.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setBus(bus);
            frame.repaint();
        });

        createDoubleDeckerButton = new JButton("Двухэтажный автобус");
        createDoubleDeckerButton.setBounds(160, 0, 170, 30);
        createDoubleDeckerButton.addActionListener(e -> {
            bus = new DoubleDecker(300 + ((int) (Math.random() * 300)), 800 + ((int) (Math.random() * 2000)), Color.BLACK, Color.RED,
                    true, true,  true);
            bus.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            draw.setBus(bus);
            frame.repaint();
        });

        listNumberOfDoors = new JComboBox<>(new String[]{"3 двери", "4 двери", "5 дверей"});
        listNumberOfDoors.setBounds(12, 45, 150, 30);

        listAdditionalElems = new JComboBox<>(new String[]{"Прямоугольные двери", "Двойные двери", "Вертикальные двери"});
        listAdditionalElems.setBounds(12, 75, 150, 30);
    }

    public FormBus() {
        Random rnd = new Random();
        draw = new Draw();
        frame = new JFrame("Автобус");
        frame.setSize(900, 500);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createTrackedButton);
        frame.getContentPane().add(createDoubleDeckerButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(listNumberOfDoors);
        frame.getContentPane().add(listAdditionalElems);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public void setBus(ITransport bus)
    {
        Random rnd = new Random();
        bus.setPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, 140, 190);
        draw.setBus(bus);
        frame.repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(bus != null) {
            bus.DrawTransport(g);
        }
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }

}

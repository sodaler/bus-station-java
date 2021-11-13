import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class FormBus {
    private JButton createButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<Integer> choiceButton;
    private Bus bus;
    private JFrame frame = new JFrame("Автобус");
    private Draw draw = new Draw();

    public void direction(JButton button) {
        String turn = button.getName();


        switch(turn) {
            case "Up":
                this.bus.MoveTransport(Direction.Up);
                break;
            case "Down":
                this.bus.MoveTransport(Direction.Down);
                break;
            case "Left":
                this.bus.MoveTransport(Direction.Left);
                break;
            case "Right":
                this.bus.MoveTransport(Direction.Right);
        }

        this.frame.repaint();
    }

    public void initialization() {
        ImageIcon up = new ImageIcon("src/Images/up.jpg");
        ImageIcon down = new ImageIcon("src/Images/down.jpg");
        ImageIcon left = new ImageIcon("src/Images/left.jpg");
        ImageIcon right = new ImageIcon("src/Images/right.jpg");
        Image imgTake = up.getImage();
        Image turnScale = imgTake.getScaledInstance(50, 50, 4);
        up = new ImageIcon(turnScale);
        this.upButton = new JButton(up);
        this.upButton.setName("Up");
        this.upButton.setBounds(750, 300, 40, 40);
        this.upButton.addActionListener((e) -> {
            this.direction(this.upButton);
        });
        Image imgDown = down.getImage();
        Image downScale = imgDown.getScaledInstance(50, 55, 4);
        down = new ImageIcon(downScale);
        this.downButton = new JButton(down);
        this.downButton.setName("Down");
        this.downButton.setBounds(750, 350, 40, 40);
        this.downButton.addActionListener((e) -> {
            this.direction(this.downButton);
        });
        imgTake = right.getImage();
        turnScale = imgTake.getScaledInstance(50, 50, 4);
        right = new ImageIcon(turnScale);
        this.rightButton = new JButton(right);
        this.rightButton.setName("Right");
        this.rightButton.setBounds(800, 350, 40, 40);
        this.rightButton.addActionListener((e) -> {
            this.direction(this.rightButton);
        });
        imgTake = left.getImage();
        turnScale = imgTake.getScaledInstance(50, 50, 4);
        left = new ImageIcon(turnScale);
        this.leftButton = new JButton(left);
        this.leftButton.setName("Left");
        this.leftButton.setBounds(700, 350, 40, 40);
        this.leftButton.addActionListener((e) -> {
            this.direction(this.leftButton);
        });
        this.createButton = new JButton("Создать");
        this.createButton.setBounds(0, 0, 90, 30);
        this.createButton.addActionListener((e) -> {
            this.bus = new Bus(300 + (int)(Math.random() * 300.0D), 800 + (int)(Math.random() * 2000.0D), Color.BLACK, Color.BLUE, true, true, this.choiceButton.getSelectedIndex() + 3);
            this.bus.setPosition(10 + (int)(Math.random() * 100.0D), 10 + (int)(Math.random() * 100.0D), 900, 500);
            this.upButton.setEnabled(true);
            this.downButton.setEnabled(true);
            this.rightButton.setEnabled(true);
            this.leftButton.setEnabled(true);
            this.draw.setBus(this.bus);
            this.frame.repaint();
        });
        this.choiceButton = new JComboBox(new Integer[]{3, 4, 5});
        this.choiceButton.setBounds(100, 0, 80, 40);
    }

    public FormBus() {
        this.frame.setSize(900, 500);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.initialization();
        this.frame.getContentPane().add(this.createButton);
        this.frame.getContentPane().add(this.upButton);
        this.frame.getContentPane().add(this.downButton);
        this.frame.getContentPane().add(this.leftButton);
        this.frame.getContentPane().add(this.rightButton);
        this.frame.getContentPane().add(this.choiceButton);
        this.frame.getContentPane().add(this.draw);
        this.draw.setBounds(0, 0, 900, 500);
        this.frame.repaint();
    }
}
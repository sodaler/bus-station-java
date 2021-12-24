import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelBusConfig extends JDialog {

    private Bus bus;
    private Color busColor;
    private DopDoor dopElems;
    private boolean applyCheck;
    private final Draw panelBus;

    public PanelBusConfig(Frame owner) {
        super(owner, true);
        setSize(900, 400);
        setLayout(null);
        setTitle("Выбор автобуса");
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelBusType = new JPanel();
        panelBusType.setLayout(null);
        Border centerBorder = BorderFactory.createTitledBorder("Тип автобуса");
        panelBusType.setBorder(centerBorder);

        JLabel labelBus = new JLabel("Обычный автобус");
        labelBus.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        labelBus.setBounds(60, 30, 190, 55 );
        labelBus.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDoubleBus = new JLabel("Двухэтажный автобус");
        labelDoubleBus.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        labelDoubleBus.setBounds(60, 100, 190, 55 );
        labelDoubleBus.setHorizontalAlignment(SwingConstants.CENTER);

        JSpinner spinnerSpeed = new JSpinner(new SpinnerNumberModel(1000, 100, 1000, 1));
        JSpinner spinnerSeats = new JSpinner(new SpinnerNumberModel(40, 20, 50, 1));
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(4000, 4000, 6000, 1));
        JLabel labelSpeed = new JLabel("Средняя скорость:");
        JLabel labelWeight = new JLabel("Вес автобуса:");
        JCheckBox checkBoxSecondFloor = new JCheckBox("Второй этаж", false);
        JCheckBox checkBoxHeadLight = new JCheckBox("Наличие фары", false);
        JCheckBox checkBoxAdditionalDoor = new JCheckBox("Дополнительная дверь", false);

        MouseAdapter listenerType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                switch (label.getText()) {
                    case "Обычный автобус":
                        bus = new Bus((Integer) spinnerSpeed.getValue(), (Integer) spinnerWeight.getValue(), Color.white);
                        break;
                    case "Двухэтажный автобус":
                        bus = new DoubleDecker((Integer) spinnerSpeed.getValue(),(Integer) spinnerWeight.getValue(), Color.white, Color.black,
                                 checkBoxAdditionalDoor.isSelected(), checkBoxHeadLight.isSelected(), checkBoxSecondFloor.isSelected());
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + panelBusType.getX() >= panelBus.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + panelBusType.getX() <= panelBus.getX() + panelBus.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelBusType.getY() >= panelBus.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelBusType.getY() <= panelBus.getY() + panelBus.getHeight()) {
                    bus.setPosition(30, 30, panelBus.getWidth(), panelBus.getHeight());
                    panelBus.setBus(bus);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                bus = null;
            }
        };

        labelBus.addMouseListener(listenerType);
        labelDoubleBus.addMouseListener(listenerType);
        panelBusType.add(labelBus);
        panelBusType.add(labelDoubleBus);

        add(panelBusType);
        panelBusType.setBounds(10, 10, 320, 170);

        panelBus = new Draw();
        getContentPane().add(panelBus);
        panelBus.setBounds(360, 18, 275, 160);
        panelBus.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        MouseListener listenerView = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (bus != null || (panelBus.getBus() instanceof DoubleDecker && dopElems != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (bus != null || dopElems != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };
        panelBus.addMouseListener(listenerView);

        JPanel panelParams = new JPanel();
        panelParams.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Параметры");
        panelParams.setBorder(centerBorder);

        panelParams.add(spinnerSeats);
        panelParams.add(spinnerSpeed);
        panelParams.add(spinnerWeight);
        panelParams.add(labelSpeed);
        panelParams.add(labelWeight);
        panelParams.add(checkBoxAdditionalDoor);
        panelParams.add(checkBoxHeadLight);
        panelParams.add(checkBoxSecondFloor);

        add(panelParams);
        panelParams.setBounds(10, 185, 320, 150);

        labelSpeed.setBounds(10, 20, 120, 15);
        spinnerSpeed.setBounds(30, 35, 100, 25);
        spinnerSeats.setBounds(30, 75, 100, 25);
        labelWeight.setBounds(10, 100, 100, 15);
        spinnerWeight.setBounds(30, 115, 100, 25);
        checkBoxSecondFloor.setBounds(140, 80, 120, 19);
        checkBoxHeadLight.setBounds(140, 100, 170, 19);
        checkBoxAdditionalDoor.setBounds(140, 120, 180, 19);

        JPanel panelColors = new JPanel();
        panelColors.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Цвет");
        panelColors.setBorder(centerBorder);

        JLabel labelMainColor = new JLabel("Основной цвет");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(10, 25, 100, 30);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelAdditionalColor = new JLabel("Доп цвет");
        labelAdditionalColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAdditionalColor.setBounds(10, 70, 100, 30);
        labelAdditionalColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                busColor = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (panelBus.getBus() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        panelBus.getBus().setMainColor(busColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelAdditionalColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelAdditionalColor.getX() + labelAdditionalColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelAdditionalColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelAdditionalColor.getY() + labelAdditionalColor.getHeight() &&
                            panelBus.getBus() instanceof DoubleDecker) {
                        DoubleDecker doubleBus = (DoubleDecker) panelBus.getBus();
                        doubleBus.setDopColor(busColor);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                busColor = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Основной цвет":
                        if (panelBus.getBus() != null && busColor != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                        break;
                    case "Доп цвет":
                        if (panelBus.getBus() instanceof DoubleDecker && busColor != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                        break;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (busColor != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelAdditionalColor.addMouseListener(listenerColorLabel);

        JPanel panelRed = new JPanel();
        panelRed.setBackground(new Color(255, 0, 51));
        panelRed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelRed.setBounds(127, 25, 30, 30);
        panelRed.addMouseListener(listenerColor);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(new Color(38, 90, 198));
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setBounds(163, 25, 30, 30);
        panelBlue.addMouseListener(listenerColor);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.white);
        panelWhite.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelWhite.setBounds(199, 25, 30, 30);
        panelWhite.addMouseListener(listenerColor);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(new Color(52, 227, 95));
        panelGreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGreen.setBounds(235, 25, 30, 30);
        panelGreen.addMouseListener(listenerColor);

        JPanel panelTurqoise = new JPanel();
        panelTurqoise.setBackground(new Color(64,224,208));
        panelTurqoise.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelTurqoise.setBounds(127, 70, 30, 30);
        panelTurqoise.addMouseListener(listenerColor);

        JPanel panelPurple = new JPanel();
        panelPurple.setBackground(new Color(148,0,211));
        panelPurple.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPurple.setBounds(163, 70, 30, 30);
        panelPurple.addMouseListener(listenerColor);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.black);
        panelBlack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlack.setBounds(199, 70, 30, 30);
        panelBlack.addMouseListener(listenerColor);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(new Color(255,255,0));
        panelYellow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelYellow.setBounds(235, 70, 30, 30);
        panelYellow.addMouseListener(listenerColor);

        panelColors.setBounds(360, 185, 275, 110);
        add(panelColors);
        panelColors.add(labelMainColor);
        panelColors.add(labelAdditionalColor);
        panelColors.add(panelRed);
        panelColors.add(panelBlue);
        panelColors.add(panelWhite);
        panelColors.add(panelGreen);
        panelColors.add(panelTurqoise);
        panelColors.add(panelPurple);
        panelColors.add(panelBlack);
        panelColors.add(panelYellow);

        JPanel panelAddDopElems = new JPanel();
        panelAddDopElems.setLayout(null);
        centerBorder = BorderFactory.createTitledBorder("Доп. элементы");
        panelAddDopElems.setBorder(centerBorder);

        add(panelAddDopElems);
        panelAddDopElems.setBounds(655, 10, 210, 285);

        JLabel labelRectDoors = new JLabel("Прямоугольные двери");
        labelRectDoors.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelRectDoors.setBounds(10, 30, 190, 55);
        labelRectDoors.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDoubleDoors = new JLabel("Двойные двери");
        labelDoubleDoors.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelDoubleDoors.setBounds(10, 100, 190, 55);
        labelDoubleDoors.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelVertDoors = new JLabel("Вертикальные двери");
        labelVertDoors.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelVertDoors.setBounds(10, 170, 190, 55);
        labelVertDoors.setHorizontalAlignment(SwingConstants.CENTER);

        panelAddDopElems.add(labelRectDoors);
        panelAddDopElems.add(labelDoubleDoors);
        panelAddDopElems.add(labelVertDoors);

        JSpinner spinnerDoorsNumber = new JSpinner(new SpinnerNumberModel(3, 3, 5, 1));
        JLabel labelDoorsNumber = new JLabel("Количество дверей");

        labelDoorsNumber.setBounds(53, 230, 120, 15);
        spinnerDoorsNumber.setBounds(65, 250, 80, 25);

        panelAddDopElems.add(labelDoorsNumber);
        panelAddDopElems.add(spinnerDoorsNumber);

        MouseAdapter listenerAdditionalElems = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel labelAdditionalElems = (JLabel) e.getSource();
                switch (labelAdditionalElems.getText()) {
                    case "Прямоугольные двери":
                        dopElems = new RectDoor((Integer) spinnerDoorsNumber.getValue() - 3);
                        break;
                    case "Двойные двери":
                        dopElems = new VertDoor((Integer) spinnerDoorsNumber.getValue() - 3);
                        break;
                    case "Вертикальные двери":
                        dopElems = new DoubleDoor((Integer) spinnerDoorsNumber.getValue() - 3);
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (panelBus.getBus() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelAddDopElems.getX() >= panelBus.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() + panelAddDopElems.getX() <= panelBus.getX() + panelBus.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAddDopElems.getY() >= panelBus.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAddDopElems.getY() <= panelBus.getY() + panelBus.getHeight() &&
                            panelBus.getBus() instanceof DoubleDecker) {
                        DoubleDecker doubleBus = (DoubleDecker) panelBus.getBus();
                        doubleBus.setDopDoor(dopElems);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                dopElems = null;
            }
        };

        labelRectDoors.addMouseListener(listenerAdditionalElems);
        labelDoubleDoors.addMouseListener(listenerAdditionalElems);
        labelVertDoors.addMouseListener(listenerAdditionalElems);

        JButton buttonOk = new JButton("Добавить");
        JButton buttonCancel = new JButton("Отмена");
        add(buttonOk);
        add(buttonCancel);
        buttonOk.setBounds(375, 300, 120, 30);
        buttonCancel.setBounds(505, 300, 120, 30);
        buttonOk.addActionListener(e -> {
            applyCheck = true;
            dispose();
        });
        buttonCancel.addActionListener(e -> dispose());
        setVisible(true);
    }

    public Bus getBus() {
        if (applyCheck) {
            return (Bus) panelBus.getBus();
        } else {
            return null;
        }
    }
}

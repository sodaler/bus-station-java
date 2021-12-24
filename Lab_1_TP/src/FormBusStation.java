
import javax.swing.*;
import javax.swing.border.Border;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class FormBusStation {

    private final Queue<Bus> queue;
    private final JFrame frame;
    private final JTextField placeNum;
    private final BusStationCollection busStationCollection;
    private final DefaultListModel<String> busStationList;
    private final JList<String> listBoxBusStation;
    private final JTextField busStationName;
    private final PanelBusStation panelBusStation;

    public FormBusStation() {
        queue = new LinkedList<>();

        frame = new JFrame("Автовокзал");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        busStationCollection = new BusStationCollection(700, 450);

        panelBusStation = new PanelBusStation(busStationCollection);
        JButton buttonParkBus = new JButton("Припарковать автобус");
        JLabel labelPlace = new JLabel("Место:");
        JButton buttonAddToQueue = new JButton("Добавить в очередь");
        JButton buttonGetFromQueue = new JButton("Получить из очереди");

        JPanel groupBoxPickBus = new JPanel();
        Border centerBorder = BorderFactory.createTitledBorder("Забрать автобус:");
        groupBoxPickBus.setBorder(centerBorder);
        placeNum = new JFormattedTextField();

        JPanel groupBoxBusStation = new JPanel();
        centerBorder = BorderFactory.createTitledBorder("Автовокзалы");
        groupBoxBusStation.setBorder(centerBorder);
        JLabel labelBusStationName = new JLabel("Название автовокзала:");
        busStationList = new DefaultListModel<>();
        listBoxBusStation = new JList<>(busStationList);
        busStationName = new JTextField();
        JButton buttonAddBusStation = new JButton("Добавить автовокзал");
        JButton buttonDeleteBusStation = new JButton("Удалить автовокзал");

        frame.getContentPane().add(buttonParkBus);
        frame.getContentPane().add(panelBusStation);

        groupBoxPickBus.add(placeNum);
        groupBoxPickBus.add(labelPlace);
        groupBoxPickBus.add(buttonAddToQueue);
        groupBoxPickBus.add(buttonGetFromQueue);
        frame.getContentPane().add(groupBoxPickBus);

        groupBoxBusStation.add(labelBusStationName);
        groupBoxBusStation.add(listBoxBusStation);
        groupBoxBusStation.add(busStationName);
        groupBoxBusStation.add(buttonAddBusStation);
        groupBoxBusStation.add(buttonDeleteBusStation);
        frame.getContentPane().add(groupBoxBusStation);

        panelBusStation.setBounds(5, 5, 650, 450);
        buttonParkBus.setBounds(670, 265, 200, 40);

        groupBoxBusStation.setLayout(null);
        groupBoxBusStation.setBounds(670, 10, 200, 240);
        labelBusStationName.setBounds(10, 20, 180, 30);
        busStationName.setBounds(10, 50, 180, 20);
        buttonAddBusStation.setBounds(10, 80, 180, 30);
        listBoxBusStation.setBounds(10, 120, 180, 60);
        buttonDeleteBusStation.setBounds(10, 190, 180, 30);

        groupBoxPickBus.setLayout(null);
        groupBoxPickBus.setBounds(670, 280, 200, 150);
        labelPlace.setBounds(10, 20, 50, 30);
        placeNum.setBounds(70, 30, 30, 20);
        buttonAddToQueue.setBounds(10, 70, 180, 30);
        buttonGetFromQueue.setBounds(10, 110, 180, 30);

        buttonParkBus.addActionListener(e -> parkBus());
        buttonAddToQueue.addActionListener(e -> takeBus());
        buttonGetFromQueue.addActionListener(e -> getBus());
        buttonAddBusStation.addActionListener(e -> addBusStation());
        buttonDeleteBusStation.addActionListener(e -> deleteBusStation());
        listBoxBusStation.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void parkBus() {
        if (listBoxBusStation.getSelectedIndex() >= 0) {
            PanelBusConfig configPanel = new PanelBusConfig(frame);
            Bus bus = configPanel.getBus();

            if (bus == null) return;
            if (busStationCollection.get(listBoxBusStation.getSelectedValue()).add(bus) > 0) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Автовокзал переполнен");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Автовокзал не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void takeBus() {
        if (listBoxBusStation.getSelectedIndex() >= 0) {
            if (!placeNum.getText().equals("")) {
                try {
                    Bus bus = busStationCollection.get(listBoxBusStation.getSelectedValue()).remove(Integer.parseInt(placeNum.getText()));
                    if (bus != null) {
                        queue.add(bus);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Автобус с таким индексом отсутствует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Автобус с таким индексом отсутствует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Автовокзал не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getBus() {
        if (!queue.isEmpty()) {
            FormBus frameBus = new FormBus();

            frameBus.setBus(Objects.requireNonNull(queue.poll()));
            frame.repaint();
        }
    }

    private void reloadLevels() {
        int index = listBoxBusStation.getSelectedIndex();
        busStationList.removeAllElements();

        int i = 0;
        for (String name : busStationCollection.keys()) {
            busStationList.add(i, name);
            i++;
        }

        int itemsCount = busStationList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxBusStation.setSelectedIndex(0);
        }
        else if (index >= 0 && index < itemsCount) {
            listBoxBusStation.setSelectedIndex(index);
        }
    }

    private void addBusStation() {
        if (!busStationName.getText().equals("")) {
            busStationCollection.addBusStation(busStationName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название автовокзала", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBusStation() {
        if (listBoxBusStation.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить автовокзал " + listBoxBusStation.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                busStationCollection.deleteBusStation(listBoxBusStation.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Автовокзал не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listListener() {
        panelBusStation.setSelectedItem(listBoxBusStation.getSelectedValue());
        frame.repaint();
    }
}


import javax.swing.*;
import java.awt.*;

public class PanelBusStation extends JPanel {

    private final BusStation<Bus, DopDoor> busStation;

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (busStation != null) {
            busStation.drawBusStation(g2);
        }
    }

    public BusStation<Bus, DopDoor> getBusStation() {
        return busStation;
    }

    public PanelBusStation(BusStation<Bus, DopDoor> busStation) {
        this.busStation = busStation;
    }
}
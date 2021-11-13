import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    private Bus bus;

    public void paintComponent(Graphics g) {
        if (bus != null) {
            bus.DrawTransport(g);
        }
    }

    public void setBus(Bus ex) {
        this.bus = ex;
    }

    public Bus getBus() {
        return bus;
    }

}

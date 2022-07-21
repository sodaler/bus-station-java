import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    private ITransport bus;

    public void paintComponent(Graphics g) {
        if (bus != null) {
            bus.DrawTransport(g);
        }
    }

    public void setBus(ITransport ex) {
        this.bus = ex;
    }

    public ITransport getBus() {
        return bus;
    }

}

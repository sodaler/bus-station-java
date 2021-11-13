import java.awt.Graphics;
import javax.swing.JPanel;

public class Draw extends JPanel {
    private Bus bus;

    public void paintComponent(Graphics g) {
        if (this.bus != null) {
            this.bus.DrawTransport(g);
        }

    }

    public void setBus(Bus ex) {
        this.bus = ex;
    }

    public Bus getBus() {
        return this.bus;
    }
}

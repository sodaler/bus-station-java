
import java.awt.*;

public class BusStation<T extends ITransport, H extends DopDoor> {
    private final T[] places;
    private final int pictureWidth;
    private final int pictureHeight;

    private final int placeSizeWidth = 230;
    private final int placeSizeHeight = 100;

    public BusStation(int pictureWidth, int pictureHeight) {
        int width = pictureWidth / placeSizeWidth;
        int height = pictureHeight / placeSizeHeight;
        places = (T[])new ITransport [width * height];;
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public int add(T transport) {

        for (int i = 0; i < places.length; i++) {
            if (places[i] == null) {
                transport.setPosition(10 + placeSizeWidth * (i % (pictureHeight / placeSizeHeight)), 10 + placeSizeHeight * (i / (pictureHeight / placeSizeHeight)), pictureWidth, pictureHeight);
                places[i] = transport;
                return 1;
            }
        }
        return -1;
    }

    public T remove(int index) {
        if (index >= 0 && index < places.length && places[index] != null) {
            Object temp = places[index];
            places[index] = null;
            return (T) (temp);
        } else {
            return null;
        }
    }

    public int countOccupiedPlaces() {
        int placesNumber = 0;
        for (Object object : places) {
            if (object != null) {
                placesNumber++;
            }
        }
        return placesNumber;
    }

    public boolean moreOrEqual(double number) {
        return countOccupiedPlaces() >= number;
    }

    public boolean lessOrEqual(double number) {
        return countOccupiedPlaces() <= number;
    }

    public void drawBusStation(Graphics2D g) {
        drawMarking(g);
        g.setStroke(new BasicStroke(1));
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.DrawTransport(g);
            }
        }
    }

    public void drawMarking(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; j++) {
                g.drawLine(i * placeSizeWidth, j * placeSizeHeight, i * placeSizeWidth + placeSizeWidth / 2, j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth,0, i * placeSizeWidth, (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
}
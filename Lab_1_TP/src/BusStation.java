import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BusStation<T extends ITransport, H extends DopDoor> {
    private final List<T> _places;
    private final int _maxCount;

    private final int _pictureWidth;
    private final int _pictureHeight;

    private final int _placeSizeWidth = 230;
    private final int _placeSizeHeight = 100;

    public BusStation(int _pictureWidth, int _pictureHeight) {
        int columnsNumber = _pictureWidth / _placeSizeWidth;
        int rowsNumber = _pictureHeight / _placeSizeHeight;
        _maxCount = columnsNumber * rowsNumber;
        _places = new ArrayList<>();
        this._pictureWidth = _pictureWidth;
        this._pictureHeight = _pictureHeight;
    }


    public int add(T bus) {
        if (_places.size() < _maxCount)
        {
            _places.add(bus);
            return _places.size();
        }
        return -1;
    }


    public T remove(int index) {
        if(index >= 0 && index<_maxCount && _places.get(index) != null){
            T vehicle = _places.get(index);
            _places.remove(index);
            return vehicle;
        }
        return null;
    }

    public int countOccupiedPlaces() {
        int placesNumber = 0;
        for (Object object : _places) {
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
        int rowsNumber = _pictureWidth / _placeSizeWidth;

        drawMarking(g);
        g.setStroke(new BasicStroke(1));

        for (int i = 0; i < _places.size(); i++)
        {
            _places.get(i).setPosition(10 + _placeSizeWidth * (i % rowsNumber), 10 + _placeSizeHeight * (i / rowsNumber), _pictureWidth, _pictureHeight);
            _places.get(i).DrawTransport(g);
        }
    }

    public void drawMarking(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < _pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < _pictureHeight / _placeSizeHeight + 1; j++) {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth,0, i * _placeSizeWidth, (_pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }


    public T get(int index) {
        if (index > -1 && index < _places.size()) {
            return _places.get(index);
        }
        return null;
    }
}
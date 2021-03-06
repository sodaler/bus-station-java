import java.awt.*;

public class DoubleDoor implements DopDoor{

    private DoorNumber doorNumber;

    @Override
    public void setNumber(int number) {

        this.doorNumber = DoorNumber.getNumber(number);
    }

    public DoubleDoor(int number) {
        setNumber(number);
    }



    @Override
    public void draw(Graphics g, int _startPosX, int _startPosY, int busWidth, int busHeight) {
        g.setColor(Color.DARK_GRAY);
        if (doorNumber == DoorNumber.three || doorNumber == DoorNumber.four || doorNumber == DoorNumber.five) {
            g.fillRect(_startPosX + 96, _startPosY + 50, 15, 15);
            g.drawRect(_startPosX + 96, _startPosY + 50, 15, 30);
            g.fillRect(_startPosX + 62, _startPosY + 50, 15, 15);
            g.drawRect(_startPosX + 62, _startPosY + 50, 15, 30);
            g.fillRect(_startPosX + 79, _startPosY + 50, 15, 15);
            g.drawRect(_startPosX + 79, _startPosY + 50, 15, 30);
        }

        if (doorNumber == DoorNumber.four || doorNumber == DoorNumber.five) {
            g.fillRect(_startPosX + 113, _startPosY + 50, 15, 15);
            g.drawRect(_startPosX + 113, _startPosY + 50, 15, 30);
        }
        if (doorNumber == DoorNumber.five) {
            g.fillRect(_startPosX + 45, _startPosY + 50, 15, 15);
            g.drawRect(_startPosX + 45, _startPosY + 50, 15, 30);
        }
    }

    public String toString() {
        return this.getClass().getSimpleName() + '.' + doorNumber.ordinal();
    }
}

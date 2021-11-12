import java.awt.Graphics;

public class Door {
    private DoorNumber doorNumber;

    public void setNumber(int number) {
        this.doorNumber = DoorNumber.getNumber(number);
    }

    public void DrawDoors(Graphics g, int startPosX, int startPosY, int width, int height) {
        if (this.doorNumber == DoorNumber.three || this.doorNumber == DoorNumber.four || this.doorNumber == DoorNumber.five) {
            g.drawRect(startPosX + 76, startPosY + 50, 15, 30);
            g.drawRect(startPosX + 42, startPosY + 50, 15, 30);
            g.drawRect(startPosX + 59, startPosY + 50, 15, 30);
        }

        if (this.doorNumber == DoorNumber.four || this.doorNumber == DoorNumber.five) {
            g.drawRect(startPosX + 93, startPosY + 50, 15, 30);
        }

        if (this.doorNumber == DoorNumber.five) {
            g.drawRect(startPosX + 27, startPosY + 50, 15, 30);
        }

    }
}
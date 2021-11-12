import java.awt.Color;
import java.awt.Graphics;

public class Bus {
    private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private final int busWidth = 160;
    private final int busHeight = 120;
    public int MaxSpeed;
    public int Weight;
    public Color MainColor;
    public Color DopColor;
    private boolean HeadLight;
    private boolean SecondFloor;
    private final Door door;

    public float get_startPosX() {
        return (float)this._startPosX;
    }

    private void set_startPosX(int _startPosX) {
        this._startPosX = _startPosX;
    }

    public int getMaxSpeed() {
        return this.MaxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return (float)this.Weight;
    }

    private void setWeight(int weight) {
        this.Weight = weight;
    }

    public Color getMainColor() {
        return this.MainColor;
    }

    private void setMainColor(Color mainColor) {
        this.MainColor = mainColor;
    }

    public Color getDopColor() {
        return this.DopColor;
    }

    private void setDopColor(Color dopColor) {
        this.DopColor = dopColor;
    }

    private boolean getHeadLight() {
        return this.HeadLight;
    }

    private void setHeadLight(boolean headLight) {
        this.HeadLight = headLight;
    }

    private boolean getSecondFloor() {
        return this.HeadLight;
    }

    private void setSecondFloor(boolean secondFloor) {
        this.SecondFloor = secondFloor;
    }

    public Bus(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean headLight, boolean secondFloor, int doorNumber) {
        this.setMaxSpeed(maxSpeed);
        this.setWeight(weight);
        this.setMainColor(mainColor);
        this.setDopColor(dopColor);
        this.setHeadLight(headLight);
        this.setSecondFloor(secondFloor);
        this.door = new Door();
        this.door.setNumber(doorNumber);
    }

    public void setPosition(int x, int y, int width, int height) {
        this._startPosX = x;
        this._startPosY = y;
        this._pictureHeight = height;
        this._pictureWidth = width;
    }

    public void MoveTransport(Direction direction) {
        float step = (float)(this.MaxSpeed * 100 / this.Weight);
        switch(direction) {
            case Right:
                if ((float)this._startPosX + step < (float)(this._pictureWidth - 160)) {
                    this._startPosX = (int)((float)this._startPosX + step);
                }
                break;
            case Left:
                if ((float)this._startPosX - step > 0.0F) {
                    this._startPosX = (int)((float)this._startPosX - step);
                }
                break;
            case Up:
                if ((float)this._startPosY - step > 0.0F) {
                    this._startPosY = (int)((float)this._startPosY - step);
                }
                break;
            case Down:
                if ((float)this._startPosY + step < (float)(this._pictureHeight - 120)) {
                    this._startPosY = (int)((float)this._startPosY + step);
                }
        }

    }

    public void DrawTransport(Graphics g) {
        g.setColor(this.MainColor);
        g.drawRect(this._startPosX, this._startPosY + 40, 130, 40);
        g.fillOval(this._startPosX + 10, this._startPosY + 75, 15, 15);
        g.fillOval(this._startPosX + 100, this._startPosY + 75, 15, 15);
        g.drawOval(this._startPosX + 10, this._startPosY + 45, 15, 20);
        g.drawOval(this._startPosX + 110, this._startPosY + 45, 15, 20);
        g.setColor(this.DopColor);
        this.door.DrawDoors(g, this._startPosX, this._startPosY, 160, 120);
        if (this.SecondFloor) {
            g.drawRect(this._startPosX, this._startPosY, 130, 40);
            g.drawOval(this._startPosX + 10, this._startPosY + 7, 15, 20);
            g.drawOval(this._startPosX + 35, this._startPosY + 7, 15, 20);
            g.drawOval(this._startPosX + 60, this._startPosY + 7, 15, 20);
            g.drawOval(this._startPosX + 85, this._startPosY + 7, 15, 20);
            g.drawOval(this._startPosX + 110, this._startPosY + 7, 15, 20);
        }

        if (this.HeadLight) {
            g.fillRect(this._startPosX - 6, this._startPosY + 55, 5, 13);
        }

    }
}
import java.awt.*;

public class DoubleDecker extends Bus {

    public Color DopColor;
    public boolean HeadLight;
    public boolean SecondFloor;
    DopDoor dopDoor;
    private boolean DoubleDoor;

    public Color getDopColor() {
        return DopColor;
    }

    public void setDopColor(Color dopColor) {
        DopColor = dopColor;
    }

    private boolean getHeadLight() {
        return HeadLight;
    }

    private void setHeadLight(boolean headLight) {
        HeadLight = headLight;
    }

    private boolean getSecondFloor() {
        return HeadLight;
    }

    private void setSecondFloor(boolean secondFloor) {
        SecondFloor = secondFloor;
    }

    public DoubleDecker(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean headLight, boolean secondFloor, int door, int number) {
        super(maxSpeed, weight, mainColor, 160, 120);

        this.DopColor = dopColor;
        this.HeadLight = headLight;
        this.SecondFloor = secondFloor;

        switch (door){
            case 3:
                dopDoor = new DoubleDoor();
                break;
            case 4:
                dopDoor = new VertDoor();
                break;
            case 5:
                dopDoor = new RectDoor();
                break;
        }

        dopDoor.setNumber(number);
    }

    @Override
    public void DrawTransport(Graphics g) {

        g.setColor(MainColor);

        super.DrawTransport(g);

        g.setColor(DopColor);

        if (SecondFloor) {

            g.drawRect(_startPosX, _startPosY, 170, 40);
            g.drawOval(_startPosX + 20, _startPosY + 7, 15, 20);
            g.drawOval(_startPosX + 50, _startPosY + 7, 15, 20);
            g.drawOval(_startPosX + 80, _startPosY + 7, 15, 20);
            g.drawOval(_startPosX + 110, _startPosY + 7, 15, 20);
            g.drawOval(_startPosX + 140, _startPosY + 7, 15, 20);

        }

        dopDoor.draw(g, _startPosX, _startPosY, busWidth, busHeight);

        if (HeadLight) {
            g.fillRect((int) _startPosX - 6, (int) _startPosY + 55, 5, 13);
        }
    }
}

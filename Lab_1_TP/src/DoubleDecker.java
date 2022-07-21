import java.awt.*;

public class DoubleDecker extends Bus {

    public Color DopColor;
    public boolean HeadLight;
    public boolean SecondFloor;
    public DopDoor DopDoor;
    public boolean HasDopDoor;
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

    public void setDopDoor(DopDoor dopDoor) {
        this.DopDoor = dopDoor;
    }

    public DoubleDecker(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean headLight, boolean secondFloor, boolean hasDopDoor) {
        super(maxSpeed, weight, mainColor, 160, 120);

        this.DopColor = dopColor;
        this.HasDopDoor = hasDopDoor;
        this.HeadLight = headLight;
        this.SecondFloor = secondFloor;
    }

    public DoubleDecker(String info) {
        String[] args = info.split(separator);
        if (args.length == 8) {
            MaxSpeed = Integer.parseInt(args[0]);
            Weight = Float.parseFloat(args[1]);
            MainColor = new Color(Integer.parseInt(args[2]));
            DopColor = new Color(Integer.parseInt(args[3]));
            HasDopDoor = Boolean.parseBoolean(args[4]);
            HeadLight = Boolean.parseBoolean(args[5]);
            SecondFloor = Boolean.parseBoolean(args[6]);
            if (args[7].contains("null")) {
                DopDoor = null;
            } else {
                String[] argsAdditionalElems = args[7].split("\\.");
                int number = Integer.parseInt(argsAdditionalElems[1]);
                switch (argsAdditionalElems[0]) {
                    case "RectDoor":
                        DopDoor = new RectDoor(number);
                        break;
                    case "VertDoor":
                        DopDoor = new VertDoor(number);
                        break;
                    case "DoubleDoor":
                        DopDoor = new DoubleDoor(number);
                        break;
                }
            }
        }
    }


    @Override
    public void DrawTransport(Graphics g) {

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

        if (HeadLight) {
            g.fillRect((int) _startPosX - 6, (int) _startPosY + 55, 5, 13);
        }

        if (DopDoor != null) {
            DopDoor.draw(g, _startPosX, _startPosY, 300, 400);
        }
    }

    public String toString() {
        return MaxSpeed + separator + Weight + separator + MainColor.getRGB() + separator + DopColor.getRGB() + separator + HasDopDoor + separator + HeadLight + separator + SecondFloor + separator + DopDoor;
    }
}
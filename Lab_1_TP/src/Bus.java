import java.awt.*;

public class Bus extends Vehicle {

    protected int busWidth = 160;
    protected int busHeight = 120;

    public Bus(int maxSpeed, float weight, Color mainColor, int busWidth, int busHeight) {
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
        this.busWidth = busWidth;
        this.busHeight = busHeight;
    }

    @Override
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (_startPosX + step < _pictureWidth - busWidth)
                {
                    _startPosX += step;
                }
                break;

            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;

            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;

            case Down:
                if (_startPosY + step < _pictureHeight - busHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

    @Override
    public void DrawTransport(Graphics g) {

        g.setColor(MainColor);

        //кузов
        g.drawRect(_startPosX, _startPosY + 40, 170, 40);
        g.fillOval(_startPosX + 20, _startPosY + 75, 15, 15);
        g.fillOval(_startPosX + 130, _startPosY + 75, 15, 15);
        g.drawRect(_startPosX + 96, _startPosY + 50, 15, 30);

        g.drawRect(_startPosX + 62, _startPosY + 50, 15, 30);
        g.drawRect(_startPosX + 79, _startPosY + 50, 15, 30);
        g.drawOval(_startPosX + 20, _startPosY + 45, 15, 20);
        g.drawOval(_startPosX + 140, _startPosY + 45, 15, 20);
    }


}
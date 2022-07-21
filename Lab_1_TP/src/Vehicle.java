import java.awt.*;

public abstract class Vehicle implements ITransport {
    protected int _startPosX;

    protected int _startPosY;

    protected int _pictureWidth;

    protected int _pictureHeight;

    public int MaxSpeed;

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(int value) {
        this.MaxSpeed = value;
    }

    public float Weight;

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float value) {
        this.Weight = value;
    }

    public Color MainColor;

    public Color getMainColor() {
        return MainColor;
    }

    public void setMainColor(Color value) {
        this.MainColor = value;
    }

    public void setPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        this._pictureWidth = width;
        this._pictureHeight = height;
    }

    public abstract void DrawTransport(Graphics g);

    public abstract void MoveTransport(Direction direction);
}
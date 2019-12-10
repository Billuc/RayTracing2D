package raytracing2d;

import java.awt.*;

public abstract class Shape {
    public Color color;
    float z = 0;
    boolean fill = true;

    public abstract void draw(Graphics g);

    public abstract Vector2 intersection(Ray r);

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getZ() {return z;}

    public void setColor(Color c) { this.color = c; }
}

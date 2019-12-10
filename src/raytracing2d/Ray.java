package raytracing2d;

import java.awt.*;

public class Ray extends Shape {

    public Vector2 pos;
    public Vector2 dir;

    public Ray(float x, float y, float dirX, float dirY) {
        this.pos = new Vector2(x, y);
        this.dir = new Vector2(dirX, dirY).unitary();
        this.color = Color.RED;
    }

    public Ray(Vector2 p, Vector2 d) {
        pos = p;
        dir = d;
        this.color = Color.RED;
    }

    public void draw(Graphics g) {
        draw(g, pos.add(dir));
    }

    @Override
    public Vector2 intersection(Ray r) {
        return null;
    }

    public void draw(Graphics g, Vector2 endPoint) {
        g.setColor(color);

        g.drawLine((int)pos.x, (int)pos.y, (int)endPoint.x, (int)endPoint.y);
    }
}

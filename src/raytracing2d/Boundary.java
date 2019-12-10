package raytracing2d;

import java.awt.*;

public class Boundary extends Shape {

    public Vector2 p1;
    public Vector2 p2;

    public Boundary(float x1, float y1, float x2, float y2) {
        color = Color.gray;
        this.p1 = new Vector2(x1, y1);
        this.p2 = new Vector2(x2, y2);
    }

    public Boundary(Vector2 a, Vector2 b) {
        color = Color.gray;
        this.p1 = a;
        this.p2 = b;
    }

    public Boundary(float x1, float y1, float x2, float y2, Color c) {
        color = c;
        this.p1 = new Vector2(x1, y1);
        this.p2 = new Vector2(x2, y2);
    }

    public Boundary(Vector2 a, Vector2 b, Color c) {
        color = c;
        this.p1 = a;
        this.p2 = b;
    }

    public void draw(Graphics g) {
        g.setColor(color);

        g.drawLine((int)this.p1.x, (int)this.p1.y, (int)this.p2.x, (int)this.p2.y);
    }

    public Vector2 intersection(Ray r) {
        Vector2 toP2 = p2.subtract(p1);
        Vector2 toRayPos = r.pos.subtract(p1);

        float u = (- r.dir.x * toRayPos.y + r.dir.y * toRayPos.x) / ( - r.dir.x * toP2.y + r.dir.y * toP2.x);
        float t = ( - toRayPos.x * toP2.y + toRayPos.y * toP2.x) / ( r.dir.x * toP2.y - r.dir.y * toP2.x);

        if (u > 1 || u < 0 || t <= 0) return null;

        return p1.add(toP2.multiply(u));
    }
}

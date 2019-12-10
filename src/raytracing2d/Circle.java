package raytracing2d;

import java.awt.*;

public class Circle extends Shape {
    
    public Vector2 center;
    public float radius;
    
    public Circle(float x, float y, float r) {
        color = Color.GREEN;

        this.center = new Vector2(x, y);
        this.radius = r;
    }
    
    public Circle(Vector2 v, float r) {
        this.center = v;
        this.radius = r;
    }

    public void draw(Graphics g) {
        g.setColor(color);

        if (fill) g.fillOval((int)(center.x - radius), (int)(center.y-radius), (int)(2*radius), (int)(2*radius));
        else g.drawOval((int)(center.x - radius), (int)(center.y-radius), (int)(2*radius), (int)(2*radius));
    }

    public Vector2 intersection(Ray r) {
        Vector2 centerToPos = r.pos.subtract(center);

        float[] interDistances = Util.solveQuadratic(r.dir.x*r.dir.x + r.dir.y*r.dir.y,
                                                        2*r.dir.x*centerToPos.x + 2*r.dir.y*centerToPos.y,
                                                            - radius*radius + centerToPos.length()*centerToPos.length());

        if (interDistances.length == 0) return null;

        for (float dist : interDistances) {
            if (dist <= 0) continue;

            return r.pos.add(r.dir.multiply(dist));
        }

        return null;
    }
}

package raytracing2d;

import java.awt.*;

public class Square extends Shape {
    public Boundary[] edges;
    private Polygon square;

    public Square(Vector2 a, Vector2 c) {
        color = Color.yellow;

        Vector2 toCenter = c.subtract(a).multiply(1/2f);
        Vector2 toB = toCenter.rotate((float) (Math.PI/2));
        Vector2 toD = toCenter.rotate((float) (-Math.PI/2));

        Vector2 b = a.add(toCenter).add(toB);
        Vector2 d = a.add(toCenter).add(toD);

        edges = new Boundary[4];
        square = new Polygon();

        edges[0] = new Boundary(a, b, color);
        edges[1] = new Boundary(b, c, color);
        edges[2] = new Boundary(c, d, color);
        edges[3] = new Boundary(d, a, color);

        square.addPoint((int)a.x, (int)a.y);
        square.addPoint((int)b.x, (int)b.y);
        square.addPoint((int)c.x, (int)c.y);
        square.addPoint((int)d.x, (int)d.y);
    }

    public Square(float ax, float ay, float cx, float cy) {
        this(new Vector2(ax, ay), new Vector2(cx, cy));
    }

    public void draw(Graphics g) {
        g.setColor(color);

        if (fill) ((Graphics2D)g).fill(square);
        else ((Graphics2D)g).draw(square);
    }

    public Vector2 intersection(Ray r) {
        return Util.cast(r, edges);
    }
}

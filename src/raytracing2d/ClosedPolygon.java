package raytracing2d;

import java.awt.*;
import java.util.ArrayList;

public class ClosedPolygon extends Shape {
    protected Vector2 startPoint;
    public ArrayList<Boundary> edges;
    protected Polygon polygon;

    public ClosedPolygon() {
        color = Color.CYAN;
        edges = new ArrayList<>();
        polygon = new Polygon();
    }

    public void addBoundary(Vector2 newPoint) {
        if (edges.size() == 0) {
            startPoint = newPoint;
            edges.add(new Boundary(newPoint, newPoint));
        }
        else {
            Vector2 lastPoint = edges.get(edges.size()-1).p1;
            edges.remove(edges.size()-1);
            edges.add(new Boundary(lastPoint, newPoint));
            edges.add(new Boundary(newPoint, startPoint));
        }
        polygon.addPoint((int)newPoint.x, (int)newPoint.y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        if (fill) ((Graphics2D)g).fill(polygon);
        else ((Graphics2D)g).draw(polygon);
    }

    @Override
    public Vector2 intersection(Ray r) {
        return Util.cast(r, edges.toArray(new Boundary[0]));
    }

    protected void generatePolygon() {
        polygon = new Polygon();
        for (Boundary b : edges) {
            polygon.addPoint((int)b.p1.x, (int)b.p1.y);
        }
    }
}

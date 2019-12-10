import raytracing2d.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;



public class Main extends JFrame {
    final List<raytracing2d.Shape> shapes = new ArrayList<>();
    Vector2 pos = new Vector2(250, 250);
    final int NB_RAYS = 1000;
    final Ray[] rays = new Ray[NB_RAYS];

    public Main() {
        super("2DRayTracing Test");
        this.setSize(600,600);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);

        this.setContentPane(new TestPanel());

        generateRays();
    }

    public void showTest() {
        this.setVisible(true);
    }

    public void hideTest() {
        this.setVisible(false);
    }

    public void addShape(raytracing2d.Shape s) {
        shapes.add(s);
        shapes.sort((o1, o2) -> Float.compare(o1.getZ(), o2.getZ()));
    }

    private void generateRays() {
        Vector2 dir0 = new Vector2(1,0);

        for (int i = 0; i < NB_RAYS; i++) {
            float angle = (float) (i*2*Math.PI/NB_RAYS);
            rays[i] = new Ray(pos, dir0.rotate(angle));
        }
    }

    private void updateRays() {
        for (Ray r : rays) {
            r.pos = pos;
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        Circle c1 = new Circle(100,100,50);
        c1.setZ(12);
        Square s1 = new Square(200, 200, 400, 400);
        s1.setZ(0);
        Boundary b1 = new Boundary(40,100, 450,500);
        b1.setZ(6);

        m.addShape(c1);
        m.addShape(s1);
        m.addShape(b1);

        m.showTest();
    }

    class TestPanel extends JPanel implements MouseMotionListener {
        public TestPanel() {
            super();
            setBackground(Color.BLACK);
            addMouseMotionListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (raytracing2d.Shape s : shapes) {
                s.draw(g);
            }

            Boundary b1 = new Boundary(0,0, this.getWidth(), 0);
            Boundary b2 = new Boundary(this.getWidth(), 0, this.getWidth(), this.getHeight());
            Boundary b3 = new Boundary(this.getWidth(), this.getHeight(), 0, this.getHeight());
            Boundary b4 = new Boundary(0, this.getHeight(), 0, 0);

            raytracing2d.Shape[] panelBounds = new raytracing2d.Shape[] {b1, b2, b3, b4};

            for (Ray r : rays) {
                Vector2 endPoint = Util.cast(r, shapes.toArray(new raytracing2d.Shape[0]));

                if (endPoint != null) r.draw(g, endPoint);
                else r.draw(g, Util.cast(r, panelBounds));
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            pos = new Vector2(e.getX(), e.getY());
            updateRays();
            repaint();
        }
    }
}

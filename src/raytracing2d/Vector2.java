package raytracing2d;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    public Vector2 multiply(float factor) {
        return new Vector2(factor * x, factor * y);
    }

    public Vector2 rotate(float angle) {
        return new Vector2((float)(x*Math.cos(angle)-y*Math.sin(angle)),
                            (float)(y*Math.cos(angle) + x*Math.sin(angle)));
    }

    public Vector2 subtract(Vector2 v) { return new Vector2(x - v.x, y - v.y); }

    public float distance(Vector2 point) {
        return (float)Math.sqrt((x-point.x)*(x-point.x) + (y-point.y)*(y-point.y));
    }

    public float length() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vector2 unitary() {
        return this.multiply(1/this.length());
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void move(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float angle() {
        return (float) Math.atan2(y, x);
    }
}

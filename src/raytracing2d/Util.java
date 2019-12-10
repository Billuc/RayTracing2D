package raytracing2d;

public class Util {
    public static float[] solveQuadratic(float a, float b, float c) {
        if (a==0) {
            if (c==0) return new float[] {0};
            return new float[] {-b/c};
        }

        float discriminant = b*b-4*a*c;

        if (discriminant < 0) return new float[]{};

        if (discriminant == 0) return new float[] {-b/(2*a)};

        return new float[] {(float)((-b-Math.sqrt(discriminant))/(2*a)), (float)((-b+Math.sqrt(discriminant))/(2*a))};
    }

    public static Vector2 cast(Ray r, Shape[] shapes) {
        float minDist = Float.MAX_VALUE;
        Vector2 intersect = null;

        for (Shape s : shapes) {
            Vector2 i = s.intersection(r);

            if (i != null) {
                float dist = i.distance(r.pos);

                if(dist < minDist) {
                    minDist = dist;
                    intersect = i;
                }
            }
        }

        return intersect;
    }

    public static float toDegree(float angle) { return (float) (180*angle/Math.PI); }

    public static float toRadian(float angle) { return (float) (Math.PI*angle/180); }
}

package lelar.graphics;

class Point {
    private float x;
    private float y;


    Point(float angle, float radius) {
        this.x = (float) Math.cos(angle) * radius;
        this.y = (float) Math.sin(angle) * radius;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }
}

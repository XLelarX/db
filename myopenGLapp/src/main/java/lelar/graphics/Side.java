package lelar.graphics;

class Side {
    private Point a;
    private Point b;

    Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    Point getA() {
        return a;
    }

    Point getB() {
        return b;
    }
}

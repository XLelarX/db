package lelar.graphics;

class Point {
    private float x;
    private float y;
    private float z;

    private float red;
    private float green;
    private float blue;


    public float getX() {
        return x;
    }

    void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    void setZ(float z) {
        this.z = z;
    }

    public float getRed() {
        return red;
    }

    void setRed(float red) {
        this.red = red;
    }

    public float getBlue() {
        return blue;
    }

    void setBlue(float blue) {
        this.blue = blue;
    }

    public float getGreen() {
        return green;
    }

    void setGreen(float green) {
        this.green = green;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}

package lelar.graphics;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class MouseInput implements MouseListener {

    private static int x;
    private static int y;

    private static int alpha;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getAlpha() {
        return alpha;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //System.out.println("mouseClicked");
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //Rendering.y = 0;
        // Rendering.x = 0;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {//&& y < e.getY()
        System.out.println(e.getX() + "/" + e.getY());

//        if (y > e.getY()) {
//            Rendering.alpha += 0.001 * (e.getY() - y);
//            Rendering.x = 0;
//            Rendering.y = 1;
//        } else
//        if (y < e.getY()) {
//            Rendering.alpha += 0.001 * (e.getY() - y);
//            Rendering.x = 0;
//            Rendering.y = 1;
//        }
//
//        if (x > e.getX()) {
//            Rendering.alpha += 0.001 * (e.getX() - x);
//            //Rendering.y = 0;
//            Rendering.x = 1;
//        } else
//        if (x < e.getX()) {
//            Rendering.alpha += 0.001 * (e.getX() - x);
//            //Rendering.y = 0;
//            Rendering.x = 1;
//        }

        if (x > e.getX() && y > e.getY()) {
            Rendering.alpha += 0.001 * (e.getX() - x);
            Rendering.x = 1;
            Rendering.y = 1;
        }
        else if (x < e.getX() && y < e.getY()) {
            Rendering.alpha +=  0.001 * (e.getX() - x);
            Rendering.x = 1;
            Rendering.y = 1;
        }
        else
            if (x < e.getX() && y > e.getY()) {
            Rendering.alpha +=  0.001 * (e.getX() - x);
            Rendering.x = -1;
            Rendering.y = 1;
        }
        else if (x > e.getX() && y < e.getY()) {
            Rendering.alpha +=  0.001 * (e.getX() - y);
            Rendering.x = 1;
            Rendering.y = -1;
        }

        //alpha =
        //System.out.println(e.getX() + "/" + e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseEvent mouseEvent) {

    }
}
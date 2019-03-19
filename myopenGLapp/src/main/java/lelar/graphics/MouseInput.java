package lelar.graphics;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class MouseInput implements MouseListener {

    private static int x;
    private static int y;


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (y > e.getY()) {
            Rendering.beta += 0.001 * (e.getY() - y);
        } else if (y < e.getY()) {
            Rendering.beta -= 0.001 * (e.getY() - y);
        }
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

//        if (x > e.getX() && y > e.getY()) {
//            Rendering.alpha += 0.001 * (e.getX() - x);
//            Rendering.x = 1;
//            Rendering.y = 1;
//        }
//        else if (x < e.getX() && y < e.getY()) {
//            Rendering.alpha +=  0.001 * (e.getX() - x);
//            Rendering.x = 1;
//            Rendering.y = 1;
//        }
//        else
//            if (x < e.getX() && y > e.getY()) {
//            Rendering.alpha +=  0.001 * (e.getX() - x);
//            Rendering.x = -1;
//            Rendering.y = 1;
//        }
//        else if (x > e.getX() && y < e.getY()) {
//            Rendering.alpha +=  0.001 * (e.getX() - y);
//            Rendering.x = 1;
//            Rendering.y = -1;
//        }

        //alpha =
        //System.out.println(e.getX() + "/" + e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseEvent mouseEvent) {

    }
}
package lelar.graphics;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.newt.event.KeyEvent;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Rendering implements GLEventListener {
    private GLU glu = new GLU();

    static float horizontal;
    static float vertical;
    static float diagonal;

    static float alpha;
    static float y = 0;
    static float x = 0;



    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


        // Clear The Screen And The Depth Buffer
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the triangle
        //glu.gluLookAt(45.0f, 1.0, 20.0, -0.5f, 0.0f, -6.0f, 0, 1, 0);


        //gl.glTranslatef(horizontal, vertical, diagonal);

        //gl.glRotatef(alpha, y, x, 0);



        gl.glBegin(GL2.GL_TRIANGLES);

        //drawing triangle in all dimensions
        // Front
        gl.glColor3f(2.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Front)

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left Of Triangle (Front)

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right Of Triangle (Front)

        // Right
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Right)

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left Of Triangle (Right)

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right Of Triangle (Right)

        // Left
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Back)

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left Of Triangle (Back)

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right Of Triangle (Back)

        //left
        gl.glColor3f(0.0f, 1.0f, 0.0f); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Left)

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left Of Triangle (Left)

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right Of Triangle (Left)

        gl.glEnd(); // Done Drawing 3d triangle (Pyramid)
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {



        GL2 gl = drawable.getGL().getGL2();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();



        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}





public class MainFrame{
    JFrame jFrame = new JFrame();

    private static File fileCoordinates = new File("C:\\Users\\Lelar\\Desktop\\Java\\myopenGLapp\\src\\main\\resources\\Coordinates.csv");
    private static File fileColors = new File("C:\\Users\\Lelar\\Desktop\\Java\\myopenGLapp\\src\\main\\resources\\Colors.csv");
    private static Scanner scannerForCoordinates;
    private static Scanner scannerForColors;

    static {
        try {
            scannerForCoordinates = new Scanner(fileCoordinates);
            scannerForColors = new Scanner(fileColors);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Point> points = new ArrayList<>();

    MainFrame(GLCanvas glcanvas){
        jFrame.setLayout(new BorderLayout());
        jFrame.setVisible(true);


        jFrame.add(glcanvas);
        jFrame.setSize(jFrame.getPreferredSize());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void keyPressed(KeyEvent e) {

    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//        System.out.println("keyTyped");
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        System.out.println("keyPressed");
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_A)
//            Rendering.horizontal -= 0.1f;
//        else if (e.getKeyCode() == KeyEvent.VK_D)
//            Rendering.horizontal += 0.1f;
//        else if (e.getKeyCode() == KeyEvent.VK_Q)
//            Rendering.vertical -= 0.1f;
//        else if (e.getKeyCode() == KeyEvent.VK_E)
//            Rendering.vertical += 0.1f;
//        else if (e.getKeyCode() == KeyEvent.VK_W)
//            Rendering.diagonal -= 0.1f;
//        else if (e.getKeyCode() == KeyEvent.VK_S)
//            Rendering.diagonal += 0.1f;
//    }

    public static void main(String[] args) {
        while (scannerForCoordinates.hasNext() && scannerForColors.hasNext()) {
            Point point = new Point();

            String[] arrayOfCoordinates = scannerForCoordinates.next().split(";");

            point.setX(Float.parseFloat(arrayOfCoordinates[0]));
            point.setY(Float.parseFloat(arrayOfCoordinates[1]));
            point.setZ(Float.parseFloat(arrayOfCoordinates[2]));

            String[] arrayOfColors = scannerForColors.next().split(";");

            point.setRed(Float.parseFloat(arrayOfColors[0]));
            point.setGreen(Float.parseFloat(arrayOfColors[1]));
            point.setBlue(Float.parseFloat(arrayOfColors[2]));


            points.add(point);
        }

        System.out.println(points);

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Rendering line3d = new Rendering();
        glcanvas.addGLEventListener(line3d);
        glcanvas.setSize(640, 480);


        //creating frame
        //adding canvas to it
        final MainFrame frame = new MainFrame(glcanvas);


        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();
    }
}



package lelar.graphics;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

import java.awt.*;

public class Rendering implements GLEventListener {
    private GLU glu = new GLU();

    static float horizontal;
    static float vertical;
    static float diagonal;

    static float alpha;
    static float beta;
    static float delta;

    static float y = 0;
    static float x = 0;
    static float z = 0;

    private final float HEIGHT = 5.0f;
    private final float INNER_RADIUS = 5.0f;
    private final float DELTA_RADIUS = .3f;
    private final float EXTERNAL_RADIUS = INNER_RADIUS + DELTA_RADIUS;


    private final Color EXTERNAL_COLOR = new Color(34, 21, 133);
    private final Color INNER_COLOR = new Color(34, 139, 34);


    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glDepthFunc(gl.GL_LESS);


        // Clear The Screen And The Depth Buffer
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -20.0f); // Move the triangle


        rotate(gl);

//        gl.glColor3f(0, 1, 0);
//        gl.glVertex3f(0, 0, -HEIGHT);
//        gl.glBegin(GL2.GL_TRIANGLE_FAN);
//
//        for (int i = 0; i <= 50; i++) {
//            float a = i / 50.0f * 3.1415f * 2.0f;
//
//            gl.glVertex3f((float) Math.cos(a) * EXTERNAL_RADIUS, (float) Math.sin(a) * EXTERNAL_RADIUS, -HEIGHT);
//            gl.glVertex3f((float) Math.cos(a) * EXTERNAL_RADIUS, (float) Math.sin(a) * EXTERNAL_RADIUS, -HEIGHT);
//        }
//
//        gl.glEnd();
//        gl.glFlush();
//
//        gl.glColor3f(1, 0, 0);
//
//        gl.glVertex3f(0, 0, HEIGHT);
//        gl.glBegin(GL2.GL_TRIANGLE_FAN);
//
//        for (int i = 0; i <= 50; i++) {
//            float a = i / 50.0f * 3.1415f * 2.0f;
//
//            gl.glVertex3f((float) Math.cos(a) * EXTERNAL_RADIUS, (float) Math.sin(a) * EXTERNAL_RADIUS, HEIGHT);
//            gl.glVertex3f((float) Math.cos(a) * EXTERNAL_RADIUS, (float) Math.sin(a) * EXTERNAL_RADIUS, HEIGHT);
//        }
//
//        gl.glEnd();
//        gl.glFlush();


        drawTunnel(gl, INNER_RADIUS, INNER_COLOR);
        drawTunnel(gl, EXTERNAL_RADIUS, EXTERNAL_COLOR);


        drawCircle(gl, -HEIGHT, EXTERNAL_RADIUS, EXTERNAL_COLOR);
        //drawCircle(gl, -HEIGHT + .3f, INNER_RADIUS, INNER_COLOR);


        drawRing(gl, .3f, -HEIGHT, EXTERNAL_COLOR, false);
        drawRing(gl, .15f, HEIGHT, EXTERNAL_COLOR, true);

        //drawRing(gl, EXTERNAL_RADIUS, EXTERNAL_COLOR);


        drawHandle(gl, .5f, EXTERNAL_RADIUS, EXTERNAL_COLOR, false);

    }

    private void drawCircle(GL2 gl, float height, float radius, Color color) {
        gl.glColor3f(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);
        gl.glVertex3f(0, 0, -HEIGHT);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);

        for (int i = 0; i <= 50; i++) {
            float a = i / 50.0f * 3.1415f * 2.0f;

            gl.glVertex3f((float) Math.cos(a) * radius, (float) Math.sin(a) * radius, height);
            gl.glVertex3f((float) Math.cos(a) * radius, (float) Math.sin(a) * radius, height);
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawRing(GL2 gl, float radius, float height, Color color, boolean up) {
        gl.glColor3f(1, 0, 0);
        // gl.glColor3f(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);


        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (int k = 0, l = k + 1; k <= 50; k++, l++) {
            float ae = k / 50.0f * 3.1415f * 2.0f;
            float be = l / 50.0f * 3.1415f * 2.0f;


            float aeX = (float) Math.cos(ae) * (EXTERNAL_RADIUS - 0.3f);
            float beX = (float) Math.cos(be) * (EXTERNAL_RADIUS - 0.3f);
            float aeY = (float) Math.sin(ae) * (EXTERNAL_RADIUS - 0.3f);
            float beY = (float) Math.sin(be) * (EXTERNAL_RADIUS - 0.3f);

            float aeX2 = (float) Math.cos(ae) * (EXTERNAL_RADIUS);
            float beX2 = (float) Math.cos(be) * (EXTERNAL_RADIUS);
            float aeY2 = (float) Math.sin(ae) * (EXTERNAL_RADIUS);
            float beY2 = (float) Math.sin(be) * (EXTERNAL_RADIUS);


            for (int i = 0, j = i + 1; i <= 25; i++, j++) {
                float a = 0;
                float b = 0;


                if (up) {
                    a = i / 50.0f * 3.1415f * 2.0f;
                    b = j / 50.0f * 3.1415f * 2.0f;
                } else {
                    a = -i / 50.0f * 3.1415f * 2.0f;
                    b = -j / 50.0f * 3.1415f * 2.0f;
                }

                gl.glVertex3f(aeX, (float) Math.cos(a) * (radius - 0.3f) + aeY, (float) Math.sin(a) * (radius - 0.3f) + height);
                gl.glVertex3f(beX, (float) Math.cos(b) * (radius - 0.3f) + beY, (float) Math.sin(b) * (radius - 0.3f) + height);


                gl.glVertex3f(aeX2, (float) Math.cos(a) * radius + aeY2, (float) Math.sin(a) * radius + height);
                gl.glVertex3f(beX2, (float) Math.cos(b) * radius + beY2, (float) Math.sin(b) * radius + height);
            }
        }

        gl.glEnd();
        gl.glFlush();
    }


    private void drawHandle(GL2 gl, float radius, float height, Color color, boolean up) {
        gl.glColor3f(1, 0, 0);
        // gl.glColor3f(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);


        gl.glBegin(GL2.GL_QUAD_STRIP);
        for (int k = 0, l = k + 1; k <= 24; k++, l++) {
            float ae = k / 50.0f * 3.1415f * 2.0f;
            float be = l / 50.0f * 3.1415f * 2.0f;


            float aeX = (float) Math.cos(ae) * (HEIGHT - 1 - 0.3f);
            float beX = (float) Math.cos(be) * (HEIGHT - 1 - 0.3f);
            float aeY = (float) Math.sin(ae) * (HEIGHT - 1 - 0.3f);
            float beY = (float) Math.sin(be) * (HEIGHT - 1 - 0.3f);

            float aeX2 = (float) Math.cos(ae) * (HEIGHT - 1);
            float beX2 = (float) Math.cos(be) * (HEIGHT - 1);
            float aeY2 = (float) Math.sin(ae) * (HEIGHT - 1);
            float beY2 = (float) Math.sin(be) * (HEIGHT - 1);


            for (int i = 0, j = i + 1; i <= 50; i++, j++) {
                float a = 0;
                float b = 0;


                if (up) {
                    a = i / 50.0f * 3.1415f * 2.0f;
                    b = j / 50.0f * 3.1415f * 2.0f;
                } else {
                    a = -i / 50.0f * 3.1415f * 2.0f;
                    b = -j / 50.0f * 3.1415f * 2.0f;
                }

//                gl.glVertex3f(aeX, (float) Math.cos(a) * (radius - 0.3f) + aeY, (float) Math.sin(a) * (radius - 0.3f) + height);
////                gl.glVertex3f(beX, (float) Math.cos(b) * (radius - 0.3f) + beY, (float) Math.sin(b) * (radius - 0.3f) + height);
////
////
////                gl.glVertex3f(aeX2, (float) Math.cos(a) * radius + aeY2, (float) Math.sin(a) * radius + height);
////                gl.glVertex3f(beX2, (float) Math.cos(b) * radius + beY2, (float) Math.sin(b) * radius + height);

                gl.glVertex3f((float) Math.cos(a) * (radius - 0.3f) + aeY + height + .2f, (float) Math.sin(a) * (radius - 0.3f), aeX);
                gl.glVertex3f((float) Math.cos(b) * (radius - 0.3f) + beY + height + .2f, (float) Math.sin(b) * (radius - 0.3f), beX);


                gl.glVertex3f((float) Math.cos(a) * radius + aeY2 + height + .2f, (float) Math.sin(a) * radius, aeX2);
                gl.glVertex3f((float) Math.cos(b) * radius + beY2 + height + .2f, (float) Math.sin(b) * radius, beX2);
            }
        }

        gl.glEnd();
        gl.glFlush();
    }


    private void rotate(GL2 gl) {
        gl.glRotatef(alpha, 1, 0, 0);
        gl.glRotatef(beta, 0, 1, 0);
        gl.glRotatef(delta, 0, 0, 1);
    }

    private void drawTunnel(GL2 gl, float radius, Color color) {
        gl.glColor3f(color.getRed() / 256f, color.getGreen() / 256f, color.getBlue() / 256f);

        gl.glBegin(GL2.GL_QUAD_STRIP);

        for (int i = 0; i <= 50; i++) {
            float a = i / 50.0f * 3.1415f * 2.0f;
            int j = i + 1;
            float b = j / 50.0f * 3.1415f * 2.0f;

            gl.glVertex3f((float) Math.cos(a) * radius, (float) Math.sin(a) * radius, -HEIGHT);
            gl.glVertex3f((float) Math.cos(b) * radius, (float) Math.sin(b) * radius, -HEIGHT);

            gl.glVertex3f((float) Math.cos(a) * radius, (float) Math.sin(a) * radius, HEIGHT);
            gl.glVertex3f((float) Math.cos(b) * radius, (float) Math.sin(b) * radius, HEIGHT);
        }

        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        System.exit(0);
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();

        //gl.glDisable(GL.GL_DEPTH_TEST);

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        //glu.gluLookAt();
        glu.gluPerspective(80.0f, h, 1.0, 40.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        //gl.glDisable(GL.GL_BLEND);
        gl.glLoadIdentity();


    }
}




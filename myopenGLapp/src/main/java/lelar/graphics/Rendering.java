package lelar.graphics;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;

import java.awt.*;
import java.nio.FloatBuffer;

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


    private final float NUMBER_OF_COLOR = 256;
    private final Color EXTERNAL_COLOR = new Color(34, 21, 133);
    private final Color INNER_COLOR = new Color(34, 139, 34);
    private final Color SIDE_COLOR = Color.red;

    private GL2 gl = null;

    private float BALANCE = 100f;

    private float SHARPNESS = BALANCE / (3.1415f * 2.0f);

    @Override
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();


        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glDepthFunc(gl.GL_LESS);


        //float light0_diffuse[] = new float[]{0.4f, 0.7f, 0.2f};
        //float light0_direction[] = new float[]{1.0f, 1.0f, 1.0f, 0.0f};

        //gl.glEnable(gl.GL_LIGHT0);


        // gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, light0_diffuse);

        //gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, FloatBuffer.wrap(light0_direction));

        // gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, light0_direction);


        // gl.glEnable(gl.GL_LIGHT0);

        //float light0_position[] = new float[]{2, 2, 2, .5f};


        //gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(light0_position));




        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -20.0f); // Move the triangle


        rotate();


        drawTunnel(INNER_RADIUS, INNER_COLOR);
        //drawTunnel(EXTERNAL_RADIUS, EXTERNAL_COLOR);

        //drawCircle(EXTERNAL_RADIUS, -HEIGHT, EXTERNAL_COLOR);
        //drawCircle(INNER_RADIUS, -HEIGHT + .3f, INNER_COLOR);

        //drawHalfRing(.3f, -HEIGHT, SIDE_COLOR, false);
        //drawHalfRing(.15f, HEIGHT, SIDE_COLOR, true);

        //drawHandle(.5f, EXTERNAL_RADIUS, SIDE_COLOR);

    }

    private void drawCircle(float radius, float height, Color color) {
        setColor(color);

        gl.glBegin(GL2.GL_TRIANGLE_FAN);

       // gl.glNormal3f(0, 1, 0);
       // gl.glFrontFace(gl.GL_CCW);

        gl.glVertex3f(0, 0, -HEIGHT);

        for (int i = 0; i <= BALANCE + 1; i++) {
            float alpha = i / SHARPNESS;

            drawMargin(alpha, alpha, radius, height);
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawTunnel(float radius, Color color) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

       // gl.glNormal3f(0, 1, 0);
       // gl.glFrontFace(gl.GL_CCW);

        for (int i = 0, j = i + 1; i <= BALANCE; i++, j++) {
            float alpha = i / SHARPNESS;
            float beta = j / SHARPNESS;

            drawMargin(alpha, beta, radius, -HEIGHT);
            drawMargin(alpha, beta, radius, HEIGHT);
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawMargin(float alpha, float beta, float radius, float height) {
        gl.glVertex3f((float) Math.cos(alpha) * radius, (float) Math.sin(alpha) * radius, height);
        gl.glVertex3f((float) Math.cos(beta) * radius, (float) Math.sin(beta) * radius, height);
    }

    private void drawHalfRing(float radius, float height, Color color, boolean up) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

        for (int k = 0, l = k + 1; k <= BALANCE; k++, l++) {
            float alphaCircle = k / SHARPNESS;
            float betaCircle = l / SHARPNESS;

            float innerRadius = EXTERNAL_RADIUS - 0.3f;
            float externalRadius = EXTERNAL_RADIUS;

            Side innerSide = new Side(new Point(alphaCircle, innerRadius), new Point(betaCircle, innerRadius));
            Side externalSide = new Side(new Point(alphaCircle, externalRadius), new Point(betaCircle, externalRadius));

            for (int i = 0, j = i + 1; i <= BALANCE / 2; i++, j++) {
                float alphaBridge;
                float betaBridge;

                if (up) {
                    alphaBridge = i / SHARPNESS;
                    betaBridge = j / SHARPNESS;
                } else {
                    alphaBridge = -i / SHARPNESS;
                    betaBridge = -j / SHARPNESS;
                }

                drawMarginForHalfRing(innerSide, alphaBridge, betaBridge, radius - .3f, height);
                drawMarginForHalfRing(externalSide, alphaBridge, betaBridge, radius, height);
            }
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawMarginForHalfRing(Side side, float alpha, float beta, float radius, float height) {
        gl.glVertex3f(side.getA().getX(), (float) Math.cos(alpha) * radius + side.getA().getY(), (float) Math.sin(alpha) * radius + height);
        gl.glVertex3f(side.getB().getX(), (float) Math.cos(beta) * radius + side.getB().getY(), (float) Math.sin(beta) * radius + height);
    }

    private void drawHandle(float radius, float height, Color color) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

        for (int k = 0, l = k + 1; k < BALANCE / 2; k++, l++) {
            float alphaCircle = k / SHARPNESS;
            float betaCircle = l / SHARPNESS;

            float innerRadius = HEIGHT - 1 - 0.3f;
            float externalRadius = HEIGHT - 1;

            Side innerSide = new Side(new Point(alphaCircle, innerRadius), new Point(betaCircle, innerRadius));
            Side externalSide = new Side(new Point(alphaCircle, externalRadius), new Point(betaCircle, externalRadius));

            for (int i = 0, j = i + 1; i <= BALANCE; i++, j++) {
                float alphaBridge = i / SHARPNESS;
                float betaBridge = j / SHARPNESS;
                drawMarginForHandle(innerSide, alphaBridge, betaBridge, radius - .3f, height + .2f);
                drawMarginForHandle(externalSide, alphaBridge, betaBridge, radius, height + .2f);
            }
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawMarginForHandle(Side side, float alpha, float beta, float radius, float height) {
        gl.glVertex3f((float) Math.cos(alpha) * radius + side.getA().getY() + height, (float) Math.sin(alpha) * radius, side.getA().getX());
        gl.glVertex3f((float) Math.cos(beta) * radius + side.getB().getY() + height, (float) Math.sin(beta) * radius, side.getB().getX());
    }

    private void rotate() {
        gl.glRotatef(alpha, 1, 0, 0);
        gl.glRotatef(beta, 0, 1, 0);
        gl.glRotatef(delta, 0, 0, 1);
    }

    private void setColor(Color color) {
        gl.glColor3f(color.getRed() / NUMBER_OF_COLOR, color.getGreen() / NUMBER_OF_COLOR, color.getBlue() / NUMBER_OF_COLOR);
    }


    @Override
    public void dispose(GLAutoDrawable arg0) {
        System.exit(0);
    }

    @Override
    public void init(GLAutoDrawable arg0) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL().getGL2();

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);


       // gl.glEnable(gl.GL_LIGHTING);

       // gl.glLightModelf(gl.GL_LIGHT_MODEL_TWO_SIDE, gl.GL_TRUE);

       // gl.glEnable(gl.GL_NORMALIZE);


        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(80.0f, h, 1.0, 40.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}
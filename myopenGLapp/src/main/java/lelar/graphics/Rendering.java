package lelar.graphics;

import com.badlogic.jglfw.Glfw;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.*;
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
        //gl.glEnable(GL2.GL_NORMALIZE);

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(0.0f, 0.0f, -20.0f); // Move the triangle


        rotate();
        {

//
//            setColor(Color.);
//            gl.glBegin(GL2.GL_QUADS);
//
//            gl.glNormal3f(-2, -2, -1);
//            gl.glVertex3f(-2, -2, 0);
//
//            gl.glNormal3f(2, -2, -1);
//            gl.glVertex3f(2, -2, 0);
//
//            gl.glNormal3f(2, 2, -1);
//            gl.glVertex3f(2, 2, 0);
//
//            gl.glNormal3f(-2, 2, -1);
//            gl.glVertex3f(-2, 2, 0);
//
//
//            gl.glEnd();


            glu.gluCylinder(glu.gluNewQuadric(), 3, 3,10,30,30);

            //drawTunnel(EXTERNAL_RADIUS, EXTERNAL_COLOR, true);

            //setColor(Color.red);
            //doughnut(.15f, EXTERNAL_RADIUS - .15f, 20, 500, HEIGHT);
            //  drawTunnel(INNER_RADIUS, INNER_COLOR, true);

            //drawCircle(EXTERNAL_RADIUS, -HEIGHT, EXTERNAL_COLOR, false);
            // doughnut(.15f, EXTERNAL_RADIUS - .15f, 20, 500, -HEIGHT);
            //  drawCircle(INNER_RADIUS, -HEIGHT + .3f, INNER_COLOR, true);
//l
//            setColor(Color.red);
            //  handle(.5f, EXTERNAL_RADIUS - 2.15f, 20, 50, HEIGHT);//Ручка
//
            // drawExp(4.7, 5, 1.001f, .01f, -(HEIGHT + 1.f), Color.red);//ВНУТРЕНЯЯ
            // drawExp(4.95, 5, 1.001f, -.01f, -(HEIGHT - 1.4f), Color.red);//ВНЕШНЯЯ
//
            //drawExp2(4.9, 7, .1f, .0004f, -5.5f, 0, 3.15f, Color.red);
            //  drawExp2(4.9, 7, .1f, .0004f, -5.5f, 0, -3.15f, Color.red);
        }

        //drawHandle(.5f, EXTERNAL_RADIUS, SIDE_COLOR);
    }

    private void drawExp(double xMin, double xMax, float xScale, float zScale, float height, Color color) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

        for (int i = 0, j = i + 1; i <= BALANCE; i++, j++) {
            float alpha = i / SHARPNESS;
            float beta = j / SHARPNESS;

            for (double x = xMin; x < xMax; x += .009) {

                double z = Math.exp(x);

                //drawMargin(alpha, beta, (float) x * xScale, (float) z * zScale + height);
            }
        }

        gl.glEnd();
    }

    private void drawExp2(double xMin, double xMax, float xScale, float zScale, float dx, float dy, float dz, Color color) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

        for (int i = 0, j = i + 1; i <= BALANCE; i++, j++) {
            float alpha = i / SHARPNESS;
            float beta = j / SHARPNESS;

            for (double x = xMin; x < xMax; x += .009) {

                double z = Math.exp(x);

                gl.glVertex3f((float) (z * zScale + dx), (float) (Math.sin(alpha) * x * xScale + dy), (float) (Math.cos(alpha) * x * xScale + dz));
                gl.glVertex3f((float) (z * zScale + dx), (float) (Math.sin(beta) * x * xScale + dy), (float) (Math.cos(beta) * x * xScale + dz));
            }
        }

        gl.glEnd();
    }

    void doughnut(float r, float R, int nsides, int rings, float height) {
        int i, j;
        float theta, phi, theta1, phi1;
        float p0[] = new float[3], p1[] = new float[3], p2[] = new float[3], p3[] = new float[3];
        float n0[] = new float[3], n1[] = new float[3], n2[] = new float[3], n3[] = new float[3];

        for (i = 0; i < rings; i++) {
            theta = (float) (i * 2.0 * Math.PI / rings);
            theta1 = (float) ((i + 1) * 2.0 * Math.PI / rings);

            for (j = 0; j < nsides; j++) {
                phi = (float) (j * 2.0 * Math.PI / nsides);
                phi1 = (float) ((j + 1) * 2.0 * Math.PI / nsides);

                p0[0] = (float) (Math.cos(theta) * (R + r * Math.cos(phi)));
                p0[1] = (float) (-Math.sin(theta) * (R + r * Math.cos(phi)));
                p0[2] = (float) (r * Math.sin(phi)) + height;

                p1[0] = (float) (Math.cos(theta1) * (R + r * Math.cos(phi)));
                p1[1] = (float) (-Math.sin(theta1) * (R + r * Math.cos(phi)));
                p1[2] = (float) (r * Math.sin(phi)) + height;

                p2[0] = (float) (Math.cos(theta1) * (R + r * Math.cos(phi1)));
                p2[1] = (float) (-Math.sin(theta1) * (R + r * Math.cos(phi1)));
                p2[2] = (float) (r * Math.sin(phi1)) + height;

                p3[0] = (float) (Math.cos(theta) * (R + r * Math.cos(phi1)));
                p3[1] = (float) (-Math.sin(theta) * (R + r * Math.cos(phi1)));
                p3[2] = (float) (r * Math.sin(phi1)) + height;

                n0[0] = (float) (Math.cos(theta) * (Math.cos(phi)));
                n0[1] = (float) (-Math.sin(theta) * (Math.cos(phi)));
                n0[2] = (float) (Math.sin(phi));

                n1[0] = (float) (Math.cos(theta1) * (Math.cos(phi)));
                n1[1] = (float) (-Math.sin(theta1) * (Math.cos(phi)));
                n1[2] = (float) (Math.sin(phi));

                n2[0] = (float) (Math.cos(theta1) * (Math.cos(phi1)));
                n2[1] = (float) (-Math.sin(theta1) * (Math.cos(phi1)));
                n2[2] = (float) (Math.sin(phi1));

                n3[0] = (float) (Math.cos(theta) * (Math.cos(phi1)));
                n3[1] = (float) (-Math.sin(theta) * (Math.cos(phi1)));
                n3[2] = (float) (Math.sin(phi1));

                gl.glBegin(gl.GL_QUADS);
                gl.glNormal3fv(FloatBuffer.wrap(n3));
                gl.glVertex3fv(FloatBuffer.wrap(p3));
                gl.glNormal3fv(FloatBuffer.wrap(n2));
                gl.glVertex3fv(FloatBuffer.wrap(p2));
                gl.glNormal3fv(FloatBuffer.wrap(n1));
                gl.glVertex3fv(FloatBuffer.wrap(p1));
                gl.glNormal3fv(FloatBuffer.wrap(n0));
                gl.glVertex3fv(FloatBuffer.wrap(p0));
                gl.glEnd();
            }
        }
    }

    void handle(float r, float R, int nsides, int rings, float height) {
        int i, j;
        float theta, phi, theta1, phi1;
        float p0[] = new float[3], p1[] = new float[3], p2[] = new float[3], p3[] = new float[3];
        float n0[] = new float[3], n1[] = new float[3], n2[] = new float[3], n3[] = new float[3];

        double a = 1;

        for (i = 0; i < rings; i++) {
            theta = (float) (i * a * Math.PI / rings);
            theta1 = (float) ((i + 1) * a * Math.PI / rings);

            for (j = 0; j < nsides; j++) {
                phi = (float) (j * 2.0 * Math.PI / nsides);
                phi1 = (float) ((j + 1) * 2.0 * Math.PI / nsides);

                float h = -5.25f;

                p0[2] = (float) (Math.cos(theta) * (R + r * Math.cos(phi)));
                p0[0] = (float) (-Math.sin(theta) * (R + r * Math.cos(phi))) + h;
                p0[1] = (float) (r * Math.sin(phi));

                p1[2] = (float) (Math.cos(theta1) * (R + r * Math.cos(phi)));
                p1[0] = (float) (-Math.sin(theta1) * (R + r * Math.cos(phi))) + h;
                p1[1] = (float) (r * Math.sin(phi));

                p2[2] = (float) (Math.cos(theta1) * (R + r * Math.cos(phi1)));
                p2[0] = (float) (-Math.sin(theta1) * (R + r * Math.cos(phi1))) + h;
                p2[1] = (float) (r * Math.sin(phi1));

                p3[2] = (float) (Math.cos(theta) * (R + r * Math.cos(phi1)));
                p3[0] = (float) (-Math.sin(theta) * (R + r * Math.cos(phi1))) + h;
                p3[1] = (float) (r * Math.sin(phi1));


                n0[0] = (float) (Math.cos(theta) * (Math.cos(phi)));
                n0[1] = (float) (-Math.sin(theta) * (Math.cos(phi)));
                n0[2] = (float) (Math.sin(phi));

                n1[0] = (float) (Math.cos(theta1) * (Math.cos(phi)));
                n1[1] = (float) (-Math.sin(theta1) * (Math.cos(phi)));
                n1[2] = (float) (Math.sin(phi));

                n2[0] = (float) (Math.cos(theta1) * (Math.cos(phi1)));
                n2[1] = (float) (-Math.sin(theta1) * (Math.cos(phi1)));
                n2[2] = (float) (Math.sin(phi1));

                n3[0] = (float) (Math.cos(theta) * (Math.cos(phi1)));
                n3[1] = (float) (-Math.sin(theta) * (Math.cos(phi1)));
                n3[2] = (float) (Math.sin(phi1));

                gl.glBegin(gl.GL_QUADS);
                gl.glNormal3fv(FloatBuffer.wrap(n3));
                gl.glVertex3fv(FloatBuffer.wrap(p3));
                gl.glNormal3fv(FloatBuffer.wrap(n2));
                gl.glVertex3fv(FloatBuffer.wrap(p2));
                gl.glNormal3fv(FloatBuffer.wrap(n1));
                gl.glVertex3fv(FloatBuffer.wrap(p1));
                gl.glNormal3fv(FloatBuffer.wrap(n0));
                gl.glVertex3fv(FloatBuffer.wrap(p0));
                gl.glEnd();
            }
        }
    }

    private void drawCircle(float radius, float height, Color color, boolean zNormal) {
        setColor(color);

        gl.glBegin(GL2.GL_TRIANGLE_FAN);

        // gl.glFrontFace(gl.GL_C);

        float n;
        if (zNormal)
            n = 1;
        else
            n = -1;


        for (int i = 0; i <= BALANCE + 1; i++) {
            float alpha = i / SHARPNESS;

            gl.glNormal3f((float) Math.cos(alpha), (float) Math.sin(alpha), n);
            gl.glVertex3f((float) Math.cos(alpha) * radius, (float) Math.sin(alpha) * radius, height);
        }

        gl.glEnd();
        gl.glFlush();
    }

    private void drawTunnel(float radius, Color color, boolean zNormal) {
        setColor(color);
        gl.glBegin(GL2.GL_QUAD_STRIP);

        float n;
        if (zNormal)
            n = 1;
        else
            n = -1;

        //for (float k = -HEIGHT; k < HEIGHT; k += .1f) {
        for (float i = 0; i <= BALANCE; i += .01f) {

            float alpha = i / SHARPNESS;
            float beta = (i + 1) / SHARPNESS;

            // normal(alpha, alpha, n);
            drawMargin(alpha, beta, radius, -HEIGHT);

            drawMargin(alpha, beta, radius, HEIGHT);
        }
        //}
        gl.glEnd();
        gl.glFlush();
    }

    private void normal(float alpha, float beta, float z) {
        gl.glNormal3f((float) Math.cos(alpha), (float) Math.sin(beta), z);
    }

    private void drawMargin(float alpha, float beta, float radius, float height) {
        gl.glNormal3f((float) Math.cos(alpha), 0, (float) Math.sin(alpha));

        gl.glVertex3f((float) Math.cos(alpha) * radius, (float) Math.sin(alpha) * radius, height);

        gl.glNormal3f((float) Math.cos(alpha), 0, (float) Math.sin(beta));

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

            for (int i = 0, j = i + 1; i <= BALANCE; i++, j++) {
                float alphaBridge;
                float betaBridge;

                if (up) {
                    alphaBridge = i / SHARPNESS;
                    betaBridge = j / SHARPNESS;
                } else {
                    alphaBridge = -i / SHARPNESS;
                    betaBridge = -j / SHARPNESS;
                }


//                p3[0] = (float) (Math.cos(theta) * (R + r * Math.cos(phi1)));
//                p3[1] = (float) (-Math.sin(theta) * (R + r * Math.cos(phi1)));
//                p3[2] = (float) (r * Math.sin(phi1));

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

        for (int k = 0, l = k + 1; k < BALANCE; k++, l++) {
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


        gl.glEnable(GL.GL_MULTISAMPLE);


        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(80.0f, h, 1.0, 40.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();


        //gl.glEnable(GL2.GL_LIGHT0);


        //gl.glEnable(GL2.GL_NORMALIZE);


        float[] light0_position = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
        float[] light0_diffuse = new float[]{1.f, 0.f, 0f, 1};

        float[] light0_ambient = new float[]{1.f, 0.0f, 0.0f, 0.0f};

        float[] light0_specular = new float[]{1.0f, 1.0f, 1.0f, 1f};

         gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, FloatBuffer.wrap(light0_position));
        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, FloatBuffer.wrap(light0_ambient));
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, FloatBuffer.wrap(light0_diffuse));
        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, FloatBuffer.wrap(light0_specular));


        //  gl.glLightf(GL2.GL_LIGHT0, GL2.GL_CONSTANT_ATTENUATION, 0.0f);
        //gl.glLightf(GL2.GL_LIGHT0, GL2.GL_LINEAR_ATTENUATION, 0.2f);
        //gl.glLightf(GL2.GL_LIGHT0, GL2.GL_QUADRATIC_ATTENUATION, 0.4f);

        //float[] ambient = new float[]{ 0.5f, 0.5f, 0.5f, 1};
        //gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(ambient));
        //gl.glLightfv();


    }

}
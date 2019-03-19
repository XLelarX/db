package lelar.graphics;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
public class TR {


    public static int width = 640;
    public static int height = 480;

    public static GLWindow window;

    public static void main(String... args) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        window = GLWindow.create(glCapabilities);
        window.setSize(width, height);
        window.setResizable(false);

        window.addGLEventListener(new Rendering());

        window.addMouseListener(new MouseInput());
        window.addKeyListener(new KeyboardInput());

        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);


        final FPSAnimator animator = new FPSAnimator(window, 60);

        animator.start();


        window.setVisible(true);
        window.requestFocus(true);
    }
}

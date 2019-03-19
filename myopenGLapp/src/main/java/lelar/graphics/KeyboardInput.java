package lelar.graphics;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A)
            Rendering.beta -= 1.5f;
        else if (e.getKeyCode() == KeyEvent.VK_D)
            Rendering.beta += 1.5f;
        else if (e.getKeyCode() == KeyEvent.VK_W)
            Rendering.alpha -= 1.5f;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            Rendering.alpha += 1.5f;
        else if (e.getKeyCode() == KeyEvent.VK_E)
            Rendering.delta -= 1.5f;
        else if (e.getKeyCode() == KeyEvent.VK_Q)
            Rendering.delta += 1.5f;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
        // Rendering.y = 0;

    }
}

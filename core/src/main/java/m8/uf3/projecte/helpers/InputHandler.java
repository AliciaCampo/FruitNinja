package m8.uf3.projecte.helpers;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
public class InputHandler implements InputProcessor {
    private List<Vector2> trayectoCorte; // Guarda los puntos que tocas o deslizas
    public InputHandler() {
        trayectoCorte = new ArrayList<>();
    }
    public List<Vector2> getTrayectoCorte() {
        return trayectoCorte;
    }
    public void limpiarTrayecto() {
        trayectoCorte.clear();
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        trayectoCorte.add(new Vector2(screenX, invertY(screenY)));
        return true;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        trayectoCorte.add(new Vector2(screenX, invertY(screenY)));
        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }
    private float invertY(int screenY) {
        return (float) (com.badlogic.gdx.Gdx.graphics.getHeight() - screenY);
    }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    // Métodos que no usamos pero hay que implementar
    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
}

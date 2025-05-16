package m8.uf3.projecte.actors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Bomb extends GameObject {
    private final Texture texturaExplosión;
    private boolean explotada = false;
    public boolean isExplotada() {
        return explotada;
    }
    private float temporizadorExplosión = 0f;
    private static final float DURACIÓN_EXPLOSIÓN = 3f;
    public Bomb(Texture textura, Texture texExplosión,
                float x, float y,
                float velX, float velY) {
        super(textura, x, y, velX, velY, textura.getWidth() / 2f);
        this.texturaExplosión = texExplosión;
    }
    public void explotar() {
        if (!explotada) {
            explotada = true;
            this.textura = texturaExplosión;
            this.velocidad.set(0f, 0f);
        }
    }
    @Override
    public void actualizar(float delta) {
        if (explotada) {
            temporizadorExplosión += delta;
        } else {
            super.actualizar(delta);
        }
    }
    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y);
    }
    @Override
    public boolean esPeligroso() {
        return true;
    }
    public boolean haTerminadoExplosión() {
        return explotada && temporizadorExplosión >= DURACIÓN_EXPLOSIÓN;
    }
}

package m8.uf3.projecte.actors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public class Fruit extends GameObject {
    private final Texture texturaCortada;
    private boolean cortada = false;
    public boolean isCortada() {
        return cortada;
    }
    public Fruit(Texture texturaEntera,
                 Texture texturaCortada,
                 float x, float y,
                 float velX, float velY) {
        super(texturaEntera, x, y, velX, velY, texturaEntera.getWidth() / 2f);
        this.texturaCortada = texturaCortada;
    }
    public void cortar() {
        if (!cortada) {
            cortada = true;
            this.textura = texturaCortada;
            this.velocidad.set(0, -200f);
        }
    }
    @Override
    public void actualizar(float delta) {
        super.actualizar(delta);
        // Asegura que, una vez cortada, no vuelva a subir
        if (cortada && velocidad.y > 0) {
            velocidad.y = -200f;
        }
    }
    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(
            textura,
            posicion.x,
            posicion.y,
            textura.getWidth() * 5f,
            textura.getHeight() * 5f
        );
    }
    @Override
    public Circle getCirculoColision() {
        return new Circle(
            posicion.x + textura.getWidth() / 4f,
            posicion.y + textura.getHeight() / 4f,
            radio * 4.0f
        );
    }
    @Override
    public boolean esPeligroso() {
        return false;
    }
    public boolean estaDesaparecida() {
        return cortada && posicion.y + textura.getHeight() < 0;
    }
}

package m8.uf3.projecte.actors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
public abstract class GameObject {
    protected Texture textura;
    protected Vector2 posicion;
    protected Vector2 velocidad;
    protected float radio; // para colisiones
    public GameObject(Texture textura, float x, float y, float velX, float velY, float radio) {
        this.textura = textura;
        this.posicion = new Vector2(x, y);
        this.velocidad = new Vector2(velX, velY);
        this.radio = radio;
    }
    public void actualizar(float delta) {
        posicion.x += velocidad.x * delta;
        posicion.y += velocidad.y * delta;
        velocidad.y -= 500 * delta; // gravedad simple
    }
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, posicion.x, posicion.y);
    }
    public boolean haSidoCortado(Vector2 puntoCorte) {
        return getCirculoColision().contains(puntoCorte);
    }
    public Circle getCirculoColision() {
        return new Circle(
            posicion.x + textura.getWidth() / 2f,
            posicion.y + textura.getHeight() / 2f,
            radio * 2.0f
        );
    }
    public boolean estaFueraPantalla() {
        return posicion.y + textura.getHeight() < 0;
    }
    public abstract boolean esPeligroso(); // true si es bomba
    public void dispose() {
        textura.dispose();
    }
}

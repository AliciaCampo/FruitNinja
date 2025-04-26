package m8.uf3.projecte.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Una fruta que, al ser cortada, cambia su textura por la cortada y cae.
 */
public class Fruit extends GameObject {

    private final Texture texturaCortada;
    private boolean cortada = false;

    /**
     * @param texturaEntera  textura antes del corte
     * @param texturaCortada textura tras el corte
     * @param x             posición inicial X
     * @param y             posición inicial Y
     * @param velX          velocidad inicial X (para lanzar)
     * @param velY          velocidad inicial Y (para lanzar)
     */
    public Fruit(Texture texturaEntera,
                 Texture texturaCortada,
                 float x, float y,
                 float velX, float velY) {
        super(texturaEntera, x, y, velX, velY, texturaEntera.getWidth() / 2f);
        this.texturaCortada = texturaCortada;
    }

    /**
     * Marca la fruta como cortada:
     * 1) Cambia la textura
     * 2) Ajusta la velocidad para que caiga
     */
    public void cortar() {
        if (!cortada) {
            cortada = true;
            this.textura = texturaCortada;
            // Empujón hacia abajo al cortarla
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
        batch.draw(textura, posicion.x, posicion.y);
    }

    @Override
    public boolean esPeligroso() {
        return false;
    }

    /**
     * Para saber cuándo borrar la fruta de la lista:
     */
    public boolean estaDesaparecida() {
        return cortada && posicion.y + textura.getHeight() < 0;
    }
}

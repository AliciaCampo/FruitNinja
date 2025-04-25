package m8.uf3.projecte.actors;

import com.badlogic.gdx.graphics.Texture;
public class Bomb extends GameObject {
    public Bomb(Texture textura, float x, float y, float velX, float velY) {
        super(textura, x, y, velX, velY, textura.getWidth() / 2f);
    }
    @Override
    public boolean esPeligroso() {
        return true;
    }
}

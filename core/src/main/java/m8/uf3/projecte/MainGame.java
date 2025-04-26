package m8.uf3.projecte;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import m8.uf3.projecte.helpers.AssetManager;
import m8.uf3.projecte.screens.GameScreen;
import m8.uf3.projecte.screens.StartScreen;
public class MainGame extends Game {
    public SpriteBatch batch;
    @Override
    public void create() {
        AssetManager.load();
        batch = new SpriteBatch();
        setScreen(new StartScreen(this));
    }
    @Override
    public void dispose() {
        super.dispose();
        if (batch != null) batch.dispose();
        AssetManager.dispose();
    }
}

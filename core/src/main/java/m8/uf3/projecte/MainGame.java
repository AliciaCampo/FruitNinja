package m8.uf3.projecte;

import com.badlogic.gdx.Game;
import m8.uf3.projecte.helpers.AssetManager;

import m8.uf3.projecte.screens.StartScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen(new StartScreen(this));

    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}

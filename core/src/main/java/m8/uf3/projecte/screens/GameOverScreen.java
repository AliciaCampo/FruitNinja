package m8.uf3.projecte.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import m8.uf3.projecte.MainGame;
import m8.uf3.projecte.helpers.AssetManager;

public class GameOverScreen implements Screen {
    private Stage stage;
    private TextButton reloadButton;
    private TextButton exitButton;
    private Music music;
    private Label gameOverLabel;
    private Label scoreLabel;
    private int punts;
    private MainGame game;

    private float scaleDirection = 1;
    private float currentScale = 7;

    public GameOverScreen(MainGame game, int punts){
        this.game = game;
        this.punts = punts;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Image backgroundimage = new Image(AssetManager.fondoJuego);
        backgroundimage.setFillParent(true);
        stage.addActor(backgroundimage);

        Label.LabelStyle labelStyle = new Label.LabelStyle(AssetManager.font, null);
        scoreLabel = new Label("Score: " + punts, labelStyle);
        scoreLabel.setFontScale(3);
        scoreLabel.pack(); // <-- Important!
        scoreLabel.setPosition(
            (stage.getWidth() - scoreLabel.getWidth()) / 2f,
            (stage.getHeight() - scoreLabel.getHeight()) / 2f
        );
        stage.addActor(scoreLabel);

        gameOverLabel = new Label("Game Over", labelStyle);
        gameOverLabel.setFontScale(currentScale);
        gameOverLabel.pack();
        gameOverLabel.setPosition(
            (stage.getWidth() - gameOverLabel.getWidth()) / 2,
            stage.getHeight() / 2 + 100
        );
        stage.addActor(gameOverLabel);


        //Texture
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();
        Texture pixmapTexture = new Texture(pixmap);
        Drawable background = new TextureRegionDrawable(new TextureRegion(pixmapTexture));
        pixmap.dispose();

        //Style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = AssetManager.fontW;
        buttonStyle.up = background;

        //Buttons
        reloadButton = new TextButton("Tornar a jugar", buttonStyle);
        reloadButton.setSize(500, 75);
        reloadButton.getLabel().setFontScale(1.5f);
        reloadButton.setPosition((stage.getWidth() - reloadButton.getWidth()) / 2, stage.getHeight() / 2 - 200);
        stage.addActor(reloadButton);

        exitButton = new TextButton("Sortir", buttonStyle);
        exitButton.setSize(200, 75);
        exitButton.getLabel().setFontScale(1.5f);
        exitButton.setPosition((stage.getWidth() - exitButton.getWidth()) / 2, stage.getHeight() / 2 - 300);
        stage.addActor(exitButton);

        reloadButton.addListener(new ClickListener() {
            @Override
                public void clicked(InputEvent event, float x, float y) {
                music.stop();
                game.setScreen(new GameScreen(game));
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                Gdx.app.exit();
            }
        });

        music = AssetManager.musicaFondo;
        if (music != null){
            music.setLooping(true);
            music.setVolume(0.7f);
        }
    }

    @Override
    public void show() {
        if (music != null){
            music.play();
        }
        //Preguntar si volen el num de vides i punts o si no afecta.
    }

    @Override
    public void render(float delta) {
        currentScale += scaleDirection * delta * 0.3f;

        if (currentScale > 7.3f) {
            currentScale = 7.3f;
            scaleDirection = -1;
        } else if (currentScale < 6.7f) {
            currentScale = 6.7f;
            scaleDirection = 1;
        }

        gameOverLabel.setFontScale(currentScale);
        gameOverLabel.pack();
        gameOverLabel.setPosition(
            (stage.getWidth() - gameOverLabel.getWidth()) / 2,
            stage.getHeight() / 2
        );

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        if (music != null){
            music.dispose();
        }
    }
}

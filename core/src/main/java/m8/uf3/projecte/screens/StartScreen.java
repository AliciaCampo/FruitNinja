package m8.uf3.projecte.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import m8.uf3.projecte.MainGame;
import m8.uf3.projecte.helpers.AssetManager;

public class StartScreen implements Screen {
    private Stage stage;
    private MainGame game;
    private Label textLbl;
    private TextButton startButton;
    private Music music;

    public StartScreen(MainGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Image fondoImage = new Image(AssetManager.fondoStart);
        fondoImage.setFillParent(true);
        stage.addActor(fondoImage);

        Label.LabelStyle textStyle = new Label.LabelStyle(AssetManager.font, null);
        textLbl = new Label("Fruit Ninja", textStyle);
        textLbl.setFontScale(7);
        textLbl.pack();
        textLbl.setPosition((stage.getWidth() - textLbl.getWidth()) / 2, stage.getHeight() / 2);
        stage.addActor(textLbl);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();
        Texture pixmapTexture = new Texture(pixmap);
        Drawable background = new TextureRegionDrawable(new TextureRegion(pixmapTexture));
        pixmap.dispose();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = AssetManager.fontW;
        buttonStyle.up = background;

        startButton = new TextButton("Jugar", buttonStyle);
        startButton.setSize(300, 100);
        startButton.getLabel().setFontScale(1.5f);
        startButton.setPosition((stage.getWidth() - startButton.getWidth()) / 2, stage.getHeight() / 2 - 200);
        stage.addActor(startButton);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                game.setScreen(new GameScreen(game));
            }
        });

        music = AssetManager.musicaFondo;
        if (music != null) {
            music.setLooping(true);
            music.setVolume(0.5f);
        }
    }

    @Override
    public void show() {
        if (music != null) {
            music.play();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        if (music != null) {
            music.dispose();
        }
    }
}

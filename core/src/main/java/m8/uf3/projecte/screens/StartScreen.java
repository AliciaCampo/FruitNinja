package m8.uf3.projecte.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    private float scaleDirection = 1;
    private float currentScale = 7;

    public StartScreen(MainGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Fondo de la pantalla
        Image fondoImage = new Image(AssetManager.fondoStart);
        fondoImage.setFillParent(true);
        stage.addActor(fondoImage);

        // Estilo para el texto
        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = AssetManager.font;
        textLbl = new Label("Fruit Ninja", textStyle);
        textLbl.setFontScale(7);
        textLbl.pack();
        textLbl.setPosition((stage.getWidth() - textLbl.getWidth()) / 2, stage.getHeight() / 2);
        stage.addActor(textLbl);

        // Crea un fondo para el botón
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0f, 0f, 1f);
        pixmap.fill();
        Texture pixmapTexture = new Texture(pixmap);
        Drawable background = new TextureRegionDrawable(new TextureRegion(pixmapTexture));
        pixmap.dispose();

        // Estilo para el botón de inicio
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

        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
        BitmapFont font = new BitmapFont(Gdx.files.internal("ui/default.fnt"));
        skin.add("default", font);

        TextButton.TextButtonStyle helpButtonStyle = new TextButton.TextButtonStyle();
        helpButtonStyle.font = skin.getFont("default");
        helpButtonStyle.up = skin.getDrawable("default-round");
        TextButton helpButton = new TextButton("?", helpButtonStyle);
        helpButton.setSize(80, 80);
        helpButton.getLabel().setFontScale(2f);
        helpButton.setPosition(stage.getWidth() - helpButton.getWidth() - 60, stage.getHeight() - helpButton.getHeight() - 40);
        stage.addActor(helpButton);

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog helpDialog = new Dialog("Ayuda", skin) {
                    @Override
                    protected void result(Object object) {
                        super.result(object);
                    }
                };

                helpDialog.getContentTable().setSize(10000, 5800);
                helpDialog.setSize(10000, 5800);

                Label.LabelStyle labelStyle = new Label.LabelStyle();
                labelStyle.font = skin.getFont("default");
                if (labelStyle.font == null) {
                    labelStyle.font = AssetManager.font;
                }

                Label helpText = new Label("Benvingut a Fruit Ninja!\n\n- Arrossega per tallar la fruita.\n- Evita les bombes.\n- Aconsegueix la puntuacio mes alta possible.\n\nBona sort!", skin);
                helpText.setStyle(labelStyle);
                helpText.setFontScale(3f);
                helpDialog.getContentTable().add(helpText).pad(20);

                TextButton.TextButtonStyle closeButtonStyle = new TextButton.TextButtonStyle();
                closeButtonStyle.font = skin.getFont("default");
                closeButtonStyle.up = skin.getDrawable("default-round");
                TextButton closeButton = new TextButton("Tancar", closeButtonStyle);
                closeButton.getLabel().setFontScale(2f);
                helpDialog.button(closeButton);
                helpDialog.show(stage);
            }
        });
    }

    @Override
    public void show() {
        if (music != null) {
            music.play();
        }
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

        textLbl.setFontScale(currentScale);
        textLbl.pack();
        textLbl.setPosition(
            (stage.getWidth() - textLbl.getWidth()) / 2,
            stage.getHeight() / 2
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        if (music != null) {
            music.dispose();
        }
    }
}

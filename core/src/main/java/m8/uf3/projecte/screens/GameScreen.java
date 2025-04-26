package m8.uf3.projecte.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import m8.uf3.projecte.MainGame;
import m8.uf3.projecte.helpers.AssetManager;
import m8.uf3.projecte.helpers.InputHandler;
import m8.uf3.projecte.actors.GameObject;
import m8.uf3.projecte.actors.Fruit;
import m8.uf3.projecte.actors.Bomb;
public class GameScreen implements Screen {
    private final MainGame juego;
    private final SpriteBatch lote;
    private InputHandler manejadorEntrada;
    private final List<GameObject> objetos;
    private float temporizadorFruta = 0f;
    private float temporizadorBomba = 0f;
    private int puntuacion = 0;
    private int vidas = 3;
    public GameScreen(MainGame juego) {
        this.juego = juego;
        this.lote = juego.batch;
        this.objetos = new LinkedList<>();
    }
    @Override
    public void show() {
        manejadorEntrada = new InputHandler();
        Gdx.input.setInputProcessor(manejadorEntrada);
        AssetManager.musicaFondo.setLooping(true);
    }
    @Override
    public void render(float delta) {
        actualizar(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        lote.begin();
        lote.draw(
            AssetManager.fondoJuego,
            0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        for (GameObject obj : objetos) {
            obj.dibujar(lote);
        }

        int margen = 30;
        int tamañoIcono = 80;
        lote.draw(
            AssetManager.moneda,
            margen,                                      // x: margen izquierdo
            Gdx.graphics.getHeight() - margen - tamañoIcono, // y: desde arriba hacia abajo
            tamañoIcono,
            tamañoIcono
        );

        AssetManager.font.getData().setScale(3f);
        AssetManager.font.draw(
            lote,
            String.valueOf(puntuacion),
            margen + tamañoIcono + 20,                       // x: a la derecha de la moneda + espacio
            Gdx.graphics.getHeight() - margen - 0           // y: un poquito más bajo para alinear bonito
        );
        AssetManager.font.getData().setScale(1f);

        for (int i = 0; i < vidas; i++) {
            lote.draw(
                AssetManager.corazon,
                Gdx.graphics.getWidth() - margen - tamañoIcono - i * (tamañoIcono + 10),  // x: de derecha a izquierda
                Gdx.graphics.getHeight() - margen - tamañoIcono,                          // y: desde arriba hacia abajo
                tamañoIcono,
                tamañoIcono
            );
        }

        lote.end();
    }


    private void actualizar(float delta) {
        temporizadorFruta += delta;
        temporizadorBomba  += delta;
        if (temporizadorFruta > 0.8f) {
            generarFruta();
            temporizadorFruta = 0f;
        }
        if (temporizadorBomba > 3f) {
            generarBomba();
            temporizadorBomba = 0f;
        }
        for (Vector2 punto : manejadorEntrada.getTrayectoCorte()) {
            Iterator<GameObject> iter = objetos.iterator();
            while (iter.hasNext()) {
                GameObject obj = iter.next();
                if (obj.haSidoCortado(punto)) {
                    if (obj.esPeligroso()) {
                        AssetManager.explBomba.play();
                        vidas--;
                        iter.remove();
                        if (vidas <= 0) {
                            AssetManager.musicaFondo.stop();
                            //juego.setScreen(new GameOverScreen(juego, puntuacion)); Pasar los datos a GameOverScreen
                            return;
                        }
                    } else {
                        AssetManager.corte.play();
                        puntuacion++;
                        iter.remove();
                    }
                }
            }
        }
        manejadorEntrada.limpiarTrayecto();
        Iterator<GameObject> iter2 = objetos.iterator();
        while (iter2.hasNext()) {
            GameObject obj = iter2.next();
            obj.actualizar(delta);
            if (obj.estaFueraPantalla()) {
                iter2.remove();
            }
        }
    }
    private void generarFruta() {
        int idx = MathUtils.random(0, 6);
        Texture entera, cortada;
        switch (idx) {
            case 0:
                entera = AssetManager.manzana;
                cortada = AssetManager.manzanaC;
                break;
            case 1:
                entera = AssetManager.pera;
                cortada = AssetManager.peraC;
                break;
            case 2:
                entera = AssetManager.limon;
                cortada = AssetManager.limonC;
                break;
            case 3:
                entera = AssetManager.naranja;
                cortada = AssetManager.naranjaC;
                break;
            case 4:
                entera = AssetManager.sandia;
                cortada = AssetManager.sandiaC;
                break;
            case 5:
                entera = AssetManager.melon;
                cortada = AssetManager.melonC;
                break;
            default:
                entera = AssetManager.pina;
                cortada = AssetManager.pinaC;
        }
        float x = MathUtils.random(100, Gdx.graphics.getWidth() - 100);
        float y = -entera.getHeight();
        float velX = MathUtils.random(-250f, 250f);
        float velY = MathUtils.random(900f, 1100f);
        objetos.add(new Fruit(entera, cortada, x, y, velX, velY));
    }
    private void generarBomba() {
        Texture entera = AssetManager.bomba;
        Texture explTex = AssetManager.explosion;  // la textura de la explosión
        float x = MathUtils.random(50, Gdx.graphics.getWidth() - 50);
        float y = -entera.getHeight();
        float velX = MathUtils.random(-80f, 80f);
        float velY = MathUtils.random(700f, 900f);
        objetos.add(new Bomb(entera, explTex, x, y, velX, velY));
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        AssetManager.musicaFondo.stop();
    }
}

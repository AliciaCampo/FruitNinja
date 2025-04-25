package m8.uf3.projecte.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetManager {
    public  static Texture manzana, pera, limon, naranja, sandia, melon, pina, bomba, moneda, corazon, explosion;
    public static Texture manzanaC, peraC, limonC, naranjaC, sandiaC, melonC, pinaC;
    public static Texture fondoStart, fondoJuego;
    public static Sound explBomba, musicaFondo, corte;
    public static BitmapFont font;
    public static void load() {
        manzana = new Texture(Gdx.files.internal("manzana_entera.png"));
        pera = new Texture(Gdx.files.internal("pera_entera.png"));
        limon = new Texture(Gdx.files.internal("limon_entero.png"));
        naranja = new Texture(Gdx.files.internal("naranja_entera.png"));
        sandia = new Texture(Gdx.files.internal("sandia_entera.png"));
        melon = new Texture(Gdx.files.internal("melon_entero.png"));
        pina = new Texture(Gdx.files.internal("pina_entera.png"));
        bomba = new Texture(Gdx.files.internal("bomb.png"));
        moneda = new Texture(Gdx.files.internal("coin.png"));
        corazon = new Texture(Gdx.files.internal("heart.png"));
        explosion = new Texture(Gdx.files.internal("explosion.png"));
        manzanaC = new Texture(Gdx.files.internal("manzana_cortada.png"));
        peraC = new Texture(Gdx.files.internal("pera_cortada"));
        limonC = new Texture(Gdx.files.internal("limon_cortado.png"));
        naranjaC = new Texture(Gdx.files.internal("naranja_cortada.png"));
        sandiaC = new Texture(Gdx.files.internal("sandia_cortada.png"));
        melonC = new Texture(Gdx.files.internal("melon_cortado.png"));
        pinaC = new Texture(Gdx.files.internal("pina_cortada.png"));
        fondoStart = new Texture(Gdx.files.internal("fondo_inicio.png"));
        fondoJuego = new Texture(Gdx.files.internal("fondo_juego2.png"));
        explBomba = Gdx.audio.newSound(Gdx.files.internal("bombExplodeFinal.wav"));
        musicaFondo = Gdx.audio.newSound(Gdx.files.internal("fruitNinjaMusicBackgroundExtended.mp3"));
        corte = Gdx.audio.newSound(Gdx.files.internal("swordSwipe2.wav"));
    }

    public static void dispose() {
        manzana.dispose();
        pera.dispose();
        limon.dispose();
        naranja.dispose();
        sandia.dispose();
        melon.dispose();
        pina.dispose();
        bomba.dispose();
        moneda.dispose();
        corazon.dispose();
        explosion.dispose();
        manzanaC.dispose();
        peraC.dispose();
        limonC.dispose();
        naranjaC.dispose();
        sandiaC.dispose();
        melonC.dispose();
        pinaC.dispose();
        fondoStart.dispose();
        fondoJuego.dispose();
        explBomba.dispose();
        musicaFondo.dispose();
        corte.dispose();
        font.dispose();
    }
}

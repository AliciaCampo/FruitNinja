package m8.uf3.projecte.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
public class AssetManager {
    public  static Texture manzana, pera, limon, naranja, sandia, melon, pina, bomba, moneda, corazon, explosion;
    public static Texture manzanaC, peraC, limonC, naranjaC, sandiaC, melonC, pinaC;
    public static Texture fondoStart, fondoJuego;
    public static Sound explBomba, corte;
    public static Music musicaFondo;
    public static BitmapFont font, fontW;
    public static void load() {
        manzana = new Texture(Gdx.files.internal("images/manzana_entera.png"));
        pera = new Texture(Gdx.files.internal("images/pera_entera.png"));
        limon = new Texture(Gdx.files.internal("images/limon_entero.png"));
        naranja = new Texture(Gdx.files.internal("images/naranja_entera.png"));
        sandia = new Texture(Gdx.files.internal("images/sandia_entera.png"));
        melon = new Texture(Gdx.files.internal("images/melon_entero.png"));
        pina = new Texture(Gdx.files.internal("images/pina_entera.png"));
        bomba = new Texture(Gdx.files.internal("images/bomb.png"));
        moneda = new Texture(Gdx.files.internal("images/coin.png"));
        corazon = new Texture(Gdx.files.internal("images/heart.png"));
        explosion = new Texture(Gdx.files.internal("images/explosion.png"));
        manzanaC = new Texture(Gdx.files.internal("images/manzana_cortada.png"));
        peraC = new Texture(Gdx.files.internal("images/pera_cortada.png"));
        limonC = new Texture(Gdx.files.internal("images/limon_cortado.png"));
        naranjaC = new Texture(Gdx.files.internal("images/naranja_cortada.png"));
        sandiaC = new Texture(Gdx.files.internal("images/sandia_cortada.png"));
        melonC = new Texture(Gdx.files.internal("images/melon_cortado.png"));
        pinaC = new Texture(Gdx.files.internal("images/pina_cortada.png"));
        fondoStart = new Texture(Gdx.files.internal("images/fondo_inicio.jpeg"));
        fondoJuego = new Texture(Gdx.files.internal("images/fondo_juego2.png"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("music/fruitNinjaMusicBackgroundExtended.mp3"));
        explBomba = Gdx.audio.newSound(Gdx.files.internal("music/bombExplodeFinal.wav"));
        corte = Gdx.audio.newSound(Gdx.files.internal("music/swordSwipe2.wav"));
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        fontW = new BitmapFont(Gdx.files.internal("font/fontwhite.fnt"));
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
        musicaFondo.dispose();
        explBomba.dispose();
        corte.dispose();
        font.dispose();
        fontW.dispose();
    }
}

package principal.entes;

import java.awt.Graphics;

import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;

public class KinRojo extends Enemigo {
	private static HojaSprites hojaKinRojo;

	public KinRojo(final int idEnemigo, final String nombre, final int vidaMaxima, final String rutaLamneto) {
		super(idEnemigo, nombre, vidaMaxima, rutaLamneto);

		if (hojaKinRojo == null) {
			hojaKinRojo = new HojaSprites(Constantes.RUTA_ENEMIGOS + idEnemigo + ".png", Constantes.LADO_SPRITE, false);
		}
	}

	public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
		DibujoDebug.dibujarImagen(g, hojaKinRojo.obtenerSprite(0).obtenerImagen(), puntoX, puntoY);
		super.dibujar(g, puntoX, puntoY);
	}
}

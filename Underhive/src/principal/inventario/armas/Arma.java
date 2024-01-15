package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import principal.Constantes;
import principal.entes.Jugador;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public abstract class Arma extends Objeto {

	public static HojaSprites hojaArmas = new HojaSprites(Constantes.RUTA_OBJETOS_ARMAS, Constantes.LADO_SPRITE, false);

	protected int ataqueMinimo;
	protected int ataqueMaximo;
//	protected boolean automatica;
//	protected boolean penetrante;
//	protected double ataquesPorSegundo;
//	protected int actualizacionesParaSiguienteAtaque;

	public Arma(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMaximo) {
		super(id, nombre, descripcion);

		this.ataqueMinimo = ataqueMinimo;
		this.ataqueMaximo = ataqueMaximo;

	}

	public Arma(int id, String nombre, String descripcion, int cantidad, int ataqueMinimo, int ataqueMaximo) {
		super(id, nombre, descripcion, cantidad);
		this.ataqueMinimo = ataqueMinimo;
		this.ataqueMaximo = ataqueMaximo;

	}

	public abstract ArrayList<Rectangle> obtenerAlcance(final Jugador jugador);

	public Sprite obtenerSprite() {

		return hojaArmas.obtenerSprite(id - 500);
	}

	private int obtenerAtaqueMedio(final int ataqueMinimo, final int ataqueMaximo) {
		Random r = new Random();

		return r.nextInt(ataqueMaximo - ataqueMinimo) + ataqueMinimo;
	}

}

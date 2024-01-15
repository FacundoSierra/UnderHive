package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;

import principal.entes.Jugador;

public class Pistola extends Arma {

	public Pistola(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMaximo) {
		super(id, nombre, descripcion, ataqueMinimo, ataqueMaximo);

	}

	public Pistola(int id, String nombre, String descripcion, int cantidad, int ataqueMinimo, int ataqueMaximo) {
		super(id, nombre, descripcion, cantidad, ataqueMinimo, ataqueMaximo);

	}

	public ArrayList<Rectangle> obtenerAlcance(Jugador jugador) {
		return null;
	}

}

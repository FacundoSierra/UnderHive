package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.dijkstra.Nodo;
import principal.herramientas.CalculadoraDistancia;
import principal.herramientas.DibujoDebug;
import principal.sonido.Sonido;

public class Enemigo {

	private Sonido lamento;

	private long duracionLamento;
	private long lamentoSiguiente = 0;

	private int idEnemigo;
	private static double posicionX;
	private static double posicionY;

	private String nombre;
	private int vidaMaxima;
	private float vidaActual;

	protected static Nodo siguienteNodo;

	public Enemigo(final int idEnemigo, final String nombre, final int vidaMaxima, final String rutaLamento) {
		this.idEnemigo = idEnemigo;

		this.posicionX = 0;
		this.posicionY = 0;

		this.nombre = nombre;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
		this.lamento = new Sonido(rutaLamento);
		this.duracionLamento = lamento.obtenerDuracion();
	}

	public void actualizar(ArrayList<Enemigo> enemigos) {
		if (lamentoSiguiente > 0) {
			lamentoSiguiente -= 1000000 / 60;
		}
		moverHaciaSiguienteNodo(enemigos);

	}

	void moverHaciaSiguienteNodo(ArrayList<Enemigo> enemigos) {
		if (siguienteNodo == null) {
			return;
		}
		// System.out.println("Nodo " + siguienteNodo.obtenerAreaPixeles());
		for (Enemigo enemigo : enemigos) {
			// System.out.println("Enemigo " + enemigo.obtenerAreaPosicional());
			if (enemigo.obtenerAreaPosicional().equals(this.obtenerAreaPosicional())) {
				continue;
			}

			if (enemigo.obtenerAreaPosicional().intersects(siguienteNodo.obtenerAreaPixeles())) {
				System.out.println("enemigo molestando!");
				return;
			}
		}
		determinarDireccion();
	}

	private void determinarDireccion() {
		final int xSiguienteNodo = siguienteNodo.obtenerPosicion().x * Constantes.LADO_SPRITE;
		final int ySiguienteNodo = siguienteNodo.obtenerPosicion().y * Constantes.LADO_SPRITE;

		double velocidad = 0.5;

		if (posicionX < xSiguienteNodo) {
			posicionX += velocidad;

		}

		if (posicionX > xSiguienteNodo) {
			posicionX -= velocidad;

		}

		if (posicionY < ySiguienteNodo) {
			posicionY += velocidad;

		}

		if (posicionY > ySiguienteNodo) {
			posicionY -= velocidad;

		}
	}

	public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
		if (vidaActual <= 0) {
			return;
		}
		dibujarBarraVida(g, puntoX, puntoY);
		DibujoDebug.dibujarRectanguloContorno(g, obtenerArea());
		dibujarDistancia(g, puntoX, puntoY);
	}

	private void dibujarBarraVida(final Graphics g, final int puntoX, final int puntoY) {
		g.setColor(Color.red);
		DibujoDebug.dibujarRectanguloRelleno(g, puntoX, puntoY - 5,
				Constantes.LADO_SPRITE * (int) vidaActual / vidaMaxima, 2);

	}

	private void dibujarDistancia(final Graphics g, final int puntoX, final int puntoY) {
		Point puntoJugador = new Point((int) ElementosPrincipales.jugador.obtenerPosicionX(),
				(int) ElementosPrincipales.jugador.obtenerPosicionY());

		Point puntoEnemigo = new Point((int) posicionX, (int) posicionY);

		Double distancia = CalculadoraDistancia.obtenerDistanciaEntrePuntos(puntoJugador, puntoEnemigo);

		DibujoDebug.dibujarString(g, String.format("%.2f", distancia), puntoX, puntoY - 8);
	}

	public void establecerPosicion(final double posicionX, final double posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	public double obtenerPosicionX() {
		return posicionX;
	}

	public double obtenerPosicionY() {
		return posicionY;
	}

	public int obtenerIdEnemigo() {
		return idEnemigo;
	}

	public float obtenerVidaActual() {
		return vidaActual;
	}

	public void perderVida(float ataqueRecibido) {
		if (lamentoSiguiente <= 0) {
			lamento.reproducir();
			lamentoSiguiente = duracionLamento;
		}

		if (vidaActual - ataqueRecibido < 0) {
			vidaActual = 0;

		} else {
			vidaActual -= ataqueRecibido;
		}
	}

	public Rectangle obtenerArea() {
		final int puntoX = (int) posicionX - (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X
				- 16;
		final int puntoY = (int) posicionY - (int) ElementosPrincipales.jugador.obtenerPosicionY() + Constantes.MARGEN_Y
				- 16;

		return new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
	}

	public Rectangle obtenerAreaPosicional() {
		return new Rectangle((int) posicionX, (int) posicionY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
	}

	public void cambiarSiguienteNodo(Nodo nodo) {
		// cuidado con posible bug
		siguienteNodo = nodo;
	}

	public Nodo obtenerSiguienteNodo() {
		return siguienteNodo;
	}
}

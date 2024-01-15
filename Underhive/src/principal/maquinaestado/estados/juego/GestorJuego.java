package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.interfaz_Usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

	MenuInferior menuInferior;

	public GestorJuego() {
		menuInferior = new MenuInferior();
	}

	private void recargarJuego() {
		final String ruta = "recursos/mapas/" + ElementosPrincipales.mapa.obtenerSiguienteMapa();
		ElementosPrincipales.mapa = new Mapa(ruta);

//		jugador.establecerPosicionX(mapa.obtenerPosicionInicial().getX());
//		jugador.establecerPosicionY(mapa.obtenerPosicionInicial().getY());
	}

	public void actualizar() {
		if (ElementosPrincipales.jugador.obtener_LIMITE_ARRIBA()
				.intersects(ElementosPrincipales.mapa.obtenerZonaSalida())) {
			recargarJuego();
		}
		ElementosPrincipales.jugador.actualizar();
		ElementosPrincipales.mapa.actualizar();

	}

	public void dibujar(Graphics g) {
		ElementosPrincipales.mapa.dibujar(g);
		ElementosPrincipales.jugador.dibujar(g);
		menuInferior.dibujar(g);

		g.setColor(Color.white);

		DibujoDebug.dibujarRectanguloRelleno(g, ElementosPrincipales.mapa.obtenerZonaSalida().x,
				ElementosPrincipales.mapa.obtenerZonaSalida().y, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

		DatosDebug.enviarDato("X = " + ElementosPrincipales.jugador.obtenerPosicionXInt());
		DatosDebug.enviarDato("Y = " + ElementosPrincipales.jugador.obtenerPosicionYInt());
		DatosDebug.enviarDato("Siguiente mapa =" + ElementosPrincipales.mapa.obtenerSiguienteMapa());
		DatosDebug.enviarDato("Coordenadas salida X = " + ElementosPrincipales.mapa.obtenerPuntoSalida().getX()
				+ ", Y = " + ElementosPrincipales.mapa.obtenerPuntoSalida().getY());

//		MenuInferior.dibujarResistencia(g, jugador.resistencia);
	}

}

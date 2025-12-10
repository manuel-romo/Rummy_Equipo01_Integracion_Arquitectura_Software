
package enumeradores;

import comandos.envolventes.ComandoEnvolvente;

/**
 * Clase ENUM que representa el tipo de comando 
 * @author juanpheras
 */
public enum TipoComando {
          
    SELECCIONAR_FICHAS_TABLERO("ComandoSeleccionarFichasTablero"),
    AGREGAR_FICHAS_TABLERO("ComandoAgregarFichasTablero"),
    AGREGAR_FICHAS_TABLERO_GRUPO("ComandoAgregarFichasTableroGrupo"),
    CAMBIAR_TURNO_SERIALIZADO("ComandoCambiarTurnoSerializado"),
    QUITAR_FICHAS_TABLERO("ComandoQuitarFichasTablero"),
    QUITAR_FICHAS_JUGADOR("ComandoQuitarFichasJugador"),
    AGREGAR_FICHAS_JUGADOR("ComandoAgregarFichasJugador"),
    RESTABLECER_TABLERO("ComandoReestablecerTablero"),
    RESPUESTA_REESTABLECER("ComandoReestablecerRespuesta"),
    TERMINAR_TURNO("ComandoTerminarTurno"),
    CAMBIO_TURNO("ComandoCambioTurno"),
    INICIAR_TURNO("ComandoIniciarTurno"),
    TOMAR_FICHA("ComandoTomarFicha"),
    RESPUESTA_TOMAR_FICHA("ComandoRespuestaTomarFicha"),
    RESPUESTA_MOVIMIENTO("ComandoRespuestaMovimiento"),
    COMANDO_TABLERO_INVALIDO("ComandoTableroInvalido"),
    COMANDO_ENVOLVENTE("ComandoEnvolvente"),
    
    COMANDO_SOLICITAR_FIN("ComandoSolicitarFin"),
    COMANDO_RESPUESTA_SOLICITAR_FIN("ComandoRespuestaSolicitarFin"),
    COMANDO_CONFIRMACION_SOLICITAR_FIN("ComandoConfirmacionSolicitarFin"),
    COMANDO_RESPUESTA_CONFIRMACION_SOLICITAR_FIN("ComandoRespuestaConfirmacionSolicitarFin"),
    
    COMANDO_ABANDONAR("ComandoAbandonar"),
    COMANDO_RESPUESTA_ABANDONAR("ComandoRespuestaAbandonar"),
    COMANDO_CONFIRMACION_ABANDONAR("ComandoConfirmacionAbandonar"),
    COMANDO_JUGADOR_ABANDONO("ComandoJugadorAbandonoPartida"),
    
    COMANDO_FIN_PARTIDA("ComandoFinPartida"),
    
    COMANDO_AGREGAR_DIRECCION_JUGADOR("ComandoAgregarDireccionJugador"),
    
    COMANDO_PARTIDA_GANADA("ComandoPartidaGanada"),
    COMANDO_JUGADOR_PARTIDA_GANADA("ComandoJugadorPartidaGanada"),
    
    // COMANDOS INICIO JUEGO
    COMANDO_INICIAR_JUEGO("ComandoIniciarJuego"),
    COMANDO_CONFIRMACION_INICIAR_JUEGO("ComandoConfirmacionIniciarJuego"),
    COMANDO_CONFIRMACION_ENVIO_INICIAR_JUEGO("ComandoConfirmacionEnvioIniciarJuego"),
    
    COMANDO_NUEVA_SOLICITUD_INICIAR_JUEGO("ComandoNuevaSolicitudIniciarJuego"),
    COMANDO_RESPUESTA_INICIAR_JUEGO("ComandoRespuestaIniciarJuego"),
    COMANDO_ACTUALIZAR_JUGADORES_INICIO_JUEGO("ComandoActualizarJugadoresInicioJuego"),
    COMANDO_DECISION_INICIAR_JUEGO("ComandoDecisionIniciarJuego"),
    //COMANDOS JP REGISTRAR JUGADOR
    COMANDO_REGISTRO_EXITOSO("ComandoRegistroExitoso"),
    COMANDO_REGISTRO_FALLIDO("ComandoRegistroFallido"),
    COMANDO_REGISTRAR_JUGADOR("ComandoRegistrarJugador"),
    
    COMANDO_CARGAR_JUGADORES("ComandoCargarJugadores"),
    
    //COMANDOS PEDRO CONFIGURAR PARTIDA
    COMANDO_CONFIGURAR_PARTIDA("ComandoConfigurarPartida"),
    COMANDO_PARTIDA_CONFIGURADA("ComandoPartidaConfigurada");
    

    private final String nombre;

    TipoComando(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que devuelve el nombre del comando en String para poder instanciarlo despues de que se convierta en JSON.
     * @param nombre nombre del command
     * @return 
     */
    public static TipoComando fromNombre(String nombre) {
        for (TipoComando t : values()) {
            if (t.nombre.equals(nombre)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No existe CommandType con nombre: " + nombre);
    }
    
}

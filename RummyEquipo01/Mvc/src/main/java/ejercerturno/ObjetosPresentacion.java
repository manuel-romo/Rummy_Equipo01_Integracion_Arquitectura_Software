package ejercerturno;

//
//package ejercerTurno;
//
//import comunicacion.Comunicacion;
//import comunicacion.IComunicacion;
//import dto.ColorFichaNegocioDTO;
//import dto.ComodinNegocioDTO;
//import dto.FichaNegocioDTO;
//import dto.FichaNormalNegocioDTO;
//import dto.GrupoColoresNegocioDTO;
//import dto.GrupoNegocioDTO;
//import dto.GrupoSecuenciaNegocioDTO;
//import dto.JugadorNegocioDTO;
//import dto.MontonNegocioDTO;
//import dto.TableroNegocioDTO;
//import ejercerTurno.Controlador;
//import ejercerTurno.Modelo;
//import ejercerTurno.VistaMesaJuego;
//import java.awt.Color;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import objetosPresentacion.GestorEventos;
//import objetosPresentacion.IComponente;
//import objetosPresentacion.IGestorEventos;
//import objetosPresentacion.PanelCasilla;
//import objetosPresentacion.PanelJugadorExterno;
//import objetosPresentacion.PanelJugadorPrincipal;
//import objetosPresentacion.PanelMesaJuego;
//import objetosPresentacion.PanelMonton;
//import objetosPresentacion.PanelMovimiento;
//import objetosPresentacion.PanelTablero;
//import objetosPresentacion.PosicionPanel;
//
///**
// *
// * @author Manuel Romo López
// * ID: 00000253080
// * 
// */
//public class ObjetosPresentacion {
//
//    private static int TOTAL_CASILLAS_TABLERO =500;
//    private static int TOTAL_CASILLAS_MANO = 14;
//    
//    public static void main(String[] args) {
//        
//        IComponente panelMonton = new PanelMonton();
//        
//        PanelCasilla[] panelesCasillaTablero = new PanelCasilla[TOTAL_CASILLAS_TABLERO];
//
//        for (int i = 0; i < TOTAL_CASILLAS_TABLERO; i++) {
//            panelesCasillaTablero[i] = new PanelCasilla(i + 1);
//        }
//        
//        
//        IComponente panelTablero = new PanelTablero(panelesCasillaTablero);
//        
//        PanelCasilla[] panelesCasillaJugador = new PanelCasilla[TOTAL_CASILLAS_MANO];
//         
//        for (int i = 0; i < TOTAL_CASILLAS_MANO; i++) {
//            panelesCasillaJugador[i] = new PanelCasilla(i + 1);
//        }
//        
//        IComponente panelJugadorPrincipal = new PanelJugadorPrincipal(panelesCasillaJugador);
//        IComponente panelJugadorExterno1 = new PanelJugadorExterno(PosicionPanel.CENTRO_ARRIBA, "Sandy43", "avatar2.png", "14");
//        IComponente panelJugadorExterno2 = new PanelJugadorExterno(PosicionPanel.DERECHA_CENTRO, "Lucas12", "avatar1.png", "14");
//        IComponente panelJugadorExterno3 = new PanelJugadorExterno(PosicionPanel.IZQUIERDA_CENTRO, "Juan27", "avatar3.png", "14");
//        
//        IComponente panelMesaJuego = new PanelMesaJuego();
//        
//        panelMesaJuego.agregarComponente(panelMonton);
//        panelMesaJuego.agregarComponente(panelTablero);
//        panelMesaJuego.agregarComponente(panelJugadorExterno1);
//        panelMesaJuego.agregarComponente(panelJugadorExterno2);
//        panelMesaJuego.agregarComponente(panelJugadorExterno3);
//        panelMesaJuego.agregarComponente(panelJugadorPrincipal);
//
//        Map<Integer, Color> mapaColoresSeleccionados = Map.of(
//            1, Color.RED,
//            2, Color.BLUE,
//            3, Color.GREEN,
//            4, Color.DARK_GRAY
//        );      
//        
//        Map<Integer,Integer> mapaIdsCasillasPanelesJugador = new HashMap<>();
//        
//        mapaIdsCasillasPanelesJugador.put(1, 1);
//        mapaIdsCasillasPanelesJugador.put(2, 2);
//        mapaIdsCasillasPanelesJugador.put(3, 3);
//        mapaIdsCasillasPanelesJugador.put(4, 4);
//        mapaIdsCasillasPanelesJugador.put(5, 5);
//        mapaIdsCasillasPanelesJugador.put(6, 6);
//        mapaIdsCasillasPanelesJugador.put(7, 7);
//        mapaIdsCasillasPanelesJugador.put(8, 8);
//        mapaIdsCasillasPanelesJugador.put(9, 9);
//        mapaIdsCasillasPanelesJugador.put(10, 10);
//        mapaIdsCasillasPanelesJugador.put(11, 11);
//        
//        Map<Integer,Integer> mapaIdsCasillasPanelesTablero = new HashMap<>();
//        
//        mapaIdsCasillasPanelesTablero.put(1, 22);
//        mapaIdsCasillasPanelesTablero.put(2, 23);        
//        mapaIdsCasillasPanelesTablero.put(3, 24);
//        
//        mapaIdsCasillasPanelesTablero.put(5, 25);
//        mapaIdsCasillasPanelesTablero.put(6, 26);
//        mapaIdsCasillasPanelesTablero.put(7, 27);        
//        mapaIdsCasillasPanelesTablero.put(8, 28);
//        mapaIdsCasillasPanelesTablero.put(9, 29);
//        
//        IComunicacion comunicacion = new Comunicacion();
//        
//        Modelo modelo = new Modelo("prueba_nombre");
//        Controlador controlador = new Controlador(modelo);
//        
//        IComponente panelMovimiento = new PanelMovimiento();
//        
//        VistaMesaJuego vistaMesaJuego = new VistaMesaJuego(
//                controlador,
//                panelMesaJuego, 
//                panelMovimiento,
//                mapaColoresSeleccionados,
//                mapaIdsCasillasPanelesTablero,
//                mapaIdsCasillasPanelesJugador);
//        
//        modelo.suscribirse(vistaMesaJuego);
//                
//        IGestorEventos gestorEventos = new GestorEventos(vistaMesaJuego, (IReceptorEventos)vistaMesaJuego);
//        
//        ((PanelTablero) panelTablero).setGestorEventos(gestorEventos);
//        ((PanelJugadorPrincipal) panelJugadorPrincipal).setGestorEventos(gestorEventos);
//        ((PanelMovimiento) panelMovimiento).setGestorEventos(gestorEventos);
//
//        
//        List<FichaNegocioDTO> fichasNegocioJugador1 = List.of(
//            new FichaNormalNegocioDTO(8, 1, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(5, 2, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(13, 3, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(1, 4, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(10, 5, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(4, 6, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(7, 7, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(2, 8, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(6, 9, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(11, 10, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(9, 11, ColorFichaNegocioDTO.COLOR_D)
//        );
//
//        List<FichaNegocioDTO> fichasNegocioJugador2 = List.of(
//            new FichaNormalNegocioDTO(1, 12, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(12, 13, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(5, 14, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(8, 15, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(3, 16, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(7, 17, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(10, 18, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(13, 19, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(2, 20, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(6, 21, ColorFichaNegocioDTO.COLOR_C)
//        );
//        
//        FichaNormalNegocioDTO ficha1Grupo1 = new FichaNormalNegocioDTO(9, 22, ColorFichaNegocioDTO.COLOR_A);
//        FichaNormalNegocioDTO ficha2Grupo1 = new FichaNormalNegocioDTO(9, 23, ColorFichaNegocioDTO.COLOR_B);
//        FichaNormalNegocioDTO ficha3Grupo1 = new FichaNormalNegocioDTO(9, 24, ColorFichaNegocioDTO.COLOR_C);
//
//        List<FichaNegocioDTO> fichasNegocioGrupo1 = List.of(
//            ficha1Grupo1,
//            ficha2Grupo1,
//            ficha3Grupo1
//        );
//        
//        GrupoNegocioDTO grupo1 = new GrupoColoresNegocioDTO(1);
//        
//        ficha1Grupo1.setNumeroGrupo(grupo1.getNumero());
//        ficha2Grupo1.setNumeroGrupo(grupo1.getNumero());
//        ficha3Grupo1.setNumeroGrupo(grupo1.getNumero());
//        
//        FichaNormalNegocioDTO ficha1Grupo2 = new FichaNormalNegocioDTO(4, 25, ColorFichaNegocioDTO.COLOR_D);
//        FichaNormalNegocioDTO ficha2Grupo2 = new FichaNormalNegocioDTO(5, 26, ColorFichaNegocioDTO.COLOR_D);
//        FichaNormalNegocioDTO ficha3Grupo2 = new FichaNormalNegocioDTO(6, 27, ColorFichaNegocioDTO.COLOR_D);
//        FichaNormalNegocioDTO ficha4Grupo2 = new FichaNormalNegocioDTO(7, 28, ColorFichaNegocioDTO.COLOR_D);
//        FichaNormalNegocioDTO ficha5Grupo2 = new FichaNormalNegocioDTO(8, 29, ColorFichaNegocioDTO.COLOR_D);
//
//        List<FichaNegocioDTO> fichasNegocioGrupo2 = List.of(
//            ficha1Grupo2,
//            ficha2Grupo2,
//            ficha3Grupo2,
//            ficha4Grupo2,
//            ficha5Grupo2
//        );
//        
//        GrupoNegocioDTO grupo2 = new GrupoSecuenciaNegocioDTO(2);
//        
//        ficha1Grupo2.setNumeroGrupo(grupo2.getNumero());
//        ficha2Grupo2.setNumeroGrupo(grupo2.getNumero());
//        ficha3Grupo2.setNumeroGrupo(grupo2.getNumero());
//        ficha4Grupo2.setNumeroGrupo(grupo2.getNumero());
//        ficha5Grupo2.setNumeroGrupo(grupo2.getNumero());
//        
//
//        List<FichaNegocioDTO> fichasMonton = List.of(
//            new FichaNormalNegocioDTO(1, 30, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(11, 31, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(12, 32, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(4, 33, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(10, 34, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(3, 35, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(12, 36, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(5, 37, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(2, 38, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(13, 39, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(8, 40, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(11, 41, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(6, 42, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(9, 43, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(1, 44, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(12, 45, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(5, 46, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(7, 47, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(10, 48, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(3, 49, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(13, 50, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(8, 51, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(11, 52, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(2, 53, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(1, 54, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(6, 55, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(10, 56, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(4, 57, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(9, 58, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(12, 59, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(5, 60, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(8, 61, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(1, 62, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(6, 63, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(11, 64, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(3, 65, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(7, 66, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(10, 67, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(2, 68, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(5, 69, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(9, 70, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(12, 71, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(4, 72, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(8, 73, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(11, 74, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(2, 75, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(7, 76, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(1, 77, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(6, 78, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(13, 79, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(4, 80, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(9, 81, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(12, 82, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(3, 83, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(8, 84, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(11, 85, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(2, 86, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(7, 87, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(10, 88, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(5, 90, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(13, 91, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(4, 92, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(9, 93, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(3, 94, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(7, 95, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(11, 96, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(2, 97, ColorFichaNegocioDTO.COLOR_C),
//            new FichaNormalNegocioDTO(6, 98, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(13, 99, ColorFichaNegocioDTO.COLOR_B),
//            new FichaNormalNegocioDTO(3, 100, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(8, 101, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(1, 102, ColorFichaNegocioDTO.COLOR_A),
//            new FichaNormalNegocioDTO(7, 103, ColorFichaNegocioDTO.COLOR_D),
//            new FichaNormalNegocioDTO(4, 104, ColorFichaNegocioDTO.COLOR_D),
//            new ComodinNegocioDTO(105),
//            new ComodinNegocioDTO(106)
//        );
//        
//        MontonNegocioDTO montonNegocio = new MontonNegocioDTO(fichasMonton);
//
//        GrupoNegocioDTO grupoNegocio1 = new GrupoColoresNegocioDTO(1, fichasNegocioGrupo1);
//        GrupoNegocioDTO grupoNegocio2 = new GrupoSecuenciaNegocioDTO(2, fichasNegocioGrupo2);
//
//        JugadorNegocioDTO jugadorNegocio1 = new JugadorNegocioDTO(
//                fichasNegocioJugador1, 
//                "/avatar2.png", 
//                "Sandy_43", 
//                false);
//        
//        JugadorNegocioDTO jugadorNegocio2 = new JugadorNegocioDTO(
//                fichasNegocioJugador2, 
//                "/avatar3.png", 
//                "Juan_27", 
//                false);
//        
//        JugadorNegocioDTO[] jugadoresExternosNegocio = {jugadorNegocio2};
//        
//        GrupoNegocioDTO[] gruposNegocio = {grupoNegocio1, grupoNegocio2};
//        
//        
//        TableroNegocioDTO tableroNegocio = new TableroNegocioDTO(
//                jugadorNegocio1,
//                jugadoresExternosNegocio, 
//                gruposNegocio, 
//                montonNegocio);
//        
////        modelo.iniciarTurno();
//    }
//}

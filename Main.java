import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "¡Bienvenido a PokeProgramación!", "Inicio", JOptionPane.INFORMATION_MESSAGE);

        Entrenador jugador = null;

        // AÑADIDO: Intentamos cargar la partida al iniciar
        Entrenador jugadorGuardado = GestorGuardado.cargarPartida();
        
        if (jugadorGuardado != null) {
            int respuesta = JOptionPane.showConfirmDialog(null, 
                "Se ha encontrado una partida guardada de " + jugadorGuardado.getNombre() + ".\n¿Deseas continuarla?", 
                "Cargar Partida", JOptionPane.YES_NO_OPTION);
                
            if (respuesta == JOptionPane.YES_OPTION) {
                jugador = jugadorGuardado;
            }
        }

        // Si no quiso cargar partida o no había, creamos uno nuevo
        if (jugador == null) {
            String nombreTrainer = JOptionPane.showInputDialog("¿Cuál es tu nombre de Entrenador?");
            if(nombreTrainer == null || nombreTrainer.trim().isEmpty()) nombreTrainer = "Ash";
            jugador = new Entrenador(nombreTrainer);
            jugador.agregarPokemon(new PokemonFuego(4, "Charmander", 5, 20, 12, 10, "charmander.png"));
        }

        boolean salir = false;

        while (!salir) {
            String[] opciones = {"1. Ver mi Equipo", "2. Modificar Equipo", "3. Buscar Combate", "4. Guardar y Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Menú Principal - Entrenador: " + jugador.getNombre(), "Menú",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, jugador.mostrarEquipo());
                    break;
                    
                case 1:
                    String[] opcMod = {"Regalar Bulbasaur", "Curar Equipo", "Liberar Pokémon"};
                    int mod = JOptionPane.showOptionDialog(null, "Gestión de PC", "Modificar Equipo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcMod, opcMod[0]);
                    
                    if (mod == 0) {
                        if(jugador.agregarPokemon(new PokemonPlanta(1, "Bulbasaur", 5, 25, 10, 12, "bulbasaur.png"))) {
                            JOptionPane.showMessageDialog(null, "¡Has recibido un Bulbasaur!");
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Tu equipo está lleno!");
                        }
                    } else if (mod == 1) {
                        // Un botón rápido para curar, útil para probar los combates
                        for(Pokemon p : jugador.getEquipo()) {
                            p.vidaActual = p.vidaMaxima;
                            p.estado = EstadoAlterado.NINGUNO;
                        }
                        JOptionPane.showMessageDialog(null, "Tus Pokémon han sido curados al máximo.");
                    } else if (mod == 2) {
                        String input = JOptionPane.showInputDialog(jugador.mostrarEquipo() + "\nIntroduce el número a liberar (1-6):");
                        if (input != null) {
                            try {
                                jugador.liberarPokemon(Integer.parseInt(input) - 1);
                                JOptionPane.showMessageDialog(null, "Pokémon liberado con éxito.");
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Número inválido.");
                            }
                        }
                    }
                    break;
                    
                case 2:
                    if (jugador.getEquipo().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "¡No tienes Pokémon, no puedes luchar!");
                    } else if (!jugador.getEquipo().get(0).estaVivo()) {
                        JOptionPane.showMessageDialog(null, "¡Tu primer Pokémon está debilitado! Cúralo en 'Modificar Equipo'.");
                    } else {
                        PokemonAgua rival = new PokemonAgua(7, "Squirtle", 5, 22, 11, 14, "squirtle.png");
                        Combate.iniciar(jugador, rival);
                    }
                    break;
                    
                case 3:
                case JOptionPane.CLOSED_OPTION:
                    // AÑADIDO: Guardar la partida justo antes de cerrar
                    GestorGuardado.guardarPartida(jugador);
                    JOptionPane.showMessageDialog(null, "¡Partida guardada con éxito!\n¡Hasta la próxima!");
                    salir = true;
                    break;
            }
        }
    }
}
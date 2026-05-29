import javax.swing.JOptionPane;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GestorPokedex pokedex = new GestorPokedex();
        pokedex.cargarDatosCSV("pokemon.csv");

        JOptionPane.showMessageDialog(null, "¡Bienvenido a PokeProgramación!\nPokédex cargada con " + pokedex.getTotalPokemon() + " especies.");

        Entrenador jugador = GestorGuardado.cargarPartida();
        
        if (jugador != null) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Continuar partida de " + jugador.getNombre() + "?", "Cargar Partida", JOptionPane.YES_NO_OPTION);
            if (resp != JOptionPane.YES_OPTION) jugador = null;
        }

        if (jugador == null) {
            String nombreTrainer = JOptionPane.showInputDialog("¿Cuál es tu nombre de Entrenador?");
            if(nombreTrainer == null || nombreTrainer.trim().isEmpty()) nombreTrainer = "Ash";
            jugador = new Entrenador(nombreTrainer);
            
            // Damos a Charmander (ID 4) de nivel 5
            jugador.agregarPokemon(pokedex.generarPokemon(4, 5));
        }

        boolean salir = false;
        Random rnd = new Random();

        while (!salir) {
            
            for (int i = 0; i < jugador.getEquipo().size(); i++) {
                Pokemon p = jugador.getEquipo().get(i);
                if (p.getNivel() >= p.getNivelEvolucion() && p.getNivelEvolucion() > 0) {
                    
                    JOptionPane.showMessageDialog(null, "¡Anda!\nTu " + p.getNombre() + " está evolucionando...", "Evolución", JOptionPane.INFORMATION_MESSAGE);
                    
                    Pokemon evolucion = pokedex.generarPokemon(p.getIdEvolucion(), p.getNivel());
                    evolucion.setExperiencia(p.getExperiencia()); 
                    
                    jugador.getEquipo().set(i, evolucion);
                    
                    JOptionPane.showMessageDialog(null, "¡Enhorabuena! Tu " + p.getNombre() + " ha evolucionado a " + evolucion.getNombre() + "!");
                }
            }

            String[] opciones = {"1. Ver mi Equipo", "2. Modificar Equipo", "3. Buscar Combate", "4. Guardar y Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Menú Principal - Entrenador: " + jugador.getNombre(), "Menú",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, jugador.mostrarEquipo());
                    break;
                    
                case 1:
                    String[] opcMod = {"Curar Equipo", "Liberar Pokémon"};
                    int mod = JOptionPane.showOptionDialog(null, "Gestión de PC", "Modificar Equipo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcMod, opcMod[0]);
                    
                    if (mod == 0) {
                        for(Pokemon p : jugador.getEquipo()) {
                            p.vidaActual = p.vidaMaxima;
                            p.setEstado(EstadoAlterado.NINGUNO);
                        }
                        JOptionPane.showMessageDialog(null, "Tus Pokémon han sido curados al máximo.");
                    } else if (mod == 1) {
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
                        // Combate contra uno de los 151 Pokémon al azar
                        int idAleatorio = rnd.nextInt(pokedex.getTotalPokemon()) + 1;
                        int nivelAleatorio = jugador.getEquipo().get(0).getNivel() + (rnd.nextInt(3) - 1); 
                        if (nivelAleatorio < 1) nivelAleatorio = 1;
                        
                        Pokemon rivalSalvaje = pokedex.generarPokemon(idAleatorio, nivelAleatorio);
                        new InterfazCombate(jugador, rivalSalvaje);
                    }
                    break;
                    
                case 3:
                case JOptionPane.CLOSED_OPTION:
                    GestorGuardado.guardarPartida(jugador);
                    JOptionPane.showMessageDialog(null, "¡Partida guardada con éxito!\n¡Hasta la próxima!");
                    salir = true;
                    break;
            }
        }
    }
}
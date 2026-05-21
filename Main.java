import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "¡Bienvenido al combate", "Inicio", JOptionPane.INFORMATION_MESSAGE);

        // 1. Registrar entrenador.
        String nombreTrainer = JOptionPane.showInputDialog("¿Cuál es tu nombre de Entrenador?");
        if (nombreTrainer == null || nombreTrainer.trim().isEmpty()) nombreTrainer = "Rojo";
        Entrenador jugador = new Entrenador(nombreTrainer);

        // Pokémon inicial por defecto.
        jugador.agregarPokemon(new PokemonFuego(4, "Charmander", 5, 20, 12, 10, "charmander.jpg"));

        boolean salir = false;

        while (!salir) {
            String[] opciones = {"1. Ver mi Equipo", "2. Modificar Equipo", "3. Buscar Combate", "4. Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Menú Principal - Entrenador: " + jugador.getNombre(), "Menú",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0: // Ver mi equipo.
                    JOptionPane.showMessageDialog(null, jugador.mostrarEquipo());
                    break;

                case 1: // Modificar equipo.
                    String[] opcMod = {"Regalar Bulbasaur", "Liberar Pokémon"};
                    int mod = JOptionPane.showOptionDialog(null, "Gestión de PC", "Modificar Equipo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcMod, opcMod[0]);
                    
                    if (mod == 0) {
                        if(jugador.agregarPokemon(new PokemonPlanta(1, "Bulbasaur", 5, 25, 10, 12, "bulbasaur.jpg"))) {
                            JOptionPane.showMessageDialog(null, "¡Has recibido un Bulbasaur!");
                        } else {
                            JOptionPane.showMessageDialog(null, "¡Tu equipo está lleno!");
                        }
                    } else if (mod == 1) {
                        String input = JOptionPane.showInputDialog(jugador.mostrarEquipo() + "\nIntroduce el número a liberar (1-6):");
                        if (input != null) {
                            try {
                                jugador.liberarPokemon(Integer.parseInt(input) - 1);
                                JOptionPane.showMessageDialog(null, "Pokémon liberado.");
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Número inválido.");
                            }
                        }
                    }
                    break;

                case 2: // Iniciar combate.
                    if (jugador.getEquipo().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "¡No tienes pokémon, no puedes luchar!");
                    } else {
                        // Genera un rival nuevo y curado cada vez que termina un combate.
                        PokemonAgua rival = new PokemonAgua(7, "Squirtle", 5, 22, 11, 14, "sqrt.jpg");
                        Combate.iniciar(jugador, rival);
                    }
                    break;

                case 3: // Salir.
                case JOptionPane.CLOSED_OPTION:
                    salir = true;
                    break;
            }
        }
    }
}

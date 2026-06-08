import javax.print.attribute.standard.JobHoldUntil;
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
                    String[] opcMod = {"Curar Equipo", "Cambiar Primer Pokémon", "Liberar Pokémon"};
                    int mod = JOptionPane.showOptionDialog(null, "Gestión de PC", "Modificar Equipo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcMod, opcMod[0]);

                    if (mod == 0) {
                        for(Pokemon p : jugador.getEquipo()) {
                            p.vidaActual = p.getVidaMaxima();
                            p.setEstado(EstadoAlterado.NINGUNO);
                        }
                        JOptionPane.showMessageDialog(null, "Tus Pokémon han sido curados.");

                    } else if (mod == 1) {
                        // Cambía de "líder"
                        if (jugador.getEquipo().size() > 1) {
                            String input = JOptionPane.showInputDialog(jugador.mostrarEquipo() + "\nIntroduce el numero del Pokémon que quieres que luche primero " + jugador.getEquipo().size() + "):");
                            if (input != null) {
                                try {
                                    int index = Integer.parseInt(input) - 1;
                                    if (index > 0 && index < jugador.getEquipo().size()) {
                                        // Intercambia el pokémon elegido con el que está en la posicón 0.
                                        Pokemon temp = jugador.getEquipo().get(0);
                                        jugador.getEquipo().set(0, jugador.getEquipo().get(index));
                                        jugador.getEquipo().set(index, temp);
                                        JOptionPane.showMessageDialog(null, "i" + jugador.getEquipo().get(0).getNombre() + " es ahora tu Pokénon principal");
                                    } else if (index == 0) {
                                        JOptionPane.showMessageDialog(null, "Ese Pokémon ya es el líder");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Número inválido.");
                                    }
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, "Introduce un número válido.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Solo tienes un Pokémon en el equipo.");
                        }

                    } else if (mod == 2) {
                        String input = JOptionPane.showInputDialog(jugador.mostrarEquipo() + "\nIntroduce el número a liberar " + jugador.getEquipo().size() + ")");
                        if (input != null) {
                            try {
                                int index = Integer.parseInt(input) - 1;
                                if (index >= 0 && index < jugador.getEquipo().size()) {
                                    jugador.liberarPokemon(index);
                                    JOptionPane.showMessageDialog(null, "Pokémon liberado con éxito.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Número inválido.");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
                            }
                        }
                    }
                    break;
                    
                case 2:
                    boolean enZonaCombate = true;

                    // El bucle mantiene al jugador en la "hierba alta"
                    while (enZonaCombate) {

                        // Comprobación antes de luchar.
                        if (jugador.getEquipo().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "¡No tienes Pokémon, no puedes combatir!");
                            break; // Te echa al menú principal.
                        } else if (!jugador.getEquipo().get(0).estaVivo()) {
                            JOptionPane.showMessageDialog(null, "Tu primer pokémon está debilitado.\nDebes curarlo antes de iniciar otro combate");
                            // Aquí no hay break para que el menú post-combate de la opción de cerrar.
                        } else {
                            // Generar el rival y lanzar la ventana (el programa se pausa aquí)
                            int idAleatorio = rnd.nextInt(pokedex.getTotalPokemon()) + 1;
                            int nivelAleatorio = jugador.getEquipo().get(0).getNivel() + (rnd.nextInt(3) - 1);
                            if (nivelAleatorio < 1) nivelAleatorio = 1;

                            Pokemon rivalSalvaje = pokedex.generarPokemon(idAleatorio, nivelAleatorio);
                            new InterfazCombate(jugador, rivalSalvaje);
                        }

                        // Menú post-combate.
                        boolean decidiendo = true;
                        while (decidiendo) {
                            String[] opcionesPost = {"Seguir Combatiendo", "Curar Equipo", "Volver al menú"};
                            int accionPost = JOptionPane.showOptionDialog(null,
                                "El combate ha terminado. ¿Qué quieres hacer ahora?",
                                "Post-Combate",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opcionesPost,
                                opcionesPost[0]);

                            if (accionPost == 0) { // Seguir combatiendo.
                                if (!jugador.getEquipo().get(0).estaVivo()) {
                                    JOptionPane.showMessageDialog(null, "¡No puedes seguir! Cura primero a tu pokémon.");
                                    // Al no cambiar 'decidiendo', el menú post-combate vuelve a salir.
                                } else {
                                    decidiendo = false; // Rompe el sub-bucle y vuelve arriba a generar otro combate.
                                }

                            } else if (accionPost == 1) {
                                for(Pokemon p : jugador.getEquipo()) {
                                    p.vidaActual = p.getVidaMaxima();
                                    p.setEstado(EstadoAlterado.NINGUNO);
                                }
                                JOptionPane.showMessageDialog(null, "¡ Tus pokémon han descansado y están a tope de salud");
                                // Sigue atrapado en el bucle 'decidiendo' por lo que vuelve a preguntar qué hacer.

                            } else {
                                decidiendo = false;
                                enZonaCombate = false; // Rompe el bucle principal de combate.
                            }
                        }
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